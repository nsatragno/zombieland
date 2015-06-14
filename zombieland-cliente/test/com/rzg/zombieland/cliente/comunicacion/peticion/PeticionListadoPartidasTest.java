package com.rzg.zombieland.cliente.comunicacion.peticion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.jdeferred.DoneCallback;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.rzg.zombieland.cliente.comunicacion.peticion.PeticionListadoPartidas;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOCreacionPartida;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaListadoPartidas;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;

/**
 * Verifica el correcto funcionamiento de una petición de listado de partidas.
 * @author nicolas
 *
 */
public class PeticionListadoPartidasTest {

    /**
     * Simplemente prueba que se pueda obtener el mensaje de petición, aún cuando no hay objeto
     * enviable.
     */
    @Test
    public void testGetMensajePeticion() {
        PeticionListadoPartidas peticion = new PeticionListadoPartidas();
        Assert.assertEquals("null", peticion.getMensajePeticion());
    }
    
    /**
     * Prueba que el listado de POJOs se parsee correctamente.
     * @throws ParametrosNoValidosException 
     * @throws InterruptedException 
     */
    @Test
    public void testObtenerRespuestaPeticion() throws ParametrosNoValidosException, InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        PeticionListadoPartidas peticion = new PeticionListadoPartidas();
        final List<POJOPartida> pojos = new ArrayList<POJOPartida>();
        
        pojos.add(new POJOPartida(new POJOCreacionPartida(5, 5, "a"), "b"));
        pojos.add(new POJOPartida(new POJOCreacionPartida(10, 10, "c"), "d"));
        RespuestaListadoPartidas respuesta = new RespuestaListadoPartidas(pojos);
        
        peticion.procesarRespuesta(new Gson().toJson(respuesta));
        peticion.getRespuesta().then(new DoneCallback<RespuestaListadoPartidas>() {
            
            @Override
            public void onDone(RespuestaListadoPartidas respuesta) {
                Assert.assertArrayEquals(pojos.toArray(), respuesta.getPartidas().toArray());
                latch.countDown();
            }
        });
        Assert.assertTrue(latch.await(1, TimeUnit.SECONDS));
    }
    
    /**
     * Prueba que un listado de POJOs vacío parsee correctamente.
     * @throws ParametrosNoValidosException 
     * @throws InterruptedException 
     */
    @Test
    public void testObtenerRespuestaPeticionVacia() throws ParametrosNoValidosException, InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);
        PeticionListadoPartidas peticion = new PeticionListadoPartidas();
        final List<POJOPartida> pojos = new ArrayList<POJOPartida>();
        RespuestaListadoPartidas respuesta = new RespuestaListadoPartidas(pojos);
        
        peticion.procesarRespuesta(new Gson().toJson(respuesta));
        peticion.getRespuesta().then(new DoneCallback<RespuestaListadoPartidas>() {
            
            @Override
            public void onDone(RespuestaListadoPartidas respuesta) {
                Assert.assertTrue(respuesta.fuePeticionExitosa());
                Assert.assertEquals(0, respuesta.getPartidas().size());
                latch.countDown();
            }
        });
        Assert.assertTrue(latch.await(1, TimeUnit.SECONDS));
    }
}
