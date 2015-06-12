package com.rzg.zombieland.comunes.comunicacion;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;

import javax.swing.ImageIcon;

import com.rzg.zombieland.comunes.misc.Avatar;
import com.rzg.zombieland.comunes.misc.Coordenada;

/**
 * Proyecta la parte del tablero que un personaje puede ver.
 * 
 * @author nicolas
 *
 */
public class ProyeccionTablero extends Enviable {
	/**
	 * Identifica una entidad de la proyección.
	 * 
	 * @author nicolas
	 *
	 */
	public class POJOEntidad {
		// El nombre de esta entidad. Puede ser, por ejemplo, el nombre del
		// jugador que controla al
		// personaje.
		private String etiqueta;

		// La coordenada de la entidad.
		private Coordenada coordenada;

		// El avatar de la misma.
		private Avatar avatar;

		public POJOEntidad(String etiqueta, Coordenada coordenada, Avatar avatar) {
			this.etiqueta = etiqueta;
			this.coordenada = coordenada;
			this.avatar = avatar;
		}

		public Coordenada getCoordenada() {
			return coordenada;
		}
	}

	// Tamaño total del tablero - Ancho/largo de la matriz en casilleros
	private int casilleros;

	// Las esquina que esta proyección revela del mapa.
	private Coordenada esquinaSuperiorIzquierda;
	private Coordenada esquinaInferiorDerecha;

	// Entidades visibles del tablero.
	private List<POJOEntidad> entidades;

	private boolean primeraVez = true;

	public ProyeccionTablero() {

	}

	/**
	 * @param casilleros
	 *            ancho / largo en casilleros
	 * @param esquinaSuperiorIzquierda
	 *            de la proyeccion
	 * @param esquinaInferiorDerecha
	 *            de la proyeccion
	 * @param entidades
	 */
	public ProyeccionTablero(int casilleros,
			Coordenada esquinaSuperiorIzquierda,
			Coordenada esquinaInferiorDerecha, List<POJOEntidad> entidades) {
		this.casilleros = casilleros;
		this.esquinaSuperiorIzquierda = esquinaSuperiorIzquierda;
		this.esquinaInferiorDerecha = esquinaInferiorDerecha;
		this.entidades = entidades;
	}

	public List<POJOEntidad> getEntidades() {
		return entidades;
	}

	public void paint(Graphics g, Image[] img, int anchoTablero,
			int margenIzquierdo, int margenSuperior, ImageIcon fondo) {
		int anchoCasillero = anchoTablero / casilleros;
		int anchoReal = getAnchoEfectivo(anchoTablero);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(Color.BLACK);
		// La cuadrícula y el fondo -- ESTA ES LA SOLUCION --
		if (primeraVez) {
			g.drawImage(fondo.getImage(), margenIzquierdo, margenSuperior,
					getAnchoEfectivo(anchoTablero),
					getAnchoEfectivo(anchoTablero), null);
			for (int i = 0; i <= casilleros; i++) {
				g2D.drawLine(margenIzquierdo + anchoCasillero * i,
						margenSuperior, margenIzquierdo + anchoCasillero * i,
						margenSuperior + anchoReal);
				g2D.drawLine(margenIzquierdo, margenSuperior + anchoCasillero
						* i, margenIzquierdo + anchoReal, margenSuperior
						+ anchoCasillero * i);
			}
			primeraVez = false;
		}
		int j = 0;
		for (POJOEntidad entidad : entidades) {
			g.drawImage(img[j], entidad.getCoordenada().getX() * anchoCasillero
					+ margenIzquierdo, entidad.getCoordenada().getY()
					* anchoCasillero + margenSuperior, anchoCasillero,
					anchoCasillero, null);
			j++;
		}

		// La famosa 'Proyeccion'
		// Primero ajustamos la transparencia, a pedido de Iván.
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.6f);
		g2D.setComposite(ac);
		// Son 4 rectangulos. Uno arriba, uno abajo y 2 a cada lado.
		g2D.fillRect(margenIzquierdo, margenSuperior, anchoReal,
				esquinaSuperiorIzquierda.getY() * anchoCasillero);
		g2D.fillRect(margenIzquierdo, esquinaInferiorDerecha.getY()
				* anchoCasillero + margenSuperior, anchoReal, anchoReal
				- esquinaInferiorDerecha.getY() * anchoCasillero);
		g2D.fillRect(margenIzquierdo, esquinaSuperiorIzquierda.getY()
				* anchoCasillero + margenSuperior,
				esquinaSuperiorIzquierda.getX() * anchoCasillero,
				esquinaInferiorDerecha.getY() * anchoCasillero
						- esquinaSuperiorIzquierda.getY() * anchoCasillero);
		g2D.fillRect(esquinaInferiorDerecha.getX() * anchoCasillero
				+ margenIzquierdo, esquinaSuperiorIzquierda.getY()
				* anchoCasillero + margenSuperior, anchoReal
				- esquinaInferiorDerecha.getX() * anchoCasillero,
				esquinaInferiorDerecha.getY() * anchoCasillero
						- esquinaSuperiorIzquierda.getY() * anchoCasillero);
	}

	// Este método devuelve siempre el ancho necesario del tablero
	// para que los casilleros entren justo (elimina los excedentes)
	public int getAnchoEfectivo(int dimension) {
		return dimension - dimension % casilleros;
	}
}
