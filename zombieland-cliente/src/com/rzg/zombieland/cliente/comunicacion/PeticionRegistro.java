package com.rzg.zombieland.cliente.comunicacion;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.POJORegistro;
import com.rzg.zombieland.comunes.comunicacion.RespuestaRegistro;

/**
 * Modela una petición de registro de nuevo jugador.
 * @author nicolas
 *
 */
public class PeticionRegistro extends Peticion<RespuestaRegistro> {

    private POJORegistro registro;
    
    public PeticionRegistro(POJORegistro registro) {
        this.registro = registro;
    }
    
    @Override
    protected String getMensajePeticion() {
        Gson gson = new Gson();
        return gson.toJson(registro);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.REGISTRAR_JUGADOR; 
    }

    @Override
    protected RespuestaRegistro procesarRespuesta(String respuesta) {
        Gson gson = new Gson();
        return gson.fromJson(respuesta, RespuestaRegistro.class);
    }

}
