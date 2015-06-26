package com.rzg.zombieland.server.comunicacion.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.sesion.ManejadorSesion;

/**
 * Maneja el envío de un mensaje de chat..
 * @author nicolas
 *
 */
public class ControladorEnviarMensajeChat extends ControladorConSesion {

    private static final String MENSAJE_SIN_PARTIDA = 
            "Tenés que estar en una partida para poder enviar un mensaje";

    /**
     * Crea un controlador de mensaje de chat.
     * @param manejadorSesion
     * @throws ZombielandException
     */
    public ControladorEnviarMensajeChat(ManejadorSesion manejadorSesion) {
        super(manejadorSesion);
    }

    @Override
    public String procesarAutenticado(String linea) {
        Gson gson = new Gson();
        if (getSesion().getPartida() == null)
            return gson.toJson(new RespuestaGenerica(MENSAJE_SIN_PARTIDA));
        
        String mensaje = 
                getSesion().getJugador().getNombre() + ": " + gson.fromJson(linea, String.class);
        getSesion().getPartida().enviarMensajeChat(mensaje);
        return gson.toJson(new RespuestaGenerica());
    }

}
