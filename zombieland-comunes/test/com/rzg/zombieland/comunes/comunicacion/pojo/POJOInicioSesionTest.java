package com.rzg.zombieland.comunes.comunicacion.pojo;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;

/**
 * Verifica el correcto funcionamiento del POJO de inicio de sesión.
 * @author nicolas
 *
 */
public class POJOInicioSesionTest {

    /**
     * Intenta construir un pojo con valores válidos.
     * @throws ParametrosNoValidosException no debería.
     */
    @Test
    public void testValido() throws ParametrosNoValidosException {
        POJOInicioSesion pojo = new POJOInicioSesion("juan", "1234");
        assertEquals("juan", pojo.getNombre());
        assertEquals("1234", pojo.getClave());
    }
    
    /**
     * Intenta construir un pojo con ambos valores no válidos.
     */
    @Test
    public void testNoValido() {
        try {
            new POJOInicioSesion("", "");
            fail("Debería haber lanzado una excepción");
        } catch (ParametrosNoValidosException e) {
            assertEquals(2, e.getCantidadParametros());
        }
    }
}
