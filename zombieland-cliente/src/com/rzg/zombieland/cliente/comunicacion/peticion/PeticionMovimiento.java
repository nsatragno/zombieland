package com.rzg.zombieland.cliente.comunicacion.peticion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.Movimiento.Direccion;

/**
 * Solicita cambiar el próximo movimiento del personaje.
 * @author nicolas
 *
 */
public class PeticionMovimiento extends Peticion<Direccion, RespuestaGenerica> {

    /**
     * Crea una petición de movimiento.
     * @param movimiento
     */
    public PeticionMovimiento(Direccion direccion) {
        super(direccion, RespuestaGenerica.class);
        if (direccion == null)
            throw new NullPointerException("El movimiento no puede ser null");
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.MOVER_PERSONAJE;
    }

}
