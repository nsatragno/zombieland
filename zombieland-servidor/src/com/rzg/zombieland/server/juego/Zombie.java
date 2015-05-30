package com.rzg.zombieland.server.juego;

/**
 * Personaje cuyo único deseo en el mundo es comer cerebros. RAWR!
 * @author nicolas
 *
 */
public class Zombie extends Personaje {

    // TODO definir sprite.
    private final String SPRITE = "zombie.jpg";
    
    @Override
    public String getSprite() {
        return SPRITE;
    }

    @Override
    public void colisionar(EntidadTablero entidad) {
        // TODO convertir entidad en zombie.
    }

}
