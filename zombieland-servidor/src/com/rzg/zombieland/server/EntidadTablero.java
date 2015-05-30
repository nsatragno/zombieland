package com.rzg.zombieland.server;

/**
 * Elemento que vive en un tablero. 
 * @author nicolas
 *
 */
public abstract class EntidadTablero {
    
    public enum RespuestaColision {
        PUEDE_MOVERSE, NO_PUEDE_MOVERSE;
    }
    
    /**
     * 
     * @param personaje
     */
    public abstract RespuestaColision colisionaCon(Personaje personaje);
}
