package com.rzg.zombieland.cliente.comunicacion.peticion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJORegistro;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;

/**
 * 
 * @author Ivan
 *
 */

public class PeticionCambioDatosJugador extends Peticion<POJORegistro, RespuestaGenerica>{
	
	/**
     * Crea una petición de cambio de datos del jugador.
     * @param registro
     */
    public PeticionCambioDatosJugador(POJORegistro registro) {
        super(registro, RespuestaGenerica.class);
    }
    
    @Override
    protected int getCodigoPeticion() {
        return Enviable.CAMBIO_DATOS; 
    }

}
