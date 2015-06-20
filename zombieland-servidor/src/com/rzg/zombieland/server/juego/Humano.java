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
	public void colisionar(EntidadTablero e, EntidadTablero[][] matriz) {
		// Si hay una colision llamada por un humano que recibió por parámetro
		// un
		// zombie, hay cosas que hacer.
		// El primer if filtra a los obstáculos.
		if (e.esPersonaje()) {
			if (e.getClass() == Zombie.class) {
				// Cambio al humano por un nuevo zombie.
				Coordenada posicion = this.getPosicion();
				EntidadTablero entidad = new Zombie(getJugador(), posicion, getTablero());
				matriz[posicion.getX()][posicion.getY()] = entidad;
			}
		}
	}

	@Override
	public boolean esPersonaje() {
		return true;
	}
}
