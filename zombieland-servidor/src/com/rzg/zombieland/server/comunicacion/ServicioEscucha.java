package com.rzg.zombieland.server.comunicacion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import com.rzg.zombieland.comunes.comunicacion.HiloEscucha;
import com.rzg.zombieland.comunes.comunicacion.HiloListener;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.comunicacion.controlador.ControladorServidorFactory;

/**
 * Escucha conexiones entrantes y lanza hilos para manejarlas.
 * 
 * @author nicolas
 *
 */
public class ServicioEscucha extends Thread implements HiloListener {
	// El puerto por defecto para el constructor sin argumentos.
    private final static int PUERTO_POR_DEFECTO = 2048;
    
    // Generador de sockets.
    private final ServerSocket serverSocket;

    // Indica si el hilo está corriendo.
    private boolean corriendo;
    
    // Mantiene una referencia a los hilos que creó para cerrarlos. 
    private List<HiloEscucha> hilosEscucha;
    
    // Bloquea la eliminación de hilos. 
    private Semaphore semaforo;
    
    // Indica si actualmente se están eliminado los hilos.
    private boolean matandoHilos;
    
    /**
     * Crea un servicio de escucha para un puerto determinado.
     * @param puerto
     * @throws ZombielandException
     */
    public ServicioEscucha(int puerto) throws ZombielandException {
        super("ServicioEscucha");
        Log.info("Arrancando servidor");
        corriendo = true;
        hilosEscucha = new ArrayList<HiloEscucha>();
        try {
            serverSocket = new ServerSocket(puerto);
        } catch (IOException e) {
            Log.error("No se pudo abrir el socket: ");
            Log.error(e.getMessage());
            e.printStackTrace();
            throw new ZombielandException("No se pudo iniciar el servidor: "
                    + e.getLocalizedMessage());
        }
        semaforo = new Semaphore(1);
        matandoHilos = false;
    }
    
    /**
     * Crea un servicio de escucha con el puerto por defecto.
     * @throws ZombielandException
     */
    public ServicioEscucha() throws ZombielandException {
    	this(PUERTO_POR_DEFECTO);
	}

    @Override
    public void run() {
    	Log.info("Servidor arrancado");
        while (corriendo) {
            try {
                ControladorServidorFactory factory = new ControladorServidorFactory();
                HiloEscucha hilo = new HiloEscucha(serverSocket.accept(), factory, this);
                factory.setHiloEscucha(hilo);
                hilo.start();
                synchronized (this) {
                    hilosEscucha.add(hilo);
                }
            } catch (SocketException e) {
                // Esperada. La usamos para salir.
                try {
                    // Voy a arrancar a matar a los hilos, bloqueo las actualizaciones del listado.
                    semaforo.acquire();
                    matandoHilos = true;
                    for (HiloEscucha hilo : hilosEscucha) {
                        hilo.cerrar(false);
                        try {
                            // Permito que el hilo se cierre.
                            semaforo.release();
                            hilo.join(1000);
                            // Y lo vuelvo a bloquear.
                            semaforo.acquire();
                        } catch (InterruptedException e1) {
                            Log.error("No se pudo unir al hilo de escucha hijo:");
                            Log.error(e1.getMessage());
                            e1.printStackTrace();
                        }
                    }
                    semaforo.release();
                } catch (InterruptedException e1) {
                    Log.error("Interrumpido al intentar cerrar: " + e1.getMessage());
                }
                return;
            } catch (IOException e) {
                Log.error("Ocurrió un error al intentar abrir la conexión con un nuevo cliente: ");
                Log.error(e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Cierra al servicio de escucha, limpiando todos los recursos que adquirió.
     */
    public void cerrar() {
    	Log.info("Cerrando servidor");
        corriendo = false;
        if (serverSocket != null)
            try {
                serverSocket.close();
            } catch (IOException e) {
            }
    }

    /**
     * @return los hilos de escucha.
     */
    public List<HiloEscucha> getHilos() {
        return hilosEscucha;
    }

    @Override
    public synchronized void hiloCerrado(HiloEscucha hilo) {
        try {
            // Bloqueo si se está operando sobre el listado de hilos. 
            semaforo.acquire();
        } catch (InterruptedException e) {
            Log.error("Interrumpido al adquirir el semáforo para hiloCerrado: " + e.getMessage());
            return;
        }
        // Me aseguro que no se estén matando hilos para removerme del listado.
        if (!matandoHilos)
            hilosEscucha.remove(hilo);
        semaforo.release();
    }
}
