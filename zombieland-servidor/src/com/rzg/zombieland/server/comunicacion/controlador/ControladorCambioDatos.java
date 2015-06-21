package com.rzg.zombieland.server.comunicacion.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJORegistro;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.persistencia.JugadorDao;
import com.rzg.zombieland.server.sesion.Jugador;
import com.rzg.zombieland.server.sesion.ManejadorSesion;

public class ControladorCambioDatos extends ControladorConSesion
{

	public ControladorCambioDatos(ManejadorSesion manejadorSesion)
	{
		super(manejadorSesion);
	}

	@Override
	protected String procesarAutenticado(String linea)
	{
		Gson gson = new Gson();
		POJORegistro registro = gson.fromJson(linea, POJORegistro.class);
		JugadorDao dao = new JugadorDao();
		try {
			Jugador jugador = new Jugador(registro);
			Jugador existente = dao.getJugadorPorNombre(jugador.getNombre());
			if(existente != null && jugador.getId() != existente.getId())
				throw new ZombielandException("El nombre de usuario ya fue elegido");
			dao.actualizarObjeto(jugador);
			Log.info("El jugador: " + jugador.getNombre() + " ha cambiado sus datos");
			return gson.toJson(new RespuestaGenerica());
		} catch(ZombielandException e) {
			return gson.toJson(new RespuestaGenerica(e.getMessage()));
		} finally {
			dao.cerrarSesion();
		}
	}
}
