package com.rzg.zombieland.server.meta;

import java.util.ArrayList;
import java.util.List;

/**
 * Almancena la lista de partidas actual.
 * @author nicolas
 *
 */
public class ServicioPartidas {

    private static ServicioPartidas instancia;

    private List<Partida> partidas;
    
    private ServicioPartidas() {
        partidas = new ArrayList<Partida>();
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
        partidas.add(partida);
    }

    /**
     * @return el listado de partidas actual.
     */
    public List<Partida> getPartidas() {
        return partidas;
    }
}
