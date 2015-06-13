package com.rzg.zombieland.server.meta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.rzg.zombieland.comunes.comunicacion.pojo.POJOCreacionPartida;
import com.rzg.zombieland.server.sesion.Jugador;

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
        /**
         * Todavía no ha arrancado, está en la fase de espera de jugadores.
         */
        EN_ESPERA("En espera"), 
        
        /**
         *  La partida está en progreso.
         */
        ACTIVA("Activa"),
        
        /**
         * La partida ha finalizado.
         */
        FINALIZADA("Finalizada");
        
        // Una descripción amigable para el usuario del estado.
        private String descripcion;
        
        private Estado(String descripcion) {
            this.descripcion = descripcion;
        }
        
        /**
         * @return una descripción amigable para el usuario.
         */
        public String getDescripcion() {
            return descripcion;
        }
    }
    
    // ID único que identifica la partida.
    private UUID id;
    
    // Jugador que creó la partida.
    private Jugador administrador;
    
    // El nombre de la partida.
    private String nombre;
    
    // Listado de jugadores unidos a la partida. Incluye al |administrador|.
    private List<Jugador> jugadores;
    
    // Listado de espectadores viendo la partida.
    private List<Jugador> espectadores;
    
    // Indica el estado actual de la partida.
    private Estado estado;
    
    // Rondas de la partida, editables hasta que arranca. 
    private List<Ronda> rondas;
    
    // La cantidad máxima de jugadores permitida. La partida arrancará cuando se alcance.
    private int cantidadMaximaJugadores;
    
    // El número de ronda actual. Empieza por cero.
    private int numeroRondaActual;
    
    // Indica el momento en el que se inició la partida, expresados como tiempo UNIX.
    private long tiempoArranque;
    
    /**
     * Crea una partida nueva a partir de un administrador.
     * @param administrador - el jugador que crea la partida.
     * @param nombre - el nombre de la partida, arbitrario.
     * @param datosPartida - el POJO que viene del cliente con los datos de la partida.
     * @throws NullPointerException si el administrador o los datos de partida son null.
     */
    public Partida(Jugador administrador, POJOCreacionPartida datosPartida) {
        this(administrador, datosPartida.getNombre(), getLista(administrador), new ArrayList<Jugador>(),
             datosPartida.getCantidadRondas(), datosPartida.getCantidadMaximaJugadores());
    }
    
    /**
     * @param administrador - el administrador que creó la partida.
     * @return una lista de jugadores a partir del administrador.
     */
    private static List<Jugador> getLista(Jugador administrador) {
        List<Jugador> listado = new ArrayList<Jugador>(1);
        listado.add(administrador);
        return listado;
    }

    /**
     * Crea una partida nueva a partir de la partida anterior.
     * @param partida
     */
    public Partida(Partida partida) {
        this(partida.administrador, partida.nombre, partida.jugadores, partida.espectadores,
             partida.rondas.size(), partida.cantidadMaximaJugadores);
    }
    
    /**
     * Constructor por parámetros.
     * @param administrador
     * @param jugadores
     * @param espectadores
     * @param cantidadRondas
     * @param cantidadMaximaJugadores
     */
    private Partida(Jugador administrador, String nombre, List<Jugador> jugadores, List<Jugador> espectadores,
                    int cantidadRondas, int cantidadMaximaJugadores) {
        if (administrador == null)
            throw new NullPointerException();
        if (nombre == null || nombre.isEmpty())
            throw new NullPointerException();
        this.administrador = administrador;
        this.nombre = nombre;
        id = UUID.randomUUID();
        estado = Estado.EN_ESPERA;
        rondas = new ArrayList<Ronda>();
        for (int i = 0; i < cantidadRondas; i++)
            rondas.add(new Ronda());
    }
    
    /**
     * Constructor sin parámetros para Hibernate.
     */
    public Partida() { }
    
    /**
     * Devuelve el resultado de una partida para un jugador.
     * @param jugador - el jugador para el que se quieren obtener los resultados.
     * @return el resultado de la partida.
     */
    public ResultadoPartida getResultadoPartida(Jugador jugador) {
        // TODO implementar.
        return null;
    }
}
