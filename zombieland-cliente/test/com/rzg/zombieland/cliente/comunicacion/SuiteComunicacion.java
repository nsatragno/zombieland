package com.rzg.zombieland.cliente.comunicacion;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Todos los tests de comunicación.
 * @author nicolas
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ PeticionRegistroTest.class, PeticionTest.class, PeticionListadoPartidasTest.class })
public class SuiteComunicacion {

}
