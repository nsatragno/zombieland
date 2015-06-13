package com.rzg.zombieland.comunes.comunicacion.pojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;

/**
 * Verifica el correcto funcionamiento de los POJO de creación de partida.
 * @author nicolas
 *
 */
public class POJOCreacionPartidaTest {

    private final static int CANTIDAD_JUGADORES_VALIDA = 5;
    private final static int CANTIDAD_RONDAS_VALIDA = 10;
    
    /**
     * Verifica los valores creando un pojo con datos válidos.
     * @throws ParametrosNoValidosException
     */
    @Test
    public void testParametrosValidos() throws ParametrosNoValidosException {
        POJOCreacionPartida pojo = 
                new POJOCreacionPartida(CANTIDAD_RONDAS_VALIDA, CANTIDAD_JUGADORES_VALIDA, "a");
        assertEquals(CANTIDAD_RONDAS_VALIDA, pojo.getCantidadRondas());
        assertEquals(CANTIDAD_JUGADORES_VALIDA, pojo.getCantidadMaximaJugadores());
    }

    
    /**
     * Verifica la existencia de error al usar una cantidad de rondas fuera de los límites.
     * @throws ParametrosNoValidosException
     */
    @Test
    public void testCantidadRondasFueraDeLosLimites() {
        try {
            new POJOCreacionPartida(POJOCreacionPartida.CANTIDAD_MAXIMA_RONDAS + 1,
                                    CANTIDAD_JUGADORES_VALIDA, "a");
            fail("Debería haber lanzado una excepción");
        } catch (ParametrosNoValidosException  e) {
            // Esperada.
        }
        try {
            new POJOCreacionPartida(POJOCreacionPartida.CANTIDAD_MINIMA_RONDAS - 1,
                                    CANTIDAD_JUGADORES_VALIDA, "a");
            fail("Debería haber lanzado una excepción");
        } catch (ParametrosNoValidosException  e) {
            // Esperada.
        }
    }
    
    /**
     * Verifica la existencia de error al usar valores fuera de los límites.
     * @throws ParametrosNoValidosException
     */
    @Test
    public void testCantidadJugadoresFueraDeLosLimites() {
        try {
            new POJOCreacionPartida(CANTIDAD_RONDAS_VALIDA,
                                    POJOCreacionPartida.CANTIDAD_MAXIMA_JUGADORES + 1, "a");
            fail("Debería haber lanzado una excepción");
        } catch (ParametrosNoValidosException  e) {
            // Esperada.
        }
        try {
            new POJOCreacionPartida(CANTIDAD_RONDAS_VALIDA,
                                    POJOCreacionPartida.CANTIDAD_MINIMA_JUGADORES - 1, "a");
            fail("Debería haber lanzado una excepción");
        } catch (ParametrosNoValidosException  e) {
            // Esperada.
        }
    }
    
    /**
     * Verifica la existencia de error al usar una cantidad de jugadores que no sea 
     * múltiplo de la cantidad de partidas.
     * @throws ParametrosNoValidosException
     */
    @Test
    public void testCantidadDeJugadoresNoMultiploDeCantidadPartidas() {
        try {
            new POJOCreacionPartida(CANTIDAD_RONDAS_VALIDA,
                                    CANTIDAD_JUGADORES_VALIDA - 1, "a");
            fail("Debería haber lanzado una excepción");
        } catch (ParametrosNoValidosException  e) {
            // Esperada.
        }
    }
    
    /**
     * Verifica que al haber varios errores, se devuelva un mensaje de error por cada uno. 
     * múltiplo de la cantidad de partidas.
     * @throws ParametrosNoValidosException
     */
    @Test
    public void testTodosLosErrores() throws ParametrosNoValidosException {
        final int CANTIDAD_ERRORES_ESPERADOS = 4;
        try {
            new POJOCreacionPartida(POJOCreacionPartida.CANTIDAD_MINIMA_RONDAS - 15,
                                    POJOCreacionPartida.CANTIDAD_MINIMA_JUGADORES - 17, "");
            fail("Debería haber lanzado una excepción");
        } catch (ParametrosNoValidosException  e) {
            assertEquals(CANTIDAD_ERRORES_ESPERADOS, e.getCantidadParametros());
        }
    }
}
