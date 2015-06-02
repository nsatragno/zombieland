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
    private final static String LINEA_DEVOLUCION = "Chau socket :)";

    @BeforeClass
    public static void prepararServidor() 
            throws ZombielandException, UnknownHostException, IOException {
        servicioEscucha = new ServicioEscucha();
        servicioEscucha.start();
        ControladorTest.setLineaDevolucion(LINEA_DEVOLUCION);
    }
    
    @Before
    public void prepararCliente() throws UnknownHostException, IOException {
        socket = new Socket(host, puerto);
        salida = new PrintWriter(socket.getOutputStream());
        reader = new InputStreamReader(socket.getInputStream());
        entrada = new BufferedReader(reader);
    }
    
    @After
    public void cerrarCliente() throws IOException {
        entrada.close();
        salida.close();
        reader.close();
        socket.close();
    }
    
    @AfterClass
    public static void cerrarServidor() throws IOException, InterruptedException {
        servicioEscucha.cerrar();
        servicioEscucha.join();
    }
    
    @Test
    public void testRecepcionPeticion() throws IOException, InterruptedException, BrokenBarrierException {
        final String LINEA_ENVIO = "testRecepcionPeticion";
        salida.write(Enviable.TEST);
        salida.println(LINEA_ENVIO);
        salida.flush();
        String lineaEntrada = entrada.readLine();
        assertEquals(lineaEntrada, LINEA_DEVOLUCION);
        assertTrue(ControladorTest.proceso(LINEA_ENVIO));
    }

    @Test
    public void testRecepcionPeticionNoConocida() throws IOException, InterruptedException, BrokenBarrierException {
        final String LINEA_ENVIO = "testRecepcionPeticionNoConocida";
        salida.write(0xFF);
        salida.println(LINEA_ENVIO);
        salida.flush();
        String lineaEntrada = entrada.readLine();
        assertEquals(lineaEntrada, Enviable.LINEA_ERROR);
        assertFalse(ControladorTest.proceso(LINEA_ENVIO));
    }
}
