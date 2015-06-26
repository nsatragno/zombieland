package com.rzg.zombieland.comunes.comunicacion.pojo;

import java.util.Map;

/**
 * POJO utilizado para actualizaciones en el puntaje de una ronda.
 * @author nicolas
 *
 */
public class POJOResultadoRonda {
	private Map<String, Integer> puntajes;
	
	public POJOResultadoRonda(Map<String, Integer> puntajes) {
	    if (puntajes == null)
	        throw new NullPointerException("El puntaje no puede ser null.");
	    this.puntajes = puntajes;
	}
	
	public Map<String, Integer> getPuntajes(){
		return puntajes;
	}
}
