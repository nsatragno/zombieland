package com.rzg.zombieland.cliente.comunicacion.peticion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJORegistro;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;

/**
 * Modela una petición de registro de nuevo jugador.
 * @author nicolas
 *
 */
public class PeticionRegistro extends Peticion<POJORegistro, RespuestaGenerica> {

    /**
     * Crea una petición de registro.
     * @param registro
     */
    public PeticionRegistro(POJORegistro registro) {
        super(registro, RespuestaGenerica.class);
    }
    
    @Override
    protected int getCodigoPeticion() {
        return Enviable.REGISTRAR_JUGADOR; 
    }
}
