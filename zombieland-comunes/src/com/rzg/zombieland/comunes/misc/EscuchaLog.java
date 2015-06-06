package com.rzg.zombieland.comunes.misc;

/**
 * Define una interfaz que recibe notificaciones de log.
 * @author nicolas
 *
 */
public interface EscuchaLog {
	// Notifica de un log nuevo.
	public void onLog(String mensaje, int nivel);
}
