package com.rzg.zombieland.cliente.meta;

import com.rzg.zombieland.comunes.comunicacion.pojo.POJOLobby;

/**
 * Almacena estado que se debe compartir entre pantallas.
 * @author nicolas
 *
 */
public class Estado {

    private static Estado instancia;
    
    private POJOLobby estadoLobby;
    
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
    public void setEstadoLobby(POJOLobby pojo) {
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
    public POJOLobby getEstadoLobby() {
        return estadoLobby;
    }

    /**
     * @return el nombre del jugador actual.
     */
    public String getNombreJugador() {
        return jugador;
    }
}
