package com.rzg.zombieland.cliente.comunicacion;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.jdeferred.DoneCallback;
import org.junit.Assert;
import org.junit.Test;

import com.rzg.zombieland.comunes.comunicacion.pojo.POJORegistro;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.persistencia.JugadorDao;
import com.rzg.zombieland.server.sesion.Jugador;

/**
 * Prueba una petición de registro.
 * @author nicolas
 */
public class PeticionRegistroTest extends PeticionTestHarness {
    
    /**
     * Verifica crear y enviar una petición de registro válida.
     * @throws ZombielandException
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws ParametrosNoValidosException
     */
    @Test
    public void testPeticion() throws ZombielandException, InterruptedException, ExecutionException, ParametrosNoValidosException {
        POJORegistro registro = new POJORegistro("Carlos", "123456789","Nombre de mi papa", "Bob");
        PeticionRegistro peticion = new PeticionRegistro(registro);
        ServicioCliente.getInstancia().getHiloEscucha().enviarPeticion(peticion);
        final CountDownLatch latch = new CountDownLatch(1);
        peticion.getRespuesta().then(new DoneCallback<RespuestaGenerica>() {

			@Override
			public void onDone(RespuestaGenerica respuesta) {
				assertEquals(true, respuesta.fuePeticionExitosa());
				latch.countDown();
			}
		});
        Assert.assertTrue(latch.await(5, TimeUnit.SECONDS));
        JugadorDao dao = new JugadorDao();
        Jugador jugador = dao.getListado().get(0);
        assertEquals(jugador.getNombre(), registro.getNombre());
        assertEquals(jugador.getPreguntaSecreta(), registro.getPreguntaSecreta());
        assertEquals(jugador.getRespuestaSecreta(), registro.getRespuestaSecreta());
    }
}
