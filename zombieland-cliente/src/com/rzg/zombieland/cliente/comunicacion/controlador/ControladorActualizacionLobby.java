package com.rzg.zombieland.cliente.comunicacion.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.cliente.meta.Estado;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.controlador.Controlador;

/**
 * Maneja una actualización del lobby.
 * @author nicolas
 *
 */
public class ControladorActualizacionLobby extends Controlador {

    @Override
    public String procesar(String linea) {
        Gson gson = new Gson();
        POJOPartida partida = gson.fromJson(linea, POJOPartida.class);
        Estado.getInstancia().setEstadoLobby(partida);
        return gson.toJson(new RespuestaGenerica(), RespuestaGenerica.class);
    }

}
