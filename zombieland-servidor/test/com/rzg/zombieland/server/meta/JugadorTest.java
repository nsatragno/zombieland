package com.rzg.zombieland.server.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.sesion.Jugador;

/**
 * Tests para el modelo de jugador.
 * @author nicolas
 *
 */
public class JugadorTest {

    /**
     * Intenta crear un jugador con todos los datos válidos.
     * @throws ParametrosNoValidosException 
     */
    @Test
    public void testCrearBien() throws ParametrosNoValidosException {
        new Jugador("Juan", "1234", "1234", "Nombre de mi madre", "María");
    }
    
    /**
     * 
     * @throws ParametrosNoValidosException 
     */
    @Test(expected=ZombielandException.class)
    public void testCrearConClavesDistintas() throws ParametrosNoValidosException {
        new Jugador("Juan", "1234", "12345", "Nombre de mi madre", "María");
    }

    /**
     * Intenta crear un jugador con sin nombre. Tiene que explotar.
     * @throws ParametrosNoValidosException 
     */
    @Test(expected=ZombielandException.class)
    public void testCrearSinNombre() throws ParametrosNoValidosException {
        new Jugador("", "1234", "1234", "Nombre de mi madre", "María");
    }
    
    /**
     * Intenta crear un jugador sin clave. Tiene que explotar.
     * @throws ParametrosNoValidosException 
     */
    @Test(expected=ZombielandException.class)
    public void testCrearSinClave() throws ParametrosNoValidosException {
        new Jugador("Juan", "", "", "Nombre de mi madre", "María");
    }
    
    /**
     * Intenta crear un jugador sin pregunta. Tiene que explotar.
     * @throws ParametrosNoValidosException 
     */
    @Test(expected=ZombielandException.class)
    public void testCrearSinPregunta() throws ParametrosNoValidosException {
        new Jugador("Juan", "1234", "1234", "", "María");
    }
    
    /**
     * Intenta crear un jugador sin respuesta. Tiene que explotar.
     * @throws ParametrosNoValidosException 
     */
    @Test(expected=ZombielandException.class)
    public void testCrearSinRespuesta() throws ParametrosNoValidosException {
        new Jugador("Juan", "1234", "1234", "Nombre de mi madre", "");
    }
    
    /**
     * Intenta crear un jugador sin nada. Tiene que explotar en todos los parámetros.
     */
    @Test
    public void testCrearSinNada() {
        try {
            new Jugador("", "", "validacionDistinta", "", "");
            fail("Debería haber lanzado una excepción");
        } catch (ParametrosNoValidosException e) {
            assertEquals(5, e.getCantidadParametros());
        }
    }
}
