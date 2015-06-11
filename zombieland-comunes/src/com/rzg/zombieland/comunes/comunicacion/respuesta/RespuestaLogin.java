package com.rzg.zombieland.comunes.comunicacion.respuesta;

import java.security.InvalidParameterException;


/**
 * Representa la respuesta de una petición de inicio de sesión.
 * @author nicolas
 *
 */
public class RespuestaLogin {
    // Mensaje de error. null si la petición fue exitosa.
    private String mensajeError;
    
    /**
     * Construye una respuesta con error.
     * @param mensajeError 
     */
    public RespuestaLogin(String mensajeError) {
        if (mensajeError == null)
            throw new NullPointerException("El mensaje de error no puede ser nulo ni vacío");
        if (mensajeError.isEmpty())
            throw new InvalidParameterException("El mensaje de error no puede ser nulo ni vacío");
        this.mensajeError = mensajeError;
    }
    
    /**
     * Construye una respuesta exitosa.
     */
    public RespuestaLogin() { }
    
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
