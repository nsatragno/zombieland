package com.rzg.zombieland.cliente.comunicacion.peticion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;

/**
 * Solicita cerrar la sesión. 
 * @author nicolas
 */
public class PeticionCierreSesion extends Peticion<Object, RespuestaGenerica> {

    /**
     * Constructor por defecto.
     */
    public PeticionCierreSesion() {
        super(null, RespuestaGenerica.class);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.CERRAR_SESION;
    }
}
