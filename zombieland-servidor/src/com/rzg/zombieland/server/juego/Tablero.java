package com.rzg.zombieland.server.juego;

import java.util.List;
import java.util.Random;

import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero;
import com.rzg.zombieland.comunes.misc.Coordenada;

/**
 * El tablero de juego, que contiene obstáculos, humanos y zombies.
 * 
 * @author Nicolas, Manuel
 *
 */
public class Tablero {
	// El tablero en sí, con todos sus actores. Incluidos obstáculos.
	private EntidadTablero[][] matriz;

	// Personajes que participan del juego - Humanos solamente.
	private List<Personaje> personajes;

	// Posición en el listado de personajes del que se moverá primero.
	private int primerPersonaje;

	// Personaje de la ronda que arranca como zombi.
	private Personaje zombi;

	/**
	 * Constructor por defecto. Aquí se generarán los obstáculos en forma
	 * 'aleatoria'
	 */
	public Tablero(int casilleros, List<Personaje> personajes, Personaje zombi) {
		Random rnd = new Random(); // Que quede claro que va a ser una cuestión
									// de suerte
		boolean resuelto = false; // Flag que me indica si ya posicione o no a
									// la entidad.

		Coordenada c;

		matriz = new EntidadTablero[casilleros][casilleros]; // Matriz de
																// entidades
																// creada //
																// entidades

		// Ponemos al zombi
		zombi.setPosicion(new Coordenada(casilleros / 2, casilleros / 2)); // Siempre
																			// arranca
																			// en
																			// el
																			// medio.
		matriz[casilleros / 2][casilleros / 2] = zombi; // Lo ponemos en la
														// matriz.

		// Ponemos los obstaculos. Si la matriz es de 10x10, son 100 casilleros.
		// Con 30 obstáculos estariamos bien -- Sería el 30%
		// NOTA - Es muy aleatorio, si vemos que no sale bien, probamos otras
		// cosas.
		for (int i = 0; i < Math.pow(casilleros, 2) * 0.3; i++) {
			resuelto = false;
			while (!resuelto) {
				c = new Coordenada(rnd.nextInt() % casilleros, rnd.nextInt()
						% casilleros);
				if (matriz[c.getX()][c.getY()] == null
					&& matriz[c.getX() + 1][c.getY()] == null
					&& matriz[c.getX() - 1][c.getY()] == null
					&& matriz[c.getX()][c.getY() + 1] == null
					&& matriz[c.getX()][c.getY() - 1] == null
					&& matriz[c.getX() + 1][c.getY() - 1] == null
					&& matriz[c.getX() + 1][c.getY() + 1] == null
					&& matriz[c.getX() - 1][c.getY() - 1] == null
					&& matriz[c.getX() - 1][c.getY() + 1] == null) {
					// Basicamente, inserta un obstaculo si a la redonda no hay nada. 
					// Esto evita tener obstaculos adyacentes y evita encerrar al zombi.
					matriz[c.getX()][c.getY()] = new Obstaculo(c);
				}
			}
		}

		// Ponemos a los humanos
		for (Personaje personaje : personajes) {
			resuelto = false;
			while (!resuelto) {
				c = new Coordenada(rnd.nextInt() % casilleros, rnd.nextInt()
						% casilleros);
				if (matriz[c.getX()][c.getY()] == null) {
					matriz[c.getX()][c.getY()] = personaje;
					personaje.setPosicion(c);
					resuelto = true;
				}
			}

		}

	}

	/**
	 * @param superiorIzquierda
	 * @param inferiorDerecha
	 * @return la proyección del tablero entre las dos esquinas dadas.
	 */
	public ProyeccionTablero getProyeccion(Coordenada superiorIzquierda,
			Coordenada inferiorDerecha) {
		// TODO implementar.
		return null;
	}

	/**
	 * Obtiene la entidad por coordenada.
	 * 
	 * @param coordenada
	 * @return la entidad en la coordenada dada, o null si no hay ninguna.
	 */
	public EntidadTablero getEntidadEn(Coordenada coordenada) {
		// TODO implementar.
		return null;
	}

	/**
	 * Mueve una entidad.
	 * 
	 * @param desde
	 *            - coordenada donde la entidad original se encuentra.
	 * @param hasta
	 *            - coordenada de destino. Debe estar vacía.
	 */
	public void moverEntidad(Coordenada desde, Coordenada hasta) {
		// TODO implementar.
	}
}
