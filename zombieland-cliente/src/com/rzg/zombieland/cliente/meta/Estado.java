package com.rzg.zombieland.cliente.meta;

import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;

/**
 * Almacena estado que se debe compartir entre pantallas.
 * @author nicolas
 *
 */
public class Estado {

    private static Estado instancia;
    
    private POJOPartida estadoLobby;
    
    private String jugador;
    
    /**
     * @return la instancia de estado.
     */
    public static Estado getInstancia() {
        if (instancia == null)
            instancia = new Estado();
        return instancia;
    }
    
    /**
     * Establece el estado del lobby a partir de su POJO.
     * @param pojo
     */
    public void setEstadoLobby(POJOPartida pojo) {
        estadoLobby = pojo;
    }
    
    /**
     * Establece el nombre de un jugador.
     * @param nombreJugador
     */
    public void setNombreJugador(String nombreJugador) {
        jugador = nombreJugador;
    }
    
    /**
     * @return el estado del lobby.
     */
    public POJOPartida getEstadoLobby() {
        return estadoLobby;
    }

    /**
     * @return el nombre del jugador actual.
     */
    public String getNombreJugador() {
        return jugador;
    }
}
