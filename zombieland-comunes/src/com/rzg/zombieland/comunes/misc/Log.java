package com.rzg.zombieland.comunes.misc;

/**
 * Maneja un log de la aplicación.
 * TODO implementar log4j.
 * @author nicolas
 *
 */
public abstract class Log {
    public static void debug(String mensaje) {
        System.out.println("[DEBUG] " + mensaje);
    }
    
    public static void info(String mensaje) {
        System.out.println("[INFO] " + mensaje);
    }

    public static void error(String mensaje) {
        System.err.println("[ERROR] " + mensaje);
    }
}

