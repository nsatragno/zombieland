package com.rzg.zombieland.cliente.comunicacion;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.Peticion;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOInicioSesion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;

/**
 * Define una petición del cliente para autenticarse.
 * @author nicolas
 *
 */
public class PeticionInicioSesion extends Peticion<RespuestaGenerica> {
    
    // Los datos de inicio de sesión que se envían.
    private POJOInicioSesion pojo;
    
    /**
     * Construye una petición de login para el POJO indicado.
     * @param pojo
     */
    public PeticionInicioSesion(POJOInicioSesion pojo) {
        this.pojo = pojo;
    }

    @Override
    protected String getMensajePeticion() {
        return new Gson().toJson(pojo);
    }

    @Override
    protected int getCodigoPeticion() {
        return Enviable.INICIAR_SESION;
    }

    @Override
    protected RespuestaGenerica generarRespuesta(String respuesta) {
        return new Gson().fromJson(respuesta, RespuestaGenerica.class);
    }
}
