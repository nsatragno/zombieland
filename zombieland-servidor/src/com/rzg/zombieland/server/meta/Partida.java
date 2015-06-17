package com.rzg.zombieland.server.meta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.rzg.zombieland.comunes.comunicacion.pojo.POJOCreacionPartida;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;
import com.rzg.zombieland.comunes.misc.ZombielandException;
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
    
    /**
     * Interfaz para escuchar cambios en la vida de la partida.
     * @author nicolas
     *
     */
    public interface PartidaListener {
        /**
         * Notifica que la partida ha quedado vacía.
         * @param partida - la partida que se ha quedado vacía.
         */
        void notificarPartidaVacia(Partida partida);
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
    
    // El objeto que escucha los cambios en la vida de la partida.
    private PartidaListener listener;
    
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
     * TODO determinar si lo vamos a usar, o vamos a reutilizar la misma partida.
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
        id = UUID.randomUUID();
        this.administrador = administrador;
        this.nombre = nombre;
        this.jugadores = jugadores;
        this.espectadores = espectadores;
        this.cantidadMaximaJugadores = cantidadMaximaJugadores;
        estado = Estado.EN_ESPERA;
        rondas = new ArrayList<Ronda>(cantidadRondas);
        for (int i = 0; i < cantidadRondas; i++)
            rondas.add(new Ronda());
    }
    
    /**
     * Devuelve el resultado de una partida para un jugador.
     * @param jugador - el jugador para el que se quieren obtener los resultados.
     * @return el resultado de la partida.
     */
    public ResultadoPartida getResultadoPartida(Jugador jugador) {
        // TODO implementar.
        return null;
    }

    /**
     * @return un pojo con los datos de la partida.
     */
    public POJOPartida getPOJO() {
        return new POJOPartida(id.toString(),
                               administrador.getNombre(),
                               proyectarNombres(jugadores),
                               proyectarNombres(espectadores), 
                               rondas.size(), 
                               cantidadMaximaJugadores, 
                               nombre,
                               estado.getDescripcion());
    }
    
    /**
     * @param jugadores
     * @return un listado con los nombres de los jugadores.
     */
    private List<String> proyectarNombres(List<Jugador> jugadores) {
        List<String> nombresJugadores = new ArrayList<String>(jugadores.size());
        for (Jugador jugador : jugadores)
            nombresJugadores.add(jugador.getNombre());
        return nombresJugadores;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Partida))
            return false;
        Partida otro = (Partida)obj;
        return otro.id.equals(id);
    }

    /**
     * @return el ID de la partida.
     */
    public UUID getId() {
        return id;
    }

    /**
     * @return el listado de jugadores unidos a la partida.
     */
    public List<Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Agrega un jugador a la partida.
     * @param jugadorNuevo
     * @throws ZombielandException 
     */
    public void addJugador(Jugador jugadorNuevo) throws ZombielandException {
        if (estado == Estado.ACTIVA)
            throw new ZombielandException("No se puede unir a una partida en progreso");
        jugadores.add(jugadorNuevo);
        if (jugadores.size() == cantidadMaximaJugadores)
            estado = Estado.ACTIVA;
        for (Jugador jugador : jugadores) {
            if (jugador == jugadorNuevo)
                continue;
            jugador.notificarCambioPartida();
        }
        ServicioPartidas.getInstancia().notificarClientes();
    }

    /**
     * @return el administrador de la partida.
     */
    public Jugador getAdministrador() {
        return administrador;
    }

    /**
     * @return la cantidad de rondas en la partida.
     */
    public int getCantidadRondas() {
        return rondas.size();
    }
    
    /**
     * @return la cantidad máxima de jugadores admitida por la partida.
     */
    public int getMaximoJugadores() {
        return cantidadMaximaJugadores;
    }

    /**
     * @return el nombre asignado por el jugador de la partida.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el objeto que recibirá notificaciones sobre la partida.
     * @param listener
     */
    public void setListener(PartidaListener listener) {
        this.listener = listener;
    }

    /**
     * Quita un jugador de la partida.
     * @param jugadorEliminado
     */
    public void removerJugador(Jugador jugadorEliminado) throws ZombielandException {
        if (!jugadores.remove(jugadorEliminado))
            throw new ZombielandException("El jugador no estaba unido a la partida");
        if (jugadores.isEmpty()) {
            if (listener != null)
                listener.notificarPartidaVacia(this);
            return;
        }
        for (Jugador jugador : jugadores)
            jugador.notificarCambioPartida();
    }

    /**
     * @return true si se pueden unir jugadores a esta partida, false de lo contrario.
     */
    public boolean puedenUnirseJugadores() {
        return jugadores.size() < cantidadMaximaJugadores && estado == Estado.EN_ESPERA;
    }
}
