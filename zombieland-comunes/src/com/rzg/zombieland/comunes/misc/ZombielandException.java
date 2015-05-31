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
    
    public ZombielandException(String mensaje) {
        super(mensaje);
        Log.info("Excepción de Zombieland esperada: ");
        Log.info(mensaje);
    }
    
    public ZombielandException(String mensaje, List<String> errores) {
        super(mensaje);
        this.errores = errores;
        Log.info("Listado de errores: ");
        for (String error : errores)
            Log.info(error);
    }
    
    public List<String> getErrores() {
        return errores;
    }
}
