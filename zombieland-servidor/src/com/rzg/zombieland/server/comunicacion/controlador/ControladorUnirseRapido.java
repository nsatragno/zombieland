package com.rzg.zombieland.server.comunicacion.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaUnirsePartida;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.meta.Partida;
import com.rzg.zombieland.server.meta.ServicioPartidas;
import com.rzg.zombieland.server.sesion.ManejadorSesion;

/**
 * Maneja unirse a una partida rápidamente.
 * @author nicolas
 *
 */
public class ControladorUnirseRapido extends ControladorConSesion {

    private static final String MENSAJE_SIN_PARTIDAS = "No hay partidas para unirse rápidamente.";

    /**
     * Crea un controlador de unirse rápidamente a partida.
     * @param manejadorSesion
     * @throws ZombielandException
     */
    public ControladorUnirseRapido(ManejadorSesion manejadorSesion) {
        super(manejadorSesion);
    }

    @Override
    public String procesarAutenticado(String linea) {
        Gson gson = new Gson();
        Partida partida = ServicioPartidas.getInstancia().getPartidaAleatoria();
        if (partida == null)
            return gson.toJson(new RespuestaGenerica(MENSAJE_SIN_PARTIDAS));
        try {
            partida.addJugador(getSesion().getJugador());
            getSesion().setPartida(partida);
        } catch (ZombielandException e) {
            return gson.toJson(new RespuestaGenerica(e.getMessage()));
        }
        Log.debug("El jugador " + getSesion().getJugador().getNombre() + " se ha unido a una partida.");
        return gson.toJson(new RespuestaUnirsePartida(partida.getPOJO(getSesion().getJugador())));
    }

}
