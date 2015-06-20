package com.rzg.zombieland.server.juego;

import com.rzg.zombieland.comunes.misc.Avatar;
import com.rzg.zombieland.comunes.misc.Coordenada;

/**
 * Entidad inamovible del juego cuyo único propósito es impedir el movimiento.
 * @author nicolas
 *
 */
public class Obstaculo extends EntidadTablero {

    public Obstaculo(Coordenada posicion) {
    	super(posicion);
    }
    
    @Override
    public void colisionar(EntidadTablero entidad) { }

    public boolean esPersonaje(){
    	return false;
    }

    @Override
    public Avatar getAvatar() {
        return Avatar.OBSTACULO;
    }

}
