package com.rzg.zombieland.comunes.comunicacion;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.util.List;
import java.util.Map;

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
	public static class POJOEntidad {
		// El nombre de esta entidad. Puede ser, por ejemplo, el nombre del
		// jugador que controla al
		// personaje.
		private String etiqueta;

		// La coordenada de la entidad.
		private Coordenada coordenada;

		// El avatar de la misma.
		private Avatar avatar;

		/**
		 * Crea un POJO de entidad a través de los parámetros básicos.
		 * 
		 * @param etiqueta
		 * @param coordenada
		 * @param avatar
		 */
		public POJOEntidad(String etiqueta, Coordenada coordenada, Avatar avatar) {
			this.etiqueta = etiqueta;
			this.coordenada = coordenada;
			this.avatar = avatar;
		}

		/**
		 * @return la coordenada de la entidad.
		 */
		public Coordenada getCoordenada() {
			return coordenada;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result
					+ ((avatar == null) ? 0 : avatar.hashCode());
			result = prime * result
					+ ((coordenada == null) ? 0 : coordenada.hashCode());
			result = prime * result
					+ ((etiqueta == null) ? 0 : etiqueta.hashCode());
			return result;
		}

		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			POJOEntidad other = (POJOEntidad) obj;
			if (avatar != other.avatar)
				return false;
			if (coordenada == null) {
				if (other.coordenada != null)
					return false;
			} else if (!coordenada.equals(other.coordenada))
				return false;
			if (etiqueta == null) {
				if (other.etiqueta != null)
					return false;
			} else if (!etiqueta.equals(other.etiqueta))
				return false;
			return true;
		}

		public Avatar getAvatar() {
			return avatar;
		}
	}

	// Tamaño total del tablero - Ancho/largo de la matriz en casilleros
	private int casilleros;

	// Las esquina que esta proyección revela del mapa.
	private Coordenada esquinaSuperiorIzquierda;
	private Coordenada esquinaInferiorDerecha;

	// Entidades visibles del tablero.
	private List<POJOEntidad> entidades;
	
	// El tiempo, en milisegundos, para que arranque el próximo paso.
	private int tiempoParaElSiguientePaso;

	/**
	 * @param casilleros
	 *            ancho / largo en casilleros
	 * @param esquinaSuperiorIzquierda
	 *            de la proyeccion
	 * @param esquinaInferiorDerecha
	 *            de la proyeccion
	 * @param entidades
	 * @param tiempoParaElSiguientePaso
	 */
	public ProyeccionTablero(int casilleros,
			Coordenada esquinaSuperiorIzquierda,
			Coordenada esquinaInferiorDerecha,
			List<POJOEntidad> entidades,
			int tiempoParaElSiguientePaso) {
		this.casilleros = casilleros;
		this.esquinaSuperiorIzquierda = esquinaSuperiorIzquierda;
		this.esquinaInferiorDerecha = esquinaInferiorDerecha;
		this.entidades = entidades;
        this.tiempoParaElSiguientePaso = tiempoParaElSiguientePaso;
	}

	/**
	 * @return las entidades que figuran en la proyección.
	 */
	public List<POJOEntidad> getEntidades() {
		return entidades;
	}

	public int getCasilleros() {
		return casilleros;
	}

	/**
	 * Dibuja una proyección de tablero.
	 * 
	 * @param g
	 * @param img
	 * @param anchoTablero
	 * @param margenIzquierdo
	 * @param margenSuperior
	 * @param fondo
	 */
	public void paint(Graphics g, Map<Avatar, Image> img, int anchoTablero,
			int margenIzquierdo, int margenSuperior, ImageIcon fondo) {
		int anchoCasillero = anchoTablero / casilleros;
		int anchoReal = getAnchoEfectivo(anchoTablero);
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(Color.BLACK);
		// La cuadrícula y el fondo -- ESTA ES LA SOLUCION --
		g.drawImage(fondo.getImage(), margenIzquierdo, margenSuperior,
				getAnchoEfectivo(anchoTablero), getAnchoEfectivo(anchoTablero),
				null);
		for (int i = 0; i <= casilleros; i++) {
			g2D.drawLine(margenIzquierdo + anchoCasillero * i, margenSuperior,
					margenIzquierdo + anchoCasillero * i, margenSuperior
							+ anchoReal);
			g2D.drawLine(margenIzquierdo, margenSuperior + anchoCasillero * i,
					margenIzquierdo + anchoReal, margenSuperior
							+ anchoCasillero * i);
		}
		for (POJOEntidad entidad : entidades) {
			g.drawImage(img.get(entidad.getAvatar()), entidad.getCoordenada()
					.getX() * anchoCasillero + margenIzquierdo, entidad
					.getCoordenada().getY() * anchoCasillero + margenSuperior,
					anchoCasillero, anchoCasillero, null);
		}

		// La famosa 'Proyeccion'
		// Primero ajustamos la transparencia, a pedido de Iván.
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.8f);
		g2D.setComposite(ac);
		// Son 4 rectangulos. Uno arriba, uno abajo y 2 a cada lado.
		g2D.fillRect(margenIzquierdo, margenSuperior, anchoReal,
				esquinaSuperiorIzquierda.getY() * anchoCasillero);
		g2D.fillRect(margenIzquierdo, (esquinaInferiorDerecha.getY() + 1)
				* anchoCasillero + margenSuperior, anchoReal, anchoReal
				- (esquinaInferiorDerecha.getY() + 1) * anchoCasillero);
		g2D.fillRect(margenIzquierdo, esquinaSuperiorIzquierda.getY()
				* anchoCasillero + margenSuperior,
				esquinaSuperiorIzquierda.getX() * anchoCasillero,
				esquinaInferiorDerecha.getY() * anchoCasillero
						- (esquinaSuperiorIzquierda.getY() - 1)
						* anchoCasillero);
		g2D.fillRect((esquinaInferiorDerecha.getX() + 1) * anchoCasillero
				+ margenIzquierdo, esquinaSuperiorIzquierda.getY()
				* anchoCasillero + margenSuperior, anchoReal
				- (esquinaInferiorDerecha.getX() + 1) * anchoCasillero,
				esquinaInferiorDerecha.getY() * anchoCasillero
						- (esquinaSuperiorIzquierda.getY() - 1)
						* anchoCasillero);
	}

	/**
	 * @param dimension
	 * @return el ancho necesario del tablero para que los casilleros entren
	 *         justo (elimina los excedentes)
	 */
	public int getAnchoEfectivo(int dimension) {
		return dimension - dimension % casilleros;
	}
	
	public int getTiempoParaElSiguientePaso() {
	    return tiempoParaElSiguientePaso;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + casilleros;
		result = prime * result
				+ ((entidades == null) ? 0 : entidades.hashCode());
		result = prime
				* result
				+ ((esquinaInferiorDerecha == null) ? 0
						: esquinaInferiorDerecha.hashCode());
		result = prime
				* result
				+ ((esquinaSuperiorIzquierda == null) ? 0
						: esquinaSuperiorIzquierda.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProyeccionTablero other = (ProyeccionTablero) obj;
		if (casilleros != other.casilleros)
			return false;
		if (entidades == null) {
			if (other.entidades != null)
				return false;
		} else if (!entidades.equals(other.entidades))
			return false;
		if (esquinaInferiorDerecha == null) {
			if (other.esquinaInferiorDerecha != null)
				return false;
		} else if (!esquinaInferiorDerecha.equals(other.esquinaInferiorDerecha))
			return false;
		if (esquinaSuperiorIzquierda == null) {
			if (other.esquinaSuperiorIzquierda != null)
				return false;
		} else if (!esquinaSuperiorIzquierda
				.equals(other.esquinaSuperiorIzquierda))
			return false;
		return true;
	}
}
