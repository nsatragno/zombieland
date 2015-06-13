package com.rzg.zombieland.server.controlador;

import org.junit.Assert;

import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.server.sesion.Jugador;
import com.rzg.zombieland.server.sesion.ManejadorSesion;
import com.rzg.zombieland.server.sesion.Sesion;

class ManejadorSesionImpl implements ManejadorSesion {

    private Sesion sesion;
    
    @Override
    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
    }

    @Override
    public Sesion getSesion() {
        return sesion;
    }
    
    /**
     * Crea una sesión para pruebas.
     */
    public void crearSesion() {
        try {
            sesion = new Sesion(new Jugador("a", "b", "b", "d", "e"));
        } catch (ParametrosNoValidosException e) {
            Assert.fail(e.getMensaje());
        }
    }
    
}