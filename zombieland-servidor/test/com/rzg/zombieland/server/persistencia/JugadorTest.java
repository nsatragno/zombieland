package com.rzg.zombieland.server.persistencia;

import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.sesion.Jugador;

/**
 * Test de persistencia para la entidad Jugador.
 * @author nicolas
 *
 */
public class JugadorTest extends PersistenciaTest<Jugador, Integer> {

    @Override
    protected Jugador generarObjeto() throws ParametrosNoValidosException {
        return generarJugador();
    }

    @Override
    protected void actualizarObjeto(Jugador objeto) throws ZombielandException {
        objeto.setClave("000_000");
    }

    @Override
    protected Integer getIdObjeto(Jugador objeto) {
        return objeto.getId();
    }

    @Override
    protected Dao<Jugador, Integer> getDao() {
        return new JugadorDao();
    }
}
