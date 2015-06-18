package com.rzg.zombieland.server.sesion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.server.meta.EnviaPeticionesImpl;

public class ServicioSesionTest {

    private ServicioSesion servicio;
    
    @Before
    public void setUp() {
        servicio = ServicioSesion.getInstancia();
    }
    
    @After
    public void tearDown() {
        ServicioSesion.matarInstancia();
    }
    
    @Test
    public void testAgregar() throws ParametrosNoValidosException {
        Jugador jugador = new Jugador("nombre", "1234", "1234", "test", "a");
        assertNull(servicio.getSesion(jugador));
        Sesion sesion = new Sesion(jugador, new EnviaPeticionesImpl());
        servicio.addSesion(sesion);
        assertEquals(sesion, servicio.getSesion(jugador));
    }

    @Test
    public void testRemover() throws ParametrosNoValidosException {
        Jugador jugador = new Jugador("nombre", "1234", "1234", "test", "a");
        assertNull(servicio.getSesion(jugador));
        Sesion sesion = new Sesion(jugador, new EnviaPeticionesImpl());
        servicio.addSesion(sesion);
        assertEquals(sesion, servicio.getSesion(jugador));
        sesion.cerrar();
        assertNull(servicio.getSesion(jugador));
    }

}
