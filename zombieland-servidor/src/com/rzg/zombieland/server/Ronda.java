package com.rzg.zombieland.server;

/**
 * La ronda es cada una de las «instancias» de juego. Cada partida está formada por varias rondas,
 * en las que los jugadores correrán por sus vidas y obtendrán puntajes parciales.
 * @author nicolas
 *
 */
public class Ronda {
    // Nombre de la ronda, dado por el administrador del sistema.
    private String nombre;
    
    // El tablero que contiene el estado actual del juego.
    private Tablero tablero;
}
