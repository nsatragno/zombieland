package com.rzg.zombieland.server.juego;

import com.rzg.zombieland.comunes.misc.Coordenada;
import com.rzg.zombieland.server.sesion.Jugador;

/**
 * Cobarde animal que solo sabe correr por su vida.
 * 
 * @author nicolas
 *
 */
public class Humano extends Personaje {

	// TODO definir sprite.
	private final String SPRITE = "humano.png";

	// Permite construir un humano a través de un Jugador.
	public Humano(Jugador jugador, Coordenada posicion, Tablero tablero) {
	    super(jugador, posicion, tablero);
	}

	@Override
	public String getSprite() {
		return SPRITE;
	}

	@Override
	public void colisionar(EntidadTablero e) { }

	@Override
	public boolean esPersonaje() {
		return true;
	}

    @Override
    public boolean esZombie() {
        return false;
    }
}
