package com.rzg.zombieland.cliente.comunicacion;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.rzg.zombieland.cliente.comunicacion.controlador.ControladorListadoPartidasTest;
import com.rzg.zombieland.cliente.comunicacion.peticion.PeticionListadoPartidasTest;
import com.rzg.zombieland.cliente.comunicacion.peticion.PeticionRegistroTest;
import com.rzg.zombieland.cliente.comunicacion.peticion.PeticionTest;

/**
 * Todos los tests de comunicación.
 * @author nicolas
 *
 */
@RunWith(Suite.class)
@SuiteClasses({ PeticionRegistroTest.class, PeticionTest.class, PeticionListadoPartidasTest.class,
                ControladorListadoPartidasTest.class })
public class SuiteComunicacion {

}
