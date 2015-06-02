package com.rzg.zombieland.comunes.controlador;

import java.util.HashSet;
import java.util.Set;

import com.rzg.zombieland.comunes.controlador.Controlador;

/**
 * Un controlador que no hace nada para testing.
 * @author nicolas
 *
 */
public class ControladorTest extends Controlador {
    
    // La última línea que se procesó.
    private static Set<String> lineasProcesadas = new HashSet<String>();
    
    // La línea que se devolverá al procesar.
    private static String lineaDevolucion;
    
    public ControladorTest() { }

    @Override
    public String procesar(String linea) {
        lineasProcesadas.add(linea);
        return lineaDevolucion;
    }
    
    public static String getLineaDevolucion() {
        return lineaDevolucion;
    }

    public static void setLineaDevolucion(String lineaDevolucion) {
        ControladorTest.lineaDevolucion = lineaDevolucion;
    }

    /**
     * Devuelve true si este controlador ha procesado la línea de envío dada.
     * @param lineaEnvio
     * @return
     */
    public static boolean proceso(String lineaEnvio) {
        return lineasProcesadas.contains(lineaEnvio);
    }
}
