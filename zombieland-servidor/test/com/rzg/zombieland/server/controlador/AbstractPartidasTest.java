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
    protected List<POJOPartida> pojoPartidasCreadas;
    protected List<Partida> partidasCreadas;
    
    private Jugador ultimoAdmin;

    private int ultimaCantidadJugadores;

    private int ultimaCantidadRondas;

    private String ultimoNombre;

    /**
     * @return the partidasCreadas
     */
    public List<POJOPartida> getPartidasCreadas() {
        return pojoPartidasCreadas;
    }

    /**
     * Constructor por defecto.
     */
    public AbstractPartidasTest() {
        pojoPartidasCreadas = new ArrayList<POJOPartida>();
        partidasCreadas = new ArrayList<Partida>();
    }

    /**
     * Vuela el servicio de sesión y el de partidas.
     */
    @After
    public void tearDown() {
        ServicioSesion.matarInstancia();
        ServicioPartidas.matarInstancia();
        pojoPartidasCreadas.clear();
    }
    
    /**
     * Crea una partida con datos aleatorios.
     * @throws ParametrosNoValidosException 
     */
    protected Partida crearPartida() throws ParametrosNoValidosException {
        Random random = new Random();
        ultimoAdmin = crearJugador();
        int cantidadJugadores = random.nextInt(
                POJOCreacionPartida.CANTIDAD_MAXIMA_JUGADORES - 
                POJOCreacionPartida.CANTIDAD_MINIMA_JUGADORES) + 
                    POJOCreacionPartida.CANTIDAD_MINIMA_JUGADORES;
        ultimaCantidadRondas = cantidadJugadores * 2;
        ultimaCantidadJugadores = cantidadJugadores;
        ultimoNombre = UUID.randomUUID().toString();
        Partida partida = new Partida(getUltimoAdmin(),
                                      new POJOCreacionPartida(ultimaCantidadRondas, 
                                                              ultimaCantidadJugadores, 
                                                              ultimoNombre));
        ServicioPartidas.getInstancia().addPartida(partida);
        pojoPartidasCreadas.add(partida.getPOJO(null));
        partidasCreadas.add(partida);
        return partida;
    }

    /**
     * @return el último admin adjuntado a una partida.
     */
    public Jugador getUltimoAdmin() {
        return ultimoAdmin;
    }
    
    /**
     * @return the ultimaCantidadJugadores
     */
    public int getUltimaCantidadJugadores() {
        return ultimaCantidadJugadores;
    }

    /**
     * @return the ultimaCantidadRondas
     */
    public int getUltimaCantidadRondas() {
        return ultimaCantidadRondas;
    }

    /**
     * @return the ultimoNombre
     */
    public String getUltimoNombre() {
        return ultimoNombre;
    }

    protected Jugador crearJugador() throws ParametrosNoValidosException {
        String clave = UUID.randomUUID().toString();
        Jugador jugador = new Jugador(UUID.randomUUID().toString(), 
                                      clave,
                                      clave,
                                      UUID.randomUUID().toString(),
                                      UUID.randomUUID().toString());
        jugador.setId(new Random().nextInt());
        return jugador;
    }


}
