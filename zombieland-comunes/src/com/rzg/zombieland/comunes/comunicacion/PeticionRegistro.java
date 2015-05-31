package com.rzg.zombieland.comunes.comunicacion;

import com.google.gson.Gson;


/**
 * Petición de registro de jugador enviada por el cliente.
 * @author nicolas
 *
 */
public class PeticionRegistro extends PeticionCliente {
    // Los datos del jugador que se quieren crear.
    private POJOJugador jugador;

    public PeticionRegistro(String bytes) {
        super(bytes);
        Gson gson = new Gson();
        jugador = gson.fromJson(bytes, POJOJugador.class);
    }
    
    public PeticionRegistro(POJOJugador jugador) {
        this.jugador = jugador;
    }

    @Override
    public String serializar() {
        Gson gson = new Gson();
        return gson.toJson(jugador);
    }

    public POJOJugador getJugador() {
        return jugador;
    }
}
