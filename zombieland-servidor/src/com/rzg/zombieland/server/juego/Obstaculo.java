package com.rzg.zombieland.server.juego;

import com.rzg.zombieland.comunes.misc.Coordenada;

/**
 * Entidad inamovible del juego cuyo único propósito es impedir el movimiento.
 * @author nicolas
 *
 */
public class Obstaculo extends EntidadTablero {

    private final String SPRITE = "obstaculo.jpg";
    private Coordenada posicion;
    
    public Obstaculo(Coordenada posicion) {
    	this.posicion = posicion;
    }
    
    @Override
    public String getSprite() {
        return SPRITE;
    }

    @Override
    public void colisionar(EntidadTablero entidad, EntidadTablero[][] matriz) {
        // TODO Auto-generated method stub
    }

    public boolean esPersonaje(){
    	return false;
    }

}
