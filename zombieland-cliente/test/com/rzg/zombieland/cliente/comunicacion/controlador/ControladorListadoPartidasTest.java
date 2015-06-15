package com.rzg.zombieland.cliente.comunicacion.controlador;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.rzg.zombieland.cliente.meta.Estado;
import com.rzg.zombieland.cliente.meta.Estado.EscuchadorPartidas;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOCreacionPartida;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.POJOListadoPartidas;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;

/**
 * Verifica el correcto funcionamiento del controlador de listado de partidas.
 * @author nicolas
 *
 */
public class ControladorListadoPartidasTest {

    private static POJOListadoPartidas partidasRecibidas;
    
    @BeforeClass
    public static void setUp() {
        Estado.getInstancia().addEscuchadorPartidas(new EscuchadorPartidas() {
            
            @Override
            public void notificarPartidasActualizadas(POJOListadoPartidas respuesta) {
                partidasRecibidas = respuesta;
            }
        });
    }
    
    /**
     * Prueba recuperar partidas cuando no hay ninguna.
     * @throws ParametrosNoValidosException 
     */
    @Test
    public void testSinPartidas() throws ParametrosNoValidosException {
        List<POJOPartida> partidas = new ArrayList<POJOPartida>();
        Gson gson = new Gson();
        ControladorListadoPartidas controlador = new ControladorListadoPartidas();
        RespuestaGenerica respuesta = 
                gson.fromJson(controlador.procesar(
                                gson.toJson(new POJOListadoPartidas(partidas))),
                              RespuestaGenerica.class);
        Assert.assertTrue(respuesta.fuePeticionExitosa());
        Assert.assertNotNull(partidasRecibidas);
        Assert.assertEquals(partidas, partidasRecibidas.getPartidas());
    }
    
    /**
     * Prueba crear un par de partidas y recuperarlas.
     * @throws ParametrosNoValidosException 
     */
    @Test
    public void testBasico() throws ParametrosNoValidosException {
        List<POJOPartida> partidas = new ArrayList<POJOPartida>();
        final int CANTIDAD_PARTIDAS = 3;
        for (int i = 0; i < CANTIDAD_PARTIDAS; i++) {
            partidas.add(new POJOPartida(new POJOCreacionPartida(5, 5, "test"), "juan"));
        }
        
        Gson gson = new Gson();
        ControladorListadoPartidas controlador = new ControladorListadoPartidas();
        RespuestaGenerica respuesta = 
                gson.fromJson(controlador.procesar(
                                gson.toJson(new POJOListadoPartidas(partidas))),
                              RespuestaGenerica.class);
        Assert.assertTrue(respuesta.fuePeticionExitosa());
        Assert.assertNotNull(partidasRecibidas);
        Assert.assertEquals(partidas, partidasRecibidas.getPartidas());
    }
}

