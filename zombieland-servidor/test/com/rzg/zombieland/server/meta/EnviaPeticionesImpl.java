package com.rzg.zombieland.server.meta;

import com.rzg.zombieland.comunes.comunicacion.EnviaPeticiones;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Mock de EnviaPeticiones para testing.
 * @author nicolas
 *
 */
public class EnviaPeticionesImpl implements EnviaPeticiones {

    private Peticion<?, ?> ultimaPeticion;
    
    @Override
    public void enviarPeticion(Peticion<?, ?> peticion) throws ZombielandException {
        ultimaPeticion = peticion;
    }

    /**
     * @return la última petición que se envío.
     */
    public Peticion<?, ?> getUltimaPeticion() {
        return ultimaPeticion;
    }
}
