package com.rzg.zombieland.test.server.comunicacion;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.comunicacion.ServicioEscucha;
import com.rzg.zombieland.server.controlador.ControladorVacio;

public class RecpecionPeticionTest {

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
    private final static String LINEA_ENVIO = "Hola Socket :)";

    private static CyclicBarrier lock;
    
    @BeforeClass
    public static void prepararServidor() 
            throws ZombielandException, UnknownHostException, IOException {
        lock = new CyclicBarrier(2);
        servicioEscucha = new ServicioEscucha(lock);
        servicioEscucha.start();
        ControladorVacio.setLineaDevolucion(LINEA_DEVOLUCION);
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
    public static void cerrarServidor() throws IOException {
        servicioEscucha.cerrar();
    }
    
    @Test
    public void testRecepcionRegistrarCliente() throws IOException, InterruptedException, BrokenBarrierException {
        lock.await();
        salida.write(Enviable.TEST);
        String lineaEntrada = entrada.readLine();
        salida.println(LINEA_ENVIO);
        assertEquals(lineaEntrada, LINEA_DEVOLUCION);
        assertEquals(LINEA_ENVIO, ControladorVacio.getUltimaLineaProcesada());
    }

}
