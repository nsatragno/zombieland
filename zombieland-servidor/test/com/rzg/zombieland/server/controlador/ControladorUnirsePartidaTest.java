package com.rzg.zombieland.server.controlador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOUnirsePartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaUnirsePartida;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.comunicacion.controlador.ControladorUnirsePartida;
import com.rzg.zombieland.server.meta.EnviaPeticionesImpl;
import com.rzg.zombieland.server.meta.Partida;
import com.rzg.zombieland.server.meta.ServicioPartidas;
import com.rzg.zombieland.server.sesion.Jugador;
import com.rzg.zombieland.server.sesion.ServicioSesion;
import com.rzg.zombieland.server.sesion.Sesion;

public class ControladorUnirsePartidaTest extends AbstractPartidasTest {

    @Test
    public void testUnirseExistente() throws ZombielandException {
        Gson gson = new Gson();
        Partida partida = crearPartida();
        Sesion sesion = new Sesion(getUltimoAdmin(), new EnviaPeticionesImpl());
        sesion.setPartida(partida);
        ServicioSesion.getInstancia().addSesion(sesion);
        ManejadorSesionImpl manejador = new ManejadorSesionImpl();
        manejador.crearSesion();
        ServicioPartidas.getInstancia().addPartida(partida);
        
        ControladorUnirsePartida controlador = new ControladorUnirsePartida(manejador);
        String idPartida = partida.getId().toString();
        POJOUnirsePartida pojo = new POJOUnirsePartida(idPartida, false);
        String sRespuesta = controlador.procesarAutenticado(gson.toJson(pojo));
        RespuestaUnirsePartida respuesta = gson.fromJson(sRespuesta, RespuestaUnirsePartida.class);
        assertTrue("La respuesta no fue exitosa: " + respuesta.getMensajeError(),
                   respuesta.fuePeticionExitosa());
        assertEquals(manejador.getSesion().getPartida().getPOJO(getUltimoAdmin()),
                                                                respuesta.getPartida());
        assertEquals(2, manejador.getSesion().getPartida().getJugadores().size());
    }
    
    @Test
    public void testUnirseLlena() throws ZombielandException {
        Gson gson = new Gson();
        Partida partida = crearPartida();
        Sesion sesion = new Sesion(getUltimoAdmin(), new EnviaPeticionesImpl());
        sesion.setPartida(partida);
        ServicioSesion.getInstancia().addSesion(sesion);
        
        
        
        ManejadorSesionImpl manejador = new ManejadorSesionImpl();
        manejador.crearSesion();
        ServicioPartidas.getInstancia().addPartida(partida);
        
        llenarPartida(partida);
        
        ControladorUnirsePartida controlador = new ControladorUnirsePartida(manejador);
        String idPartida = partida.getId().toString();
        POJOUnirsePartida pojo = new POJOUnirsePartida(idPartida, false);
        String sRespuesta = controlador.procesarAutenticado(gson.toJson(pojo));
        RespuestaUnirsePartida respuesta = gson.fromJson(sRespuesta, RespuestaUnirsePartida.class);
        assertFalse(respuesta.fuePeticionExitosa());
        assertEquals(Partida.MENSAJE_PARTIDA_EN_PROGRESO, respuesta.getMensajeError());
    }

    @Test
    public void testObservarLlena() throws ZombielandException {
        Gson gson = new Gson();
        Partida partida = crearPartida();
        Sesion sesion = new Sesion(getUltimoAdmin(), new EnviaPeticionesImpl());
        sesion.setPartida(partida);
        ServicioSesion.getInstancia().addSesion(sesion);
        
        
        
        ManejadorSesionImpl manejador = new ManejadorSesionImpl();
        manejador.crearSesion();
        ServicioPartidas.getInstancia().addPartida(partida);
        
        llenarPartida(partida);
        
        ControladorUnirsePartida controlador = new ControladorUnirsePartida(manejador);
        String idPartida = partida.getId().toString();
        POJOUnirsePartida pojo = new POJOUnirsePartida(idPartida, true);
        String sRespuesta = controlador.procesarAutenticado(gson.toJson(pojo));
        RespuestaUnirsePartida respuesta = gson.fromJson(sRespuesta, RespuestaUnirsePartida.class);
        assertTrue(respuesta.fuePeticionExitosa());
    }
    
    private void llenarPartida(Partida partida) throws ParametrosNoValidosException,
            ZombielandException {
        while (partida.puedenUnirseJugadores()) {
            Jugador otro = crearJugador();
            Sesion otraSesion = new Sesion(otro, new EnviaPeticionesImpl());
            otraSesion.setPartida(partida);
            ServicioSesion.getInstancia().addSesion(otraSesion);
            partida.addJugador(otro);
        }
    }

    @Test
    public void testUnirseNoExistente() throws ParametrosNoValidosException {
        Gson gson = new Gson();
        ManejadorSesionImpl manejador = new ManejadorSesionImpl();
        manejador.crearSesion();
        
        ControladorUnirsePartida controlador = new ControladorUnirsePartida(manejador);
        POJOUnirsePartida pojo = new POJOUnirsePartida(UUID.randomUUID().toString(), false);
        String sRespuesta = controlador.procesarAutenticado(gson.toJson(pojo));
        RespuestaUnirsePartida respuesta = gson.fromJson(sRespuesta, RespuestaUnirsePartida.class);
        assertFalse(respuesta.fuePeticionExitosa());
        assertEquals(ControladorUnirsePartida.MENSAJE_PARTIDA_NO_EXISTENTE, respuesta.getMensajeError());
    }
}
