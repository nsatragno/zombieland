package com.rzg.zombieland.server.controlador;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJORegistro;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.persistencia.HibernateSingleton;
import com.rzg.zombieland.server.persistencia.JugadorDao;
import com.rzg.zombieland.server.sesion.Jugador;

/**
 * Verifica el correcto funcionamiento del controlador de registro.
 * @author nicolas
 *
 */
public class ControladorRegistroTest {

    private static final String NOMBRE_JUGADOR_VALIDO = "amanda";
    private static final String NOMBRE_JUGADOR_EXISTENTE = "juan";
    
    // El controlador bajo test.
    private ControladorRegistro controlador;
    
    private JugadorDao dao;
    
    /**
     * Hace el setup del test.
     * @throws ParametrosNoValidosException
     */
    @Before
    public void setUp() throws ParametrosNoValidosException {
        controlador = new ControladorRegistro();
        HibernateSingleton.setTest();
        dao = new JugadorDao();
        Jugador jugador = new Jugador(NOMBRE_JUGADOR_EXISTENTE, "1234", "1234", "test", "test");
        dao.guardarObjeto(jugador);
        dao.cerrarSesion();
    }
    
    /**
     * Restablece a las condiciones iniciales.
     * @throws ZombielandException no debería.
     */
    @After
    public void tearDown() throws ZombielandException {
        HibernateSingleton.cerrarConexion();
    }
    
    /**
     * Intenta inicar sesión con un usuario que no existe.
     * @throws ParametrosNoValidosException no debería.
     * @throws ZombielandException 
     */
    @Test
    public void testRegistroValido() throws ParametrosNoValidosException, ZombielandException {
        POJORegistro registro = new POJORegistro(NOMBRE_JUGADOR_VALIDO, "1234", "test", "test");
        Gson gson = new Gson();
        RespuestaGenerica respuesta = gson.fromJson(
                controlador.procesar(gson.toJson(registro)), RespuestaGenerica.class);
        Assert.assertTrue(respuesta.fuePeticionExitosa());
        Assert.assertEquals(null, respuesta.getMensajeError());
        assertEquals(new Jugador(registro), dao.getObjeto(NOMBRE_JUGADOR_VALIDO));
        dao.cerrarSesion();
    }
    
    
    /**
     * Intenta inicar sesión con un usuario que ya existe.
     * @throws ParametrosNoValidosException no debería.
     * @throws ZombielandException 
     */
    @Test
    public void testRegistroExistente() throws ParametrosNoValidosException, ZombielandException {
        POJORegistro registro = new POJORegistro(NOMBRE_JUGADOR_EXISTENTE, "1234", "test", "test");
        Gson gson = new Gson();
        RespuestaGenerica respuesta = gson.fromJson(
                controlador.procesar(gson.toJson(registro)), RespuestaGenerica.class);
        Assert.assertFalse(respuesta.fuePeticionExitosa());
        Assert.assertFalse(respuesta.getMensajeError().isEmpty());
        assertEquals(null, dao.getObjeto(NOMBRE_JUGADOR_VALIDO));
        dao.cerrarSesion();
    }

}
