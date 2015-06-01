package com.rzg.zombieland.server.persistencia;

import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.meta.Jugador;

public class JugadorTest extends PersistenciaTest<Jugador, String> {

    /**
     * Genera un jugador con parámetros aleatorios.
     * @return
     * @throws ZombielandException
     */
    @Override
    protected Jugador generarObjeto() throws ZombielandException {
        return generarJugador();
    }

    @Override
    protected void actualizarObjeto(Jugador objeto) throws ZombielandException {
        objeto.setClave("000_000");
    }

    @Override
    protected String getIdObjeto(Jugador objeto) {
        return objeto.getNombre();
    }

    @Override
    protected Dao<Jugador, String> getDao() {
        return new JugadorDao();
    }
}
