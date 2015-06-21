package com.rzg.zombieland.cliente.comunicacion.peticion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOUnirsePartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaUnirsePartida;

/**
 * Modela una petición de creación de partida. 
 * @author nicolas
 */
public class PeticionUnirsePartida extends Peticion<POJOUnirsePartida, RespuestaUnirsePartida> {

    /**
     * Constructor heredado.
     * @param id
     */
    public PeticionUnirsePartida(POJOUnirsePartida pojo) {
        super(pojo, RespuestaUnirsePartida.class);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.UNIRSE_PARTIDA;
    }
}
