package com.rzg.zombieland.server.sesion;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Singleton que administra la sesión de los jugadores. Su responsabilidad es registrar y rastrear
 * quién ha iniciado sesión, pero no valida los datos del jugador.
 * @author nicolas
 *
 */
public class ServicioSesion {

    // Instancia del servicio de sesión.
    private static ServicioSesion instancia;
    
    // Rastrea a los jugadores que han iniciado sesión, identificándolos a través de un token 
    // generado aleatoriamente.
    private Map<String, Jugador> jugadoresActivos;
    
    private ServicioSesion() {
        jugadoresActivos = new HashMap<String, Jugador>();
    }
    
    /**
     * @return la instancia activa de {@link ServicioSesion}.
     */
    public static ServicioSesion getInstancia() {
        if (instancia == null)
            instancia = new ServicioSesion();
        return instancia;
    }

    /**
     * Inicia la sesión de un jugador.
     * @param jugador - el jugador que inicia sesión.
     * @return el token de inicio de sesión con el que se identifica unívocamente.
     */
    public String alta(Jugador jugador) {
        String token = UUID.randomUUID().toString();
        jugadoresActivos.put(token, jugador);
        return token;
    }
}
