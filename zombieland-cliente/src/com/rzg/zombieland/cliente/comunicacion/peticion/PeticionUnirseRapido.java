package com.rzg.zombieland.cliente.comunicacion.peticion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaUnirsePartida;

public class PeticionUnirseRapido extends Peticion<Object, RespuestaUnirsePartida> {

    public PeticionUnirseRapido() {
        super(null, RespuestaUnirsePartida.class);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.UNIRSE_RAPIDO;
    }

}
