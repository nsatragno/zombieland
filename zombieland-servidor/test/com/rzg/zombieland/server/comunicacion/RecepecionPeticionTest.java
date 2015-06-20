package com.rzg.zombieland.server.comunicacion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.UUID;
import java.util.concurrent.BrokenBarrierException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.HiloEscucha;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaUnirsePartida;
import com.rzg.zombieland.comunes.controlador.ControladorTest;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.comunicacion.controlador.ControladorServidorFactory;
import com.rzg.zombieland.server.controlador.AbstractPartidasTest;
import com.rzg.zombieland.server.meta.Partida;
import com.rzg.zombieland.server.persistencia.HibernateSingleton;
import com.rzg.zombieland.server.sesion.Jugador;
import com.rzg.zombieland.server.sesion.ServicioSesion;
import com.rzg.zombieland.server.sesion.Sesion;

/**
 * Prueba la recepción completa de una petición.
 * @author nicolas
 *
 */
public class RecepecionPeticionTest extends AbstractPartidasTest {

    // El servicio de escucha que se prueba.
    private static ServicioEscucha servicioEscucha;
    
    private Socket socket;
    
    // Salida y entrada conectada al servicio de escucha.
    private PrintWriter salida;
    private BufferedReader entrada;
    private InputStreamReader reader;
    
    private final String host = "localhost";
    private final int puerto = 2048;
    private final static String LINEA_ENVIO = "Chau socket :)";

    private int TIEMPO_SLEEP = 5;

    private int MAX_TIEMPO = 5000;

    private int CANTIDAD_INTENTOS = 100;

    /**
     * Levanta al servidor y al cliente para el test.
     * @throws UnknownHostException
     * @throws IOException
     * @throws ZombielandException 
     * @throws InterruptedException 
     */
    @Before
    public void prepararCliente() throws UnknownHostException, IOException, ZombielandException, InterruptedException {
        HibernateSingleton.setTest();
        servicioEscucha = new ServicioEscucha();
        servicioEscucha.start();
        
        socket = new Socket(host, puerto);
        salida = new PrintWriter(socket.getOutputStream());
        reader = new InputStreamReader(socket.getInputStream());
        entrada = new BufferedReader(reader);
        
        int vueltas = 0;
        while (servicioEscucha.getHilos().size() != 1) {
            Thread.sleep(TIEMPO_SLEEP);
            vueltas++;
            assertFalse(vueltas * TIEMPO_SLEEP > MAX_TIEMPO);
        }
    }
    
    /**
     * Mata al cliente y al servidor.
     * @throws IOException
     * @throws InterruptedException 
     */
    @After
    public void cerrarCliente() throws IOException, InterruptedException {
        entrada.close();
        salida.close();
        reader.close();
        socket.close();
        servicioEscucha.cerrar();
        servicioEscucha.join();
    }
    
    /**
     * Intenta recibir una petición de pruebas, que solo devuelve un eco.
     * @throws IOException
     * @throws InterruptedException
     * @throws BrokenBarrierException
     */
    @Test
    public void testRecepcionPeticion() throws IOException, InterruptedException, BrokenBarrierException {
        salida.write(Enviable.TEST);
        UUID uuid = UUID.randomUUID();
        salida.println(uuid);
        salida.println(LINEA_ENVIO);
        salida.flush();
        int codigoRespuesta = entrada.read();
        String uuidRecibido = entrada.readLine();
        String lineaEntrada = entrada.readLine();
        assertEquals(codigoRespuesta, Enviable.RESPUESTA);
        assertEquals(lineaEntrada, LINEA_ENVIO);
        assertEquals(uuid.toString(), uuidRecibido);
        assertTrue(ControladorTest.proceso(LINEA_ENVIO));
    }

    /**
     * Envía una petición desconocida y esperar un error por respuesta.
     * @throws IOException
     * @throws InterruptedException
     * @throws BrokenBarrierException
     */
    @Test
    public void testRecepcionPeticionNoConocida() throws IOException, InterruptedException, BrokenBarrierException {
        final String LINEA_ENVIO = "testRecepcionPeticionNoConocida";
        salida.write(0xFF);
        UUID uuid = UUID.randomUUID();
        salida.println(uuid);
        salida.println(LINEA_ENVIO);
        salida.flush();
        int codigoRespuesta = entrada.read();
        String uuidRecibido = entrada.readLine();
        String lineaEntrada = entrada.readLine();
        assertEquals(Enviable.RESPUESTA, codigoRespuesta);
        assertEquals(lineaEntrada, Enviable.LINEA_ERROR);
        assertEquals(uuid.toString(), uuidRecibido);
        assertFalse(ControladorTest.proceso(LINEA_ENVIO));
    }
    
    /**
     * Crea una partida, hace que se una un jugador, y verifica que se notifique al jugador que la 
     * creó.
     * TODO mover a otro lado?
     * TODO extraer lógica de envío / recepción en funciones.
     * @throws IOException 
     * @throws InterruptedException 
     * @throws ZombielandException 
     */
    @Test
    public void testIntegracionNotificacionPartida() throws IOException, InterruptedException, ZombielandException {
        for (int i = 0; i < CANTIDAD_INTENTOS; i++) {
            Gson gson = new Gson();
            // Creo una partida y obtengo los datos del jugador que crea la partida.
            Partida partida = crearPartida();
            Jugador primerJugador = getUltimoAdmin();
            Sesion sesionPrimerJugador = new Sesion(primerJugador, servicioEscucha.getHilos().get(0));
            sesionPrimerJugador.setPartida(partida);
            ServicioSesion.getInstancia().addSesion(sesionPrimerJugador);
            
            // Otro jugador se conecta.
            try (Socket socketOtroJugador = new Socket(host, puerto);
                PrintWriter salidaOtroJugador = new PrintWriter(socketOtroJugador.getOutputStream());
                InputStreamReader readerOtroJugador = new InputStreamReader(socketOtroJugador.getInputStream());
                BufferedReader entradaOtroJugador = new BufferedReader(readerOtroJugador)) {
            
                // Esperamos a que el servidor acepta la conexión.
                int vueltas = 0;
                while (servicioEscucha.getHilos().size() != 2) {
                    Thread.sleep(TIEMPO_SLEEP);
                    vueltas++;
                    assertFalse(vueltas * TIEMPO_SLEEP > MAX_TIEMPO);
                }
                
                // El segundo jugador inicia sesión.
                HiloEscucha hiloOtroJugador = servicioEscucha.getHilos().get(1);
                Jugador otroJugador = crearJugador();
                Sesion sesionOtroJugador = new Sesion(otroJugador, hiloOtroJugador);
                ServicioSesion.getInstancia().addSesion(sesionOtroJugador);
                ControladorServidorFactory factory = 
                        (ControladorServidorFactory) hiloOtroJugador.getFactory();
                factory.setSesion(sesionOtroJugador);
        
                // El segundo jugador pide unirse a la partida.
                salidaOtroJugador.write(Enviable.UNIRSE_PARTIDA);
                UUID uuid = UUID.randomUUID();
                salidaOtroJugador.println(uuid);
                salidaOtroJugador.println(gson.toJson(partida.getId()));
                salidaOtroJugador.flush();
                
                // El segundo jugador recibe la respuesta exitosa.
                int codigoRespuesta = entradaOtroJugador.read();
                entradaOtroJugador.readLine();  // Leemos el UUID.
                String lineaEntrada = entradaOtroJugador.readLine();
                assertEquals(Enviable.RESPUESTA, codigoRespuesta);
                RespuestaUnirsePartida respuesta = gson.fromJson(lineaEntrada, RespuestaUnirsePartida.class);
                assertTrue(respuesta.fuePeticionExitosa());
                assertEquals(partida.getPOJO(otroJugador), respuesta.getPartida());
                
                // El primer jugador recibe la actualización de lobby.
                codigoRespuesta = entrada.read();
                String id = entrada.readLine(); 
                lineaEntrada = entrada.readLine();
                assertEquals(Enviable.ACTUALIZACION_LOBBY, codigoRespuesta);
                POJOPartida pojoRecibido = gson.fromJson(lineaEntrada, POJOPartida.class);
                assertEquals(partida.getPOJO(primerJugador), pojoRecibido);
                
                salida.write(Enviable.RESPUESTA);
                salida.println(id);
                salida.println(gson.toJson(new RespuestaGenerica()));
                salida.flush();
                
                // El segundo jugador se desconecta.
                socketOtroJugador.close();
                
                // El primer jugador recibe la nueva actualización de lobby.
                codigoRespuesta = entrada.read();
                id = entrada.readLine(); 
                lineaEntrada = entrada.readLine();
                assertEquals(Enviable.ACTUALIZACION_LOBBY, codigoRespuesta);
                pojoRecibido = gson.fromJson(lineaEntrada, POJOPartida.class);
                assertEquals(partida.getPOJO(primerJugador), pojoRecibido);
            }
            // Esperamos a que desconecte el primer jugador.
            int vueltas = 0;
            while (servicioEscucha.getHilos().size() != 1) {
                Thread.sleep(TIEMPO_SLEEP);
                vueltas++;
                assertFalse(vueltas * TIEMPO_SLEEP > MAX_TIEMPO);
            }
        }
        System.out.println("fin :)");
    }
}
