package com.rzg.zombieland.comunes.misc;

/**
 * Coordenada X e Y en un tablero. Las coordenadas se miden de arriba a abajo y de izquierda a 
 * derecha.
 * @author nicolas
 *
 */
public class Coordenada {
    // Pares de coordenadas.
    private final int x;
    private final int y;
   
    /**
     * Construye el par de coordenadas.
     * @param x
     * @param y
     */
    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * @return la posición X.
     */
    public int getX() {
        return x;
    }
    
    /**
     * @return la posición Y.
     */
    public int getY() {
        return y;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
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
        Coordenada other = (Coordenada) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }
}
