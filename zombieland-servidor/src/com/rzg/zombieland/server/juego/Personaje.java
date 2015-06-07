package com.rzg.zombieland.server.juego;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.UUID;

import javax.swing.JPanel;

import com.rzg.zombieland.comunes.misc.Movimiento;
import com.rzg.zombieland.server.meta.Jugador;

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
