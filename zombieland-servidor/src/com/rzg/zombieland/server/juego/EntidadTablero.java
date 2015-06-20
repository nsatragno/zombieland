package com.rzg.zombieland.server.juego;

import com.rzg.zombieland.comunes.misc.Avatar;
import com.rzg.zombieland.comunes.misc.Coordenada;

/**
 * Elemento que vive en un tablero. 
 * @author nicolas
 *
 */
public abstract class EntidadTablero {
    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((posicion == null) ? 0 : posicion.hashCode());
        return result;
    }

    // Posición que esta entidad ocupa en el tablero.
    private Coordenada posicion;
    
    public EntidadTablero(Coordenada posicion) {
        this.posicion = posicion;
    }
    
    /**
     * @return el avatar para dibujar la entidad en el tablero.
     */
    public abstract Avatar getAvatar();
    
    /**
     * Hace colisionar esta entidad con otra, posiblemente afectándolas.
     * @param entidad la entidad con la que se está colisionando.
     */
    public abstract void colisionar(EntidadTablero entidad);
    
    /**
     * @return la posición actual en el tablero de la entidad.
     */
    public Coordenada getPosicion() {
        return posicion;
    }
    
    protected void setPosicion(Coordenada posicion) {
        this.posicion = posicion;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EntidadTablero other = (EntidadTablero) obj;
        if (posicion == null) {
            if (other.posicion != null)
                return false;
        } else if (!posicion.equals(other.posicion))
            return false;
        return true;
    }
    
	public abstract boolean esPersonaje();
}
