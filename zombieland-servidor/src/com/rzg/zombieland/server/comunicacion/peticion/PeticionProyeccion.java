package com.rzg.zombieland.server.comunicacion.peticion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;

/**
 * Modela una petición de proyeccion. 
 * @author manuel/nicolás
 */
public class PeticionProyeccion extends Peticion<ProyeccionTablero, RespuestaGenerica> {

    /**
     * Constructor heredado.
     */
    public PeticionProyeccion(ProyeccionTablero proyeccion) {
        super(proyeccion, RespuestaGenerica.class);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.ACTUALIZACION_PROYECCION;
    }
}
