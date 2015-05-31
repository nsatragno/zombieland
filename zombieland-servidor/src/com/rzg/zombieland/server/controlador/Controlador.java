package com.rzg.zombieland.server.controlador;

import java.security.InvalidParameterException;

import com.rzg.zombieland.comunes.comunicacion.Enviable;

/**
 * Responde a una acción del cliente.
 * @author nicolas
 *
 */
public abstract class Controlador {
    /**
     * Devuelve un controlador de acuerdo a la línea leída. 
     * @param linea
     * @return
     */
    public static Controlador crear(int codigo) {
        switch (codigo) {
        case Enviable.TEST:
            return new ControladorVacio();
        case Enviable.REGISTRAR_JUGADOR:
            return new ControladorRegistrarCliente();
        default:
            throw new InvalidParameterException(
                    String.format("El código %h no corresponde con "
                                + "ninguno de los comandos conocidos", codigo));
        }
    }
    
    /**
     * Procesa la petición definida por la línea enviada.
     * @param linea
     * @return
     */
    public abstract String procesar(String linea);
}
