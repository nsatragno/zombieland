package com.rzg.zombieland.test.server;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.rzg.zombieland.test.server.comunicacion.SuiteComunicacion;
import com.rzg.zombieland.test.server.meta.SuiteMeta;
import com.rzg.zombieland.test.server.persistencia.SuitePersistencia;

@RunWith(Suite.class)
@SuiteClasses({SuitePersistencia.class, SuiteMeta.class, SuiteComunicacion.class})
public class TodosLosTests {
    
}
