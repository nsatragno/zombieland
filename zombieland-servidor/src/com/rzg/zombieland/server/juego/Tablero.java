package com.rzg.zombieland.server.juego;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero;
import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero.POJOEntidad;
import com.rzg.zombieland.comunes.misc.Avatar;
import com.rzg.zombieland.comunes.misc.Coordenada;
import com.rzg.zombieland.server.sesion.Jugador;

/**
 * El tablero de juego, que contiene obstáculos, humanos y zombies.
 * 
 * @author Nicolas, Manuel
 *
 */
public class Tablero {
	// El tablero en sí, con todos sus actores. Incluidos obstáculos.
	private EntidadTablero[][] matriz;

	// Jugadores que participan - Humanos solamente.
	private List<Jugador> jugadores;

	// Posición en el listado de personajes del que se moverá primero.
	private int primerPersonaje;

	// Personaje de la ronda que arranca como zombi.
	private Personaje zombi;

	/**
	 * Constructor por defecto. Aquí se generarán los obstáculos en forma
	 * 'aleatoria'
	 */
	public Tablero(int casilleros, List<Jugador> jugadores, Personaje zombi) {
		Random rnd = new Random(); // Que quede claro que va a ser una cuestión
									// de suerte
		boolean resuelto = false; // Flag que me indica si ya posicione o no a
									// la entidad.

		Coordenada c;

		matriz = new EntidadTablero[casilleros][casilleros];
		// Ponemos al zombi - primero le asignamos el nombre.
		zombi.setPosicion(new Coordenada(casilleros / 2, casilleros / 2));
		// Siempre arranca en el medio.
		matriz[casilleros / 2][casilleros / 2] = zombi; // Lo ponemos en la
														// matriz.

		// Ponemos los obstaculos. Si la matriz es de 10x10, son 100 casilleros.
		// Con 25 obstáculos estariamos bien -- Sería el 25%
		// Con el 30%, usando el algoritmo aleatorio, puede terminar en un bucle
		// infinito. Cosas que pasan.

		// Totalmente aleatorio - Resultado: En 10 corridas,
		// 4 veces encerró a un jugador.
		// Verificando que en las diagonales tampoco haya obstaculos -
		// Resultado: Luz verde. En 100000 corridas, luz verde.
		
		for (int i = 0; i < Math.pow(casilleros, 2) * 0.25; i++) {
			resuelto = false;
			while (!resuelto) {
				c = new Coordenada(Math.abs(rnd.nextInt()) % casilleros,
						Math.abs(rnd.nextInt()) % casilleros);
				if (matriz[c.getX()][c.getY()] == null
						&& matriz[c.getX() + 1 >= casilleros ? c.getX() : c
								.getX() + 1][c.getY() + 1 >= casilleros ? c
								.getY() : c.getY() + 1] == null
						&& matriz[c.getX() - 1 < 0 ? c.getX() : c.getX() - 1][c
								.getY() + 1 >= casilleros ? c.getY()
								: c.getY() + 1] == null
						&& matriz[c.getX() + 1 >= casilleros ? c.getX() : c
								.getX() + 1][c.getY() - 1 < 0 ? c.getY() : c
								.getY() - 1] == null
						&& matriz[c.getX() - 1 < 0 ? c.getX() : c.getX() - 1][c
								.getY() - 1 < 0 ? c.getY() : c.getY() - 1] == null) {
					matriz[c.getX()][c.getY()] = new Obstaculo(c);
					resuelto = true;
				}
			}
		}

		// Ponemos a los humanos
		// Los humanos se crean a partir de los nombres de los jugadores.
		for (Jugador jugador : jugadores) {
			resuelto = false;
			while (!resuelto) {
				c = new Coordenada(Math.abs(rnd.nextInt()) % casilleros,
						Math.abs(rnd.nextInt()) % casilleros);
				if (matriz[c.getX()][c.getY()] == null) {
					Humano h = new Humano(jugador.getNombre());
					h.setPosicion(c);
					matriz[c.getX()][c.getY()] = h;
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
		List<POJOEntidad> entidades = new ArrayList<POJOEntidad>();
		// Recorro mi matriz de entidades en los limites indicados por el
		// metodo.
		for (int i = superiorIzquierda.getX(); i < inferiorDerecha.getX(); i++) {
			for (int j = superiorIzquierda.getY(); j < inferiorDerecha.getY(); i++) {
				if (matriz[i][j] != null) {
					// Agrego las entidades que encuentre a la lista de la
					// proyeccion
					entidades.add(new POJOEntidad("Elemento" + i + j,
							new Coordenada(i, j), // Cada entidad ya tiene su
													// posicion
							Avatar.HOMBRE)); // Acá iria el avatar
												// correspondiente.
				}
			}
		}
		// Devuelvo la proyección. Chiche bombón.
		return new ProyeccionTablero(matriz.length, superiorIzquierda,
				inferiorDerecha, entidades);
	}

	/**
	 * Obtiene la entidad por coordenada.
	 * 
	 * @param coordenada
	 * @return la entidad en la coordenada dada, o null si no hay ninguna.
	 */
	public EntidadTablero getEntidadEn(Coordenada coordenada) {
		return matriz[coordenada.getX()][coordenada.getY()];
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
