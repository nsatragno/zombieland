package com.rzg.zombieland.server.controlador;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.respuesta.POJOCreacionPartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.meta.ServicioPartidas;

/**
 * Verifica el correcto funcionamiento del controlador de creación de partida.
 * @author nicolas
 *
 */
public class ControladorCrearPartidaTest {

    /**
     * Devuelve al estado inicial al servicio de partidas.
     */
    @After
    public void tearDown() {
        ServicioPartidas.matarInstancia();
    }
    
    /**
     * Test básico de creación de partida.
     * @throws ZombielandException
     */
    @Test
    public void test() throws ZombielandException {
        // Inicialización.
        ManejadorSesionImpl manejador = new ManejadorSesionImpl();
        manejador.crearSesion();
        ControladorCrearPartida controlador = new ControladorCrearPartida(manejador);
        Gson gson = new Gson();
        POJOCreacionPartida pojo = new POJOCreacionPartida(10, 5, "comer cerebros");
        
        Assert.assertNull(manejador.getSesion().getPartida());
        
        String respuesta = controlador.procesar(gson.toJson(pojo));
        RespuestaGenerica objetoRespuesta = gson.fromJson(respuesta, RespuestaGenerica.class);
        
        Assert.assertTrue(objetoRespuesta.fuePeticionExitosa());
        Assert.assertNotNull(manejador.getSesion().getPartida());
        Assert.assertEquals(1, ServicioPartidas.getInstancia().getPartidas().size());
    }

}
