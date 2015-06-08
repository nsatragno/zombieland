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
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.controlador.ControladorTest;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Prueba la recepción completa de una petición.
 * @author nicolas
 *
 */
public class RecepecionPeticionTest {

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

    /**
     * Levanta al servidor para el test.
     * @throws ZombielandException
     * @throws UnknownHostException
     * @throws IOException
     */
    @BeforeClass
    public static void prepararServidor() 
            throws ZombielandException, UnknownHostException, IOException {
        servicioEscucha = new ServicioEscucha();
        servicioEscucha.start();
    }
    
    /**
     * Levanta al cliente para el test.
     * @throws UnknownHostException
     * @throws IOException
     */
    @Before
    public void prepararCliente() throws UnknownHostException, IOException {
        socket = new Socket(host, puerto);
        salida = new PrintWriter(socket.getOutputStream());
        reader = new InputStreamReader(socket.getInputStream());
        entrada = new BufferedReader(reader);
    }
    
    /**
     * Mata al cliente.
     * @throws IOException
     */
    @After
    public void cerrarCliente() throws IOException {
        entrada.close();
        salida.close();
        reader.close();
        socket.close();
    }
    
    /**
     * Mata al servidor.
     * @throws IOException
     * @throws InterruptedException
     */
    @AfterClass
    public static void cerrarServidor() throws IOException, InterruptedException {
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
        assertEquals(codigoRespuesta, Enviable.RESPUESTA);
        assertEquals(lineaEntrada, Enviable.LINEA_ERROR);
        assertEquals(uuid.toString(), uuidRecibido);
        assertFalse(ControladorTest.proceso(LINEA_ENVIO));
    }
}
