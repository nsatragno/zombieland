package com.rzg.zombieland.server.comunicacion.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJORegistro;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.controlador.Controlador;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.persistencia.JugadorDao;
import com.rzg.zombieland.server.sesion.Jugador;

/**
 * Controla el registro de usuario. 
 * @author nicolas
 */
public class ControladorRegistro extends Controlador {

    @Override
    public String procesar(String linea) {
        Gson gson = new Gson();
        POJORegistro registro = gson.fromJson(linea, POJORegistro.class);
        JugadorDao dao = new JugadorDao();
        try {
            Jugador jugador = new Jugador(registro);
            Jugador existente = dao.getObjeto(jugador.getNombre());
            if (existente != null)
                throw new ZombielandException("El usuario ya existe");
            dao.guardarObjeto(jugador);
            return gson.toJson(new RespuestaGenerica());
        } catch (ZombielandException e) {
            return gson.toJson(new RespuestaGenerica(e.getMessage()));
        } finally {
            dao.cerrarSesion();
        }
    }

}
