package com.rzg.zombieland.cliente.comunicacion.peticion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJORegistro;

/**
 * 
 * @author Ivan
 *
 */

public class PeticionObtenerDatosJugador extends Peticion<Object, POJORegistro> {
	
	/**
     * Construye una petición de cambio de datos de usuario para un usuario.
     * 
     */
    public PeticionObtenerDatosJugador() {
        super(null, POJORegistro.class);
    }
	
	@Override
    protected int getCodigoPeticion() {
        return Enviable.DEVOLVER_DATOS;
    }
}
