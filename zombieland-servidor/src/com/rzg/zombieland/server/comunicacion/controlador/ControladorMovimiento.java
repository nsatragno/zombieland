package com.rzg.zombieland.server.comunicacion.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.Movimiento;
import com.rzg.zombieland.comunes.misc.Movimiento.Direccion;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.juego.Personaje;
import com.rzg.zombieland.server.sesion.ManejadorSesion;

/**
 * Maneja mover a un personaje.
 * @author nicolas
 *
 */
public class ControladorMovimiento extends ControladorConSesion {

    private static final String MENSAJE_SIN_PARTIDA = "No hay una partida en progreso";
    /**
     * Crea un controlador de mover al personaje.
     * @param manejadorSesion
     * @throws ZombielandException
     */
    public ControladorMovimiento(ManejadorSesion manejadorSesion) {
        super(manejadorSesion);
    }

    @Override
    public String procesarAutenticado(String linea) {
        Gson gson = new Gson();
        Personaje personaje = getSesion().getPersonaje();
        if (personaje == null)
            return gson.toJson(new RespuestaGenerica(MENSAJE_SIN_PARTIDA));
        Direccion direccion = gson.fromJson(linea, Direccion.class);
        personaje.setSiguienteMovimiento(new Movimiento(direccion));
        Log.debug("El jugador " + getSesion().getJugador().getNombre() + " se ha movido en "
                + "dirección " + direccion.name() + ".");
        return gson.toJson(new RespuestaGenerica());
    }

}
