package com.rzg.zombieland.server.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOInicioSesion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.controlador.Controlador;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.server.sesion.Jugador;
import com.rzg.zombieland.server.sesion.ManejadorSesion;
import com.rzg.zombieland.server.sesion.ServicioSesion;
import com.rzg.zombieland.server.sesion.Sesion;

/**
 * Gestiona una petición de inicio de sesión, devolviendo el ID de sesión o un mensaje de error. 
 * @author nicolas
 */
public class ControladorInicioSesion extends Controlador {
    
    // El manejador al que se le registra el jugador.
    private ManejadorSesion manejadorSesion;

    /**
     * Construye el controlador de inicio de sesión a partir del manejador.
     * @param manejadorSesion el manejador al que se le registrará el jugador.
     */
    public ControladorInicioSesion(ManejadorSesion manejadorSesion) {
        this.manejadorSesion = manejadorSesion;
    }

    @Override
    public String procesar(String linea) {
        Gson gson = new Gson();
        POJOInicioSesion datos = gson.fromJson(linea, POJOInicioSesion.class);
        Jugador jugador = Jugador.iniciarSesion(datos.getNombre(), datos.getClave());
        if (jugador == null) {
            return gson.toJson(new RespuestaGenerica("No se pudo iniciar sesión. Verifique "
                                                      + "el usuario y contraseña"));
        }
        Log.info("El jugador " + jugador.getNombre() + " ha iniciado sesión.");
        Sesion sesion = new Sesion(jugador);
        ServicioSesion.getInstancia().addSesion(sesion);
        manejadorSesion.setSesion(sesion);
        
        return gson.toJson(new RespuestaGenerica());
    }

}
