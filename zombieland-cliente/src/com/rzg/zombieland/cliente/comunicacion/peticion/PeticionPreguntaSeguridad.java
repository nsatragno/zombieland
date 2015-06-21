package com.rzg.zombieland.cliente.comunicacion.peticion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJONombreUsuario;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaPreguntaSeg;
/**
 * 
 * @author Nicolas L
 *
 */
public class PeticionPreguntaSeguridad extends Peticion<POJONombreUsuario, RespuestaPreguntaSeg>{
	
	  /**
     * Construye una petición para obtener una Pregunta de Seguridad de un
     * Usuario.
     * @param pojo
     */
    public PeticionPreguntaSeguridad(POJONombreUsuario pojo) {
        super(pojo, RespuestaPreguntaSeg.class);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.PREGUNTA_SEGURIDAD;
    }

}
