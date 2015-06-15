package com.rzg.zombieland.server.comunicacion.peticion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.comunicacion.respuesta.POJOListadoPartidas;

/**
 * Modela una petición de listado de partidas. 
 * @author nicolas
 */
public class PeticionListadoPartidas extends Peticion<POJOListadoPartidas, RespuestaGenerica> {

    /**
     * Constructor heredado.
     */
    public PeticionListadoPartidas(POJOListadoPartidas listado) {
        super(listado, RespuestaGenerica.class);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.LISTADO_PARTIDAS;
    }
}
