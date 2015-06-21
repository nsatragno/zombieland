package com.rzg.zombieland.server.comunicacion.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJONombreUsuario;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPreguntaSeguridad;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaPreguntaSeg;
import com.rzg.zombieland.comunes.controlador.Controlador;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.server.persistencia.JugadorDao;
import com.rzg.zombieland.server.sesion.Jugador;
 /**
  * Gestiona una peticion de recuperar contraseña, debe encontrar el usuario
  * y devolver la pregunta de seguridad.
  * @author Nicolas L
  *
  */
public class ControladorObtenerPreguntaSeguridad extends Controlador{
	/*private ManejadorSesion manejadorSesion;
	
	public ControladorObtenerPreguntaSeguridad(ManejadorSesion manejadorSesion) {
		
	}*/
	
	@Override
	public String procesar(String linea) {
		Gson gson = new Gson();
		POJONombreUsuario nombre = gson.fromJson(linea, POJONombreUsuario.class);
		String nombreUsuario = nombre.getNombre();  
		JugadorDao dao = new JugadorDao();
		Jugador jugador = dao.getObjeto(nombreUsuario);
		dao.cerrarSesion();
		if (jugador == null) {
	            return gson.toJson(new RespuestaGenerica("El usuario no existe"));
	        }
		try {
			RespuestaPreguntaSeg rta = new RespuestaPreguntaSeg(
												new POJOPreguntaSeguridad(jugador.getPreguntaSecreta(),
																	jugador.getRespuestaSecreta()));
			return gson.toJson(rta);
		} catch (ParametrosNoValidosException e) {
			return gson.toJson(new RespuestaGenerica("No se encontraron datos."));
		}
	}

}
