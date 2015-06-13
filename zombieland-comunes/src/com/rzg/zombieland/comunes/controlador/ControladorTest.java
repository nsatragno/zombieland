package com.rzg.zombieland.comunes.controlador;

import java.util.HashSet;
import java.util.Set;

/**
 * Un controlador que devuelve lo mismo que se envió para testing.
 * @author nicolas
 *
 */
public class ControladorTest extends Controlador {
    
    // La última línea que se procesó.
    private static Set<String> lineasProcesadas = new HashSet<String>();

    @Override
    public String procesar(String linea) {
        lineasProcesadas.add(linea);
        return linea;
    }
    
    /**
     * @param lineaEnvio
     * @return true si este controlador ha procesado la línea de envío dada.
     */
    public static boolean proceso(String lineaEnvio) {
        return lineasProcesadas.contains(lineaEnvio);
    }
}
