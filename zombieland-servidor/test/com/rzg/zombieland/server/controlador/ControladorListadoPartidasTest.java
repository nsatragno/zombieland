package com.rzg.zombieland.server.controlador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaListadoPartidas;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.server.comunicacion.controlador.ControladorConSesion;
import com.rzg.zombieland.server.comunicacion.controlador.ControladorListadoPartidas;
import com.rzg.zombieland.server.meta.ServicioPartidas;

/**
 * Verifica el correcto funcionamiento del controlador de listado de partidas.
 * @author nicolas
 *
 */
public class ControladorListadoPartidasTest extends AbstractPartidasTest {

    
    /**
     * Prueba recuperar partidas cuando no hay ninguna.
     * @throws ParametrosNoValidosException 
     */
    @Test
    public void testSinPartidas() throws ParametrosNoValidosException {
        assertEquals(0, partidasCreadas.size());
        assertEquals(0, ServicioPartidas.getInstancia().getPartidas().size());
        
        Gson gson = new Gson();
        ManejadorSesionImpl manejador = new ManejadorSesionImpl();
        manejador.crearSesion();
        ControladorListadoPartidas controlador = new ControladorListadoPartidas(manejador);
        RespuestaListadoPartidas respuesta = gson.fromJson(controlador.procesar(gson.toJson(null)),
                                                           RespuestaListadoPartidas.class);
        Assert.assertTrue(respuesta.getPartidas().isEmpty());
    }
    
    /**
     * Prueba crear un par de partidas y recuperarlas.
     * @throws ParametrosNoValidosException 
     */
    @Test
    public void testBasico() throws ParametrosNoValidosException {
        final int CANTIDAD_PARTIDAS = 3;
        crearPartidas(CANTIDAD_PARTIDAS);
        
        Gson gson = new Gson();
        ManejadorSesionImpl manejador = new ManejadorSesionImpl();
        manejador.crearSesion();
        ControladorListadoPartidas controlador = new ControladorListadoPartidas(manejador);
        RespuestaListadoPartidas respuesta = gson.fromJson(controlador.procesar(gson.toJson(null)),
                                                           RespuestaListadoPartidas.class);
        for (POJOPartida partida : partidasCreadas)
            assertTrue(respuesta.getPartidas().contains(partida));
        assertTrue(respuesta.fuePeticionExitosa());
    }
    
    /**
     * Prueba crear diez mil partidas y recuperarlas.
     * @throws ParametrosNoValidosException 
     */
    @Test
    public void testFatiga() throws ParametrosNoValidosException {
        final int CANTIDAD_PARTIDAS = 10_000;
        crearPartidas(CANTIDAD_PARTIDAS);
        
        Gson gson = new Gson();
        ManejadorSesionImpl manejador = new ManejadorSesionImpl();
        manejador.crearSesion();
        ControladorListadoPartidas controlador = new ControladorListadoPartidas(manejador);
        RespuestaListadoPartidas respuesta = gson.fromJson(controlador.procesar(gson.toJson(null)),
                                                           RespuestaListadoPartidas.class);
        for (POJOPartida partida : partidasCreadas)
            assertTrue(respuesta.getPartidas().contains(partida));
        assertTrue(respuesta.fuePeticionExitosa());
    }
    
    /**
     * Prueba que, en caso de que no haya login, se envíe un mensaje de error.
     * @throws ParametrosNoValidosException 
     */
    @Test
    public void testSinLogin() throws ParametrosNoValidosException {
        Gson gson = new Gson();
        ManejadorSesionImpl manejador = new ManejadorSesionImpl();
        ControladorListadoPartidas controlador = new ControladorListadoPartidas(manejador);
        RespuestaListadoPartidas respuesta = gson.fromJson(controlador.procesar(gson.toJson(null)),
                                                           RespuestaListadoPartidas.class);
        Assert.assertFalse(respuesta.fuePeticionExitosa());
        Assert.assertEquals(ControladorConSesion.MENSAJE_NO_AUTENTICADO, respuesta.getMensajeError());
    }

    private void crearPartidas(final int CANTIDAD_PARTIDAS) throws ParametrosNoValidosException {
        for (int i = 0; i < CANTIDAD_PARTIDAS; i++)
            crearPartida();
        // Verificamos que realmente se hayan creado tres partidas. Esto es un test... del test.
        assertEquals(CANTIDAD_PARTIDAS, partidasCreadas.size());
        assertEquals(CANTIDAD_PARTIDAS, ServicioPartidas.getInstancia().getPartidas().size());
    }
}

