package com.rzg.zombieland.cliente.comunicacion.peticion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.jdeferred.DoneCallback;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOCreacionPartida;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.POJOListadoPartidas;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.server.comunicacion.peticion.PeticionListadoPartidas;

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
        PeticionListadoPartidas peticion = new PeticionListadoPartidas(null);
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
        PeticionListadoPartidas peticion = new PeticionListadoPartidas(null);
        final List<POJOPartida> pojos = new ArrayList<POJOPartida>();
        
        pojos.add(new POJOPartida(new POJOCreacionPartida(5, 5, "a"), "b"));
        pojos.add(new POJOPartida(new POJOCreacionPartida(10, 10, "c"), "d"));
        POJOListadoPartidas respuesta = new POJOListadoPartidas(pojos);
        
        peticion.procesarRespuesta(new Gson().toJson(respuesta));
        peticion.getRespuesta().then(new DoneCallback<RespuestaGenerica>() {
            
            @Override
            public void onDone(RespuestaGenerica respuesta) {
                Assert.assertTrue(respuesta.fuePeticionExitosa());
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
        PeticionListadoPartidas peticion = new PeticionListadoPartidas(null);
        final List<POJOPartida> pojos = new ArrayList<POJOPartida>();
        POJOListadoPartidas respuesta = new POJOListadoPartidas(pojos);
        
        peticion.procesarRespuesta(new Gson().toJson(respuesta));
        peticion.getRespuesta().then(new DoneCallback<RespuestaGenerica>() {
            
            @Override
            public void onDone(RespuestaGenerica respuesta) {
                Assert.assertTrue(respuesta.fuePeticionExitosa());
                latch.countDown();
            }
        });
        Assert.assertTrue(latch.await(1, TimeUnit.SECONDS));
    }
}
