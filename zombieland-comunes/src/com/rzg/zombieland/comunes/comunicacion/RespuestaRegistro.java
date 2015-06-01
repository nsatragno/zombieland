package com.rzg.zombieland.comunes.comunicacion;

/**
 * Representa la respuesta de una petición de registro.
 * @author nicolas
 *
 */
public class RespuestaRegistro {
    // True si el usuario se pudo registrar, false de lo contrario.
    private boolean peticionExitosa;
    
    public RespuestaRegistro(boolean peticionExitosa) {
        this.peticionExitosa = peticionExitosa;
    }

    public boolean getPeticionExitosa() {
        return peticionExitosa;
    }
}
