package com.rzg.zombieland.server.persistencia;

import java.util.Random;

import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.meta.ResultadoJugador;
import com.rzg.zombieland.server.sesion.Jugador;

/**
 * Tests de persistencia de ResultadoPartida.
 * @author nicolas
 *
 */
public class ResultadoJugadorTest extends PersistenciaTest<ResultadoJugador, Integer> {

    @Override
    protected ResultadoJugador generarObjeto() throws ParametrosNoValidosException {
        Random random = new Random();
        // Ya debe existir un jugador en la DB para poder guardar al resultado de partida.
        Jugador jugador = generarJugador();
        JugadorDao dao = new JugadorDao();
        dao.guardarObjeto(jugador);
        dao.cerrarSesion();
        return new ResultadoJugador(Math.abs(random.nextInt()), Math.abs(random.nextInt()), jugador);
    }

    @Override
    protected void actualizarObjeto(ResultadoJugador objeto) throws ZombielandException {
        Random random = new Random();
        objeto.setPuntos(Math.abs(random.nextInt()));
    }

    @Override
    protected Dao<ResultadoJugador, Integer> getDao() {
        return new ResultadoPartidaDao();
    }

    @Override
    protected Integer getIdObjeto(ResultadoJugador objeto) {
        return objeto.getId();
    }

}
