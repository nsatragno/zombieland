package com.rzg.zombieland.test.server.meta;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.meta.Jugador;

public class JugadorTest {

    @Test
    public void testCrearBien() throws ZombielandException {
        new Jugador("Juan", "1234", "1234", "Nombre de mi madre", "María");
    }
    
    @Test(expected=ZombielandException.class)
    public void testCrearConClavesDistintas() throws ZombielandException {
        new Jugador("Juan", "1234", "12345", "Nombre de mi madre", "María");
    }

    @Test(expected=ZombielandException.class)
    public void testCrearSinNombre() throws ZombielandException {
        new Jugador("", "1234", "1234", "Nombre de mi madre", "María");
    }
    
    @Test(expected=ZombielandException.class)
    public void testCrearSinClave() throws ZombielandException {
        new Jugador("Juan", "", "", "Nombre de mi madre", "María");
    }
    
    @Test(expected=ZombielandException.class)
    public void testCrearSinPregunta() throws ZombielandException {
        new Jugador("Juan", "1234", "1234", "", "María");
    }
    
    @Test(expected=ZombielandException.class)
    public void testCrearSinRespuesta() throws ZombielandException {
        new Jugador("Juan", "1234", "1234", "Nombre de mi madre", "");
    }
    
    @Test
    public void testCrearSinNada() {
        try {
            new Jugador("", "", "validacionDistinta", "", "");
            fail("Debería haber lanzado una excepción");
        } catch (ZombielandException e) {
            assertEquals(5, e.getErrores().size());
        }
    }
}
