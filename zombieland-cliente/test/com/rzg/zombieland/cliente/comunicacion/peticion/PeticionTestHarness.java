package com.rzg.zombieland.cliente.comunicacion.peticion;

import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.net.UnknownHostException;

import org.junit.After;
import org.junit.Before;

import com.rzg.zombieland.cliente.comunicacion.ServicioCliente;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.comunicacion.ServicioEscucha;
import com.rzg.zombieland.server.persistencia.HibernateSingleton;

public class PeticionTestHarness {
    private static final long TIEMPO_SLEEP = 5;
    private static final long MAX_TIEMPO = 1000;
    protected static ServicioEscucha servicio;
    
    @Before
    public void lanzarClienteYServidor() throws ZombielandException, UnknownHostException, IOException, InterruptedException {
        HibernateSingleton.setTest();
        servicio = new ServicioEscucha();
        servicio.start();
        ServicioCliente.crearInstancia(2048, "localhost");
        int vueltas = 0;
        while (servicio.getHilos().size() != 1) {
            Thread.sleep(TIEMPO_SLEEP);
            vueltas++;
            assertFalse(vueltas * TIEMPO_SLEEP > MAX_TIEMPO);
        }
    }
    

    @After
    public void terminarClienteYServidor() throws ZombielandException, InterruptedException {
        HibernateSingleton.cerrarConexion();
        ServicioCliente.cerrar();
        servicio.cerrar();
        servicio.join();
    }
}
