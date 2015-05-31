package com.rzg.zombieland.server.persistencia;

import java.util.UUID;

import com.rzg.zombieland.server.meta.ResultadoPartida;

/**
 * Administra la persistencia de un resultado de partida.
 * @author nicolas
 *
 */
public class ResultadoPartidaDao extends Dao<ResultadoPartida, UUID> {
    public ResultadoPartidaDao() {
        super(ResultadoPartida.class);
    }
}
