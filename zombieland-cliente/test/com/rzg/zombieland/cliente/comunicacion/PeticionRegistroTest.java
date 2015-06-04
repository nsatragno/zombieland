package com.rzg.zombieland.cliente.comunicacion;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rzg.zombieland.comunes.comunicacion.HiloEscucha;
import com.rzg.zombieland.comunes.comunicacion.POJORegistro;
import com.rzg.zombieland.comunes.controlador.ControladorTestFactory;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.comunicacion.ServicioEscucha;
import com.rzg.zombieland.server.meta.Jugador;
import com.rzg.zombieland.server.persistencia.HibernateSingleton;
import com.rzg.zombieland.server.persistencia.JugadorDao;

public class PeticionRegistroTest extends PeticionTestHarness {
    
    @Test
    public void testPeticion() throws ZombielandException, InterruptedException, ExecutionException {
        
        POJORegistro registro = new POJORegistro("Carlos", "123456789","Nombre de mi papá", "Bob");
        PeticionRegistro peticion = new PeticionRegistro(registro);
        hiloEscucha.enviarPeticion(peticion);
        assertEquals(true, peticion.getRespuesta().get().getPeticionExitosa());
        
        JugadorDao dao = new JugadorDao();
        Jugador jugador = dao.getListado().get(0);
        assertEquals(jugador.getNombre(), registro.getNombre());
        assertEquals(jugador.getPreguntaSecreta(), registro.getPreguntaSecreta());
        assertEquals(jugador.getRespuestaSecreta(), registro.getRespuestaSecreta());
    }
}
