package com.rzg.zombieland.comunes.comunicacion;

/**
 * Define un componente que puede enviarse a través de un socket, y contiene las constantes de 
 * tipos de peticiones. 
 * @author nicolas
 *
 */
public abstract class Enviable {
    /**
     * Prueba para los tests. 
     */
    public static final int TEST = 0x0;
    
    /**
     * Registrar un jugador nuevo.
     */
    public static final int REGISTRAR_JUGADOR = 0x1;
    
    /**
     * Respuesta a una petición anterior. 
     */
    public static final int RESPUESTA = 0x2;
    
    /**
     * Iniciar sesión de un jugador existente. 
     */
    public static final int INICIAR_SESION = 0x3;
    
    /**
     * Crear una nueva partida que administra el jugador.
     */
    public static final int CREAR_PARTIDA = 0x4;
    

    /**
     * Obtener el listado de partidas actual.
     */
    public static final int LISTADO_PARTIDAS = 0x5;

    /**
     * Unirse a una partida ya existente.
     */
    public static final int UNIRSE_PARTIDA = 0x6;
    
    /**
     * Indica que ha habido un error en el proceso.
     */
    public static final String LINEA_ERROR = "__!!__ERROR__!!__";

    
}
