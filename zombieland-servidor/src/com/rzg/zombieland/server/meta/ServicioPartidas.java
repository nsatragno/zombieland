package com.rzg.zombieland.server.meta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.rzg.zombieland.comunes.comunicacion.HiloEscucha;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.POJOListadoPartidas;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.comunicacion.peticion.PeticionListadoPartidas;
import com.rzg.zombieland.server.interfaz.Principal;

/**
 * Almancena la lista de partidas actual.
 * @author nicolas
 *
 */
public class ServicioPartidas {

    private static ServicioPartidas instancia;

    private Map<UUID, Partida> partidas;
    
    private ServicioPartidas() {
        partidas = new HashMap<UUID, Partida>();
    }
    
    /**
     * @return la instancia del servicio de partidas.
     */
    public static ServicioPartidas getInstancia() {
        if (instancia == null)
            instancia = new ServicioPartidas();
        return instancia;
    }

    /**
     * Añade una partida.
     * @param partida
     * @throws ZombielandException 
     */
    public synchronized void addPartida(Partida partida) {
        partidas.put(partida.getId(), partida);
        if (Principal.getServicioEscucha() != null) {
            Principal.getServicioEscucha().broadcast(obtenerPeticion());
        }
    }

    private PeticionListadoPartidas obtenerPeticion() {
        List<POJOPartida> listado = new ArrayList<POJOPartida>();
        for (Partida partidaExistente : partidas.values())
            listado.add(partidaExistente.getPOJO());
        return new PeticionListadoPartidas(new POJOListadoPartidas(listado));
    }

    /**
     * @return el listado de partidas actual.
     */
    public Collection<Partida> getPartidas() {
        return partidas.values();
    }

    /**
     * Vuela la instancia para tests.
     */
    public static void matarInstancia() {
        instancia = null;
    }

    /**
     * @param fromString
     * @return una partida según su ID.
     */
    public Partida getPartida(UUID id) {
        return partidas.get(id);
    }

    /**
     * Envía el listado de partidas a través del hilo dado.
     * @param hilo
     */
    public void enviarPartidas(HiloEscucha hilo) {
        try {
            hilo.enviarPeticion(obtenerPeticion());
        } catch (ZombielandException e) {
        }
    }
}
