package com.rzg.zombieland.comunes.comunicacion;

import java.util.UUID;

import org.jdeferred.Deferred;
import org.jdeferred.Promise;
import org.jdeferred.impl.DeferredObject;

/**
 * Modela una petición realizada al servidor.
 * 
 * @author nicolas
 * @param <Respuesta> el tipo de objeto respuesta de la petición.
 *
 */
public abstract class Peticion<Respuesta> {
    // Un ID único para identificar a la petición.
    private UUID id;
    
    // Promesa que se llena con la respuesta.
    private Deferred<Respuesta, Object, Respuesta> promesa;
     
    /**
     * Construye la petición asingándole un ID aleatorio y creando la promsa.
     */
    public Peticion() {
        this.id = UUID.randomUUID();
        promesa = new DeferredObject<Respuesta, Object, Respuesta>();
    }
    /**
     * @return el mensaje de una petición.
     */
    protected abstract String getMensajePeticion();

    /**
     * @return el código de la petición.
     */
    protected abstract int getCodigoPeticion();

    /**
     * Procesa una respuesta. 
     * 
     * @param respuesta
     * @return el resultado de procesar la respuesta.
     */
    protected abstract Respuesta generarRespuesta(String respuesta);
    
    /**
     * @return un ID único de petición.
     */
    public UUID getID() {
        return id;
    }
    
    /**
     * @return la promesa de la respuesta.
     */
    public Promise<Respuesta, Object, Respuesta> getRespuesta() {
        return promesa.promise();
    }
    
    /**
     * Completa la promesa con la respuesta dada.
     * @param respuesta
     */
    public void procesarRespuesta(String respuesta) {
        promesa.resolve(generarRespuesta(respuesta));
    }
}
