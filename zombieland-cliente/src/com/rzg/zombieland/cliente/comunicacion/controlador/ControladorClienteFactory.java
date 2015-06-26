package com.rzg.zombieland.cliente.comunicacion.controlador;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
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
        switch (codigo) {
        case Enviable.LISTADO_PARTIDAS:
            return new ControladorListadoPartidas();
        case Enviable.ACTUALIZACION_LOBBY:
            return new ControladorActualizacionLobby();
        case Enviable.ACTUALIZACION_PROYECCION:
            return new ControladorActualizacionProyeccion();
        case Enviable.RECIBIR_MENSAJE_CHAT:
            return new ControladorRecibirMensaje();
        default:
            throw new ComandoDesconocidoException(
                    "El comando " + codigo + " no es conocido por el cliente");
        }
    }

}
