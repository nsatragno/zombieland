package com.rzg.zombieland.comunes.comunicacion;

import java.util.List;

import com.rzg.zombieland.comunes.misc.Coordenada;

/**
 * Proyecta la parte del tablero que un personaje puede ver.
 * @author nicolas
 *
 */
public class ProyeccionTablero extends Enviable {
    // Tamaño total del tablero.
    private int ancho;
    private int largo;
    
    // Las esquina que esta proyección revela del mapa.
    private Coordenada esquinaSuperiorIzquierda;
    private Coordenada esquinaInferiorDerecha;
    
    // Entidades visibles del tablero.
    private List<POJOEntidad> entidades;

    public ProyeccionTablero(String bytes) {
        super(bytes);
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public String serializar() {
        // TODO Auto-generated method stub
        return null;
    }
}
