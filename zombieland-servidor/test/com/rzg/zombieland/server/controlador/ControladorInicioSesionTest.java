package com.rzg.zombieland.server.controlador;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOInicioSesion;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.persistencia.HibernateSingleton;
import com.rzg.zombieland.server.persistencia.JugadorDao;
import com.rzg.zombieland.server.sesion.Jugador;
import com.rzg.zombieland.server.sesion.ManejadorSesion;
import com.rzg.zombieland.server.sesion.Sesion;

/**
 * Prueba el controlador de inicio de sesión.
 * @author nicolas
 *
 */
public class ControladorInicioSesionTest {

    private Jugador jugadorValido;
    
    /**
     * Clase para verificar el correcto funcionamiento del controlador.
     * @author nicolas
     *
     */
    private class Manejador implements ManejadorSesion {

        private Sesion sesion;

        @Override
        public void setSesion(Sesion sesion) {
            this.sesion = sesion;
        }

        @Override
        public Sesion getSesion() {
            return sesion;
        }
        
    }

    /**
     * Inicializa el test cargando datos previos en la DB.
     * @throws ZombielandException 
     */
    @Before
    public void setUp() throws ZombielandException {
        HibernateSingleton.setTest();
        JugadorDao dao = new JugadorDao();
        jugadorValido = new Jugador("juan", "1234", "1234", "a", "b");
        dao.guardarObjeto(jugadorValido);
        dao.cerrarSesion();
    }
    
    /**
     * Cierra todo antes de matar el test.
     */
    @After
    public void tearDown() {
        HibernateSingleton.cerrarConexion();
    }
    
    /**
     * Prueba un inicio de sesión con datos válidos.
     * @throws ZombielandException 
     * @throws ParametrosNoValidosException 
     */
    @Test
    public void testInicioValido() throws ZombielandException, ParametrosNoValidosException {
        Manejador manejador = new Manejador();
        ControladorInicioSesion controlador = new ControladorInicioSesion(manejador);
        POJOInicioSesion pojo = new POJOInicioSesion(jugadorValido.getNombre(), jugadorValido.getClave());
        Gson gson = new Gson();
        controlador.procesar(gson.toJson(pojo));
        Assert.assertEquals(jugadorValido, manejador.getSesion().getJugador());
        Assert.assertNotNull(manejador.getSesion().getId());
    }
    
    /**
     * Prueba un inicio de sesión con nombre no válido.
     * @throws ZombielandException 
     * @throws ParametrosNoValidosException 
     */
    @Test
    public void testInicioNombreNoValido() throws ZombielandException, ParametrosNoValidosException {
        Manejador manejador = new Manejador();
        ControladorInicioSesion controlador = new ControladorInicioSesion(manejador);
        POJOInicioSesion pojo = new POJOInicioSesion("asd", jugadorValido.getClave());
        Gson gson = new Gson();
        controlador.procesar(gson.toJson(pojo));
        Assert.assertNull(manejador.getSesion());
    }

    /**
     * Prueba un inicio de sesión con clave no válida.
     * @throws ZombielandException 
     * @throws ParametrosNoValidosException 
     */
    @Test
    public void testInicioClaveNoValida() throws ZombielandException, ParametrosNoValidosException {
        Manejador manejador = new Manejador();
        ControladorInicioSesion controlador = new ControladorInicioSesion(manejador);
        POJOInicioSesion pojo = new POJOInicioSesion(jugadorValido.getNombre(), "claveNoValida");
        Gson gson = new Gson();
        controlador.procesar(gson.toJson(pojo));
        Assert.assertNull(manejador.getSesion());
    }
}
