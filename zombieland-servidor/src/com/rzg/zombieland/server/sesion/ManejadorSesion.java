package com.rzg.zombieland.server.sesion;

import com.rzg.zombieland.server.sesion.Sesion.SesionListener;


/**
 * Define el comportamiento de una clase que maneja la sesión de un jugador para un hilo de escucha
 * particular.
 * @author nicolas
 *
 */
public interface ManejadorSesion extends SesionListener {
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
