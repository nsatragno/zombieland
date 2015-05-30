package com.rzg.zombieland.server.juego;

import com.rzg.zombieland.server.misc.Coordenada;

/**
 * Elemento que vive en un tablero. 
 * @author nicolas
 *
 */
public abstract class EntidadTablero {
    // Posición que esta entidad ocupa en el tablero.
    private Coordenada posicion;
    
    /**
     * @return el nombre de la imagen con la que esta entidad se representa.
     */
    public abstract String getSprite();
    
    /**
     * Hace colisionar esta entidad con otra, posiblemente afectándolas.
     */
    public abstract void colisionar(EntidadTablero entidad);
    
    public Coordenada getPosicion() {
        return posicion;
    }
    
    protected void setPosicion(Coordenada posicion) {
        this.posicion = posicion;
    }
}
