package com.rzg.zombieland.comunes.comunicacion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.google.gson.Gson;

public class POJORegistroTest {

    private final String jsonRegistro = 
            "{"
            + "nombre: 'Juan',"
            + "clave: '1234',"
            + "preguntaSecreta: 'nombre de mi madre',"
            + "respuestaSecreta: 'maria'"
           +"}";
    private final POJORegistro registro = 
            new POJORegistro("Juan", "1234", "nombre de mi madre", "maria");
    
    @Test
    public void testDeserializar() {
        Gson gson = new Gson();
        POJORegistro registroDeserializado = gson.fromJson(jsonRegistro, POJORegistro.class);
        assertEquals(registroDeserializado, registro);
    }
    
    @Test
    public void testSerializar() {
        Gson gson = new Gson();
        String registroSerializada = gson.toJson(registro);
        POJORegistro registroDeserializada = gson.fromJson(registroSerializada, POJORegistro.class);
        assertEquals(registroDeserializada, registro);
    }
}
