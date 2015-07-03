package com.rzg.zombieland.server.juego;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero;
import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero.POJOEntidad;
import com.rzg.zombieland.comunes.misc.Coordenada;
import com.rzg.zombieland.server.sesion.Jugador;

/**
 * El tablero de juego, que contiene obstáculos, humanos y zombies.
 * 
 * @author Nicolas, Manuel
 *
 */
public class Tablero {
    // El tablero en sí, con todos sus actores. Incluidos obstáculos.
    private EntidadTablero[][] matriz;

    // Jugadores que participan - Humanos solamente.
    private List<Personaje> personajes;

    // Listado de personajes convertidos en el orden en el que se convirtieron. El primero siempre
    // es el zombie original.
    private List<Personaje> jugadoresConvertidos;

    /**
     * Constructor por defecto. Aquí se generarán los obstáculos en forma
     * 'aleatoria'
     */
    public Tablero(int casilleros, List<? extends Jugador> jugadores, Jugador zombi) {
        personajes = new ArrayList<Personaje>();
        jugadoresConvertidos = new ArrayList<Personaje>();
        Random rnd = new Random(); // Que quede claro que va a ser una cuestión
                                   // de suerte
        boolean resuelto = false; // Flag que me indica si ya posicione o no a
                                  // la entidad.

        
        
        matriz = new EntidadTablero[casilleros][casilleros];
        // Ponemos al zombi - primero le asignamos el nombre.
        Zombie personajeZombie = new Zombie(zombi, new Coordenada(casilleros / 2, casilleros / 2), this);
        jugadoresConvertidos.add(personajeZombie);
        personajes.add(personajeZombie);
        // Siempre arranca en el medio.
        matriz[casilleros / 2][casilleros / 2] = personajeZombie; // Lo ponemos
                                                                  // en la
                                                                  // matriz.

        // Ponemos los obstaculos. Si la matriz es de 10x10, son 100 casilleros.
        // Con 25 obstáculos estariamos bien -- Sería el 25%
        // Con el 30%, usando el algoritmo aleatorio, puede terminar en un bucle
        // infinito. Cosas que pasan.

        // Totalmente aleatorio - Resultado: En 10 corridas,
        // 4 veces encerró a un jugador.
        // Verificando que en las diagonales tampoco haya obstaculos -
        // Resultado: Luz verde. En 100000 corridas, luz verde.

        for (int i = 0; i < Math.pow(casilleros, 2) * 0.25; i++) {
            resuelto = false;
            while (!resuelto) {
                Coordenada c = new Coordenada(Math.abs(rnd.nextInt()) % casilleros, Math.abs(rnd.nextInt())
                        % casilleros);
                if (matriz[c.getX()][c.getY()] == null
                        && matriz[c.getX() + 1 >= casilleros ? c.getX() : c.getX() + 1][c.getY() + 1 >= casilleros ? c
                                .getY() : c.getY() + 1] == null
                        && matriz[c.getX() - 1 < 0 ? c.getX() : c.getX() - 1][c.getY() + 1 >= casilleros ? c
                                .getY() : c.getY() + 1] == null
                        && matriz[c.getX() + 1 >= casilleros ? c.getX() : c.getX() + 1][c.getY() - 1 < 0 ? c
                                .getY() : c.getY() - 1] == null
                        && matriz[c.getX() - 1 < 0 ? c.getX() : c.getX() - 1][c.getY() - 1 < 0 ? c
                                .getY() : c.getY() - 1] == null) {
                    matriz[c.getX()][c.getY()] = new Obstaculo(c);
                    resuelto = true;
                }
            }
        }

        // Ponemos a los humanos
        for (Jugador jugador : jugadores) {
            if (jugador == zombi)
                continue;
            resuelto = false;
            while (!resuelto) {
                Coordenada c = new Coordenada(Math.abs(rnd.nextInt()) % casilleros, Math.abs(rnd.nextInt())
                        % casilleros);
                if (matriz[c.getX()][c.getY()] == null) {
                    Humano h = new Humano(jugador, c, this);
                    matriz[c.getX()][c.getY()] = h;
                    resuelto = true;
                    personajes.add(h);
                }
            }

        }

    }

    /**
     * @param superiorIzquierda
     * @param inferiorDerecha
     * @param tiempoTurno - el tiempo en milisegundos para que arranque el siguiente turno.
     * @return la proyección del tablero entre las dos esquinas dadas.
     */
    private ProyeccionTablero getProyeccion(Coordenada superiorIzquierda,
                                            Coordenada inferiorDerecha, 
                                            int tiempoTurno) {
        superiorIzquierda =  normalizar(superiorIzquierda);
        inferiorDerecha = normalizar(inferiorDerecha);
        List<POJOEntidad> entidades = new ArrayList<POJOEntidad>();
        
        // Recorro mi matriz de entidades en los limites indicados por el
        // metodo.
        for (int i = superiorIzquierda.getX(); i <= inferiorDerecha.getX(); i++) {
            for (int j = superiorIzquierda.getY(); j <= inferiorDerecha.getY(); j++) {
                if (matriz[i][j] != null) {
                    // Agrego las entidades que encuentre a la lista de la
                    // proyeccion
                    entidades.add(new POJOEntidad("Elemento" + i + j, new Coordenada(i, j),
                                  matriz[i][j].getAvatar()));
                }
            }
        }
        // Devuelvo la proyección. Chiche bombón.
        return new ProyeccionTablero(
                matriz.length, superiorIzquierda, inferiorDerecha, entidades, tiempoTurno);
    }

    /**
     * @param coordenada
     * @return una coordenada que se ajusta a los límites del tablero de acuerdo a la coordanada 
     *         dada.
     */
    private Coordenada normalizar(Coordenada coordenada) {
        if (coordenada.getX() >= matriz.length)
            coordenada = new Coordenada(matriz.length - 1, coordenada.getY());
        else if (coordenada.getX() < 0)
            coordenada = new Coordenada(0, coordenada.getY());
        
        if (coordenada.getY() >= matriz.length)
            coordenada = new Coordenada(coordenada.getX(), matriz.length - 1);
        else if (coordenada.getY() < 0)
            coordenada = new Coordenada(coordenada.getX(), 0);
        
        return coordenada;
    }

    /**
     * Obtiene la entidad por coordenada.
     * 
     * @param coordenada
     * @return la entidad en la coordenada dada, o null si no hay ninguna.
     */
    public EntidadTablero getEntidadEn(Coordenada coordenada) {
        return matriz[coordenada.getX()][coordenada.getY()];
    }

    /**
     * Mueve una entidad.
     * 
     * @param desde
     *            - coordenada donde la entidad original se encuentra.
     * @param hasta
     *            - coordenada de destino. Debe estar vacía.
     */
    public synchronized Coordenada moverEntidad(Coordenada desde, Coordenada hasta) {
        // Primero pregunto si a la posición a la cual quiere desplazarse no hay
        // nada
        if (fueraDeLaMatriz(desde)) {
            throw new InvalidParameterException(
                    "La coordenada desde está fuera de los límites de la matriz");
        }
        if (fueraDeLaMatriz(hasta)) {
            // No se mueva nada.
            return desde;
        }
        if (matriz[hasta.getX()][hasta.getY()] == null) {
            matriz[hasta.getX()][hasta.getY()] = matriz[desde.getX()][desde.getY()];
            matriz[desde.getX()][desde.getY()] = null;
            return hasta;
        } else {
            getEntidadEn(desde).colisionar(getEntidadEn(hasta));
            getEntidadEn(hasta).colisionar(getEntidadEn(desde));
            return desde;
        }
    }

    /**
     * @param coordenada
     * @return true si la coordenada está fuera de la matriz, false de lo contrario.
     */
    private boolean fueraDeLaMatriz(Coordenada coordenada) {
        return (coordenada.getX() >= matriz.length || coordenada.getX() < 0 ||
                coordenada.getY() >= matriz.length || coordenada.getY() < 0);
    }

    /**
     * Mueve a todos los personajes.
     */
    public void moverTodos() {
        synchronized (personajes) {
            // Ordenamos los personajes de acuerdo al orden en el que realizaron los
            // movimientos.
            Collections.sort(personajes, new Comparator<Personaje>() {
    
                @Override
                public int compare(Personaje p1, Personaje p2) {
                    return p1.compareTo(p2);
                }
            });
            for (Personaje personaje : personajes)
                personaje.mover();
        }
    }

    /**
     * Quita a un jugador del tablero.
     * @param jugadorEliminado
     */
    public void removerJugador(Jugador jugadorEliminado) {
        synchronized (personajes) {
            boolean quedanZombies = false;
            Iterator<Personaje> iterator = personajes.iterator();
            while (iterator.hasNext()) {
                Personaje personaje = iterator.next();
                if (personaje.getJugador().getId().equals(jugadorEliminado.getId())) {
                    iterator.remove();
                    matriz[personaje.getPosicion().getX()][personaje.getPosicion().getY()] = null;
                } else if (personaje.esZombie()) {
                    quedanZombies = true;
                }
            }
            if (!quedanZombies) {
                int index = new Random().nextInt(personajes.size());
                Personaje personaje = personajes.get(index);
                Coordenada posicion = personaje.getPosicion();
                
                Zombie zombie = new Zombie(personaje.getJugador(), posicion, this);
                personajes.set(index, zombie);
                matriz[posicion.getX()][posicion.getY()] = zombie;
            }
        }
    }
    
    /**
     * @return true si la partida finalizó, false de lo contrario.
     */
    public boolean partidaFinalizada() {
        synchronized (personajes) {
            for (Personaje personaje : personajes) {
                if (!personaje.esZombie())
                    return false; 
            }
        }
        return true;
    }

    /**
     * Remplaza la entidad en el tablero con la dada.
     * @param posicion
     * @param zombie
     */
    public void remplazarEntidadEn(Coordenada posicion, Zombie zombie) {
        synchronized (personajes) {
            for (int i = 0; i < personajes.size(); i++) {
            Personaje personaje = personajes.get(i);
            if (personaje.getJugador().getId().equals(zombie.getJugador().getId()))
                personajes.set(i, zombie);
            }
        }
        matriz[posicion.getX()][posicion.getY()] = zombie;
    }

    public ProyeccionTablero getProyeccionJugador(Jugador jugador, int tiempoTurno) {
        Personaje personajeJugador = null;
        synchronized (personajes) {
            for (Personaje personaje : personajes) {
                if (personaje.getJugador().getId().equals(jugador.getId())) {
                    personajeJugador = personaje;
                    break;
                }
            }
        }
        Coordenada[] rectanguloVision;
        if (personajeJugador == null) {
            // Es la proyección para un espectador.
            rectanguloVision = new Coordenada[] { 
                new Coordenada(0, 0), 
                new Coordenada(matriz.length - 1, matriz.length - 1)
            };
        } else {
            rectanguloVision = personajeJugador.getRectanguloVision();
        }
        return getProyeccion(rectanguloVision[0], rectanguloVision[1], tiempoTurno);
    }

    /**
     * @return la esquina inferior derecha del tablero.
     */
    public Coordenada getEsquinaInferiorDerecha() {
        return new Coordenada(matriz.length - 1, matriz.length - 1);
    }

    /**
     * @param numero - una posición N de jugador.
     * @return el jugador que fue convertido en N lugar.
     */
    public Jugador getJugadorConvertidoNumero(int numero) {
        return jugadoresConvertidos.get(numero).getJugador();
    }
    
    /**
     * Añade uno al listado de personajes convertidos.
     * @param personaje
     */
    public void addPersonajeConvertido(Personaje personaje) {
        jugadoresConvertidos.add(personaje);
    }
}
