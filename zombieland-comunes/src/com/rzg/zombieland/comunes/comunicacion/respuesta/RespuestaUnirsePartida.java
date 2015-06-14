package com.rzg.zombieland.comunes.comunicacion.respuesta;

import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;

/**
 * Modela una respuesta de una petición de unirse a una partida.  
 * @author nicolas
 *
 */
public class RespuestaUnirsePartida extends RespuestaGenerica {
    // Los datos de la partida a la que se une.
    private POJOPartida partida;

    /**
     * Constructor por listado.
     * @param partidas
     */
    public RespuestaUnirsePartida(POJOPartida partida) {
        super();
        this.partida = partida;
    }
    
    /**
     * Constructor con error :(
     * @param mensajeError 
     */
    public RespuestaUnirsePartida(String mensajeError) {
        super(mensajeError);
    }

    /**
     * @return la partida.
     */
    public POJOPartida getPartida() {
        return partida;
    }
}