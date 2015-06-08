package com.rzg.zombieland.cliente.comunicacion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.jdeferred.DoneCallback;
import org.junit.Test;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.controlador.ControladorTest;
import com.rzg.zombieland.comunes.misc.ZombielandException;

public class PeticionTest extends PeticionTestHarness {
    
    private final static String MENSAJE_TEST = "Mensaje test! :D";
    
    private static class ObjetoPeticionTest extends Peticion<String> {
        
        private String mensajeTest;
        
        public ObjetoPeticionTest(String mensajeTest) {
            this.mensajeTest = mensajeTest;
        }
        
        @Override
        protected String getMensajePeticion() {
            return mensajeTest;
        }

        @Override
        protected int getCodigoPeticion() {
            return Enviable.TEST;
        }

        @Override
        protected String generarRespuesta(String respuesta) {
            return respuesta;
        }
    }

    @Test
    public void testPeticionSimple() throws ZombielandException, InterruptedException, ExecutionException {
    	final CountDownLatch latch = new CountDownLatch(1);
        ObjetoPeticionTest peticion = new ObjetoPeticionTest(MENSAJE_TEST);
        ServicioCliente.getInstancia().getHiloEscucha().enviarPeticion(peticion);
        peticion.getRespuesta().then(new DoneCallback<String>() {

			@Override
			public void onDone(String arg0) {
				assertEquals(MENSAJE_TEST, arg0);
				assertTrue(ControladorTest.proceso(MENSAJE_TEST));
				latch.countDown();
			}
		});
        latch.await(1, TimeUnit.SECONDS);
    }
    
    @Test
    public void testPeticionesFatiga() throws ZombielandException, InterruptedException, ExecutionException {
        Random random = new Random();
        final CountDownLatch latch = new CountDownLatch(1000);
        for (int i = 0; i < 1000; i++) {
            final String mensaje = Integer.toString(random.nextInt());
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
        latch.await(1, TimeUnit.SECONDS);
        if (latch.getCount() != 0)
            fail("No retornaron todas las peticiones");
    }
}
