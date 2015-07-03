package com.rzg.zombieland.server.persistencia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.server.meta.ResultadoJugador;
import com.rzg.zombieland.server.sesion.Jugador;

/**
 * Maneja una sesión de Hibernate.
 * @author nicolas
 *
 */
public class HibernateSingleton {
    private final SessionFactory sessionFactory;
    private static HibernateSingleton instancia;
    private static boolean test = false;
    
    /**
     * Inicializa el singleton con un nombre de DB particular.
     * @param nombreDB
     */
    private HibernateSingleton(String nombreDB) {
        sessionFactory = new Configuration()
        .setProperty("hibernate.connection.url", (test ? "jdbc:hsqldb:mem:" : "jdbc:hsqldb:file:") + nombreDB)
        .setProperty("hibernate.hbm2ddl.auto", test ? "create" : "update")
        .setProperty("driver_class", "org.hsqldb.jdbcDriver")
        .setProperty("hibernate.connection.username", "sa")
        .setProperty("hibernate.connection.password", "cambiar")
        .setProperty("hibernate.connection.pool_size", "1")
        .setProperty("hibernate.connection.dialect", "org.hibernate.dialect.HSQLDialect")
        .setProperty("current_session_context_class", "thread")
        .setProperty("show_sql", "false")
        .setProperty("cache.provider_class", "org.hibernate.cache.NoCacheProvider")
        .addAnnotatedClass(ResultadoJugador.class)
        .addAnnotatedClass(Jugador.class)
        .buildSessionFactory();
    }
    
    /**
     * Pone a la base de datos en modo «test». Debe llamarse antes del primer getInstancia().
     */
    public static void setTest() {
        test = true;
    }

    /**
     * @return la instancia de HibernateSingleton.
     */
    public synchronized static HibernateSingleton getInstancia() {
        if (instancia == null) {
            if (test)
                instancia = new HibernateSingleton("db/test.db");
            else
                instancia = new HibernateSingleton("db/produccion.db");
        }
        return instancia;
    }
    
    /**
     * Abre una sesión de Hibernate. ¡No olvides cerrarla!
     * @return la sesión.
     */
    public Session openSession() {
        return sessionFactory.openSession();
    }
    
    /**
     * Cierra la conexión con la DB. Es necesario para que se pueda cerrar el programa.
     */
    public static void cerrarConexion() {
        getInstancia().sessionFactory.close();
        instancia = null;
    }

    /**
     * "Calienta" la base de datos con una consulta para no tener que esperar que se verifiquen 
     * todas las tablas con la primera consulta.
     */
    public static void prepararDB() {
        Log.info("Preparando base de datos");
        Session session = getInstancia().sessionFactory.openSession();
        session.createCriteria(Jugador.class);
        session.get(Jugador.class, 1);
        session.close();
        Log.info("Base de datos lista");
    }
}
