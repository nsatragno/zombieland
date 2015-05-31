package com.rzg.zombieland.comunes.comunicacion;

/**
 * Representa una petición iniciada por un cliente.
 * @author nicolas
 *
 */
public abstract class PeticionCliente extends Enviable {
    // TODO agregar token autenticación.
    
    public PeticionCliente(String bytes) {
        super(bytes);
        // TODO Auto-generated constructor stub
    }
    
    protected PeticionCliente() { }
}