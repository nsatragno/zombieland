package com.rzg.zombieland.server.controlador;

import java.util.UUID;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaUnirsePartida;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.meta.Partida;
import com.rzg.zombieland.server.meta.ServicioPartidas;
import com.rzg.zombieland.server.sesion.ManejadorSesion;

/**
 * Maneja unirse a una partida.
 * @author nicolas
 *
 */
public class ControladorUnirsePartida extends ControladorConSesion {

    public static final String MENSAJE_PARTIDA_NO_EXISTENTE = "La partida no existe";

    /**
     * Crea un controlador de creación de partida.
     * @param manejadorSesion
     * @throws ZombielandException
     */
    public ControladorUnirsePartida(ManejadorSesion manejadorSesion) {
        super(manejadorSesion);
    }

    @Override
    public String procesarAutenticado(String linea) {
        Gson gson = new Gson();
        String idPartida = gson.fromJson(linea, String.class);
        Partida partida = ServicioPartidas.getInstancia().getPartida(UUID.fromString(idPartida));
        if (partida == null)
            return gson.toJson(new RespuestaGenerica(MENSAJE_PARTIDA_NO_EXISTENTE));
        getSesion().setPartida(partida);
        partida.addJugador(getSesion().getJugador());
        Log.debug("El jugador " + getSesion().getJugador().getNombre() + " se ha unido a una partida.");
        return gson.toJson(new RespuestaUnirsePartida(partida.getPOJO()));
    }

}
