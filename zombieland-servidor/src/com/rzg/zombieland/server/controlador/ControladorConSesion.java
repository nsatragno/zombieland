package com.rzg.zombieland.server.controlador;

import com.rzg.zombieland.comunes.controlador.Controlador;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.sesion.ManejadorSesion;
import com.rzg.zombieland.server.sesion.Sesion;

/**
 * Define un controlador que requiere del inicio de sesión para operar.
 * @author nicolas
 *
 */
public abstract class ControladorConSesion extends Controlador {

    private ManejadorSesion manejadorSesion;
    
    /**
     * Construye un controlador con sesión.
     * @param manejadorSesion - el manejador de la sesión.
     * @throws ZombielandException si la sesión es null (es decir, el jugador no ha iniciado 
     *         sesión).
     */
    public ControladorConSesion(ManejadorSesion manejadorSesion) throws ZombielandException {
        if (manejadorSesion.getSesion() == null)
            throw new ZombielandException("La acción requiere del inicio de sesión");
        this.manejadorSesion = manejadorSesion; 
    }
    
    /**
     * @return la sesión del jugador.
     */
    protected Sesion getSesion() {
        return manejadorSesion.getSesion();
    }
}
