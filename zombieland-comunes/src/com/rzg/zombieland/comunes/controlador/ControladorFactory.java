package com.rzg.zombieland.comunes.controlador;

import com.rzg.zombieland.comunes.controlador.Controlador.ComandoDesconocidoException;


/**
 * Crea controladores para las distintas peticiones.
 * @author nicolas
 *
 */
public interface ControladorFactory {
    /**
     * @param codigo - el código de petición.
     * @return un controlador que maneja la petición.
     * @throws ComandoDesconocidoException si la petición es desconocida.
     */
    public Controlador crear(int codigo) throws ComandoDesconocidoException;
}
