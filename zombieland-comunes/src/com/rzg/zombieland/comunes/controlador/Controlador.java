package com.rzg.zombieland.comunes.controlador;

import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Responde a una acción del cliente.
 * @author nicolas
 *
 */
public abstract class Controlador {
    public static class ComandoDesconocidoException extends ZombielandException {
        private static final long serialVersionUID = -8914691837771387774L;

        public ComandoDesconocidoException(String error) {
            super(error);
        }
    }
    
    /**
     * Procesa la petición definida por la línea enviada.
     * @param linea
     * @return
     */
    public abstract String procesar(String linea);
}
