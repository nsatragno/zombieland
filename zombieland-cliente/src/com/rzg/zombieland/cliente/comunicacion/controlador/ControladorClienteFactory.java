package com.rzg.zombieland.cliente.comunicacion.controlador;

import com.rzg.zombieland.comunes.controlador.Controlador;
import com.rzg.zombieland.comunes.controlador.Controlador.ComandoDesconocidoException;
import com.rzg.zombieland.comunes.controlador.ControladorFactory;

/**
 * Fábrica de controladores para el cliente.
 * @author nicolas
 *
 */
public class ControladorClienteFactory implements ControladorFactory {

    @Override
    public Controlador crear(int codigo) throws ComandoDesconocidoException {
        return null;
    }

}
