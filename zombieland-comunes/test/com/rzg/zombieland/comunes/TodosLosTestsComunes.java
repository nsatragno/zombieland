package com.rzg.zombieland.comunes;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.rzg.zombieland.comunes.comunicacion.SuiteComunicacion;
import com.rzg.zombieland.comunes.misc.SuiteMisc;

/**
 * Engloba todos los test del proyecto zombieland-comunes. 
 * @author nicolas
 */
@RunWith(Suite.class)
@SuiteClasses({SuiteComunicacion.class, SuiteMisc.class })
public class TodosLosTestsComunes {

}
