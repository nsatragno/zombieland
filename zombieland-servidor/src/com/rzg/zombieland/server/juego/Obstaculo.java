package com.rzg.zombieland.server.juego;

/**
 * Entidad inamovible del juego cuyo único propósito es impedir el movimiento.
 * @author nicolas
 *
 */
public class Obstaculo extends EntidadTablero {

    private final String SPRITE = "obstaculo.jpg";
    
    @Override
    public String getSprite() {
        return SPRITE;
    }

    @Override
    public void colisionar(EntidadTablero entidad) {
        // TODO Auto-generated method stub
    }

}
