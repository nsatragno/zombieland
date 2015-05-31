package com.rzg.zombieland.server.persistencia;

import java.util.UUID;

import com.rzg.zombieland.server.meta.Jugador;

/**
 * Administra la persistencia de un jugador.
 * @author nicolas
 *
 */
public class JugadorDao extends Dao<Jugador, UUID> {
    public JugadorDao() {
        super(Jugador.class);
    }
}
