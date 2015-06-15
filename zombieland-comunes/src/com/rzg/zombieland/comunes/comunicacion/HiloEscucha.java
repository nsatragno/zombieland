package com.rzg.zombieland.comunes.comunicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.rzg.zombieland.comunes.controlador.Controlador;
import com.rzg.zombieland.comunes.controlador.Controlador.ComandoDesconocidoException;
import com.rzg.zombieland.comunes.controlador.ControladorFactory;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Clase que se ocupa de la comunicación con un cliente en particular.
 * Una vez que se arranca el hilo, se escucha constantemente. Enviar una petición desde otro hilo
 * es thread-safe (bueno, supuestamente). Ver comentarios en implementación para conocer detalles
 * del protocolo.
 * @author nicolas
 *
 */
public class HiloEscucha extends Thread implements EnviaPeticiones {

    // El socket sobre el que se escucha al cliente.
    private Socket socket;
    
    // Indica si el hilo se está ejecutando.
    private boolean corriendo;
    
    // Fábrica de controladores.
    private ControladorFactory controladorFactory;
    
    // Mapea las peticiones para su respuesta.
    private Map<UUID, Peticion<?, ?>> mapaPeticiones;

    // Objeto al que se le avisa cuando ocurren distintos eventos en el hilo de escucha.
    private HiloListener listener;
    
    /**
     * Comienza a escuchar en el socket dado, delegando las peticiones a la fábrica de
     * controladores. El socket ya debe estar abierto.
     * @param socket - el socket con el que se escuchará al cliente.
     * @param controladorFactory - fábrica de controladores inyectada.
     */
    public HiloEscucha(Socket socket, ControladorFactory controladorFactory, HiloListener listener) {
        super("HiloEscucha: " + socket.getInetAddress());
        corriendo = true;
        Log.debug("Estableciendo nueva conexión con " + socket.getInetAddress());
        this.socket = socket;
        this.controladorFactory = controladorFactory;
        mapaPeticiones = new HashMap<UUID, Peticion<?, ?>>();
        this.listener = listener;
    }
    
    @Override
    public void run() {
        // Los dos warnings siguiente NO son de verdad, son un bug de Eclipse:
        // https://bugs.eclipse.org/bugs/show_bug.cgi?id=371614
        try (@SuppressWarnings("resource")
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             @SuppressWarnings("resource")
             // Obtengo un reader de entrada.
             BufferedReader in = new BufferedReader(new InputStreamReader(
                 socket.getInputStream()))) {
            while (corriendo) {
                // El hilo se detendrá acá hasta que el otro extremo envíe un código de petición. 
                int codigo = in.read();
                Log.debug("Recibiendo datos en HiloEscucha " + this + ". Código:");
                Log.debug(codigo);
                
                // -1 indica que el otro extremo cerró la conexión.
                if (codigo == -1) {
                    Log.debug("Cerrando hilo escucha: llegó el -1");
                    cerrar(true);
                    return;
                }
                // Este bloque es sincronizado para que si otro hilo intenta enviar una
                // respuesta sobre esta instancia de escucha mientras nosotros estamos
                // escribiendo en el socket, los mensajes no se intercalen y rompan el
                // protocolo.                
                try {
                    synchronized (this) {
                        // Si el servidor envía una respuesta, resolvemos la petición que teníamos
                        // relacionada. Primero buscamos la buscamos en el mapa de peticiones de
                        // acuerdo al ID que nos envió el otro extremo, luego la quitamos y le enviamos
                        // la respuesta para que la procese.
                        if (codigo == Enviable.RESPUESTA) {
                            mapaPeticiones.remove(UUID.fromString(in.readLine())).
                                procesarRespuesta(in.readLine());
                            continue;
                        }
                        // Si llegamos acá, la petición no viene de una respuesta. Obtenemos el ID
                        // generado por el cliente de la petición para que la pueda identificar.
                        String uuid = in.readLine();
                        
                        // En esta sección se puede ver el protocolo de envío de respuestas.
                        // Escribimos el código de las respuestas para que el cliente pueda
                        // identificar el mensaje apropiadamente.
                        out.write(Enviable.RESPUESTA);
                        // Escribimos el mismo UUID que nos dio el otro extremo.
                        out.println(uuid);
                        // Obtengo un controlador que es quien tomará las acciones apropiadas para
                        // la petición, como mover a un personaje o registrar un jugador.
                        Controlador controlador = controladorFactory.crear(codigo);
                        Log.debug("Contenido:");
                        // La próxima línea es el cuerpo de la petición. Su formato varía de 
                        // acuerdo al tipo de petición y lo sabe manejar el controlador.
                        String contenido = in.readLine();
                        Log.debug(contenido);
                        // Obtenemos la respuesta a partir del controlador.
                        String respuesta = controlador.procesar(contenido);
                        Log.debug("Respuesta:");
                        Log.debug(respuesta);
                        // Escribimos la respuesta en el búfer de salida...
                        out.println(respuesta);
                        // ...y nos aseguramos de que se limpie de una vez para evitar deadlocks
                        // en los tests.
                        out.flush();
                    }
                } catch (ComandoDesconocidoException e) {
                    // Esto no debería suceder jamás de los jamases, a menos que se corte la
                    // conexión en el medio del envío del código de comando... en cualquier caso,
                    // debemos poder manejar el error.
                    synchronized (this) {
                        Log.error("Se envió un comando desconocido: " + e.getMessage());
                        e.printStackTrace();
                        out.println(Enviable.LINEA_ERROR);
                        Log.error("Matando hilo de escucha :(");
                        cerrar(true);
                    }
                }
            }
            // Ya no estamos más corriendo. Cerramos el socket y nos vamos a dormir.
            socket.close();
        } catch (SocketException e) {
            Log.info("Cerrando hilo de escucha " + getName() + ":");
            Log.info("Motivo: " + e.getMessage());
            // Esperada, se usa para cerrar el Thread.
            return;
        } catch (IOException e) {
            Log.error("Error en hilo de escucha " + getName() + ":");
            Log.error(e.getMessage());
            e.printStackTrace();
        }
    }
    
    /**
     * Envía la petición al otro lado de la conexión.
     * @param peticion
     * @throws ZombielandException 
     */
    @Override
    public void enviarPeticion(Peticion<?, ?> peticion) throws ZombielandException {
        try {
            // Antes de enviar la petición, la almacenamos en un mapa identificada por un ID
            // generado aleatoriamente para poder identificar su respuesta. Es importante notar que
            // la vuelta de la petición desde el otro extremo se maneja en otro hilo, por lo que la
            // respuesta debe resolverse asíncronamete mediante una promesa.
            mapaPeticiones.put(peticion.getID(), peticion);
            PrintWriter salida = new PrintWriter(socket.getOutputStream());
            Log.debug("Enviando petición");
            // Ver comentarios en run() sobre el synchronized. 
            synchronized (this) {
                // El protocolo de envío de peticiones es sencillo: código, ID, contenido.
                salida.write(peticion.getCodigoPeticion());
                salida.println(peticion.getID().toString());
                salida.println(peticion.getMensajePeticion());
                salida.flush();
            }
        } catch (IOException e) {
            Log.error("Error de conexión:");
            Log.error(e.getMessage());
            e.printStackTrace();
            throw new ZombielandException("No se pudo comunicar con el otro extremo al enviar una petición");
        }
    }
    /**
     * Cierra el hilo de escucha.
     * @param notificar - true si debe notificar a los escuchadores, false de lo contrario.
     */
    public void cerrar(boolean notificar) {
        corriendo = false;
        try {
            socket.close();
        } catch (IOException e) {
        }
        if (notificar && listener != null)
            listener.hiloCerrado(this);
    }

    /**
     * @return la fábrica de controladores.
     */
    public ControladorFactory getFactory() {
        return controladorFactory;
    }
}
