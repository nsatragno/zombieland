package com.rzg.zombieland.comunes.comunicacion;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.rzg.zombieland.comunes.comunicacion.pojo.POJOCreacionPartidaTest;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOInicioSesionTest;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJORegistroTest;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaLoginTest;

/**
 * Todos los tests del paquete de comunicación.
 * @author nicolas
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ POJORegistroTest.class, HiloEscuchaTest.class, RespuestaLoginTest.class,
                POJOInicioSesionTest.class, POJOCreacionPartidaTest.class })
public class SuiteComunicacion {

}
