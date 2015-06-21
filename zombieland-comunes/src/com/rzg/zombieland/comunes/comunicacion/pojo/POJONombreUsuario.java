package com.rzg.zombieland.comunes.comunicacion.pojo;

import java.util.ArrayList;
import java.util.List;

import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;

/**
 * POJO utilizado para Recuperar Contraseña.
 * Envio el nombre de usuario 
 * @author Nicolas L
 *
 */

public class POJONombreUsuario {
	//Nombre del usuario
	private String nombre;
	
	/**
     * Construye el POJO de nombre usuario para recuperar contraseña.
     * @param nombre - el nombre del usuario.
     * @throws ParametrosNoValidosException si alguno de los parámetros falta.
     */
    public POJONombreUsuario(String nombre) throws ParametrosNoValidosException {
        List<String> errores = new ArrayList<String>();
        
        if (nombre.isEmpty())
            errores.add("Nombre");
        this.nombre = nombre;
        if (errores.size() != 0)
            throw new ParametrosNoValidosException("Recuperar Contraseña", errores);
    }
    
    /**
     * @return el nombre de jugador.
     */
    public String getNombre() {
        return nombre;
    }
}
