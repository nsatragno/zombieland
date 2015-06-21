package com.rzg.zombieland.comunes.comunicacion.pojo;

import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;


/**
 * Engloba los datos necesarios para unirse a una partida.
 * @author nicolas
 *
 */
public class POJOUnirsePartida {
    // El ID que identifica a la partida.
    private String idPartida;

    // True indica que se uno como espectador, false que lo hace como jugador.
    private boolean espectador;

    public POJOUnirsePartida(String idPartida, boolean espectador) {
        if (idPartida == null || idPartida.isEmpty())
            throw new NullPointerException("El ID de la partida no puede ser nulo o vacío");
        this.idPartida = idPartida;
        this.espectador = espectador;
    }
    
    /**
     * @return the idPartida
     */
    public String getIdPartida() {
        return idPartida;
    }

    /**
     * @return the espectador
     */
    public boolean esEspectador() {
        return espectador;
    }
}
