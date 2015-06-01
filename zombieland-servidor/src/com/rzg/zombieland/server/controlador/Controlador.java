package com.rzg.zombieland.server.controlador;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
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
     * Devuelve un controlador de acuerdo a la línea leída. 
     * @param linea
     * @return
     * @throws ComandoDesconocidoException 
     */
    public static Controlador crear(int codigo) throws ComandoDesconocidoException {
        switch (codigo) {
        case Enviable.TEST:
            return new ControladorTest();
        case Enviable.REGISTRAR_JUGADOR:
            return new ControladorRegistro();
        default:
            throw new ComandoDesconocidoException(
                    String.format("El código 0x%X no corresponde con "
                                + "ninguno de los comandos conocidos", codigo));
        }
    }
    
    /**
     * Procesa la petición definida por la línea enviada.
     * @param linea
     * @return
     */
    public abstract String procesar(String linea);
}
