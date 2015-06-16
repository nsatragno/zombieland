package com.rzg.zombieland.server.meta;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOCreacionPartida;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.controlador.AbstractPartidasTest;
import com.rzg.zombieland.server.sesion.Jugador;
import com.rzg.zombieland.server.sesion.ServicioSesion;
import com.rzg.zombieland.server.sesion.Sesion;

public class PartidaTest extends AbstractPartidasTest {

    @Test
    public void testCrearPartida() throws ParametrosNoValidosException {
        crearPartida();
        Partida partida = partidasCreadas.get(0);
        assertEquals(1, partida.getJugadores().size());
        assertEquals(getUltimoAdmin(), partida.getJugadores().get(0));
        assertEquals(getUltimoAdmin(), partida.getAdministrador());
        assertEquals(1, partida.getJugadores().size());
        assertEquals(getUltimaCantidadRondas(), partida.getCantidadRondas());
        assertEquals(getUltimaCantidadJugadores(), partida.getMaximoJugadores());
        assertEquals(getUltimoNombre(), partida.getNombre());
    }
    
    @Test
    public void testGetPojo() throws ParametrosNoValidosException {
        crearPartida();
        Partida partida = partidasCreadas.get(0);
        POJOCreacionPartida pojoCreacion = 
                new POJOCreacionPartida(getUltimaCantidadRondas(),
                                        getUltimaCantidadJugadores(), 
                                        getUltimoNombre());
        POJOPartida pojo = new POJOPartida(pojoCreacion, getUltimoAdmin().getNombre());
        assertEquals(pojo, partida.getPOJO());
    }
    
    @Test
    public void testNotificar() throws ZombielandException {
        crearPartida();
        Jugador otroJugador = crearJugador();
        EnviaPeticionesImpl enviaPeticiones = new EnviaPeticionesImpl();
        Sesion sesion = new Sesion(getUltimoAdmin(), enviaPeticiones);
        ServicioSesion.getInstancia().addSesion(sesion);
        
        Partida partida = partidasCreadas.get(0);
        sesion.setPartida(partida);
        partida.addJugador(otroJugador);
        POJOPartida pojo = 
                new Gson().fromJson(enviaPeticiones.getUltimaPeticion().getMensajePeticion(), 
                                    POJOPartida.class);
        assertEquals(pojo, partida.getPOJO());
        
        partida.removerJugador(otroJugador);
        pojo = new Gson().fromJson(enviaPeticiones.getUltimaPeticion().getMensajePeticion(), 
                                   POJOPartida.class);
        assertEquals(pojo, partida.getPOJO());
    }
    
    @Test
    public void testAgregarJugadoresDeMas() throws ZombielandException {
        crearPartida();
        Partida partida = partidasCreadas.get(0);
        
        Sesion sesion = new Sesion(getUltimoAdmin(), new EnviaPeticionesImpl());
        sesion.setPartida(partida);
        ServicioSesion.getInstancia().addSesion(sesion);
        
        for (int i = 1; i < getUltimaCantidadJugadores(); i++) {
            Jugador jugador = crearJugador();
            sesion = new Sesion(jugador, new EnviaPeticionesImpl());
            sesion.setPartida(partida);
            ServicioSesion.getInstancia().addSesion(sesion);
            partida.addJugador(jugador);
        }
        try {
            partida.addJugador(crearJugador());
            fail("Debería haber lanzado una excepción");
        } catch (ZombielandException e) {
            // Esperada.
        }
    }
    
    @Test
    public void testQuitarJugadorQueNoEstaba() throws ZombielandException {
        crearPartida();
        Partida partida = partidasCreadas.get(0);
        
        Sesion sesion = new Sesion(getUltimoAdmin(), new EnviaPeticionesImpl());
        sesion.setPartida(partida);
        ServicioSesion.getInstancia().addSesion(sesion);
        
        try {
            partidasCreadas.get(0).removerJugador(crearJugador());
            fail("Debería haber lanzado una excepción");
        } catch (ZombielandException e) {
            // Esperada.
        }
    }
}
