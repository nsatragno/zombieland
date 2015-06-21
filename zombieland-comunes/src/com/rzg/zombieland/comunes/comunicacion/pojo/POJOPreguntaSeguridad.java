package com.rzg.zombieland.comunes.comunicacion.pojo;

import java.util.ArrayList;
import java.util.List;

import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;

/**
 * 
 * @author Nicolas L
 *
 */
public class POJOPreguntaSeguridad {
	//Nombre del usuario
		private String pregunta;
		private String respuesta;
		
		/**
	     * Construye el POJO de pregunta de seguridad para recuperar contraseña.
	     * @param preg pregunta de seguridad del usuario.
	     * @throws ParametrosNoValidosException si alguno de los parámetros falta.
	     */
	    public POJOPreguntaSeguridad(String preg, String rta) throws ParametrosNoValidosException {
	        List<String> errores = new ArrayList<String>();
	        
	        if (preg.isEmpty())
	            errores.add("Pregunta Seguridad");
	        this.pregunta = preg;
	        if (rta.isEmpty())
	            errores.add("Respuesta de Seguridad");
	        this.respuesta = rta;
	        if (errores.size() != 0)
	            throw new ParametrosNoValidosException("Recuperar Contraseña", errores);
	    }
	    
	    /**
	     * @return la pregunta de seguridad del usuario.
	     */
	    public String getPreguntaSeguridad() {
	        return pregunta;
	    }
	    
	    /**
	     * @return la respuesta de seguridad del usuario.
	     */
	    public String getRespuestaSeguridad() {
	        return respuesta;
	    }
}
