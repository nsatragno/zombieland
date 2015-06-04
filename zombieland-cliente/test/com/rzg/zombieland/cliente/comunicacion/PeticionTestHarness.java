package com.rzg.zombieland.cliente.comunicacion;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.rzg.zombieland.comunes.comunicacion.HiloEscucha;
import com.rzg.zombieland.comunes.controlador.ControladorTestFactory;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.comunicacion.ServicioEscucha;
import com.rzg.zombieland.server.persistencia.HibernateSingleton;

public class PeticionTestHarness {
    protected static ServicioEscucha servicio;
    protected static HiloEscucha hiloEscucha;
    
    @BeforeClass
    public static void lanzarClienteYServidor() throws ZombielandException, UnknownHostException, IOException {
        HibernateSingleton.setTest();
        servicio = new ServicioEscucha();
        servicio.start();
        Socket socket = new Socket("localhost", 2048);
        hiloEscucha = new HiloEscucha(socket, new ControladorTestFactory());
        hiloEscucha.start();
    }
    

    @AfterClass
    public static void terminarClienteYServidor() throws ZombielandException, InterruptedException {
        HibernateSingleton.cerrarConexion();
        hiloEscucha.cerrar();
        hiloEscucha.join();
        servicio.cerrar();
        servicio.join();
    }
}
