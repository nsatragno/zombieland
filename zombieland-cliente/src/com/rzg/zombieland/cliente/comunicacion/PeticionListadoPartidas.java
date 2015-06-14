package com.rzg.zombieland.cliente.comunicacion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaListadoPartidas;

/**
 * Modela una petición de listado de partidas. 
 * @author nicolas
 */
public class PeticionListadoPartidas extends Peticion<Object, RespuestaListadoPartidas> {

    /**
     * Constructor heredado.
     */
    public PeticionListadoPartidas() {
        super(null, RespuestaListadoPartidas.class);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.LISTADO_PARTIDAS;
    }
}
