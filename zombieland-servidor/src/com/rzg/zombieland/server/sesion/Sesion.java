package com.rzg.zombieland.server.sesion;

import java.util.UUID;

import com.rzg.zombieland.comunes.comunicacion.EnviaPeticiones;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.meta.Partida;

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

    // La partida a la que está asociada el jugador. Puede ser null.
    private Partida partida;
    
    // El objeto que envía peticiones al otro lado.
    private EnviaPeticiones hilo;
    
    /**
     * Construye una sesión a partir del jugador.
     * @param jugador
     * @param hilo - el hilo de escucha que atiende esta sesión.
     * @throws NullPointerException si el jugador es nulo.
     */
    public Sesion(Jugador jugador, EnviaPeticiones hilo) {
        if (jugador == null)
            throw new NullPointerException("El jugador no puede ser null");
        if (hilo == null)
            throw new NullPointerException("El hilo no puede ser null");
        this.jugador = jugador;
        id = UUID.randomUUID();
        this.hilo = hilo;
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


    /**
     * @return la partida jugada en esta sesión. 
     */
    public Partida getPartida() {
        return partida;
    }
    
    /**
     * Establece la partida que el jugador comienza.
     * @param partida
     * @throws ZombielandException 
     */
    public void setPartida(Partida partida) throws ZombielandException {
        abandonarPartidaActual();
        this.partida = partida;
    }

    /**
     * Envía una petición al cliente asociado a la sesión.
     * @throws ZombielandException 
     */
    public void enviarPeticion(Peticion<?, ?> peticion) throws ZombielandException {
        hilo.enviarPeticion(peticion);
    }

    /**
     * Hace que el jugador abandone la partida.
     * @throws ZombielandException 
     */
    private void abandonarPartidaActual() throws ZombielandException {
        if (partida == null)
            return;
        partida.removerJugador(jugador);
        partida = null;
    }
}
