package com.rzg.zombieland.cliente.comunicacion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.POJOCreacionPartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;

/**
 * Modela una petición de creación de partida. 
 * @author nicolas
 */
public class PeticionCreacionPartida extends Peticion<POJOCreacionPartida, RespuestaGenerica> {

    /**
     * Constructor heredado.
     * @param pojo
     */
    public PeticionCreacionPartida(POJOCreacionPartida pojo) {
        super(pojo, RespuestaGenerica.class);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.CREAR_PARTIDA;
    }
}
