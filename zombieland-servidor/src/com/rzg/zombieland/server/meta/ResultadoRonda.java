package com.rzg.zombieland.server.meta;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.rzg.zombieland.server.sesion.Jugador;

/**
 * Modela el resultado de una ronda.
 * @author nicolas
 *
 */
public class ResultadoRonda {

    private Map<Jugador, Integer> puntos;
    
    /**
     * Crea un resultado de partida vacío. Por defecto todos los jugadores tienen cero puntos.
     */
    public ResultadoRonda(Collection<Jugador> jugadores) {
        puntos = new HashMap<Jugador, Integer>();
        for (Jugador jugador : jugadores)
            puntos.put(jugador, 0);
    }
    
    
    /**
     * Establece el puntaje de un jugador.
     * @param jugador
     * @param puntaje
     */
    public void setPuntaje(Jugador jugador, int puntaje) {
        puntos.put(jugador, puntaje);
    }
}
