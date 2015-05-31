package com.rzg.zombieland.server.comunicacion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.server.controlador.Controlador;
import com.rzg.zombieland.server.controlador.Controlador.ComandoDesconocidoException;

/**
 * Clase que se ocupa de la comunicación con un cliente en particular.
 * 
 * @author nicolas
 *
 */
public class HiloEscucha extends Thread {

    // El socket sobre el que se escucha al cliente.
    private Socket socket;
    
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
    
    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(
                 socket.getInputStream()))) {
            int codigo = in.read();
            Log.debug("Recibiendo datos en HiloEscucha " + this + ". Código:");
            Log.debug(codigo);
            // Fin del stream.
            if (codigo == -1)
                return;
            
            try {
                Controlador controlador = Controlador.crear(codigo);
                Log.debug("Contenido:");
                String contenido = in.readLine();
                Log.debug(contenido);
                out.println(controlador.procesar(contenido));
                socket.close();
            } catch (ComandoDesconocidoException e) {
                out.println(Enviable.LINEA_ERROR);
            }
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
