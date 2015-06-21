package com.rzg.zombieland.comunes.comunicacion.pojo;

import java.util.ArrayList;
import java.util.List;

import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;

/**
 * POJO utilizado para el cambio de contraseña de un Usuario
 * @author Nicolas L
 *
 */
public class POJOCambioPass {
		private String nombre;
		
		private String pass;
		public POJOCambioPass(String nombre, String pass) throws ParametrosNoValidosException{
			List<String> errores = new ArrayList<String>();
	        
	        if (nombre.isEmpty())
	            errores.add("Nombre");
	        this.nombre = nombre;
	        if (pass.isEmpty())
	            errores.add("Contraseña");
	        this.pass = pass;
	        if (errores.size() != 0)
	            throw new ParametrosNoValidosException("Cambiar Contraseña", errores);
		}
		
		public String getNombre(){
			return nombre;
		}
		
		public String getPass(){
			return pass;
		}
}
