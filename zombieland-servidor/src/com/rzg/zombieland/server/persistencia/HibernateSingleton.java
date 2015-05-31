package com.rzg.zombieland.server.persistencia;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.rzg.zombieland.server.meta.Jugador;
import com.rzg.zombieland.server.meta.ResultadoPartida;

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
        .setProperty("hibernate.connection.url", "jdbc:hsqldb:file:" + nombreDB)
        .setProperty("hibernate.hbm2ddl.auto", test ? "create" : "update")
        .addAnnotatedClass(ResultadoPartida.class)
        .addAnnotatedClass(Jugador.class)
        .configure("com/rzg/zombieland/server/persistencia/hibernate.config.xml")
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
    public static HibernateSingleton getInstancia() {
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
     * @return
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
}
