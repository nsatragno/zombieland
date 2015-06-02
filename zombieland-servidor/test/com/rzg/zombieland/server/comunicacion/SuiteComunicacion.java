package com.rzg.zombieland.server.comunicacion;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.rzg.zombieland.comunes.comunicacion.HiloEscuchaTest;

@RunWith(Suite.class)
@SuiteClasses({ RecepecionPeticionTest.class, ServicioEscuchaTest.class })
public class SuiteComunicacion
{

}
