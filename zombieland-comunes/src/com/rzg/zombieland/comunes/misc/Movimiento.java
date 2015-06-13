package com.rzg.zombieland.comunes.misc;

/**
 * Representa los movimientos que puede realizar un personaje. 
 * @author nicolas
 *
 */
public class Movimiento {
    /**
     * El tipo de movimiento.
     * @author nicolas
     *
     */
	public enum Tipo {
		/**
		 * Movimiento hacia «arriba». 
		 */
		NORTE,
		/**
		 * Movimiento hacia la derecha.
		 */
		ESTE,
		/**
		 * Movimiento hacia «abajo».
		 */
		SUR,
		/**
		 * Movimiento hacia la izquierda.
		 */
		OESTE,
        /**
         * No se mueve.
         */
		NINGUNO;
	}

	// Tiempo en el que se recibe la orden de movimiento, para poder determinar el orden de estos.
	private long tiempoDeMovimientoMillis;
	
	// El tipo de movimiento.
	private Tipo tipo;
}
