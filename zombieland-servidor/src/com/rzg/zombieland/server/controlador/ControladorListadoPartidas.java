package com.rzg.zombieland.server.controlador;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;
import com.rzg.zombieland.comunes.comunicacion.pojo.RespuestaListadoPartidas;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.meta.Partida;
import com.rzg.zombieland.server.meta.ServicioPartidas;
import com.rzg.zombieland.server.sesion.ManejadorSesion;

/**
 * Devuelve el listado de partidas.
 * @author nicolas
 *
 */
public class ControladorListadoPartidas extends ControladorConSesion {

    /**
     * Crea un controlador de listado de partidas.
     * @param manejadorSesion
     * @throws ZombielandException
     */
    public ControladorListadoPartidas(ManejadorSesion manejadorSesion) {
        super(manejadorSesion);
    }

    @Override
    public String procesarAutenticado(String linea) {
        List<Partida> partidas = ServicioPartidas.getInstancia().getPartidas();
        List<POJOPartida> pojos = new ArrayList<POJOPartida>();
        for (Partida partida : partidas)
            pojos.add(partida.getPOJO());
        RespuestaListadoPartidas respuesta = new RespuestaListadoPartidas(pojos);
        return new Gson().toJson(respuesta);
    }

    @Override
    protected String mensajeErrorNoAutenticado() {
        return new Gson().toJson(new RespuestaListadoPartidas(MENSAJE_NO_AUTENTICADO));
    }
}
