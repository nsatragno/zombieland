package com.rzg.zombieland.cliente.comunicacion.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.cliente.meta.Estado;
import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.controlador.Controlador;

/**
 * Maneja una actualización del tablero.
 * @author nicolas
 *
 */
public class ControladorActualizacionProyeccion extends Controlador {

    @Override
    public String procesar(String linea) {
        Gson gson = new Gson();
        ProyeccionTablero proyeccion = gson.fromJson(linea, ProyeccionTablero.class);
        Estado.getInstancia().setProyeccion(proyeccion);
        return gson.toJson(new RespuestaGenerica(), RespuestaGenerica.class);
    }

}
