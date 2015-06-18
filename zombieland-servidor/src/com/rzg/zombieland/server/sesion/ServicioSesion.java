package com.rzg.zombieland.server.sesion;

import java.util.HashMap;
import java.util.Map;

import com.rzg.zombieland.server.sesion.Sesion.SesionListener;


/**
 * Mantiene en memoria todas las sesiones.
 * @author nicolas
 *
 */
public class ServicioSesion implements SesionListener {

    // La instancia en sí del servicio de partidas.
    private static ServicioSesion instancia;
    
    private Map<Jugador, Sesion> sesiones;
    
    /**
     * Constructor por defecto.
     */
    private ServicioSesion() {
        sesiones = new HashMap<Jugador, Sesion>();
    }
    
    /**
     * @return la instancia del servicio de partidas.
     */
    public static ServicioSesion getInstancia() {
        if (instancia == null)
            instancia = new ServicioSesion();
        return instancia;
    }
    
    /**
     * Agrega una sesión.
     * @param sesion
     */
    public synchronized void addSesion(Sesion sesion) {
        sesiones.put(sesion.getJugador(), sesion);
        sesion.addListener(this);
    }

    /**
     * @param jugador 
     * @return la sesión del jugador, o null si no existe.
     */
    public synchronized Sesion getSesion(Jugador jugador) {
        return sesiones.get(jugador);
    }

    /**
     * Destruye la instancia del servicio (para tests).
     */
    public static void matarInstancia() {
        instancia = null;
    }

    @Override
    public synchronized void notificarSesionCerrada(Sesion sesion) {
        sesiones.remove(sesion.getJugador());
    }
}
