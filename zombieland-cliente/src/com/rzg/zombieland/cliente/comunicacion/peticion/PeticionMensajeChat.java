package com.rzg.zombieland.cliente.comunicacion.peticion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;

/**
 * Solicita enviar un mensaje de chat. 
 * @author nicolas
 */
public class PeticionMensajeChat extends Peticion<String, RespuestaGenerica> {

    /**
     * Constructor por defecto.
     */
    public PeticionMensajeChat(String mensaje) {
        super(mensaje, RespuestaGenerica.class);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.ENVIAR_MENSAJE_CHAT;
    }
}
