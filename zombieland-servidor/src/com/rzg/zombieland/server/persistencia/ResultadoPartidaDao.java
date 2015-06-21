package com.rzg.zombieland.server.persistencia;

import com.rzg.zombieland.server.meta.ResultadoJugador;

/**
 * Administra la persistencia de un resultado de partida.
 * @author nicolas
 *
 */
public class ResultadoPartidaDao extends Dao<ResultadoJugador, Integer> {
    /**
     * Crea un ResultadoPartidaDao.
     */
    public ResultadoPartidaDao() {
        super(ResultadoJugador.class);
    }
}
