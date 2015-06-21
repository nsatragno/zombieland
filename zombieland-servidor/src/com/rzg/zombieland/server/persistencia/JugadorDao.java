package com.rzg.zombieland.server.persistencia;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.rzg.zombieland.server.sesion.Jugador;

/**
 * Administra la persistencia de un jugador.
 * @author nicolas
 *
 */
public class JugadorDao extends Dao<Jugador, Integer> {
    /**
     * Crea un JugadorDao.
     */
    public JugadorDao() {
        super(Jugador.class);
    }

    public Jugador getJugadorPorNombre(String nombre) {
        Criteria criteria = getSession().createCriteria(Jugador.class);
        criteria.add(Restrictions.eq("nombre", nombre));
        return (Jugador) criteria.uniqueResult();
    }
}
