package com.rzg.zombieland.comunes.comunicacion.pojo;

import java.util.List;

import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;

/**
 * Engloba el listado de partidas para que parsearlo con JSON no sea doloroso.  
 * @author nicolas
 *
 */
public class RespuestaListadoPartidas extends RespuestaGenerica {
    // El listado de partidas.
    private List<POJOPartida> partidas;

    /**
     * Constructor por listado.
     * @param partidas
     */
    public RespuestaListadoPartidas(List<POJOPartida> partidas) {
        super();
        this.partidas = partidas;
    }
    
    /**
     * Constructor con error :(
     * @param mensajeError 
     */
    public RespuestaListadoPartidas(String mensajeError) {
        super(mensajeError);
    }

    /**
     * @return las partidas.
     */
    public List<POJOPartida> getPartidas() {
        return partidas;
    }
}