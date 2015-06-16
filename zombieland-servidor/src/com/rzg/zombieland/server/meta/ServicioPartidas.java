package com.rzg.zombieland.server.meta;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.rzg.zombieland.comunes.comunicacion.EnviaPeticiones;
import com.rzg.zombieland.comunes.comunicacion.HiloEscucha;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.POJOListadoPartidas;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.comunicacion.peticion.PeticionListadoPartidas;
import com.rzg.zombieland.server.interfaz.Principal;
import com.rzg.zombieland.server.meta.Partida.PartidaListener;

/**
 * Almancena la lista de partidas actual.
 * @author nicolas
 *
 */
public class ServicioPartidas implements PartidaListener {

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
    public void addPartida(Partida partida) {
        synchronized (partidas) {
            partidas.put(partida.getId(), partida);
        }
        partida.setListener(this);
        notificarClientes();
    }

    /**
     * @return el listado de partidas actual.
     */
    public Collection<Partida> getPartidas() {
        synchronized (partidas) {
            return partidas.values();
        }
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
        synchronized (partidas) {
            return partidas.get(id);
        }
    }

    /**
     * Envía el listado de partidas a través del hilo dado.
     * @param hilo
     */
    public void enviarPartidas(EnviaPeticiones hilo) {
        try {
            if (hilo != null) {
                List<POJOPartida> listado = new ArrayList<POJOPartida>();
                synchronized (partidas) {
                    for (Partida partidaExistente : partidas.values())
                        listado.add(partidaExistente.getPOJO());    
                }
                hilo.enviarPeticion(new PeticionListadoPartidas(new POJOListadoPartidas(listado)));
            }
        } catch (ZombielandException e) {
            Log.error("No se pudo enviar actualización de partida a un hilo");
        }
    }
    
    /**
     * Le envía las partidas a todos los clientes.
     */
    private void notificarClientes() {
        if (Principal.getServicioEscucha() != null) {
            for (HiloEscucha hilo : Principal.getServicioEscucha().getHilos())
                enviarPartidas(hilo);
        }
    }

    @Override
    public void notificarPartidaVacia(Partida partida) {
        synchronized (partidas) {
            partidas.remove(partida.getId());
        }
        notificarClientes();
    }
}
