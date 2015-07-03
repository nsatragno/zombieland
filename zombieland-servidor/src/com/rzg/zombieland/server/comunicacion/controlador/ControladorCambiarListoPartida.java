package com.rzg.zombieland.server.comunicacion.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.meta.Partida;
import com.rzg.zombieland.server.sesion.ManejadorSesion;

/**
 * Maneja establecer la bandera de "listo" en la partida.
 * @author nicolas
 *
 */
public class ControladorCambiarListoPartida extends ControladorConSesion {

    /**
     * Crea un controlador de abandono de partida.
     * @param manejadorSesion
     * @throws ZombielandException
     */
    public ControladorCambiarListoPartida(ManejadorSesion manejadorSesion) {
        super(manejadorSesion);
    }

    @Override
    public String procesarAutenticado(String linea) {
        Gson gson = new Gson();
        try {
            Partida partida = getSesion().getPartida();
            if (partida == null)
                throw new ZombielandException("El jugador no está asociado a una partida");
            partida.cambiarListoJugador(getSesion().getJugador(),
                                        gson.fromJson(linea, Boolean.class));
        } catch (ZombielandException e) {
            return gson.toJson(new RespuestaGenerica(e.getMessage()));
        }
        return gson.toJson(new RespuestaGenerica());
    }

}
