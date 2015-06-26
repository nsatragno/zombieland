package com.rzg.zombieland.cliente.comunicacion.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.cliente.meta.Estado;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.controlador.Controlador;

public class ControladorRecibirMensaje extends Controlador {

    @Override
    public String procesar(String linea) {
        Gson gson = new Gson();
        String mensaje = gson.fromJson(linea, String.class);
        Estado.getInstancia().recibidoMensajeChat(mensaje);
        return gson.toJson(new RespuestaGenerica());
    }

}
