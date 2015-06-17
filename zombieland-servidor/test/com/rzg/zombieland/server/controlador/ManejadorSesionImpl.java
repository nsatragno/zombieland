package com.rzg.zombieland.server.controlador;

import org.junit.Assert;

import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.server.meta.EnviaPeticionesImpl;
import com.rzg.zombieland.server.sesion.Jugador;
import com.rzg.zombieland.server.sesion.ManejadorSesion;
import com.rzg.zombieland.server.sesion.Sesion;

class ManejadorSesionImpl implements ManejadorSesion {

    private Sesion sesion;
    private boolean sesionCerrada = false;
    
    /**
     * @return true si la sesión fue cerrada, false de lo contrario.
     */
    public boolean getSesionCerrada() {
        return sesionCerrada;
    }
    
    @Override
    public void setSesion(Sesion sesion) {
        this.sesion = sesion;
        sesion.addListener(this);
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
            sesion = new Sesion(new Jugador("a", "b", "b", "d", "e"), new EnviaPeticionesImpl());
        } catch (ParametrosNoValidosException e) {
            Assert.fail(e.getMensaje());
        }
    }

    @Override
    public void notificarSesionCerrada(Sesion sesion) {
        sesionCerrada = true;
        this.sesion = null;
    }
    
}