package com.rzg.zombieland.server.controlador;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.sesion.ManejadorSesion;

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
    
    /**
     * Verifica usar el controlador con datos válidos.
     * @throws ZombielandException 
     */
    @Test
    public void testSesionValida() throws ZombielandException {
        ManejadorSesionImpl manejador = new ManejadorSesionImpl();
        manejador.crearSesion();
        ControladorConSesionImpl controlador = new ControladorConSesionImpl(manejador);
        assertEquals(ControladorConSesionImpl.MENSAJE_LINEA_PROCESADA, controlador.procesar("test"));
    }

    /**
     * Verifica que intentar usar el controlador con una sesión nula lance una excepción.
     * @throws ZombielandException 
     */
    @Test(expected = ZombielandException.class)
    public void testDatosSesionNula() throws ZombielandException {
        new ControladorConSesionImpl(new ManejadorSesionImpl());
    }
}
