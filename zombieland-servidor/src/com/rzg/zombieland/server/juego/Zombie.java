package com.rzg.zombieland.server.juego;

import com.rzg.zombieland.comunes.misc.Coordenada;
import com.rzg.zombieland.server.sesion.Jugador;

/**
 * Personaje cuyo único deseo en el mundo es comer cerebros. RAWR!
 * 
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
	
	public Zombie(Jugador jugador) {
	    super(jugador);
    }

	@Override
	public void colisionar(EntidadTablero entidad, EntidadTablero[][] matriz) {
		// Si hay una colision llamada por un zombi que recibió por parámetro un
		// humano
		// Hay cosas que hacer.
		if (entidad.esPersonaje()) {
			if (entidad.getClass() == Humano.class) {
				// Cambio al humano por un nuevo zombie.
				Coordenada posicion = entidad.getPosicion();
				entidad = new Zombie(((Humano) entidad).getJugador());
				matriz[posicion.getX()][posicion.getY()] = entidad;
			}
		}
		// Si no pasó nada devuelvo la misma entidad.
	}

	public boolean esPersonaje() {
		return true;
	}
}
