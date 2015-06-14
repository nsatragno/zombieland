package com.rzg.zombieland.server.controlador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.UUID;

import org.junit.Test;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOCreacionPartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaUnirsePartida;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.server.comunicacion.controlador.ControladorUnirsePartida;
import com.rzg.zombieland.server.meta.Partida;
import com.rzg.zombieland.server.meta.ServicioPartidas;

public class ControladorUnirsePartidaTest extends AbstractPartidasTest {

    @Test
    public void testUnirseExistente() throws ParametrosNoValidosException {
        Gson gson = new Gson();
        crearPartida();
        ManejadorSesionImpl manejador = new ManejadorSesionImpl();
        manejador.crearSesion();
        POJOCreacionPartida pojo = new POJOCreacionPartida(4, 4, "comer cerebros");
        Partida partida = new Partida(manejador.getSesion().getJugador(), pojo);
        manejador.getSesion().setPartida(partida);
        ServicioPartidas.getInstancia().addPartida(partida);
        
        ControladorUnirsePartida controlador = new ControladorUnirsePartida(manejador);
        String idPartida = manejador.getSesion().getPartida().getId().toString();
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
