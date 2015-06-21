package com.rzg.zombieland.comunes.comunicacion.pojo;

import java.util.ArrayList;
import java.util.List;

import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero;

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
                                "",
                                null);
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
    
    // La proyección del tablero asociada a la partida, si existe.
    private ProyeccionTablero proyeccion;
    
    /**
     * Construye un POJO de partida.
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
                       String nombre, String estado, ProyeccionTablero proyeccion) {
        this.id = id;
        this.administrador = administrador;
        this.jugadores = jugadores;
        this.espectadores = espectadores;
        this.cantidadRondas = cantidadRondas;
        this.cantidadMaximaJugadores = cantidadJugadores;
        this.nombre = nombre;
        this.estado = estado;
        this.proyeccion = proyeccion;
    }
    
    /**
     * Construye un pojo de partida a partir del pojo de creación de partida.
     * @param pojo
     * @param nombreAdmin 
     */
    public POJOPartida(POJOCreacionPartida pojo, String nombreAdmin) {
        this(null, nombreAdmin, crearListadoJugadores(nombreAdmin), new ArrayList<String>(), 
             pojo.getCantidadRondas(), pojo.getCantidadMaximaJugadores(), pojo.getNombre(), 
             "En espera", null);
    }

    /**
     * @param nombreAdmin
     * @return un listado de jugadores que solo contiene el nombre dado.
     */
    private static List<String> crearListadoJugadores(String nombre) {
        List<String> listado = new ArrayList<String>();
        listado.add(nombre);
        return listado;
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
               otro.nombre.equals(nombre) &&
               proyeccion == null ? otro.proyeccion == null : proyeccion.equals(otro.proyeccion);
    }
    
    @Override
    public String toString() {
        return "POJOPartida [administrador=" + administrador + ", jugadores=" + jugadores
                + ", espectadores=" + espectadores + ", cantidadRondas=" + cantidadRondas
                + ", cantidadMaximaJugadores=" + cantidadMaximaJugadores + ", nombre=" + nombre
                + "]";
    }

    public String getEstado() {
        return estado;
    }
    
    public ProyeccionTablero getProyeccion() {
        return proyeccion;
    }

    /**
     * Establece la proyección de la partida.
     * @param proyeccion2
     */
    public void setProyeccion(ProyeccionTablero proyeccion) {
        this.proyeccion = proyeccion;
    }
}
