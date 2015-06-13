package com.rzg.zombieland.server.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOCreacionPartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.meta.Partida;
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
    public ControladorCrearPartida(ManejadorSesion manejadorSesion) throws ZombielandException {
        super(manejadorSesion);
    }

    @Override
    public String procesar(String linea) {
        Gson gson = new Gson();
        POJOCreacionPartida pojo = gson.fromJson(linea, POJOCreacionPartida.class);
                Partida partida = new Partida(getSesion().getJugador(), pojo);
        getSesion().setPartida(partida);
        return gson.toJson(new RespuestaGenerica());
    }

}
