package com.rzg.zombieland.server.persistencia;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Session;

/**
 * Administra la persistencia de un objeto.
 * @author nicolas
 * @param <T> El tipo de objeto que se persiste.
 * @param <T_ID> El tipo del ID del objeto que se persiste.
 *
 */
public abstract class Dao<T, T_ID extends Serializable> {
    // La sesión de Hibernate actual.
    private Session session;
    
    // La clase a la que hace referencia el DAO.
    private Class<T> klass;
    
    /**
     * Las clases que heredan deben implementar este constructor con la clase particular que
     * persisten.
     * @param klass
     */
    protected Dao(Class<T> klass) {
        if (klass == null)
            throw new NullPointerException();
        this.klass = klass;
    }
    
    /**
     * Devuelve una sesión de Hibernate, abriéndola si es necesario.
     * @return la sesión de hibernate.
     */
    protected Session getSession() {
        if (session == null) {
            session = HibernateSingleton.getInstancia().openSession();
            session.beginTransaction();
        }
        return session;
    }
    
    /**
     * @param id
     * @return un objeto según su ID.
     */
    public T getObjeto(T_ID id) {
        return getSession().get(klass, id);
    }
    
    /**
     * Guarda un objeto en la DB.
     * @param objeto
     */
    public void guardarObjeto(T objeto) {
        getSession().save(objeto);
    }

    /**
     * Actualiza un objeto en la DB.
     * @param objeto
     */
    public void actualizarObjeto(T objeto) {
        getSession().update(objeto);
    }
    
    /**
     * Finaliza la transacción y cierra la sesión de Hibernate.
     */
    public void cerrarSesion() {
        getSession().getTransaction().commit();
        getSession().close();
        session = null;
    }

    /**
     * @return un listado de objetos sin filtrar.
     */
    @SuppressWarnings("unchecked")
    public List<T> getListado() {
        return getSession().createCriteria(klass).list();
    }
}
