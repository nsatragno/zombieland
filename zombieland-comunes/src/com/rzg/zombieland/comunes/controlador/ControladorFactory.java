package com.rzg.zombieland.comunes.controlador;

import com.rzg.zombieland.comunes.controlador.Controlador.ComandoDesconocidoException;


/**
 * Crea controladores para las distintas peticiones.
 * @author nicolas
 *
 */
public interface ControladorFactory {
    public Controlador crear(int codigo) throws ComandoDesconocidoException;
}
