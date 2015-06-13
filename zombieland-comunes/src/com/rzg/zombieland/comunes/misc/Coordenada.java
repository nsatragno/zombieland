package com.rzg.zombieland.comunes.misc;

/**
 * Coordenada X e Y en un tablero. Las coordenadas se miden de arriba a abajo y de izquierda a 
 * derecha.
 * @author nicolas
 *
 */
public class Coordenada {
    // Pares de coordenadas.
    private int x;
    private int y;
   
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
     * Establece la posición X.
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }
    
    
    /**
     * @return la posición Y.
     */
    public int getY() {
        return y;
    }
    
    /**
     * Establece la posición Y.
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }
}
