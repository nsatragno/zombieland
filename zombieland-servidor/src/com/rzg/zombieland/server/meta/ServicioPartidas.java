package com.rzg.zombieland.server.meta;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
     */
    public void addPartida(Partida partida) {
        partidas.put(partida.getId(), partida);
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
}
