package com.rzg.zombieland.server.juego;

import java.util.UUID;

import com.rzg.zombieland.comunes.misc.Movimiento;
import com.rzg.zombieland.server.sesion.Jugador;

/**
 * Representa una entidad del tablero controlada por un jugador.
 * @author nicolas
 *
 */
public abstract class Personaje extends EntidadTablero {
    // ID único que la identifica.
    private UUID id;

    // Jugador que controla este personaje.
    private Jugador jugador;
    
    // Movimiento que ejecutar en el siguiente turno.
    private Movimiento siguienteMovimiento;
    
    // Tablero en el que el personaje está inscrito.
    private Tablero tablero;
    
    /**
     * Establece el siguiente movimiento del personaje.
     * @param siguienteMovimiento - el siguiente movimiento.
     */
    public void setSiguienteMovimiento(Movimiento siguienteMovimiento) {
        this.siguienteMovimiento = siguienteMovimiento;
    }
    /**
     * Realiza el siguiente movimiento.
     */
    public static void mover() {
        // TODO implementar.
    }
}
