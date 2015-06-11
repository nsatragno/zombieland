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
public class ProyeccionTablero extends Enviable // Hacer constructor.
{
	/**
	 * Identifica una entidad de la proyección.
	 * 
	 * @author nicolas
	 *
	 */
	public class POJOEntidad
	{
		// El nombre de esta entidad. Puede ser, por ejemplo, el nombre del
		// jugador que controla al
		// personaje.
		private String etiqueta;

		// La coordenada de la entidad.
		private Coordenada coordenada;

		// El avatar de la misma.
		private Avatar avatar;

		public POJOEntidad(String etiqueta, Coordenada coordenada, Avatar avatar)
		{
			this.etiqueta = etiqueta;
			this.coordenada = coordenada;
			this.avatar = avatar;
		}

		public Coordenada getCoordenada()
		{
			return coordenada;
		}
	}

	// Tamaño total del tablero - Ancho de la matriz en casilleros
	private int ancho;
	private int largo;

	// Las esquina que esta proyección revela del mapa.
	private Coordenada esquinaSuperiorIzquierda;
	private Coordenada esquinaInferiorDerecha;

	// Entidades visibles del tablero.
	private List<POJOEntidad> entidades;

	public ProyeccionTablero()
	{

	}

	/**
	 * @param ancho
	 *            en casilleros
	 * @param largo
	 *            en casilleros
	 * @param esquinaSuperiorIzquierda
	 *            de la proyeccion
	 * @param entidades
	 */
	public ProyeccionTablero(int ancho, int largo,
			Coordenada esquinaSuperiorIzquierda, List<POJOEntidad> entidades)
	{
		this.ancho = ancho;
		this.largo = largo;
		this.esquinaSuperiorIzquierda = esquinaSuperiorIzquierda;
		this.entidades = entidades;
	}

	public List<POJOEntidad> getEntidades()
	{
		return entidades;
	}

	public void paint(Graphics g, Image[] img, int anchoTablero,
			int margenIzquierdo, int margenSuperior, ImageIcon fondo)
	{
		int anchoCasillero = anchoTablero / ancho;
		Graphics2D g2D = (Graphics2D) g;
		g2D.setColor(Color.BLACK);
		// La cuadrícula
		for (int i = 0; i <= ancho; i++)
		{
			g2D.drawLine(margenIzquierdo + anchoCasillero * i, margenSuperior,
					margenIzquierdo + anchoCasillero * i, margenSuperior + getAnchoEfectivo(anchoTablero));
			g2D.drawLine(margenIzquierdo,margenSuperior + anchoCasillero * i, 
					margenIzquierdo + getAnchoEfectivo(anchoTablero), margenSuperior + anchoCasillero * i);
		}
		
		int j = 0;
		for (POJOEntidad entidad : entidades)
		{
			g.drawImage(img[j], entidad.getCoordenada().getX() * anchoCasillero + margenIzquierdo,
					entidad.getCoordenada().getY() * anchoCasillero + margenSuperior, 
					anchoCasillero,
					anchoCasillero,
					null);
			j++;
		}

		// La famosa 'Proyeccion'
		// Primero ajustamos la transparencia, a pedido de Iván.
		AlphaComposite ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER,
				0.6f);
		g2D.setComposite(ac);
		// Son 4 rectangulos. Uno arriba, uno abajo y 2 a cada lado.
		// Los +-4 son para encajar bien en la cuadricula.
		g2D.fillRect(margenIzquierdo, margenSuperior, getAnchoEfectivo(anchoTablero),
				esquinaSuperiorIzquierda.getY() * anchoCasillero);
		g2D.fillRect(margenIzquierdo,margenSuperior + esquinaSuperiorIzquierda.getY() + 6 * anchoCasillero ,
				getAnchoEfectivo(anchoTablero), getAnchoEfectivo(anchoTablero));
//		g2D.fillRect(esqSupIzq.getX(), esquinaSuperiorIzquierda.getY() - 4,
//				esquinaSuperiorIzquierda.getX() - esqSupIzq.getX() - 4,
//				esquinaInferiorDerecha.getY() - esquinaSuperiorIzquierda.getY());
//		g2D.fillRect(
//				esquinaInferiorDerecha.getX() - 4,
//				esquinaSuperiorIzquierda.getY() - 4,
//				esqInfDer.getX()
//						- esquinaSuperiorIzquierda.getX() // Le resto 2 por un
//															// pequeño error de
//															// cuadricula
//						- (esquinaInferiorDerecha.getX() - esquinaSuperiorIzquierda
//								.getX()), esquinaInferiorDerecha.getY()
//						- esquinaSuperiorIzquierda.getY());
	}

	public int getAnchoEfectivo(int dimension)
	{
		return dimension - dimension % ancho;
	}
}
