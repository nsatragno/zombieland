package com.rzg.zombieland.comunes.comunicacion.pojo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.misc.Avatar;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;

/**
 * Verifica el funcionamiento del POJO de registro.
 * @author nicolas
 *
 */
public class POJORegistroTest {

    private final String jsonRegistro = 
            "{"
            + "nombre: 'Juan',"
            + "clave: '1234',"
            + "preguntaSecreta: 'nombre de mi madre',"
            + "respuestaSecreta: 'maria',"
            + "avatar: 'hombre'"
           +"}";
    private POJORegistro registro; 
    
    /**
     * Crea un POJORegistro para usar en todos los tests.
     */
    public POJORegistroTest()  {
        try {
            registro = new POJORegistro("Juan", "1234", "nombre de mi madre", "maria", null);
        } catch (ParametrosNoValidosException e) {
            Assert.fail();
        }
    }
    
    /**
     * Prueba deserealizar un pojo de registro.
     */
    @Test
    public void testDeserializar() {
        Gson gson = new Gson();
        POJORegistro registroDeserializado = gson.fromJson(jsonRegistro, POJORegistro.class);
        assertEquals(registroDeserializado, registro);
    }
    
    /**
     * Prueba serializar un pojo de registro.
     */
    @Test
    public void testSerializar() {
        Gson gson = new Gson();
        String registroSerializada = gson.toJson(registro);
        POJORegistro registroDeserializada = gson.fromJson(registroSerializada, POJORegistro.class);
        assertEquals(registroDeserializada, registro);
    }
    
    @Test
    public void testDatosNoValidos() {
    	try {
			new POJORegistro(null, null, null, null, Avatar.OBSTACULO);
			fail();
		} catch (ParametrosNoValidosException e) {
			assertEquals(5, e.getCantidadParametros());
		}
    }
}
