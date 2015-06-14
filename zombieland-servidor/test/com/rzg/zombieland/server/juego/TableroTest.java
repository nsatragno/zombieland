package com.rzg.zombieland.server.juego;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.rzg.zombieland.comunes.misc.Coordenada;
import com.rzg.zombieland.server.sesion.Jugador;

/**
 * @author Manuel
 */

public class TableroTest {

	/**
	 * Verifica que al crearse el tablero la posición inicial del zombi sea el
	 * centro de éste.
	 */
	@Test
	public void testPosicionZombie() {
		java.util.List<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(new Jugador("Humano1"));
		jugadores.add(new Jugador("Humano2"));
		Jugador zombi = new Jugador("Zombi1");
		Personaje zombie = new Zombie(zombi.getNombre());
		Tablero tablero = new Tablero(10, jugadores, zombie);
		Assert.assertEquals(tablero.getEntidadEn(new Coordenada(5, 5)), zombie);
	}

	/**
	 * Verifica en n iteraciones si el zombi quedó encerrado por obstáculos
	 * debido a la distribución aleatoria de los mismos.
	 */
	public boolean jugadoresEncerrados() {
		java.util.List<Jugador> jugadores = new ArrayList<Jugador>();
		Jugador zombi = new Jugador("Zombi1");
		Personaje zombie = new Zombie(zombi.getNombre());
		for (int i = 0; i < 100000; i++) {
			Tablero tablero = new Tablero(10, jugadores, zombie);
			// El truco está en verificar las cuatro puntas, ya que los
			// movimientos
			// no pueden ser diagonales. Pruebo con el zombi porque está en el
			// medio
			// y la probabilidad es la misma. Si pasa este test, asumo que pasan
			// todos.
			Coordenada posicion = zombie.getPosicion();
			if (tablero.getEntidadEn(new Coordenada(posicion.getX() + 1,
					posicion.getY())) != null
					&& tablero.getEntidadEn(new Coordenada(posicion.getX(),
							posicion.getY() + 1)) != null
					&& tablero.getEntidadEn(new Coordenada(posicion.getX() - 1,
							posicion.getY())) != null
					&& tablero.getEntidadEn(new Coordenada(posicion.getX(),
							posicion.getY() + 1)) != null) {
				return true;
			}
		}
		return false;
	}

	@Test
	public void testJugadoresEncerrados() {
		Assert.assertEquals(false, jugadoresEncerrados());
	}
	
	/**
	 * Devuelve la cantidad de personajes de un tablero.
	 * @param t El tablero en cuestión
	 */
	public int cantidadPersonajes(Tablero t) {
		int acum = 0;
		for(int i = 0; i < 10; i ++)
			for(int j = 0; j < 10; j ++)
				if(t.getEntidadEn(new Coordenada(i,j)) != null &&
						t.getEntidadEn(new Coordenada(i,j)).esPersonaje())
					acum++;
		return acum;
	}
	
	/**
	 * Testea que la cantidad de jugadores del tablero sea la misma que la
	 * cantidad de jugadores en el juego.
	 */
	@Test
	public void testCantidadDeJugadores() {
		java.util.List<Jugador> jugadores = new ArrayList<Jugador>();
		jugadores.add(new Jugador("Humano1"));
		jugadores.add(new Jugador("Humano2"));
		jugadores.add(new Jugador("Humano3"));
		Jugador zombi = new Jugador("Zombi1");
		Personaje zombie = new Zombie(zombi.getNombre());
		Tablero tablero = new Tablero(10, jugadores, zombie);
		Assert.assertEquals(4,cantidadPersonajes(tablero));
	}
}
