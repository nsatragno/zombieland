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
 * 
 * @author nicolas
 *
 */
public class HiloEscucha extends Thread {

    // El socket sobre el que se escucha al cliente.
    private Socket socket;
    
    // Indica si el hilo se está ejecutando.
    private boolean corriendo;
    
    // Fábrica de controladores.
    private ControladorFactory controladorFactory;
    
    // Mapea las peticiones para su respuesta.
    private Map<UUID, Peticion<?>> mapaPeticiones;
    
    /**
     * Construye un hilo de escucha.
     * 
     * @param socket
     *            - el socket con el que se escuchará al cliente.
     */
    public HiloEscucha(Socket socket, ControladorFactory controladorFactory) {
        super("HiloEscucha: " + socket.getInetAddress());
        corriendo = true;
        Log.debug("Aceptando nueva conexión de " + socket.getInetAddress());
        this.socket = socket;
        this.controladorFactory = controladorFactory;
        mapaPeticiones = new HashMap<UUID, Peticion<?>>();
    }
    
    @Override
    public void run() {
        // Los dos warnings siguiente NO son de verdad, son un bug de Eclipse:
        // https://bugs.eclipse.org/bugs/show_bug.cgi?id=371614
        try (@SuppressWarnings("resource")
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            @SuppressWarnings("resource")
             BufferedReader in = new BufferedReader(new InputStreamReader(
                 socket.getInputStream()))) {
            while (corriendo) {
                int codigo = in.read();
                Log.debug("Recibiendo datos en HiloEscucha " + this + ". Código:");
                Log.debug(codigo);
                
                // Condición de fin del stream.
                if (codigo == -1) {
                    Log.debug("Cerrando hilo escucha: llegó el -1");
                    return;
                }
                
                try {
                    // Si el servidor envía una respuesta, resolvemos la petición que teníamos
                    // relacionada.
                    if (codigo == Enviable.RESPUESTA) {
                        mapaPeticiones.remove(UUID.fromString(in.readLine())).
                            procesarRespuesta(in.readLine());
                        continue;
                    }
                    String uuid = in.readLine();
                    synchronized (this) {
                        out.write(Enviable.RESPUESTA);
                        out.println(uuid);
                        Controlador controlador = controladorFactory.crear(codigo);
                        Log.debug("Contenido:");
                        String contenido = in.readLine();
                        Log.debug(contenido);
                        String respuesta = controlador.procesar(contenido);
                        Log.debug("Respuesta:");
                        Log.debug(respuesta);
                        out.println(respuesta);
                        out.flush();
                    }
                } catch (ComandoDesconocidoException e) {
                    synchronized (this) {
                        out.println(Enviable.LINEA_ERROR);
                    }
                }
            }
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
    public void enviarPeticion(Peticion<?> peticion) throws ZombielandException {
        try {
            mapaPeticiones.put(peticion.getID(), peticion);
            PrintWriter salida = new PrintWriter(socket.getOutputStream());
            Log.debug("Enviando petición");
            synchronized (this) {
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
     */
    public void cerrar() {
        corriendo = false;
        try {
            socket.close();
        } catch (IOException e) {
        }
    }

}
