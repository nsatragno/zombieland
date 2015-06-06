package com.rzg.zombieland.comunes.misc;

import java.util.ArrayList;
import java.util.List;

/**
 * Maneja un log de la aplicación.
 * TODO implementar log4j.
 * @author nicolas
 *
 */
public abstract class Log {
	private static List<EscuchaLog> escuchadores = new ArrayList<EscuchaLog>();
	
	public static final int DEBUG = 0;
	public static final int INFO = 1;
	public static final int ERROR = 2;
	
	public static void agregarEscuchador(EscuchaLog escuchador) {
		escuchadores.add(escuchador);
	}
	
    public static void debug(Object mensaje) {
    	String imprimir = "[DEBUG] " + mensaje;
    	for (EscuchaLog escuchador : escuchadores)
    		escuchador.onLog(imprimir, DEBUG);
        System.out.println(imprimir);
    }
    
    public static void info(Object mensaje) {
    	String imprimir = "[INFO] " + mensaje;
    	for (EscuchaLog escuchador : escuchadores)
    		escuchador.onLog(imprimir, INFO);
        System.out.println(imprimir);
    }

    public static void error(Object mensaje) {
    	String imprimir = "[ERROR] " + mensaje;
    	for (EscuchaLog escuchador : escuchadores)
    		escuchador.onLog(imprimir, ERROR);
        System.err.println(imprimir);
    }
}

