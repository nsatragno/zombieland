package com.rzg.zombieland.cliente.comunicacion.peticion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;

/**
 * Solicita abandonar la partida actual. 
 * @author nicolas
 */
public class PeticionAbandonarPartida extends Peticion<Object, RespuestaGenerica> {

    /**
     * Constructor por defecto.
     */
    public PeticionAbandonarPartida() {
        super(null, RespuestaGenerica.class);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.ABANDONAR_PARTIDA;
    }
}
