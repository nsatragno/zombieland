package com.rzg.zombieland.server.sesion;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Corre todos los tests de sesión.
 * @author nicolas
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ SesionTest.class, ServicioSesionTest.class })
public class SuiteSesion {

}
