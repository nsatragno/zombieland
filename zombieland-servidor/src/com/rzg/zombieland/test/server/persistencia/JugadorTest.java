package com.rzg.zombieland.test.server.persistencia;

import java.util.UUID;

import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.meta.Jugador;
import com.rzg.zombieland.server.persistencia.Dao;
import com.rzg.zombieland.server.persistencia.JugadorDao;

public class JugadorTest extends PersistenciaTest<Jugador, UUID> {

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
        objeto.setNombre("Jugador Juan");
    }

    @Override
    protected UUID getIdObjeto(Jugador objeto) {
        return objeto.getId();
    }

    @Override
    protected Dao<Jugador, UUID> getDao() {
        return new JugadorDao();
    }
}
