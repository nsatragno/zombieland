package com.rzg.zombieland.server.comunicacion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Escucha conexiones entrantes y lanza hilos para manejarlas.
 * @author nicolas
 *
 */
public class ServicioEscucha extends Thread {
    // El puerto en el que se escucha.
    private final int puerto = 2048;
    
    // Generador de sockets.  
    private final ServerSocket serverSocket;
    
    // Indica si el hilo está corriendo.
    private boolean corriendo;
    
    // Lock opcional para notificar del fin de la incialización del hilo.
    private CyclicBarrier lock;
    
    public ServicioEscucha() throws ZombielandException {
        super("ServicioEscucha");
        corriendo = true;
        try {
            serverSocket = new ServerSocket(puerto);
        } catch (IOException e) {
            Log.error("No se pudo abrir el socket: ");
            Log.error(e.getMessage());
            Log.error("Stacktrace: ");
            e.printStackTrace();
            throw new ZombielandException("No se pudo iniciar el servidor: " + e.getLocalizedMessage());
        }
    }
    
    public ServicioEscucha(CyclicBarrier lock) throws ZombielandException {
        this();
        this.lock = lock;
    }

    @Override
    public void run() {
        while (corriendo) {
            try {
                new HiloEscucha(serverSocket.accept(), lock).start();
            } catch (IOException e) {
                Log.error("Ocurrió un error al intentar abrir la conexión con un nuevo cliente: ");
                Log.error(e.getMessage());
                Log.error("Stacktrace: ");
                e.printStackTrace();
            }
        }
    }
    
    public void cerrar() {
        corriendo = false;
    }
}
