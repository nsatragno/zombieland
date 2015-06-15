package com.rzg.zombieland.comunes.comunicacion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

import com.rzg.zombieland.comunes.controlador.ControladorTestFactory;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Testea que el hilo de escucha funcione correctamente.
 * @author nicolas
 *
 */
public class HiloEscuchaTest {

    private boolean hiloCerrado;
    
    /**
     * Intenta cerrar un hilo de escucha y verifica que haga el join efectivamente.
     * @throws InterruptedException
     * @throws UnknownHostException
     * @throws IOException
     * @throws ZombielandException
     */
    @Test
    public void testCerrar() throws InterruptedException, UnknownHostException, IOException, ZombielandException {
        ServerSocket serverSocket = new ServerSocket(2048);
        Socket socket = new Socket("localhost", 2048);
        hiloCerrado = false;
        HiloEscucha escucha = new HiloEscucha(socket, new ControladorTestFactory(), new HiloListener() {

            @Override
            public void hiloCerrado(HiloEscucha hilo) {
                hiloCerrado = true;
            }
        });
        escucha.start();
        assertTrue(escucha.isAlive());
        escucha.cerrar();
        escucha.join();
        socket.close();
        serverSocket.close();
        assertFalse(escucha.isAlive());
        assertTrue(hiloCerrado);
    }
}
