package com.rzg.zombieland.server.comunicacion.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJORegistro;
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
		return null;
	}

}
