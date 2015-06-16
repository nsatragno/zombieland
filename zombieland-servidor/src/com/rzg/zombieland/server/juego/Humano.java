package com.rzg.zombieland.server.juego;

import com.rzg.zombieland.comunes.misc.Coordenada;

/**
 * Cobarde animal que solo sabe correr por su vida.
 * 
 * @author nicolas
 *
 */
public class Humano extends Personaje {

	// TODO definir sprite.
	private final String SPRITE = "humano.png";

	// Usuario que identifica al humano. Puede servir más adelante para colocar
	// el nombre por encima.
	private String usuario;

	// Permite construir un humano a través de un Jugador.
	public Humano(String usuario) {
		this.usuario = usuario;
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
				EntidadTablero entidad = new Zombie(usuario);
				matriz[posicion.getX()][posicion.getY()] = entidad;
			}
		}
	}

	public String getUsuario() {
		return usuario;
	}

	public boolean esPersonaje() {
		return true;
	}
}
