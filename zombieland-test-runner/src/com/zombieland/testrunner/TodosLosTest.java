package com.zombieland.testrunner;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.rzg.zombieland.TodosLosTestsServidor;
import com.rzg.zombieland.cliente.TodosLosTestsCliente;
import com.rzg.zombieland.comunes.TodosLosTestsComunes;

/**
 * Comodidad para correr todos los tests de una.
 * @author nicolas
 *
 */
@RunWith(Suite.class)
@SuiteClasses({TodosLosTestsComunes.class, TodosLosTestsCliente.class, TodosLosTestsServidor.class})
public class TodosLosTest {

}
