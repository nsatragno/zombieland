package com.rzg.zombieland.comunes.comunicacion;

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
	}

	// Tamaño total del tablero - Ancho de la matriz
	private int ancho;
	private int largo;

	// Las esquina que esta proyección revela del mapa.
	private Coordenada esquinaSuperiorIzquierda;
	private Coordenada esquinaInferiorDerecha;

	// Entidades visibles del tablero.
	private List<POJOEntidad> entidades;

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
		// TODO Auto-generated method stub
		return esquinaSuperiorIzquierda;
	}
	public Coordenada getEsqInfDer()
	{
		// TODO Auto-generated method stub
		return esquinaInferiorDerecha;
	}
}
