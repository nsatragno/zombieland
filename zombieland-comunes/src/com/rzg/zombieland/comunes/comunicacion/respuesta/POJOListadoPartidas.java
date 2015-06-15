package com.rzg.zombieland.comunes.comunicacion.respuesta;

import java.util.List;

import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;

/**
 * Engloba el listado de partidas para que parsearlo con JSON no sea doloroso.  
 * @author nicolas
 *
 */
public class POJOListadoPartidas {
    // El listado de partidas.
    private List<POJOPartida> partidas;

    /**
     * Constructor por listado.
     * @param partidas
     */
    public POJOListadoPartidas(List<POJOPartida> partidas) {
        this.partidas = partidas;
    }
    
    /**
     * @return las partidas.
     */
    public List<POJOPartida> getPartidas() {
        return partidas;
    }
}