package com.rzg.zombieland.cliente.comunicacion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaUnirsePartida;

/**
 * Modela una petición de creación de partida. 
 * @author nicolas
 */
public class PeticionUnirsePartida extends Peticion<String, RespuestaUnirsePartida> {

    /**
     * Constructor heredado.
     * @param id
     */
    public PeticionUnirsePartida(String id) {
        super(id, RespuestaUnirsePartida.class);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.UNIRSE_PARTIDA;
    }
}
