package com.rzg.zombieland.server.persistencia;

import java.util.UUID;

import com.rzg.zombieland.server.meta.ResultadoJugador;

/**
 * Administra la persistencia de un resultado de partida.
 * @author nicolas
 *
 */
public class ResultadoPartidaDao extends Dao<ResultadoJugador, UUID> {
    /**
     * Crea un ResultadoPartidaDao.
     */
    public ResultadoPartidaDao() {
        super(ResultadoJugador.class);
    }
}
