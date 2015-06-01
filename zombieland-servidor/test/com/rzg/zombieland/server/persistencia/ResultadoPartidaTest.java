package com.rzg.zombieland.server.persistencia;

import java.util.Random;
import java.util.UUID;

import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.meta.Jugador;
import com.rzg.zombieland.server.meta.ResultadoPartida;

/**
 * Tests de persistencia de ResultadoPartida.
 * @author nicolas
 *
 */
public class ResultadoPartidaTest extends PersistenciaTest<ResultadoPartida, UUID> {

    @Override
    protected ResultadoPartida generarObjeto() throws ZombielandException {
        Random random = new Random();
        // Ya debe existir un jugaro en la DB para poder guardar al resultado de partida.
        Jugador jugador = generarJugador();
        JugadorDao dao = new JugadorDao();
        dao.guardarObjeto(jugador);
        dao.cerrarSesion();
        return new ResultadoPartida(Math.abs(random.nextInt()), Math.abs(random.nextInt()), jugador);
    }

    @Override
    protected void actualizarObjeto(ResultadoPartida objeto) throws ZombielandException {
        Random random = new Random();
        objeto.setPuntos(Math.abs(random.nextInt()));
    }

    @Override
    protected Dao<ResultadoPartida, UUID> getDao() {
        return new ResultadoPartidaDao();
    }

    @Override
    protected UUID getIdObjeto(ResultadoPartida objeto) {
        return objeto.getId();
    }

}
