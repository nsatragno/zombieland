package com.rzg.zombieland.server.controlador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaUnirsePartida;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.comunicacion.controlador.ControladorUnirsePartida;
import com.rzg.zombieland.server.meta.EnviaPeticionesImpl;
import com.rzg.zombieland.server.meta.Partida;
import com.rzg.zombieland.server.meta.ServicioPartidas;
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
        String sRespuesta = controlador.procesarAutenticado(gson.toJson(idPartida, String.class));
        RespuestaUnirsePartida respuesta = gson.fromJson(sRespuesta, RespuestaUnirsePartida.class);
        assertTrue("La respuesta no fue exitosa: " + respuesta.getMensajeError(),
                   respuesta.fuePeticionExitosa());
        assertEquals(manejador.getSesion().getPartida().getPOJO(), respuesta.getPartida());
        assertEquals(2, manejador.getSesion().getPartida().getJugadores().size());
    }


    @Test
    public void testUnirseNoExistente() throws ParametrosNoValidosException {
        Gson gson = new Gson();
        ManejadorSesionImpl manejador = new ManejadorSesionImpl();
        manejador.crearSesion();
        
        ControladorUnirsePartida controlador = new ControladorUnirsePartida(manejador);
        String sRespuesta = controlador.procesarAutenticado(
                gson.toJson(UUID.randomUUID().toString(), String.class));
        RespuestaUnirsePartida respuesta = gson.fromJson(sRespuesta, RespuestaUnirsePartida.class);
        assertFalse(respuesta.fuePeticionExitosa());
        assertEquals(ControladorUnirsePartida.MENSAJE_PARTIDA_NO_EXISTENTE, respuesta.getMensajeError());
    }
}
