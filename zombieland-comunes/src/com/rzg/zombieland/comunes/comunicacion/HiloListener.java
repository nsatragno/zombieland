package com.rzg.zombieland.comunes.comunicacion;

/**
 * Define una interfaz que se usa para ser notificado de eventos en el hilo de escucha.
 * @author nicolas
 *
 */
public interface HiloListener {
    /**
     * Notifica que el hilo se cerró.
     * @param hilo
     */
    public void hiloCerrado(HiloEscucha hilo);
}
