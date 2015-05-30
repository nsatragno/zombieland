package com.rzg.zombieland.server.misc;

/**
 * Coordenada X e Y en un tablero.
 * @author nicolas
 *
 */
public class Coordenada {
    // Pares de coordenadas.
    private int x;
    private int y;
    
    public Coordenada(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public int getX() {
        return x;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getY() {
        return y;
    }
}
