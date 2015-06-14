package com.rzg.zombieland.server.controlador;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import org.junit.After;

import com.rzg.zombieland.comunes.comunicacion.pojo.POJOCreacionPartida;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.server.meta.Partida;
import com.rzg.zombieland.server.meta.ServicioPartidas;
import com.rzg.zombieland.server.sesion.Jugador;
import com.rzg.zombieland.server.sesion.ServicioSesion;

public abstract class AbstractPartidasTest {
    protected List<POJOPartida> partidasCreadas;

    /**
     * Constructor por defecto.
     */
    public AbstractPartidasTest() {
        partidasCreadas = new ArrayList<POJOPartida>();
    }

    /**
     * Vuela el servicio de sesión y el de partidas.
     */
    @After
    public void tearDown() {
        ServicioSesion.matarInstancia();
        ServicioPartidas.matarInstancia();
        partidasCreadas.clear();
    }
    
    /**
     * Crea una partida con datos aleatorios.
     * @throws ParametrosNoValidosException 
     */
    protected void crearPartida() throws ParametrosNoValidosException {
        Random random = new Random();
        String clave = UUID.randomUUID().toString();
        Jugador admin = new Jugador(UUID.randomUUID().toString(), 
                                    clave,
                                    clave,
                                    UUID.randomUUID().toString(),
                                    UUID.randomUUID().toString());
        int cantidadJugadores = random.nextInt(
                POJOCreacionPartida.CANTIDAD_MAXIMA_JUGADORES - 
                POJOCreacionPartida.CANTIDAD_MINIMA_JUGADORES) + 
                    POJOCreacionPartida.CANTIDAD_MINIMA_JUGADORES;
        Partida partida = new Partida(admin,
                                      new POJOCreacionPartida(cantidadJugadores * 2, 
                                                              cantidadJugadores, 
                                                              UUID.randomUUID().toString()));
        ServicioPartidas.getInstancia().addPartida(partida);
        partidasCreadas.add(partida.getPOJO());
    }   
}
