package com.rzg.zombieland.server.comunicacion;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import com.rzg.zombieland.comunes.comunicacion.HiloEscucha;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.controlador.ControladorServidorFactory;

/**
 * Escucha conexiones entrantes y lanza hilos para manejarlas.
 * 
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
    
    // Mantiene una referencia a los hilos que creó para cerrarlos. 
    private List<HiloEscucha> hilosEscucha;
    
    public ServicioEscucha() throws ZombielandException {
        super("ServicioEscucha");
        corriendo = true;
        hilosEscucha = new ArrayList<HiloEscucha>();
        try {
            serverSocket = new ServerSocket(puerto);
        } catch (IOException e) {
            Log.error("No se pudo abrir el socket: ");
            Log.error(e.getMessage());
            Log.error("Stacktrace: ");
            e.printStackTrace();
            throw new ZombielandException("No se pudo iniciar el servidor: "
                    + e.getLocalizedMessage());
        }
    }

    @Override
    public void run() {
        while (corriendo) {
            try {
                HiloEscucha hilo = new HiloEscucha(serverSocket.accept(), new ControladorServidorFactory());
                hilo.start();
                hilosEscucha.add(hilo);
            } catch (SocketException e) {
                // Esperada. La usamos para salir.
                for (HiloEscucha hilo : hilosEscucha) {
                    hilo.cerrar();
                    try {
                        hilo.join();
                    } catch (InterruptedException e1) {
                        Log.error("No se pudo unir al hilo de escucha hijo:");
                        Log.error(e1.getMessage());
                        e1.printStackTrace();
                    }
                }
                return;
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
        if (serverSocket != null)
            try {
                serverSocket.close();
            } catch (IOException e) {
            }
    }
}
