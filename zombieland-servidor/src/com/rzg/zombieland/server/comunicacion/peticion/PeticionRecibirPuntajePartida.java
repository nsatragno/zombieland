package com.rzg.zombieland.server.comunicacion.peticion;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOResultadoRonda;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;

public class PeticionRecibirPuntajePartida 
        extends Peticion<POJOResultadoRonda, RespuestaGenerica> {

    public PeticionRecibirPuntajePartida(POJOResultadoRonda resultado) {
        super(resultado, RespuestaGenerica.class);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.RECIBIR_PUNTAJE_PARTIDA;
    }

}
