package com.rzg.zombieland.server.comunicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.server.juego.BucleJuego;

public class ServicioJuego {

	private static ServicioJuego instancia;
	private List<BucleJuego> listaBucles;
	private Semaphore semaforo;
	private boolean matandoHilos;
	
	public ServicioJuego(){
		listaBucles = new ArrayList<BucleJuego>();
		Semaphore semaforo = new Semaphore(1);
		matandoHilos = false;
	}
	
	public static ServicioJuego getInstancia() {
		if(instancia == null)
			instancia = new ServicioJuego();
		return instancia;
	}
	
	public void agregarBucle(BucleJuego bucle) {
		listaBucles.add(bucle);
	}
	
	public void quitarDeLista(BucleJuego bucle) {
		try {
			semaforo.acquire();
			if(!matandoHilos)
				listaBucles.remove(bucle);
			semaforo.release();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void matarBucles(){
		try {
			semaforo.acquire();
			matandoHilos = true;
		} catch (InterruptedException e1) {
		}
		for(BucleJuego bucle : listaBucles) {
			bucle.interrupt();
			try {
				semaforo.release();
				bucle.join();
				semaforo.acquire();
			} catch (InterruptedException e) {
				Log.error("Houston do you copy me?");
			}
		}
		semaforo.release();
	}
}
