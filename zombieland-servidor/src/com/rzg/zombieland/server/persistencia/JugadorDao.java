package com.rzg.zombieland.server.persistencia;

import com.rzg.zombieland.server.sesion.Jugador;

/**
 * Administra la persistencia de un jugador.
 * @author nicolas
 *
 */
public class JugadorDao extends Dao<Jugador, String> {
    /**
     * Crea un JugadorDao.
     */
    public JugadorDao() {
        super(Jugador.class);
    }
}
