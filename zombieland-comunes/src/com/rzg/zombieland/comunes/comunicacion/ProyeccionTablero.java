package com.rzg.zombieland.comunes.comunicacion;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

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

		public POJOEntidad(String etiqueta, Coordenada coordenada)
		{
			this.etiqueta = etiqueta;
			this.coordenada = coordenada;
		}
		public Coordenada getCoordenada(){
			return coordenada;
		}
	}
	
	// Tamaño total del tablero - Ancho de la matriz
	private int ancho;
	private int largo;

	// Las esquina que esta proyección revela del mapa.
	private Coordenada esquinaSuperiorIzquierda;
	private Coordenada esquinaInferiorDerecha;

	// Entidades visibles del tablero.
	private List<POJOEntidad> entidades;

	public ProyeccionTablero() {
		
	}
	
	public ProyeccionTablero(int ancho, int largo, Coordenada esquinaSuperiorIzquierda, 
			Coordenada esquinaInferiorDerecha, List<POJOEntidad> entidades) {
		this.ancho = ancho;
		this.largo = largo;
		this.esquinaInferiorDerecha = esquinaInferiorDerecha;
		this.esquinaSuperiorIzquierda = esquinaSuperiorIzquierda;
		this.entidades = entidades;
	}
	
	public int getAncho()
	{
		return ancho;
	}

	public int getLargo()
	{
		return largo;
	}

	public Coordenada getEsqSupIzq()
	{
		return esquinaSuperiorIzquierda;
	}
	public Coordenada getEsqInfDer()
	{
		return esquinaInferiorDerecha;
	}
	public List<POJOEntidad> getEntidades() {
		return entidades;
	}

	public void paint(Graphics g,Image img,int tamañoCasilleros) {
		for (POJOEntidad entidad : entidades) {
			g.drawImage(img, entidad.getCoordenada().getX(), entidad
					.getCoordenada().getY(), tamañoCasilleros,
					tamañoCasilleros, null);
		}
		// La famosa 'Proyeccion'
		g.setColor(Color.BLACK);
		// Son 4 rectangulos. Uno arriba, uno abajo y 2 a cada lado.
		g.fillRect(35,55,ancho,esquinaSuperiorIzquierda.getY() - 55);
		g.fillRect(35,esquinaInferiorDerecha.getY(),ancho,555 - esquinaInferiorDerecha.getY());
		g.fillRect(35,esquinaSuperiorIzquierda.getY(),
				esquinaSuperiorIzquierda.getX() - 35,
				esquinaInferiorDerecha.getY() - esquinaSuperiorIzquierda.getY());
		g.fillRect(esquinaInferiorDerecha.getX(),esquinaSuperiorIzquierda.getY(), 
				533 - esquinaSuperiorIzquierda.getX() - (esquinaInferiorDerecha.getX() - esquinaSuperiorIzquierda.getX()),
				esquinaInferiorDerecha.getY() - esquinaSuperiorIzquierda.getY());
	}
}
