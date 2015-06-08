package com.rzg.zombieland;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.rzg.zombieland.server.comunicacion.SuiteComunicacion;
import com.rzg.zombieland.server.meta.SuiteMeta;
import com.rzg.zombieland.server.persistencia.SuitePersistencia;

/**
 * Todos los tests del servidor.
 * @author nicolas
 *
 */
@RunWith(Suite.class)
@SuiteClasses({SuitePersistencia.class, SuiteMeta.class, SuiteComunicacion.class})
public class TodosLosTestsServidor {
    
}
