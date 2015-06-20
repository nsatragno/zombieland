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
}
