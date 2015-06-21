package com.rzg.zombieland.server.meta;

import java.security.InvalidParameterException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.rzg.zombieland.server.sesion.Jugador;

/**
 * Almacena el resultado de una partida para un solo jugador con fin de registrar estadísticas.
 * @author nicolas
 *
 */
@Entity
public class ResultadoJugador {
    // Identificación del resultado.
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    
    // Puntos totales ganados en la partida.
    @Column
    private int puntos;
    
    // Indica el lugar en el que salió el jugador (1ro, 2do, etc).
    @Column
    private int ranking;
    
    // El jugador para el que se registra la estadística.
    @ManyToOne
    private Jugador jugador;
    
    /**
     * Constructor vacío para Hibernate.
     */
    public ResultadoJugador() {
        
    }
    
    /**
     * Crea un nuevo resultado de partida.
     * @param puntos
     * @param ranking
     * @param jugador 
     */
    public ResultadoJugador(int puntos, int ranking, Jugador jugador) {
        if (puntos < 0)
            throw new InvalidParameterException("El puntaje no puede ser menor a cero");
        this.puntos = puntos;
        
        if (ranking < 1)
            throw new InvalidParameterException("El ranking no puede ser menor a uno");
        this.ranking = ranking;
        
        if (jugador == null)
            throw new NullPointerException("El jugador no puede ser null");
        this.jugador = jugador;
    }
    
    /**
     * @return los puntos del resultado de la partida.
     */
    public int getPuntos() {
        return puntos;
    }
    
    /**
     * Actualiza los puntos del resultado de partida con el nuevo valor.
     * @param puntos
     */
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }
    
    /**
     * @return la posición en el que el jugador quedó dentro de la partida.
     */
    public int getRanking() {
        return ranking;
    }
    
    @Override
    public String toString() {
        return "ResultadoPartida [id=" + id + ", puntos=" + puntos + ", ranking=" + ranking
                + ", jugador=" + jugador + "]";
    }

    /**
     * @return el ID único de resultado de partida.
     */
    public int getId() {
        return id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof ResultadoJugador)) return false;
        ResultadoJugador otro = (ResultadoJugador)obj;
        return id == otro.id &&
               puntos == otro.puntos &&
               ranking == otro.ranking &&
               jugador.equals(otro.jugador);
    }
}
