package com.rzg.zombieland.cliente.comunicacion.peticion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;

public class PeticionCambiarListoPartida extends Peticion<Boolean, RespuestaGenerica> {

    public PeticionCambiarListoPartida(boolean listo) {
        super(listo, RespuestaGenerica.class);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.CAMBIAR_LISTO_PARTIDA;
    }

}
