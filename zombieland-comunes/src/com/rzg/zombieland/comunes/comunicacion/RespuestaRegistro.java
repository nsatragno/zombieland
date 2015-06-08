package com.rzg.zombieland.comunes.comunicacion;

/**
 * Representa la respuesta de una petición de registro.
 * @author nicolas
 *
 */
public class RespuestaRegistro {
    // True si el usuario se pudo registrar, false de lo contrario.
    private boolean peticionExitosa;
    
    private String mensajeError;
    
    /**
     * Construye una respuesta exitosa.
     */
    public RespuestaRegistro() {
        this.peticionExitosa = true;
    }
    
    /**
     * Construye una respuesta con un mensaje de error.
     * @param mensajeError
     */
    public RespuestaRegistro(String mensajeError) {
        this.peticionExitosa = false;
        this.mensajeError = mensajeError;
    }
    
    public boolean fuePeticionExitosa() {
        return peticionExitosa;
    }
    
    public String getMensajeError() {
        return mensajeError;
    }
}
