package com.rzg.zombieland.comunes.misc;

/**
 * Estado de la partida actual.
 * 
 * @author nicolas
 *
 */
public enum EstadoPartida {
    /**
     * Todavía no ha arrancado, está en la fase de espera de jugadores.
     */
    EN_ESPERA("En espera"),

    /**
     * La partida está en progreso.
     */
    ACTIVA("Activa"),

    /**
     * La partida ha finalizado.
     */
    FINALIZADA("Finalizada"), 
    
    /**
     * Una ronda terminó y estamos esperando a la siguiente.
     */
    ENTRE_RONDAS("Entre rondas");

    // Una descripción amigable para el usuario del estado.
    private String descripcion;

    private EstadoPartida(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * @return una descripción amigable para el usuario.
     */
    public String getDescripcion() {
        return descripcion;
    }
}