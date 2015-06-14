package com.rzg.zombieland.comunes.comunicacion.pojo;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rzg.zombieland.comunes.comunicacion.respuesta.POJOCreacionPartida;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;

/**
 * Verifica la funcionalidad del POJO de lobby.
 * @author nicolas
 *
 */
public class POJOLobbyTest {

    private static final int CANTIDAD_RONDAS = 10;
    private static final int CANTIDAD_JUGADORES = 5;
    private static final String NOMBRE = "comer cerebros";
    private static final String JUGADOR = "juan";

    /**
     * Prueba crear un pojo y recuperar sus datos.
     * @throws ParametrosNoValidosException
     */
    @Test
    public void testBasico() throws ParametrosNoValidosException {
        POJOCreacionPartida pojoPartida = 
                new POJOCreacionPartida(CANTIDAD_RONDAS, CANTIDAD_JUGADORES, NOMBRE);
        POJOPartida pojo = new POJOPartida(pojoPartida, JUGADOR);
        assertEquals(JUGADOR, pojo.getAdministrador());
        assertEquals(CANTIDAD_JUGADORES, pojo.getCantidadMaximaJugadores());
        assertEquals(CANTIDAD_RONDAS, pojo.getCantidadRondas());
        assertEquals(NOMBRE, pojo.getNombre());
        assertEquals(1, pojo.getJugadores().size());
        assertEquals(JUGADOR, pojo.getJugadores().get(0));
        assertEquals(0, pojo.getEspectadores().size());
    }

}
