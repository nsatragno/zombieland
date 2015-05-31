package com.rzg.zombieland.comunes.misc;

/**
 * Representa los movimientos que puede realizar un personaje. 
 * @author nicolas
 *
 */
public class Movimiento {
	public enum Tipo {
		NORTE, ESTE, SUR, OESTE, NINGUNO;
	}

	// Tiempo en el que se recibe la orden de movimiento, para poder determinar el orden de estos.
	private long tiempoDeMovimientoMillis;
	
	// El tipo de movimiento.
	private Tipo tipo;
}
