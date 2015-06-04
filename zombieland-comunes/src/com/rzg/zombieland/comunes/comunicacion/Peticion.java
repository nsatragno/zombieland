package com.rzg.zombieland.comunes.comunicacion;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

/**
 * Modela una petición realizada al servidor.
 * 
 * @author nicolas
 *
 */
public abstract class Peticion<Respuesta> {
    // Un ID único para identificar a la petición.
    private UUID id;
    
    // Promesa que se llena con la respuesta.
    private CompletableFuture<Respuesta> promesa;
    
    public Peticion() {
        this.id = UUID.randomUUID();
        promesa = new CompletableFuture<Respuesta>();
    }
    /**
     * Devuelve el mensaje de una petición.
     * 
     * @return
     */
    protected abstract String getMensajePeticion();

    /**
     * Devuelve el código de la petición.
     * 
     * @return
     */
    protected abstract int getCodigoPeticion();

    /**
     * Procesa una respuesta y devuelve un producto para su consumo.
     * 
     * @param respuesta
     * @return
     */
    protected abstract Respuesta generarRespuesta(String respuesta);
    
    /**
     * Devuelve un ID único de petición.
     */
    public UUID getID() {
        return id;
    }
    
    /**
     * Devuelve la promesa de la respuesta.
     * @return
     */
    public CompletableFuture<Respuesta> getRespuesta() {
        return promesa;
    }
    
    /**
     * Completa la promesa con la respuesta dada.
     * @param respuesta
     */
    public void procesarRespuesta(String respuesta) {
        promesa.complete(generarRespuesta(respuesta));
    }
}
