package com.rzg.zombieland.server.juego;

import com.rzg.zombieland.comunes.misc.Coordenada;
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
    // Jugador que controla este personaje.
    private Jugador jugador;
    
    // Movimiento que ejecutar en el siguiente turno.
    private Movimiento siguienteMovimiento;
    
    // Tablero en el que el personaje está inscrito.
    private Tablero tablero;
    
    public Personaje(Jugador jugador, Coordenada posicion, Tablero tablero) {
        super(posicion);
        this.jugador = jugador;
        Sesion sesion = ServicioSesion.getInstancia().getSesion(jugador);
        sesion.setPersonaje(this);
        siguienteMovimiento = Movimiento.NINGUNO;
        this.tablero = tablero;
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
        setPosicion(tablero.moverEntidad(getPosicion(), siguienteMovimiento.mover(getPosicion())));
        siguienteMovimiento = Movimiento.NINGUNO;
    }
    
    public Jugador getJugador(){
    	return jugador;
    }

    @Override
    public final int compareTo(Personaje p2) {
        return siguienteMovimiento.compareTo(p2.siguienteMovimiento);
    }
    
    protected Tablero getTablero() {
        return tablero;
    }

    /**
     * @return true si el personaje es un zombie, false de lo contrario.
     */
    public abstract boolean esZombie();

    /**
     * @return un array cuyo primer elemento es la coordenada superior izquierda límite que el
     * personaje puede ver, y el segundo elemento la coordenada inferior derecha.
     */
    public abstract Coordenada[] getRectanguloVision();
}
