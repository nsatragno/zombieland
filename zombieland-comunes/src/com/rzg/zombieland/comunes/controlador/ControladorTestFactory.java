package com.rzg.zombieland.comunes.controlador;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.controlador.Controlador.ComandoDesconocidoException;

public class ControladorTestFactory implements ControladorFactory {

    @Override
    public Controlador crear(int codigo) throws ComandoDesconocidoException {
        if (codigo == Enviable.TEST)
            return new ControladorTest();
        throw new ComandoDesconocidoException("El comando " + codigo + " no es conocido"); 
    }
}
