package com.rzg.zombieland.server.sesion;


/**
 * Define el comportamiento de una clase que maneja la sesión de un jugador para un hilo de escucha
 * particular.
 * @author nicolas
 *
 */
public interface ManejadorSesion {
    /**
     * Establece la sesión que el manejador administra.
     * @param sesion
     */
    public void setSesion(Sesion sesion);
    
    /**
     * @return la sesión administrada, o null si el jugador no inició sesión.
     */
    public Sesion getSesion();
}
