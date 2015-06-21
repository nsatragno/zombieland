package com.rzg.zombieland.server.comunicacion.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOCambioPass;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJONombreUsuario;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPreguntaSeguridad;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaPreguntaSeg;
import com.rzg.zombieland.comunes.controlador.Controlador;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.server.persistencia.JugadorDao;
import com.rzg.zombieland.server.sesion.Jugador;

public class ControladorCambioPass extends Controlador {

	@Override
	public String procesar(String linea) {
		Gson gson = new Gson();
		POJOCambioPass cambiopass = gson.fromJson(linea, POJOCambioPass.class);
		String nombreUsuario = cambiopass.getNombre();
		String pass = cambiopass.getPass();
		JugadorDao dao = new JugadorDao();
		Jugador jugador = dao.getJugadorPorNombre(nombreUsuario);
		
		if (jugador == null) {
	            return gson.toJson(new RespuestaGenerica("El usuario no existe"));
	        }
		
			jugador.setClave(pass);
			dao.actualizarObjeto(jugador);
			dao.cerrarSesion();
			 Log.info("El jugador " + jugador.getNombre() + " ha modificado su contraseña.");
			return gson.toJson(new RespuestaGenerica());
	
	}

}
