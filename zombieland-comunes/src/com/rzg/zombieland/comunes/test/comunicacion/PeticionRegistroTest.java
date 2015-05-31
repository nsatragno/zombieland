package com.rzg.zombieland.comunes.test.comunicacion;

import static org.junit.Assert.*;

import org.junit.Test;

import com.rzg.zombieland.comunes.comunicacion.POJOJugador;
import com.rzg.zombieland.comunes.comunicacion.PeticionRegistro;

public class PeticionRegistroTest {

    private final String jsonJugador = 
            "{"
            + "nombre: 'Juan',"
            + "clave: '1234',"
            + "preguntaSecreta: 'nombre de mi madre',"
            + "respuestaSecreta: 'maria'"
           +"}";
    private final POJOJugador jugadorDeseralizado = 
            new POJOJugador("Juan", "1234", "nombre de mi madre", "maria");
    
    @Test
    public void testDeserializar() {
        PeticionRegistro peticionRegistro = new PeticionRegistro(jsonJugador);
        assertEquals(peticionRegistro.getJugador(), jugadorDeseralizado);
    }
    
    @Test
    public void testSerializar() {
        PeticionRegistro peticionRegistro = new PeticionRegistro(jugadorDeseralizado);
        PeticionRegistro otraPeticion = new PeticionRegistro(peticionRegistro.serializar());
        assertEquals(peticionRegistro.getJugador(), otraPeticion.getJugador());
    }
}
