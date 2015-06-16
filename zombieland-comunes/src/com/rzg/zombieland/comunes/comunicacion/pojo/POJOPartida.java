package com.rzg.zombieland.comunes.comunicacion.pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa el estado actual de un lobby: cantidad de jugadores, nombres, etc.
 * @author nicolas
 *
 */
public class POJOPartida {

    /**
     * Una partida vacía.
     */
    public static final POJOPartida PARTIDA_VACIA; 
    
    static {
        PARTIDA_VACIA = 
                new POJOPartida(null,
                                null, 
                                new ArrayList<String>(), 
                                new ArrayList<String>(), 
                                0, 
                                0, 
                                "", 
                                "");
    }

    /**
     * El ID que identifica unívocamente a la partida.
     */
    private String id;
    
    /**
     * El administrador de la partida.
     */
    private String administrador;
    
    /**
     * Listado de nombres de jugadores, incluyendo al administrador.
     */
    private List<String> jugadores;

    /**
     * Listado de nombres de espectadores.
     */
    private List<String> espectadores;
    
    private int cantidadRondas;
    
    private int cantidadMaximaJugadores;
    
    private String nombre;
    
    private String estado;
    
    /**
     * Construye un POJO de lobby.
     * @param administrador - incluye al administrador.
     * @param jugadores
     * @param espectadores
     * @param cantidadRondas 
     * @param cantidadJugadores 
     * @param nombre 
     * @param estado 
     */
    public POJOPartida(String id, String administrador, List<String> jugadores,
                       List<String> espectadores, int cantidadRondas, int cantidadJugadores,
                       String nombre, String estado) {
        this.id = id;
        this.administrador = administrador;
        this.jugadores = jugadores;
        this.espectadores = espectadores;
        this.cantidadRondas = cantidadRondas;
        this.cantidadMaximaJugadores = cantidadJugadores;
        this.nombre = nombre;
        this.estado = estado;
    }
    
    /**
     * Construye un pojo de lobby a partir del pojo de creación de partida.
     * @param pojo
     * @param nombreAdministrador 
     */
    public POJOPartida(POJOCreacionPartida pojo, String nombreAdministrador) {
        administrador = nombreAdministrador;
        jugadores = new ArrayList<String>();
        espectadores = new ArrayList<String>();
        cantidadRondas = pojo.getCantidadRondas();
        cantidadMaximaJugadores = pojo.getCantidadMaximaJugadores();
        nombre = pojo.getNombre();
        jugadores.add(nombreAdministrador);
    }

    /**
     * @return el administrador.
     */
    public String getAdministrador() {
        return administrador;
    }

    /**
     * @return los jugadores.
     */
    public List<String> getJugadores() {
        return jugadores;
    }

    /**
     * @return los espectadores.
     */
    public List<String> getEspectadores() {
        return espectadores;
    }

    /**
     * @return the cantidadRondas
     */
    public int getCantidadRondas() {
        return cantidadRondas;
    }

    /**
     * @return the cantidadJugadores
     */
    public int getCantidadMaximaJugadores() {
        return cantidadMaximaJugadores;
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * @return the id
     */
    public String getId() {
        return id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (!(getClass().isAssignableFrom(obj.getClass())))
            return false;
        POJOPartida otro = (POJOPartida)obj;
        return otro.administrador.equals(administrador) &&
               otro.cantidadMaximaJugadores == cantidadMaximaJugadores &&
               otro.cantidadRondas == cantidadRondas &&
               otro.espectadores.equals(espectadores) &&
               otro.jugadores.equals(jugadores) &&
               otro.nombre.equals(nombre);
    }
    
    @Override
    public String toString() {
        return "POJOPartida [administrador=" + administrador + ", jugadores=" + jugadores
                + ", espectadores=" + espectadores + ", cantidadRondas=" + cantidadRondas
                + ", cantidadMaximaJugadores=" + cantidadMaximaJugadores + ", nombre=" + nombre
                + "]";
    }

    public Object getEstado() {
        return estado;
    }
}
