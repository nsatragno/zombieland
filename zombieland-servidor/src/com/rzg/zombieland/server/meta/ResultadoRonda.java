package com.rzg.zombieland.server.meta;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.rzg.zombieland.comunes.comunicacion.pojo.POJOResultadoRonda;
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
     * Sube el puntaje de un jugador por el número dado.
     * @param jugador
     * @param puntaje
     */
    public void addPuntaje(Jugador jugador, int puntaje) {
        synchronized (puntos) {
            puntos.put(jugador, puntos.get(jugador) + puntaje);
        }
    }


    public POJOResultadoRonda getPojo() {
        synchronized (puntos) {
            Map<String, Integer> mapaPojo = new HashMap<String, Integer>();
            for (Entry<Jugador, Integer> entry : puntos.entrySet())
                mapaPojo.put(entry.getKey().getNombre(), entry.getValue());
            return new POJOResultadoRonda(mapaPojo);    
        }
    }
}
