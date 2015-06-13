package com.rzg.zombieland.server.controlador;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.sesion.Jugador;
import com.rzg.zombieland.server.sesion.ManejadorSesion;
import com.rzg.zombieland.server.sesion.Sesion;

/**
 * Verifica la funcionalidad del controlador con sesión.
 * @author nicolas
 *
 */
public class ControladorConSesionTest {

    private class ControladorConSesionImpl extends ControladorConSesion {
        
        private final static String MENSAJE_LINEA_PROCESADA = "Procesada!";
        
        public ControladorConSesionImpl(ManejadorSesion manejadorSesion) 
                throws ZombielandException {
            super(manejadorSesion);
        }

        @Override
        public String procesar(String linea) {
            return MENSAJE_LINEA_PROCESADA;
        }

    }
    
    private class ManejadorSesionImpl implements ManejadorSesion {

        private Sesion sesion;
        
        public ManejadorSesionImpl() {
            try {
                sesion = new Sesion(new Jugador("a", "b", "c", "d", "e"));
            } catch (ParametrosNoValidosException e) {
                Assert.fail("La construcción del jugador no debería haber lanzado una excepción");
            }
        }
        
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
     * Verifica usar el controlador con datos válidos.
     * @throws ZombielandException 
     */
    @Test
    public void testSesionValida() throws ZombielandException {
        ControladorConSesionImpl controlador = new ControladorConSesionImpl(new ManejadorSesionImpl());
        assertEquals(ControladorConSesionImpl.MENSAJE_LINEA_PROCESADA, controlador.procesar("test"));
    }

    /**
     * Verifica que intentar usar el controlador con una sesión nula lance una excepción.
     * @throws ZombielandException 
     */
    @Test(expected = ZombielandException.class)
    public void testDatosSesionNula() throws ZombielandException {
        new ControladorConSesionImpl(null);
    }
}
