package com.rzg.zombieland.comunes.comunicacion.respuesta;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.junit.Test;

/**
 * Verifica el correcto funcionamiento de la respuesta de login.
 * @author nicolas
 *
 */
public class RespuestaLoginTest {

    /**
     * Intenta construir una respuesta válida y exitosa.
     */
    @Test
    public void testRespuestaExitosa() {
        RespuestaLogin respuesta = new RespuestaLogin();
        assertEquals(null, respuesta.getMensajeError());
        assertTrue(respuesta.fuePeticionExitosa());
    }

    /**
     * Intenta construir una respuesta válida y no exitosa.
     */
    @Test
    public void testRespuestaNoExitosa() {
        RespuestaLogin respuesta = new RespuestaLogin("error 500");
        assertEquals("error 500", respuesta.getMensajeError());
        assertFalse(respuesta.fuePeticionExitosa());
    }

    /**
     * Intenta construir una respuesta que es a la vez exitosa y no exitosa.
     */
    @Test(expected = InvalidParameterException.class)
    public void testRespuestaNoValida() {
        new RespuestaLogin("");
    }
    
    /**
     * Intenta construir una respuesta vacía.
     */
    @Test(expected = NullPointerException.class)
    public void testRespuestaVacia() {
        new RespuestaLogin(null);
    }

        
}
