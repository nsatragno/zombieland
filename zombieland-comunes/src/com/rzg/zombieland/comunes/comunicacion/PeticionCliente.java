package com.rzg.zombieland.comunes.comunicacion;

/**
 * Representa una petición iniciada por un cliente.
 * @author nicolas
 *
 */
public abstract class PeticionCliente extends Enviable {
    // Jugador que realiza la petición.
    private POJOJugador jugador;
    
    protected PeticionCliente(Byte[] bytes) {
        super(bytes);
        // TODO Auto-generated constructor stub
    }
}