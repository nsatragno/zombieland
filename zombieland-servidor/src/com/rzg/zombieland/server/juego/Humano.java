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
	
	// El largo de un lado del cuadrado de visión del humano.
	private final static int TAMAÑO_VISION = 5;

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

    @Override
    public Coordenada[] getRectanguloVision() {
        Coordenada[] rectangulo = new Coordenada[2];
        int delta = (TAMAÑO_VISION - 1) / 2;
        rectangulo[0] = new Coordenada(getPosicion().getX() - delta, getPosicion().getY() - delta);
        rectangulo[1] = new Coordenada(getPosicion().getX() + delta, getPosicion().getY() + delta);
        return rectangulo;
    }
}
