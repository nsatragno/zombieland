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
	
	public Zombie(Jugador jugador, Coordenada posicion, Tablero tablero) {
	    super(jugador, posicion, tablero);
    }

	@Override
	public void colisionar(EntidadTablero entidad) {
		if (entidad.esPersonaje()) {
		    Personaje personaje = (Personaje)entidad;
		    if (!personaje.esZombie()) {
				// Cambio al humano por un nuevo zombie.
		        Coordenada posicion = entidad.getPosicion();
		        Zombie zombie = new Zombie(personaje.getJugador(), posicion, getTablero()); 
		        getTablero().remplazarEntidadEn(posicion, zombie);
			}
		}
	}

	public boolean esPersonaje() {
		return true;
	}
	
	@Override
	public boolean esZombie() {
	    return true;
	}
}
