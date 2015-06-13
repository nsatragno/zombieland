package com.rzg.zombieland.comunes.misc;

import java.util.List;

/**
 * Excepción con un mensaje amigable para el usuario.
 * @author nicolas
 *
 */
public class ZombielandException extends Exception {
    private static final long serialVersionUID = 2748734264163995657L;

    private List<String> errores;
    
    /**
     * Construye una excepción.
     * @param mensaje - el mensaje de error.
     */
    public ZombielandException(String mensaje) {
        super(mensaje);
        Log.info("Excepción de Zombieland esperada: ");
        Log.info(mensaje);
    }
    
    /**
     * Construye una excepción.
     * @param mensaje - el mensaje de error.
     * @param errores - el listado de mensajes de error.
     */
    public ZombielandException(String mensaje, List<String> errores) {
        super(mensaje);
        this.errores = errores;
        Log.info("Listado de errores: ");
        for (String error : errores)
            Log.info(error);
    }
    
    /**
     * @return el listado de errores.
     */
    public List<String> getErrores() {
        return errores;
    }
}
