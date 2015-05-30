package com.rzg.zombieland.server;

import java.util.List;
import java.util.UUID;

/**
 * Define una partida. La partida empieza cuando es creada por un jugador y termina cuando el
 * último jugador es convertido en zombie.
 * @author nicolas
 *
 */
public class Partida {
    /**
     * Estado de la partida actual.
     * @author nicolas
     *
     */
    public enum Estado {
        // Todavía no ha arrancado, está en la fase de espera de jugadores.
        EN_ESPERA("En espera"), 
        
        // La partida está en progreso.
        ACTIVA("Activa");
        
        private String descripcion;
        
        private Estado(String descripcion) {
            this.descripcion = descripcion;
        }
        
        public String getDescripcion() {
            return descripcion;
        }
    }
    
    // ID único que identifica la partida.
    private UUID id;
    
    // Jugador que creó la partida.
    private Jugador administrador;
    
    // Listado de jugadores unidos a la partida. Incluye al |administrador|.
    private List<Jugador> jugadores;
    
    // Indica el estado actual de la partida.
    private Estado estado;
    
    // Rondas de la partida, editables hasta que arranca. 
    private List<Ronda> rondas;
    
    // El número de ronda actual. Empieza por cero.
    private int numeroRondaActual;
    
    // Indica el momento en el que se inició la partida, expresados como tiempo UNIX.
    private long tiempoArranque;
    
    /**
     * Crea una partida nueva a partir de un administrador.
     * @param administrador
     */
    public Partida(Jugador administrador) {
        this.administrador = administrador;
    }
    
    /**
     * Crea una partida nueva a partir de la partida anterior.
     * @param partida
     */
    public Partida(Partida partida) {
        // TODO implementar.
    }
}
