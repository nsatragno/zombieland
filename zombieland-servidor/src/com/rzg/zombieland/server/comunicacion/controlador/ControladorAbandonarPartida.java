package com.rzg.zombieland.server.comunicacion.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.sesion.ManejadorSesion;

/**
 * Maneja abandonar una partida.
 * @author nicolas
 *
 */
public class ControladorAbandonarPartida extends ControladorConSesion {

    /**
     * Crea un controlador de abandono de partida.
     * @param manejadorSesion
     * @throws ZombielandException
     */
    public ControladorAbandonarPartida(ManejadorSesion manejadorSesion) {
        super(manejadorSesion);
    }

    @Override
    public String procesarAutenticado(String linea) {
        Gson gson = new Gson();
        try {
            getSesion().setPartida(null);
        } catch (ZombielandException e) {
            return gson.toJson(new RespuestaGenerica(e.getMessage()));
        }
        Log.debug("El jugador " + getSesion().getJugador().getNombre() + " ha abandonado una partida.");
        return gson.toJson(new RespuestaGenerica());
    }

}
