package com.rzg.zombieland.server.controlador;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Agrupa los tests de controladores.
 * @author nicolas
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ ControladorRegistroTest.class, ControladorInicioSesionTest.class })
public class SuiteControlador {

}
