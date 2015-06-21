package com.rzg.zombieland.server.juego;

import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.meta.Partida;

public class BucleJuego extends Thread implements Runnable {

	private Partida partida;
	private final long TIEMPO_TURNO = 5000;

	public BucleJuego(Partida partida) {
		this.partida = partida;
	}
	
	@Override
	public void run() {
		try {
			Thread.sleep(TIEMPO_TURNO);
			try {
				partida.moverTodos();
				partida.enviarProyeccion();
			} catch (ZombielandException e) {
				Log.error("La partida no arrancó.");
				Log.error(e.getMessage());
				return;
			}
		} catch (InterruptedException e) {
			Log.info("Cerrando hilo de bucle de juego.");
			return;
		}
		
	}

	
}
