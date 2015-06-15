package com.rzg.zombieland.comunes.comunicacion;

import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Interfaz para algo que envía peticiones.
 * @author nicolas
 *
 */
public interface EnviaPeticiones {
    /**
     * Envía una petición.
     * @param peticion
     * @throws ZombielandException
     */
    public void enviarPeticion(Peticion<?, ?> peticion) throws ZombielandException;
}
