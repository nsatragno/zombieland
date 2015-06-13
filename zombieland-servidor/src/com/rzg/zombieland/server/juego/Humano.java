package com.rzg.zombieland.server.juego;

import com.rzg.zombieland.comunes.misc.Coordenada;


/**
 * Cobarde animal que solo sabe correr por su vida.
 * @author nicolas
 *
 */
public class Humano extends Personaje {

    // TODO definir sprite.
    private final String SPRITE = "humano.png";
    
    @Override
    public String getSprite() {
        return SPRITE;
    }

    @Override
    public void colisionar(EntidadTablero entidad) {
        // TODO Auto-generated method stub
    }
  
}
