package com.rzg.zombieland.cliente.comunicacion.peticion;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;

import com.rzg.zombieland.cliente.comunicacion.ServicioCliente;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.comunicacion.ServicioEscucha;
import com.rzg.zombieland.server.persistencia.HibernateSingleton;

public class PeticionTestHarness {
    protected static ServicioEscucha servicio;
    
    @Before
    public void lanzarClienteYServidor() throws ZombielandException, UnknownHostException, IOException {
        HibernateSingleton.setTest();
        servicio = new ServicioEscucha();
        servicio.start();
        ServicioCliente.crearInstancia(2048, "localhost");
    }
    

    @After
    public void terminarClienteYServidor() throws ZombielandException, InterruptedException {
        HibernateSingleton.cerrarConexion();
        ServicioCliente.cerrar();
        servicio.cerrar();
        servicio.join();
    }
}
