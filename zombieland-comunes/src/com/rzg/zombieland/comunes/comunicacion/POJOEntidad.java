package com.rzg.zombieland.comunes.comunicacion;

import com.rzg.zombieland.comunes.misc.Coordenada;

/**
 * Contiene los datos de una entidad que se envían a través de la red.
 * @author nicolas
 *
 */
public class POJOEntidad {
    
    // El nombre de esta entidad. Puede ser, por ejemplo, el nombre del jugador que controla al
    // personaje.
    private String etiqueta;
    
    // La coordenada de la entidad.
    private Coordenada coordenada;
}
