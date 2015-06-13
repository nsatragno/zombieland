package com.rzg.zombieland.server.sesion;

import org.junit.Assert;
import org.junit.Test;

import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;

/**
 * Verifica el correcto funcionamiento de la sesión.
 * @author nicolas
 *
 */
public class SesionTest {

    private final Jugador jugadorValido;
    
    /**
     * Inicializa al jugador válido.
     * @throws ParametrosNoValidosException
     */
    public SesionTest() throws ParametrosNoValidosException {
        jugadorValido = new Jugador("a", "b", "b", "c", "d");
    }
    
    /**
     * Prueba generar una sesión con datos válidos.
     */
    @Test
    public void testDatosValidos() {
        Sesion sesion = new Sesion(jugadorValido);
        Assert.assertEquals(jugadorValido, sesion.getJugador());
    }

    /**
     * Prueba generar una sesión con un jugador nulo y espera la excepción.
     */
    @Test(expected = NullPointerException.class)
    public void testJugadorNull() {
        new Sesion(null);
    }

}
