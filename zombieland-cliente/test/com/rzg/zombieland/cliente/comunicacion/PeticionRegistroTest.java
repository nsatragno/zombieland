package com.rzg.zombieland.cliente.comunicacion;

import static org.junit.Assert.assertEquals;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.rzg.zombieland.comunes.comunicacion.POJORegistro;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.comunicacion.ServicioEscucha;
import com.rzg.zombieland.server.meta.Jugador;
import com.rzg.zombieland.server.persistencia.HibernateSingleton;
import com.rzg.zombieland.server.persistencia.JugadorDao;

public class PeticionRegistroTest {
    
    private static ServicioEscucha servicio;
    
    @BeforeClass
    public static void lanzarServidor() throws ZombielandException {
        HibernateSingleton.setTest();
        servicio = new ServicioEscucha();
        servicio.start();   
    }
    
    @Test
    public void testPeticion() throws ZombielandException {
        POJORegistro registro = new POJORegistro("Carlos", "123456789","Nombre de mi papá", "Bob");
        PeticionRegistro peticion = new PeticionRegistro(registro);
        assertEquals(true, peticion.enviar().getPeticionExitosa());
        
        JugadorDao dao = new JugadorDao();
        Jugador jugador = dao.getListado().get(0);
        assertEquals(jugador.getNombre(), registro.getNombre());
        assertEquals(jugador.getPreguntaSecreta(), registro.getPreguntaSecreta());
        assertEquals(jugador.getRespuestaSecreta(), registro.getRespuestaSecreta());
    }

    @AfterClass
    public static void terminarServidor() throws ZombielandException {
        HibernateSingleton.cerrarConexion();
        servicio.cerrar();   
    }
}
