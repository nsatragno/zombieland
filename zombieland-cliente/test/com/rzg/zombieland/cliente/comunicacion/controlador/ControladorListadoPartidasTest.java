package com.rzg.zombieland.cliente.comunicacion.controlador;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.gson.Gson;
import com.rzg.zombieland.cliente.meta.Estado;
import com.rzg.zombieland.cliente.meta.Estado.EscuchadorPartidas;
import com.rzg.zombieland.comunes.comunicacion.respuesta.POJOListadoPartidas;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.server.controlador.AbstractPartidasTest;
import com.rzg.zombieland.server.meta.ServicioPartidas;

/**
 * Verifica el correcto funcionamiento del controlador de listado de partidas.
 * @author nicolas
 *
 */
public class ControladorListadoPartidasTest extends AbstractPartidasTest {

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
        assertEquals(0, pojoPartidasCreadas.size());
        assertEquals(0, ServicioPartidas.getInstancia().getPartidas().size());
        
        Gson gson = new Gson();
        ControladorListadoPartidas controlador = new ControladorListadoPartidas();
        RespuestaGenerica respuesta = 
                gson.fromJson(controlador.procesar(
                                gson.toJson(new POJOListadoPartidas(pojoPartidasCreadas))),
                              RespuestaGenerica.class);
        Assert.assertTrue(respuesta.fuePeticionExitosa());
        Assert.assertNotNull(partidasRecibidas);
        Assert.assertEquals(pojoPartidasCreadas, partidasRecibidas.getPartidas());
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
        ControladorListadoPartidas controlador = new ControladorListadoPartidas();
        RespuestaGenerica respuesta = 
                gson.fromJson(controlador.procesar(
                                gson.toJson(new POJOListadoPartidas(pojoPartidasCreadas))),
                              RespuestaGenerica.class);
        Assert.assertTrue(respuesta.fuePeticionExitosa());
        Assert.assertNotNull(partidasRecibidas);
        Assert.assertEquals(pojoPartidasCreadas, partidasRecibidas.getPartidas());
    }
    
    private void crearPartidas(final int CANTIDAD_PARTIDAS) throws ParametrosNoValidosException {
        for (int i = 0; i < CANTIDAD_PARTIDAS; i++)
            crearPartida();
        // Verificamos que realmente se hayan creado tres partidas. Esto es un test... del test.
        assertEquals(CANTIDAD_PARTIDAS, pojoPartidasCreadas.size());
        assertEquals(CANTIDAD_PARTIDAS, ServicioPartidas.getInstancia().getPartidas().size());
    }
}

