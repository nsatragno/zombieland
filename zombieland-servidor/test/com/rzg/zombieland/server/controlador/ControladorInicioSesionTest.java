package com.rzg.zombieland.server.controlador;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOInicioSesion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.comunicacion.controlador.ControladorConSesion;
import com.rzg.zombieland.server.comunicacion.controlador.ControladorInicioSesion;
import com.rzg.zombieland.server.meta.EnviaPeticionesImpl;
import com.rzg.zombieland.server.persistencia.HibernateSingleton;
import com.rzg.zombieland.server.persistencia.JugadorDao;
import com.rzg.zombieland.server.sesion.Jugador;
import com.rzg.zombieland.server.sesion.ServicioSesion;

/**
 * Prueba el controlador de inicio de sesión.
 * @author nicolas
 *
 */
public class ControladorInicioSesionTest {

    private Jugador jugadorValido;
    
    /**
     * Inicializa el test cargando datos previos en la DB.
     * @throws ZombielandException 
     * @throws ParametrosNoValidosException 
     */
    @Before
    public void setUp() throws ParametrosNoValidosException {
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
        ServicioSesion.matarInstancia();
    }
    
    /**
     * Prueba un inicio de sesión con datos válidos.
     * @throws ZombielandException 
     * @throws ParametrosNoValidosException 
     */
    @Test
    public void testInicioValido() throws ZombielandException, ParametrosNoValidosException {
        ManejadorSesionImpl manejador = new ManejadorSesionImpl();
        ControladorInicioSesion controlador = new ControladorInicioSesion(manejador, new EnviaPeticionesImpl());
        POJOInicioSesion pojo = new POJOInicioSesion(jugadorValido.getNombre(), jugadorValido.getClave());
        Gson gson = new Gson();
        RespuestaGenerica respuesta = gson.fromJson(controlador.procesar(gson.toJson(pojo)),
                RespuestaGenerica.class);
        assertTrue(respuesta.fuePeticionExitosa());
        Assert.assertEquals(jugadorValido, manejador.getSesion().getJugador());
        Assert.assertEquals(manejador.getSesion(),
                            ServicioSesion.getInstancia().getSesion(jugadorValido));
        Assert.assertNotNull(manejador.getSesion().getId());
    }
    
    /**
     * Prueba un inicio de sesión con nombre no válido.
     * @throws ZombielandException 
     * @throws ParametrosNoValidosException 
     */
    @Test
    public void testInicioNombreNoValido() throws ZombielandException, ParametrosNoValidosException {
        ManejadorSesionImpl manejador = new ManejadorSesionImpl();
        ControladorInicioSesion controlador = new ControladorInicioSesion(manejador, new EnviaPeticionesImpl());
        POJOInicioSesion pojo = new POJOInicioSesion("asd", jugadorValido.getClave());
        Gson gson = new Gson();
        RespuestaGenerica respuesta = gson.fromJson(controlador.procesar(gson.toJson(pojo)),
                RespuestaGenerica.class);
        assertFalse(respuesta.fuePeticionExitosa());
        Assert.assertNull(manejador.getSesion());
        Assert.assertNull(ServicioSesion.getInstancia().getSesion(jugadorValido));
    }

    /**
     * Prueba un inicio de sesión con clave no válida.
     * @throws ZombielandException 
     * @throws ParametrosNoValidosException 
     */
    @Test
    public void testInicioClaveNoValida() throws ZombielandException, ParametrosNoValidosException {
        ManejadorSesionImpl manejador = new ManejadorSesionImpl();
        ControladorInicioSesion controlador = new ControladorInicioSesion(manejador, new EnviaPeticionesImpl());
        POJOInicioSesion pojo = new POJOInicioSesion(jugadorValido.getNombre(), "claveNoValida");
        Gson gson = new Gson();
        RespuestaGenerica respuesta = gson.fromJson(controlador.procesar(gson.toJson(pojo)),
                                                    RespuestaGenerica.class);
        assertFalse(respuesta.fuePeticionExitosa());
        Assert.assertNull(manejador.getSesion());
    }


    /**
     * Prueba cerrar la sesión.
     * @throws ZombielandException 
     * @throws ParametrosNoValidosException 
     */
    @Test
    public void testInicioYCierre() throws ZombielandException, ParametrosNoValidosException {
        // Inicio la sesión.
        ManejadorSesionImpl manejador = new ManejadorSesionImpl();
        ControladorInicioSesion controlador = new ControladorInicioSesion(manejador, new EnviaPeticionesImpl());
        POJOInicioSesion pojo = new POJOInicioSesion(jugadorValido.getNombre(), jugadorValido.getClave());
        Gson gson = new Gson();
        controlador.procesar(gson.toJson(pojo));
        
        // Intento realizar una acción de autenticación.
        ControladorConSesion controladorConSesion = new ControladorConSesion(manejador) {
            
            @Override
            protected String procesarAutenticado(String respuesta) {
                // TODO Auto-generated method stub
                return "exito";
            }
        };
        
        assertEquals("exito", controladorConSesion.procesar("test"));
        
        assertFalse(manejador.getSesionCerrada());
        manejador.getSesion().cerrar();
        RespuestaGenerica respuesta = gson.fromJson(controladorConSesion.procesar(gson.toJson(pojo)),
                RespuestaGenerica.class);
        assertFalse(respuesta.fuePeticionExitosa());
        assertTrue(manejador.getSesionCerrada());
    }
    // TODO testear cierre de sesión.
}
