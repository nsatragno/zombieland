package com.rzg.zombieland.server.meta;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.controlador.AbstractPartidasTest;
import com.rzg.zombieland.server.sesion.Jugador;
import com.rzg.zombieland.server.sesion.ServicioSesion;
import com.rzg.zombieland.server.sesion.Sesion;

public class ServicioPartidasTest extends AbstractPartidasTest {

    @Test
    public void testGetPartidaAleatoria() throws ZombielandException {
        ServicioPartidas servicio = ServicioPartidas.getInstancia();
        assertNull(servicio.getPartidaAleatoria());
        Partida partida = crearPartida();
        assertEquals(partida, servicio.getPartidaAleatoria());
        
        Sesion sesion = new Sesion(getUltimoAdmin(), new EnviaPeticionesImpl());
        sesion.setPartida(partida);
        ServicioSesion.getInstancia().addSesion(sesion);
        
        Partida partidaLlena = crearPartida();
        sesion = new Sesion(getUltimoAdmin(), new EnviaPeticionesImpl());
        sesion.setPartida(partida);
        ServicioSesion.getInstancia().addSesion(sesion);
        
        ServicioSesion.getInstancia().addSesion(sesion);
        for (int i = 1; i < getUltimaCantidadJugadores(); i++) {
            assertTrue(partidaLlena.puedenUnirseJugadores());
            Jugador jugador = crearJugador();
            sesion = new Sesion(jugador, new EnviaPeticionesImpl());
            sesion.setPartida(partidaLlena);
            ServicioSesion.getInstancia().addSesion(sesion);
            partidaLlena.addJugador(jugador);
        }
        assertFalse(partidaLlena.puedenUnirseJugadores());
        
        for (int i = 0; i < 100; i++)
            assertEquals(partida, servicio.getPartidaAleatoria());
        
        for (int i = 0; i < 100; i++)
            crearPartida();
        
        assertNotNull(servicio.getPartidaAleatoria());
    }

}
