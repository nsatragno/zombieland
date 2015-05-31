package com.rzg.zombieland.server.controlador;

/**
 * Un controlador que no hace nada para testing.
 * @author nicolas
 *
 */
public class ControladorVacio extends Controlador {
    
    // La última línea que se procesó.
    private static String ultimaLineaProcesada;
    
    // La línea que se devolverá al procesar.
    private static String lineaDevolucion;
    
    public ControladorVacio() {
    }

    @Override
    public String procesar(String linea) {
        ultimaLineaProcesada = linea;
        return lineaDevolucion;
    }
    
    public static String getLineaDevolucion() {
        return lineaDevolucion;
    }

    public static void setLineaDevolucion(String lineaDevolucion) {
        ControladorVacio.lineaDevolucion = lineaDevolucion;
    }

    public static String getUltimaLineaProcesada() {
        return ultimaLineaProcesada;
    }
}
