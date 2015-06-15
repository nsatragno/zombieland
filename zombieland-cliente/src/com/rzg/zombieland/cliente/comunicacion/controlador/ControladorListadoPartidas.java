package com.rzg.zombieland.cliente.comunicacion.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.cliente.meta.Estado;
import com.rzg.zombieland.comunes.comunicacion.respuesta.POJOListadoPartidas;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.controlador.Controlador;
import com.rzg.zombieland.comunes.misc.Log;

/**
 * Devuelve el listado de partidas.
 * @author nicolas
 *
 */
public class ControladorListadoPartidas extends Controlador {

    @Override
    public String procesar(String linea) {
        Log.debug("Actualizando listado de partidas");
        Gson gson = new Gson();
        POJOListadoPartidas listado = gson.fromJson(linea, POJOListadoPartidas.class);
        Estado.getInstancia().setListadoPartidas(listado);
        RespuestaGenerica respuesta = new RespuestaGenerica();
        return new Gson().toJson(respuesta);
    }
}
