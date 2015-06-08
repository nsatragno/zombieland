package com.rzg.zombieland.cliente.comunicacion;

import com.rzg.zombieland.comunes.controlador.Controlador;
import com.rzg.zombieland.comunes.controlador.Controlador.ComandoDesconocidoException;
import com.rzg.zombieland.comunes.controlador.ControladorTestFactory;
import com.rzg.zombieland.comunes.misc.Log;

/**
 * Fábrica de controladores para el cliente.
 * @author nicolas
 *
 */
public class ControladorClienteFactory extends ControladorTestFactory {

    @Override
    public Controlador crear(int codigo) throws ComandoDesconocidoException {
        try {
            return super.crear(codigo);
        } catch (ComandoDesconocidoException e) {
            Log.error("Controlador de cliente no implementado!!!");
        }
        return null;
    }

}
