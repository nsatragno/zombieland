package com.rzg.zombieland.server.juego;

import com.rzg.zombieland.comunes.misc.Coordenada;

/**
 * Elemento que vive en un tablero. 
 * @author nicolas
 *
 */
public abstract class EntidadTablero {
    // Posición que esta entidad ocupa en el tablero.
    private Coordenada posicion;
    
    public EntidadTablero(Coordenada posicion) {
        this.posicion = posicion;
    }
    
    /**
     * @return el nombre de la imagen con la que esta entidad se representa.
     */
    public abstract String getSprite();
    
    /**
     * Hace colisionar esta entidad con otra, posiblemente afectándolas.
     * @param entidad la entidad con la que se está colisionando.
     */
    public abstract void colisionar(EntidadTablero entidad, EntidadTablero[][] matriz);
    
    /**
     * @return la posición actual en el tablero de la entidad.
     */
    public Coordenada getPosicion() {
        return posicion;
    }
    
    protected void setPosicion(Coordenada posicion) {
        this.posicion = posicion;
    }

	public abstract boolean esPersonaje();
}
