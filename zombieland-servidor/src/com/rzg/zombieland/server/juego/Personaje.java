package com.rzg.zombieland.server.juego;

import java.util.UUID;

import com.rzg.zombieland.comunes.misc.Movimiento;
import com.rzg.zombieland.server.sesion.Jugador;
import com.rzg.zombieland.server.sesion.ServicioSesion;
import com.rzg.zombieland.server.sesion.Sesion;

/**
 * Representa una entidad del tablero controlada por un jugador.
 * @author nicolas
 *
 */
public abstract class Personaje extends EntidadTablero implements Comparable<Personaje> {
    // ID único que la identifica.
    private UUID id;

    // Jugador que controla este personaje.
    private Jugador jugador;
    
    // Movimiento que ejecutar en el siguiente turno.
    private Movimiento siguienteMovimiento;
    
    // Tablero en el que el personaje está inscrito.
    private Tablero tablero;
    
    public Personaje(Jugador jugador) {
        this.jugador = jugador;
        Sesion sesion = ServicioSesion.getInstancia().getSesion(jugador);
        sesion.setPersonaje(this);
        siguienteMovimiento = Movimiento.NINGUNO;
    }
    
    /**
     * Establece el siguiente movimiento del personaje.
     * @param siguienteMovimiento - el siguiente movimiento.
     * @throws NullPointerException si el siguiente movimiento es null.
     */
    public void setSiguienteMovimiento(Movimiento siguienteMovimiento) {
        if (siguienteMovimiento == null)
            throw new NullPointerException("El movimiento no puede ser null");
        this.siguienteMovimiento = siguienteMovimiento;
    }
    /**
     * Realiza el siguiente movimiento.
     */
    public void mover() {
        // TODO implementar.
    }
    
    public Jugador getJugador(){
    	return jugador;
    }

    @Override
    public final int compareTo(Personaje p2) {
        return siguienteMovimiento.compareTo(p2.siguienteMovimiento);
    }
}
