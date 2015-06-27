package com.rzg.zombieland.cliente.meta;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOResultadoRonda;
import com.rzg.zombieland.comunes.comunicacion.respuesta.POJOListadoPartidas;

/**
 * Almacena estado que se debe compartir entre pantallas.
 * @author nicolas
 *
 */
public class Estado {
	
	public static final DefaultComboBoxModel<String> preguntas = new DefaultComboBoxModel<String>
	(new String[] { "", "Cual es su color favorito?", "Mejor amigo de la infancia?",
            "A que escuela primaria fue?", "Nombre de su primer mascota?" });

    /**
     * Interfaz para escuchar cambios de estado del lobby.
     * @author nicolas
     *
     */
    public interface EscuchadorEstadoLobby {
        /**
         * Se dispara cuando se cambie el estado del lobby.
         * @param pojo
         */
        public void notificarLobbyActualizado(POJOPartida pojo);
    }
    
    /**
     * Interfaz para escuchar cambios de estado de listado de partidas.
     * @author nicolas
     *
     */
    public interface EscuchadorPartidas {
        /**
         * Se dispara cuando se cambie el estado del lobby.
         * @param respuesta
         */
        public void notificarPartidasActualizadas(POJOListadoPartidas respuesta);
    }
    
    /**
     * Interfaz para escuchar cambios de estado de tablero.
     * @author nicolas
     *
     */
    public interface EscuchadorProyeccion {
        /**
         * Se dispara cuando se cambie el estado del tablero.
         * @param proyeccion
         */
        public void notificarProyeccionActualizada(ProyeccionTablero proyeccion);
        
        /**
         * Se dispara cuando se arranca una nueva partida.
         * @param espectador
         */
        public void notificarCambioEstadoEspectador(boolean espectador);
    }
    
    /**
     * Escuchador para los mensajes de chat.
     * @author nicolas
     *
     */
    public interface EscuchadorChat {
        /**
         * Indica que se recibió un mensaje de chat.
         * @param mensaje
         */
        public void recibidoMensaje(String mensaje);
    }
    
    /**
     * Escuchador para las actualizaciones de puntaje.
     * @author nicolas
     *
     */
    public interface EscuchadorPuntaje {
        /**
         * Indica que se recibió una actualización de puntaje.
         * @param mensaje
         */
        public void recibidoPuntaje(POJOResultadoRonda puntaje);
    }
    
    private static Estado instancia;
    
    private POJOPartida estadoLobby;
    
    private String jugador;
    
    private List<EscuchadorEstadoLobby> escuchadoresLobby;

    private List<EscuchadorPartidas> escuchadoresPartidas;

    private List<EscuchadorProyeccion> escuchadoresProyeccion;
    
    private EscuchadorChat escuchadorChat;
    
    // True si se está observando una partida, false de lo contrario.
    private boolean espectador;

    private EscuchadorPuntaje escuchadorPuntaje;
    
    public Estado() {
        escuchadoresLobby = new ArrayList<EscuchadorEstadoLobby>();
        escuchadoresPartidas = new ArrayList<EscuchadorPartidas>();
        escuchadoresProyeccion = new ArrayList<EscuchadorProyeccion>();
        estadoLobby = POJOPartida.PARTIDA_VACIA;
    }
    
    /**
     * @return la instancia de estado.
     */
    public static Estado getInstancia() {
        if (instancia == null)
            instancia = new Estado();
        return instancia;
    }
    
    /**
     * Agrega un escuchador de estado de lobby.
     * @param escuchador
     */
    public void addEscuchador(EscuchadorEstadoLobby escuchador) {
        this.escuchadoresLobby.add(escuchador);
    }
    
    /**
     * Agrega un escuchador de estado de partida.
     * @param escuchador
     */
    public void addEscuchador(EscuchadorProyeccion escuchador) {
        this.escuchadoresProyeccion.add(escuchador);
    }
    
    /**
     * Establece el escuchador de chat.
     * @param escuchador
     */
    public void setEscuchadorChat(EscuchadorChat escuchador) {
        this.escuchadorChat = escuchador;
    }
    
    /**
     * Establece el estado del lobby a partir de su POJO.
     * @param pojo
     */
    public void setEstadoLobby(POJOPartida pojo) {
        estadoLobby = pojo;
        for (EscuchadorEstadoLobby escuchador : escuchadoresLobby)
            escuchador.notificarLobbyActualizado(pojo);
        if (pojo.getProyeccion() != null)
            for (EscuchadorProyeccion escuchador : escuchadoresProyeccion)
                escuchador.notificarProyeccionActualizada(pojo.getProyeccion());
    }
    
    /**
     * Establece el nombre de un jugador.
     * @param nombreJugador
     */
    public void setNombreJugador(String nombreJugador) {
        jugador = nombreJugador;
    }
    
    /**
     * @return el estado del lobby.
     */
    public POJOPartida getEstadoLobby() {
        return estadoLobby;
    }

    /**
     * @return el nombre del jugador actual.
     */
    public String getNombreJugador() {
        return jugador;
    }

    public void addEscuchadorPartidas(EscuchadorPartidas escuchador) {
        this.escuchadoresPartidas.add(escuchador);
    }
    
    public void setEscuchadorPuntaje(EscuchadorPuntaje escuchador) {
        escuchadorPuntaje = escuchador;
    }

    public void setListadoPartidas(POJOListadoPartidas listado) {
        for (EscuchadorPartidas escuchador : escuchadoresPartidas)
            escuchador.notificarPartidasActualizadas(listado);
    }

    public void setEspectador(boolean espectador) {
        this.espectador = espectador;
        for (EscuchadorProyeccion escuchador : escuchadoresProyeccion)
            escuchador.notificarCambioEstadoEspectador(espectador);
    }
    
    public void setProyeccion(ProyeccionTablero proyeccion) {
        estadoLobby.setProyeccion(proyeccion);
        for (EscuchadorProyeccion escuchador : escuchadoresProyeccion)
            escuchador.notificarProyeccionActualizada(proyeccion);
    }

    /**
     * @return true si se está observando una partida, false de lo contrario.
     */
    public boolean isEspectador() {
        return espectador;
    }

    /**
     * Indica que se recibió un mensaje de chat.
     * @param mensaje
     */
    public void recibidoMensajeChat(String mensaje) {
        escuchadorChat.recibidoMensaje(mensaje);
    }

    /**
     * Indica que se recibió una actualización de puntaje. 
     * @param resultado
     */
    public void recibidoPuntaje(POJOResultadoRonda puntaje) {
        escuchadorPuntaje.recibidoPuntaje(puntaje);
    }
}
