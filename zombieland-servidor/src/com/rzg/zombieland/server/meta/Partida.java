package com.rzg.zombieland.server.meta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import com.rzg.zombieland.comunes.comunicacion.pojo.POJOCreacionPartida;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOResultadoRonda;
import com.rzg.zombieland.comunes.misc.EstadoPartida;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.comunicacion.ServicioJuego;
import com.rzg.zombieland.server.comunicacion.peticion.PeticionProyeccion;
import com.rzg.zombieland.server.juego.BucleJuego;
import com.rzg.zombieland.server.juego.Tablero;
import com.rzg.zombieland.server.sesion.Jugador;
import com.rzg.zombieland.server.sesion.ServicioSesion;
import com.rzg.zombieland.server.sesion.Sesion;
import com.rzg.zombieland.server.sesion.Sesion.SesionListener;

/**
 * Define una partida. La partida empieza cuando es creada por un jugador y
 * termina cuando el último jugador es convertido en zombie.
 * 
 * @author nicolas
 *
 */
public class Partida implements SesionListener {
    /**
     * Interfaz para escuchar cambios en la vida de la partida.
     * 
     * @author nicolas
     *
     */
    public interface PartidaListener {
        /**
         * Notifica que la partida ha quedado vacía.
         * 
         * @param partida
         *            - la partida que se ha quedado vacía.
         */
        void notificarPartidaVacia(Partida partida);
    }
    
    /**
     * Un jugador que, además, tiene una bandera de arranque de partida.
     * @author nicolas
     *
     */
    public class JugadorEstado extends Jugador {
        // True si "levantó la bandera" para jugar, false de lo contrario.
        private boolean listo;
        
        public JugadorEstado(Jugador jugador) {
            super(jugador);
            listo = true;
        }
    }

    public static final String MENSAJE_PARTIDA_EN_PROGRESO = "No se puede unir a una partida en progreso";

    // ID único que identifica la partida.
    private UUID id;

    // Jugador que creó la partida.
    private Jugador administrador;

    // El nombre de la partida.
    private String nombre;

    // Listado de jugadores unidos a la partida. Incluye al |administrador|.
    private List<JugadorEstado> jugadores;

    // Listado de espectadores viendo la partida.
    private List<Jugador> espectadores;

    // Indica el estado actual de la partida.
    private EstadoPartida estado;

    // Resultado acumulado de las rondas.
    private ResultadoRonda resultado;

    // La cantidad máxima de jugadores permitida. La partida arrancará cuando se
    // alcance.
    private int cantidadMaximaJugadores;

    // La cantidad de rondas.
    private int cantidadRondas;

    // El número de ronda actual. Empieza por cero.
    private int rondaActual;

    // El objeto que escucha los cambios en la vida de la partida.
    private PartidaListener listener;

    // El tablero de partida.
    private Tablero tablero;

    /**
     * Crea una partida nueva a partir de un administrador.
     * 
     * @param administrador
     *            - el jugador que crea la partida.
     * @param nombre
     *            - el nombre de la partida, arbitrario.
     * @param datosPartida
     *            - el POJO que viene del cliente con los datos de la partida.
     * @throws NullPointerException
     *             si el administrador o los datos de partida son null.
     */
    public Partida(Jugador administrador, POJOCreacionPartida datosPartida) {
        this(administrador, datosPartida.getNombre(), getLista(administrador),
                new ArrayList<Jugador>(), datosPartida.getCantidadRondas(), datosPartida
                        .getCantidadMaximaJugadores());
        Sesion sesion = ServicioSesion.getInstancia().getSesion(administrador);
        if (sesion != null)
            sesion.addListener(this);
    }

    /**
     * @param administrador
     *            - el administrador que creó la partida.
     * @return una lista de jugadores a partir del administrador.
     */
    private static List<Jugador> getLista(Jugador administrador) {
        List<Jugador> listado = new ArrayList<Jugador>(1);
        listado.add(administrador);
        return listado;
    }

    /**
     * Constructor por parámetros.
     * 
     * @param administrador
     * @param jugadores
     * @param espectadores
     * @param cantidadRondas
     * @param cantidadMaximaJugadores
     */
    private Partida(Jugador administrador, String nombre, List<Jugador> jugadores,
            List<Jugador> espectadores, int cantidadRondas, int cantidadMaximaJugadores) {
        if (administrador == null)
            throw new NullPointerException();
        if (nombre == null || nombre.isEmpty())
            throw new NullPointerException();
        this.id = UUID.randomUUID();
        this.administrador = administrador;
        this.nombre = nombre;
        this.jugadores = new ArrayList<JugadorEstado>(jugadores.size());
        for (Jugador jugador : jugadores)
            this.jugadores.add(new JugadorEstado(jugador));
        this.espectadores = espectadores;
        this.cantidadMaximaJugadores = cantidadMaximaJugadores;
        this.cantidadRondas = cantidadRondas;
        this.estado = EstadoPartida.EN_ESPERA;
    }

    /**
     * Devuelve el resultado de una partida para un jugador.
     * 
     * @param jugador
     *            - el jugador para el que se quieren obtener los resultados.
     * @return el resultado de la partida.
     */
    public ResultadoJugador getResultadoPartida(Jugador jugador) {
        // TODO implementar.
        return null;
    }

    /**
     * @param jugador
     *            - el jugador que solicita el POJO. Puede ser null, en cuyo
     *            caso no se enviará la proyección del tablero.
     * @return un pojo con los datos de la partida.
     */
    public POJOPartida getPOJO(Jugador jugador) {
        return new POJOPartida(id.toString(), administrador.getNombre(),
                proyectarNombres(jugadores), proyectarNombres(espectadores), cantidadRondas,
                cantidadMaximaJugadores, nombre, estado, tablero == null
                        || jugador == null ? null : 
                                             tablero.getProyeccionJugador(
                                                     jugador, getTiempoJuego()));
    }

    private int getTiempoJuego() {
        if (estado == EstadoPartida.ACTIVA)
            return BucleJuego.TIEMPO_TURNO;
        if (continuaSiguienteRonda())
            return BucleJuego.TIEMPO_ENTRE_PARTIDAS;
        return 0;
    }

    /**
     * @param jugadores
     * @return un listado con los nombres de los jugadores.
     */
    private List<String> proyectarNombres(List<? extends Jugador> jugadores) {
        List<String> nombresJugadores = new ArrayList<String>(jugadores.size());
        synchronized (jugadores) {
            for (Jugador jugador : jugadores)
                nombresJugadores.add(jugador.getNombre());
        }
        return nombresJugadores;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(obj instanceof Partida))
            return false;
        Partida otro = (Partida) obj;
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
    public List<? extends Jugador> getJugadores() {
        return jugadores;
    }

    /**
     * Agrega un jugador a la partida.
     * 
     * @param jugadorNuevo
     * @throws ZombielandException
     */
    public void addJugador(Jugador jugadorNuevo) throws ZombielandException {
        if (estado != EstadoPartida.EN_ESPERA)
            throw new ZombielandException(MENSAJE_PARTIDA_EN_PROGRESO);
        synchronized (jugadores) {
            if (jugadores.size() == cantidadMaximaJugadores)
                throw new ZombielandException("La partida está llena");
            jugadores.add(new JugadorEstado(jugadorNuevo));
        }
        Sesion sesion = ServicioSesion.getInstancia().getSesion(jugadorNuevo);
        if (sesion != null)
            sesion.addListener(this);
        
        if (jugadores.size() == cantidadMaximaJugadores) {
            boolean arrancarPartida = true;
            for (JugadorEstado jugador : jugadores) {
                if (!jugador.listo) {
                    arrancarPartida = false;
                    break;
                }
            }
            if (arrancarPartida)
                arrancarPartida();
        }
        
        for (Jugador jugador : jugadores) {
            if (jugador.equals(jugadorNuevo))
                continue;
            jugador.notificarCambioPartida();
        }
        notificarEspectadores();
        ServicioPartidas.getInstancia().notificarClientes();
    }

    private void arrancarPartida() {
        rondaActual = 0;
        BucleJuego bucle = new BucleJuego(this);
        resultado = new ResultadoRonda(jugadores);
        notificarPuntajes();
        siguiente();
        ServicioJuego.getInstancia().agregarBucle(bucle);
        bucle.start();
    }

    /**
     * Notifica a los espectadores del cambio de partida.
     */
    private void notificarEspectadores() {
        synchronized (espectadores) {
            for (Jugador espectador : espectadores)
                espectador.notificarCambioPartida();
        }
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
        return cantidadRondas;
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
     * 
     * @param listener
     */
    public void setListener(PartidaListener listener) {
        this.listener = listener;
    }

    /**
     * Quita un jugador de la partida.
     * 
     * @param jugadorEliminado
     * @throws ZombielandException
     *             si el jugador no estaba unido a la partida.
     */
    public void removerJugador(Jugador jugadorEliminado) throws ZombielandException {
        synchronized (espectadores) {
            if (espectadores.contains(jugadorEliminado)) {
                espectadores.remove(jugadorEliminado);
                return;
            }
        }
        synchronized (jugadores) {
            if (!jugadores.remove(jugadorEliminado))
                throw new ZombielandException("El jugador no estaba unido a la partida");
            if (jugadores.isEmpty()) {
                estado = EstadoPartida.FINALIZADA;
                if (listener != null)
                    listener.notificarPartidaVacia(this);
                return;
            }
            // Si la partida estaba en FINALIZADA, se le preguntó a los jugadores si quieren 
            // continuar. Uno de ellos dijo que no, entonces la pasamos a EN_ESPERA.
            if (estado == EstadoPartida.FINALIZADA)
                estado = EstadoPartida.EN_ESPERA;
            if (tablero != null)
                tablero.removerJugador(jugadorEliminado);
            for (Jugador jugador : jugadores)
                jugador.notificarCambioPartida();
            notificarEspectadores();
        }
        ServicioPartidas.getInstancia().notificarClientes();
    }

    /**
     * Mueve todos los jugadores.
     */
    public void moverTodos() throws ZombielandException {
        if (estado != EstadoPartida.ACTIVA)
            throw new ZombielandException("La partida debe estar activa para mover a todos");
        tablero.moverTodos();
        if (tablero.partidaFinalizada()) {
            estado = EstadoPartida.ENTRE_RONDAS;
            if (jugadores.size() > 1) {
                resultado.addPuntaje(tablero.getJugadorConvertidoNumero(jugadores.size() - 1), 3);
                resultado.addPuntaje(tablero.getJugadorConvertidoNumero(jugadores.size() - 2), 2);
                notificarPuntajes();
            }
        }
    }

    /**
     * @return true si se pueden unir jugadores a esta partida, false de lo
     *         contrario.
     */
    public boolean puedenUnirseJugadores() {
        return jugadores.size() < cantidadMaximaJugadores && estado == EstadoPartida.EN_ESPERA;
    }

    /**
     * @return el estado actual de la partida.
     */
    public EstadoPartida getEstado() {
        return estado;
    }

    public void enviarProyeccion() {
        enviarProyeccion(jugadores);
        enviarProyeccion(espectadores);
    }

    /**
     * Envía las proyecciones a un listado de jugadores.
     * @param jugadores
     */
    private void enviarProyeccion(List<? extends Jugador> jugadores) {
        synchronized (jugadores) {
            for (Jugador jugador : jugadores)
                enviarProyeccionTablero(jugador);
        }
    }

    /**
     * Envía la proyección del tablero a un jugador.
     * 
     * @param jugador
     */
    private void enviarProyeccionTablero(Jugador jugador) {
        Sesion sesion = ServicioSesion.getInstancia().getSesion(jugador);
        if (sesion == null)
            return;
        try {
            sesion.enviarPeticion(
                    new PeticionProyeccion(tablero.getProyeccionJugador(jugador,
                                                                        getTiempoJuego())));
        } catch (ZombielandException e) {
            Log.error("No le llegó la proyección del tablero a un espectador.");
        }
    }

    /**
     * @return true si la partida está siendo jugada, false de lo contrario.
     */
    public boolean activa() {
        return estado == EstadoPartida.ACTIVA;
    }

    /**
     * Arranca la siguiente ronda.
     * 
     * @return true si la partida debe continuar, false si la partida terminó.
     */
    public boolean siguiente() {
        Log.info("Arrancando siguiente ronda...");
        synchronized (jugadores) {
            if (!continuaSiguienteRonda()) {
                Log.info("...terminó la partida - notificando jugadores");
                // La partida terminó.
                estado = EstadoPartida.FINALIZADA;
                tablero = null;
                for (JugadorEstado jugador : jugadores) {
                    jugador.listo = false;
                    jugador.notificarCambioPartida();
                }
                notificarEspectadores();
                ServicioPartidas.getInstancia().notificarClientes();
                return false;
            }
            estado = EstadoPartida.ACTIVA;
            int cantidadCasilleros = new Random().nextInt(10) + 10;
            int jugadorZombie = rondaActual % jugadores.size();
            tablero = new Tablero(cantidadCasilleros, jugadores, jugadores.get(jugadorZombie));
            rondaActual++;
            Log.info("...arrancada");
        }
        return true;
    }
    
    private boolean continuaSiguienteRonda() {
        return jugadores.size() != 0 && rondaActual < cantidadRondas;
    }

    @Override
    public void notificarSesionCerrada(Sesion sesion) {
        try {
            removerJugador(sesion.getJugador());
        } catch (ZombielandException e) {
            Log.error("El jugador no pudo quitarse de la lista al desconectarse:");
            Log.error(e.getMessage());
        }
    }

    /**
     * Agrega un espectador a la partida.
     * 
     * @param espectador
     */
    public void addEspectador(Jugador espectador) {
        Sesion sesion = ServicioSesion.getInstancia().getSesion(espectador);
        if (sesion != null)
            sesion.addListener(this);
        synchronized (espectadores) {
            if (estado != EstadoPartida.EN_ESPERA)
                espectador.notificarPuntajePartida(resultado.getPojo());
            espectadores.add(espectador);
        }
    }

    /**
     * Envía un mensaje de chat a todos los jugadores y espectadores de la partida.
     * @param mensaje
     */
    public void enviarMensajeChat(String mensaje) {
        synchronized (jugadores) {
            for (Jugador jugador : jugadores) {
                jugador.enviarMensajeChat(mensaje);
            }
        }
        synchronized (espectadores) {
            for (Jugador espectador : espectadores) {
                espectador.enviarMensajeChat(mensaje);
            }
        }
    }
    
    /**
     * Envía una notificación con los puntajes a los jugadores y espectadores.
     */
    private void notificarPuntajes() {
        POJOResultadoRonda resultado = this.resultado.getPojo();
        synchronized (jugadores) {
            for (Jugador jugador : jugadores) {
                jugador.notificarPuntajePartida(resultado);
            }
        }
        synchronized (espectadores) {
            for (Jugador espectador : espectadores) {
                espectador.notificarPuntajePartida(resultado);
            }
        }
    }
    
    /**
     * Cambia el estado de la bandera "listo" del jugador dado. De acuerdo a las condiciones, puede
     * arrancar la partida.
     * @param jugador
     * @param listo
     */
    public void cambiarListoJugador(Jugador jugador, boolean listo) {
        Log.info("El jugador " + jugador.getNombre() + " indica "
               + "que " + (listo ? "" : "no ") + "está listo");
        synchronized (jugadores) {
            boolean empezarProxima = listo;
            for (JugadorEstado jugadorEstado : jugadores) {
                if (jugadorEstado.equals(jugador)) {
                    if (estado == EstadoPartida.FINALIZADA)
                        estado = EstadoPartida.EN_ESPERA;
                    jugadorEstado.listo = listo;
                }
                if (!jugadorEstado.listo)
                    empezarProxima = false;
            }
            if (jugadores.size() == cantidadMaximaJugadores && empezarProxima) {
                arrancarPartida();
                notificarEspectadores();
                for (Jugador jugadorNotificado : jugadores)
                    jugadorNotificado.notificarCambioPartida();
            }
        }
        ServicioPartidas.getInstancia().notificarClientes();
    }
}
