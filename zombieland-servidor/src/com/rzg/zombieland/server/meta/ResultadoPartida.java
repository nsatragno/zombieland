package com.rzg.zombieland.server.meta;

import java.security.InvalidParameterException;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Almacena el resultado de una partida para un solo jugador con fin de registrar estadísticas.
 * @author nicolas
 *
 */
@Entity
public class ResultadoPartida {
    // Identificación del resultado.
    @Id
    private UUID id;
    
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
    public ResultadoPartida() {
        
    }
    
    /**
     * Crea un nuevo resultado de partida.
     * @param puntos
     * @param ranking
     */
    public ResultadoPartida(int puntos, int ranking, Jugador jugador) {
        if (puntos < 0)
            throw new InvalidParameterException("El puntaje no puede ser menor a cero");
        this.puntos = puntos;
        
        if (ranking < 1)
            throw new InvalidParameterException("El ranking no puede ser menor a uno");
        this.ranking = ranking;
        
        if (jugador == null)
            throw new NullPointerException("El jugador no puede ser null");
        this.jugador = jugador;
        
        id = UUID.randomUUID();
    }
    
    public int getPuntos() {
        return puntos;
    }
    
    public int getRanking() {
        return ranking;
    }
    
    @Override
    public String toString() {
        return "ResultadoPartida [id=" + id + ", puntos=" + puntos + ", ranking=" + ranking
                + ", jugador=" + jugador + "]";
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public UUID getId() {
        return id;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof ResultadoPartida)) return false;
        ResultadoPartida otro = (ResultadoPartida)obj;
        return id.equals(otro.id) &&
               puntos == otro.puntos &&
               ranking == otro.ranking &&
               jugador.equals(otro.jugador);
    }
}
