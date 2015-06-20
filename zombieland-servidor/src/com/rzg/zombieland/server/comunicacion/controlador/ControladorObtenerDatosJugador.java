package com.rzg.zombieland.server.comunicacion.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJORegistro;
import com.rzg.zombieland.server.sesion.ManejadorSesion;

public class ControladorObtenerDatosJugador extends ControladorConSesion {

	public ControladorObtenerDatosJugador(ManejadorSesion manejadorSesion) {
		super(manejadorSesion);
	}

	@Override
	protected String procesarAutenticado(String respuesta) {
		Gson gson = new Gson();
		POJORegistro jugador = getSesion().getJugador().getPOJO();
		return gson.toJson(jugador);
	}

}
