package com.rzg.zombieland.server.comunicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.server.controlador.Controlador;

/**
 * Clase que se ocupa de la comunicación con un cliente en particular.
 * 
 * @author nicolas
 *
 */
public class HiloEscucha extends Thread {

    // El socket sobre el que se escucha al cliente.
    private Socket socket;
    
    // Lock para notificar de la creación del hilo de escucha.
    private CyclicBarrier lock; 

    /**
     * Construye un hilo de escucha.
     * 
     * @param socket
     *            - el socket con el que se escuchará al cliente.
     */
    public HiloEscucha(Socket socket) {
        super("HiloEscucha: " + socket.getInetAddress());
        Log.debug("Aceptando nueva conexión de " + socket.getInetAddress());
        this.socket = socket;
    }

    public HiloEscucha(Socket socket, CyclicBarrier lock) {
        this(socket);
        this.lock = lock;
    }

    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(
                 socket.getInputStream()))) {
            if (lock != null)
                lock.await();
            int codigo = in.read();
            Log.debug("Recibiendo datos en HiloEscucha " + this + ":");
            Log.debug(in.readLine());
            // Fin del stream.
            if (codigo == -1)
                return;
            Controlador controlador = Controlador.crear(codigo);
            
            Log.debug("Comando:");
            String contenido = in.readLine();
            Log.debug(contenido);
            out.write(controlador.procesar(contenido));
            socket.close();
        } catch (IOException | InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

}
