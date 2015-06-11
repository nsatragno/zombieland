package com.rzg.zombieland.server.sesion;

import java.util.UUID;

/**
 * Rastrea el estado de un jugador: si ha iniciado sesión, en qué partida está jugando y a qué 
 * personaje está controlando.
 * @author nicolas
 *
 */
public class Sesion {
    // El jugador relacionado a la sesión.
    private Jugador jugador;
    
    // El ID único de la sesión.
    private UUID id;
    
    /**
     * Construye una sesión a partir del jugador.
     * @param jugador
     */
    public Sesion(Jugador jugador) {
        this.jugador = jugador;
        id = UUID.randomUUID();
    }

    /**
     * @return el ID de la sesión.
     */
    public UUID getId() {
        return id;
    }
    
    /**
     * @return el jugador asociado a la sesión.
     */
    public Jugador getJugador() {
        return jugador;
    }
}
