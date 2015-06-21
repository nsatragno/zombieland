package com.rzg.zombieland.server.comunicacion.controlador;

import java.util.UUID;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOUnirsePartida;
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
        POJOUnirsePartida datosPartida = gson.fromJson(linea, POJOUnirsePartida.class);
        UUID idPartida = UUID.fromString(datosPartida.getIdPartida());
        Partida partida = ServicioPartidas.getInstancia().getPartida(idPartida);
        if (partida == null)
            return gson.toJson(new RespuestaGenerica(MENSAJE_PARTIDA_NO_EXISTENTE));
        try {
            if (datosPartida.esEspectador())
                partida.addEspectador(getSesion().getJugador());
            else
                partida.addJugador(getSesion().getJugador());
            getSesion().setPartida(partida);
        } catch (ZombielandException e) {
            return gson.toJson(new RespuestaGenerica(e.getMessage()));
        }
        Log.debug("El jugador " + getSesion().getJugador().getNombre() + " se ha unido a una partida.");
        return gson.toJson(new RespuestaUnirsePartida(partida.getPOJO(getSesion().getJugador())));
    }

}
