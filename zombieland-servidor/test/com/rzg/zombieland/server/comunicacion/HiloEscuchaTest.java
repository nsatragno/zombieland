package com.rzg.zombieland.server.comunicacion;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Testea que el hilo de escucha funcione correctamente.
 * @author nicolas
 *
 */
public class HiloEscuchaTest {

    @Test
    public void testCerrar() throws InterruptedException, UnknownHostException, IOException, ZombielandException {
        ServicioEscucha servicio = new ServicioEscucha();
        servicio.start();
        Socket socket = new Socket("localhost", 2048);
        HiloEscucha escucha = new HiloEscucha(socket);
        escucha.start();
        assertTrue(escucha.isAlive());
        escucha.cerrar();
        escucha.join();
        servicio.cerrar();
        servicio.join();
        socket.close();
        assertFalse(escucha.isAlive());
    }

    @Test
    public void testCerrarDesdeServicioEscucha() throws InterruptedException, UnknownHostException, IOException, ZombielandException {
        ServicioEscucha servicio = new ServicioEscucha();
        servicio.start();
        Socket socket = new Socket("localhost", 2048);
        assertTrue(servicio.isAlive());
        servicio.cerrar();
        servicio.join();
        assertFalse(servicio.isAlive());
        socket.close();
    }
}
