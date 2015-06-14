package com.rzg.zombieland.cliente.comunicacion.peticion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.jdeferred.DoneCallback;
import org.junit.Test;

import com.rzg.zombieland.cliente.comunicacion.ServicioCliente;
import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.controlador.ControladorTest;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Tests de peticiones que integran cliente y servidor.
 * 
 * @author nicolas
 */
public class PeticionTest extends PeticionTestHarness {
    
    private final static String MENSAJE_TEST = "'Mensaje test! :D'";
    private final static String RESPUESTA_TEST = "Mensaje test! :D";
    
    private static class ObjetoPeticionTest extends Peticion<String, String> {
        
        private String mensajeTest;
        
        public ObjetoPeticionTest(String mensajeTest) {
            super(mensajeTest, String.class);
            this.mensajeTest = mensajeTest;
        }
        
        public String getMensajePeticion() {
            return mensajeTest;
        }

        @Override
        protected int getCodigoPeticion() {
            return Enviable.TEST;
        }
    }

    /**
     * @throws ZombielandException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void testPeticionSimple() throws ZombielandException, InterruptedException, ExecutionException {
    	final CountDownLatch latch = new CountDownLatch(1);
        ObjetoPeticionTest peticion = new ObjetoPeticionTest(MENSAJE_TEST);
        ServicioCliente.getInstancia().getHiloEscucha().enviarPeticion(peticion);
        peticion.getRespuesta().then(new DoneCallback<String>() {

			@Override
			public void onDone(String arg0) {
				assertEquals(RESPUESTA_TEST, arg0);
				assertTrue(ControladorTest.proceso(MENSAJE_TEST));
				latch.countDown();
			}
		});
        assertTrue(latch.await(1, TimeUnit.SECONDS));
    }
    
    /**
     * @throws ZombielandException
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void testPeticionesFatiga() throws ZombielandException, InterruptedException, ExecutionException {
        final int PETICIONES = 1000; 
        final CountDownLatch latch = new CountDownLatch(PETICIONES);
        for (int i = 0; i < PETICIONES; i++) {
            final String mensaje = UUID.randomUUID().toString();
            ObjetoPeticionTest peticion = new ObjetoPeticionTest(mensaje);
            ServicioCliente.getInstancia().getHiloEscucha().enviarPeticion(peticion);
            peticion.getRespuesta().then(new DoneCallback<String>() {
				@Override
				public void onDone(String mensajeRecibido) {
					assertEquals(mensaje, mensajeRecibido);
                    latch.countDown();
				}
			});
        }
        assertTrue(latch.await(15, TimeUnit.SECONDS));
    }
}
