package com.rzg.zombieland.comunes.comunicacion.respuesta;

import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPreguntaSeguridad;

/**
 * Respuesta de una peticion para recuperar la contraseña, ingreso el nombre 
 * devuelve una pregunta de seguridad.
 * @author Nicolas L
 *
 */

public class RespuestaPreguntaSeg extends RespuestaGenerica {
	private POJOPreguntaSeguridad PreguntaSeg;

    public RespuestaPreguntaSeg(POJOPreguntaSeguridad pregunta) {
        super();
        PreguntaSeg = pregunta;
    }
    
    /**
     * Constructor con error :(
     * @param mensajeError 
     */
    public RespuestaPreguntaSeg(String mensajeError) {
        super(mensajeError);
    }

    /**
     * @return la Pregunta de seguridad.
     */
    public POJOPreguntaSeguridad getPreguntaSeguridad() {
        return PreguntaSeg;
    }
}
