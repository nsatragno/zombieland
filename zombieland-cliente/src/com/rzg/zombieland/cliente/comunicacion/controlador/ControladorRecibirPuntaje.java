package com.rzg.zombieland.cliente.comunicacion.controlador;

import com.google.gson.Gson;
import com.rzg.zombieland.cliente.meta.Estado;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOResultadoRonda;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.controlador.Controlador;

public class ControladorRecibirPuntaje extends Controlador {

    @Override
    public String procesar(String linea) {
        Gson gson = new Gson();
        POJOResultadoRonda resultado = gson.fromJson(linea, POJOResultadoRonda.class);
        Estado.getInstancia().recibidoPuntaje(resultado);
        return gson.toJson(new RespuestaGenerica());
    }

}
