package com.rzg.zombieland;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.rzg.zombieland.server.comunicacion.SuiteComunicacion;
import com.rzg.zombieland.server.controlador.SuiteControlador;
import com.rzg.zombieland.server.meta.SuiteMeta;
import com.rzg.zombieland.server.persistencia.SuitePersistencia;
import com.rzg.zombieland.server.sesion.SuiteSesion;

/**
 * Todos los tests del servidor.
 * @author nicolas
 *
 */
@RunWith(Suite.class)
@SuiteClasses({SuitePersistencia.class, SuiteMeta.class, SuiteComunicacion.class, SuiteControlador.class,
               SuiteSesion.class})
public class TodosLosTestsServidor {
    
}
