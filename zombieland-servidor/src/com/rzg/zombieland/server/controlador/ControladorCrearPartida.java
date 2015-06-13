package com.rzg.zombieland.server.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOCreacionPartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.meta.Partida;
import com.rzg.zombieland.server.meta.ServicioPartidas;
import com.rzg.zombieland.server.sesion.ManejadorSesion;

/**
 * Maneja la creación de una partida.
 * @author nicolas
 *
 */
public class ControladorCrearPartida extends ControladorConSesion {

    /**
     * Crea un controlador de creación de partida.
     * @param manejadorSesion
     * @throws ZombielandException
     */
    public ControladorCrearPartida(ManejadorSesion manejadorSesion) {
        super(manejadorSesion);
    }

    @Override
    public String procesarAutenticado(String linea) {
        Gson gson = new Gson();
        POJOCreacionPartida pojo = gson.fromJson(linea, POJOCreacionPartida.class);
        Partida partida = new Partida(getSesion().getJugador(), pojo);
        ServicioPartidas.getInstancia().addPartida(partida);
        getSesion().setPartida(partida);
        Log.debug("El jugador " + getSesion().getJugador().getNombre() + " ha creado una partida.");
        return gson.toJson(new RespuestaGenerica());
    }

}
