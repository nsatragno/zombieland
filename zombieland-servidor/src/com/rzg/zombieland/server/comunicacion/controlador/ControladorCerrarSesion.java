package com.rzg.zombieland.server.comunicacion.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.sesion.ManejadorSesion;

/**
 * Maneja cerrar la sesión.
 * @author nicolas
 *
 */
public class ControladorCerrarSesion extends ControladorConSesion {

    /**
     * Crea un controlador de abandono de partida.
     * @param manejadorSesion
     * @throws ZombielandException
     */
    public ControladorCerrarSesion(ManejadorSesion manejadorSesion) {
        super(manejadorSesion);
    }

    @Override
    public String procesarAutenticado(String linea) {
        Gson gson = new Gson();
        Log.debug("El jugador " + getSesion().getJugador().getNombre() + " ha cerrado la sesión.");
        getSesion().cerrar();
        return gson.toJson(new RespuestaGenerica());
    }

}
