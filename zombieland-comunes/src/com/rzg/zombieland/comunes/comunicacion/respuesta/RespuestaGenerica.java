package com.rzg.zombieland.comunes.comunicacion.respuesta;

import java.security.InvalidParameterException;


/**
 * Representa una respuesta genérica.
 * @author nicolas
 *
 */
public class RespuestaGenerica {
    // Mensaje de error. null si la petición fue exitosa.
    private String mensajeError;
    
    /**
     * Construye una respuesta con error.
     * @param mensajeError 
     */
    public RespuestaGenerica(String mensajeError) {
        if (mensajeError == null)
            throw new NullPointerException("El mensaje de error no puede ser nulo ni vacío");
        if (mensajeError.isEmpty())
            throw new InvalidParameterException("El mensaje de error no puede ser nulo ni vacío");
        this.mensajeError = mensajeError;
    }
    
    /**
     * Construye una respuesta exitosa.
     */
    public RespuestaGenerica() { }
    
    /**
     * @return true si la petición fue exitosa, false de lo contrario.
     */
    public boolean fuePeticionExitosa() {
        return mensajeError == null;
    }
    
    /**
     * @return el mensaje de error, o null si no existe.
     */
    public String getMensajeError() {
        return mensajeError;
    }
}
