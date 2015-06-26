package com.rzg.zombieland.server.comunicacion.peticion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;

/**
 * Envía una petición de recepción de mensaje de chat a los jugadores.
 * @author nicolas
 *
 */
public class PeticionRecibirMensajeChat extends Peticion<String, RespuestaGenerica> {

    public PeticionRecibirMensajeChat(String mensaje) {
        super(mensaje, RespuestaGenerica.class);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.RECIBIR_MENSAJE_CHAT;
    }

}
