package com.rzg.zombieland.cliente.comunicacion;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.comunicacion.ServicioEscucha;
import com.rzg.zombieland.server.controlador.ControladorTest;

public class PeticionTest {
    
    private static ServicioEscucha servicio;
    
    private final static String MENSAJE_TEST = "Mensaje test! :D";
    private final static String LINEA_DEVOLUCION = "Devuleto!!! :O";
    
    private static class ObjetoPeticionTest extends Peticion<String> {
        @Override
        protected String getMensajePeticion() {
            return MENSAJE_TEST;
        }

        @Override
        protected int getCodigoPeticion() {
            return Enviable.TEST;
        }

        @Override
        protected String procesarRespuesta(String respuesta) {
            return respuesta;
        }
    }
    
    @Test
    public void testPeticionesFatiga() throws ZombielandException, InterruptedException {
        ControladorTest.setLineaDevolucion(LINEA_DEVOLUCION);
        servicio = new ServicioEscucha();
        servicio.start();
        
        for (int i = 0; i < 1000; i++) {
            ObjetoPeticionTest peticion = new ObjetoPeticionTest();
            assertEquals(LINEA_DEVOLUCION, peticion.enviar());
            assertTrue(ControladorTest.proceso(MENSAJE_TEST));
        }
        
        servicio.cerrar();
        servicio.join();
    }
    
    @Test(expected = ZombielandException.class)
    public void testPeticionSinConexion() throws ZombielandException, InterruptedException {
        new ObjetoPeticionTest().enviar();
    }
}
