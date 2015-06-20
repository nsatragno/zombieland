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
     * Notifica el listado de partidas actual.
     */
    public static final int LISTADO_PARTIDAS = 0x5;

    /**
     * Unirse a una partida ya existente.
     */
    public static final int UNIRSE_PARTIDA = 0x6;
    
    /**
     * Actualización del lobby enviada por el servidor, por ejemplo si se une un jugador nuevo.  
     */
    public static final int ACTUALIZACION_LOBBY = 0x7;
    
    /**
     * Solicitud de abandono de partida enviada por el cliente.
     */
    public static final int ABANDONAR_PARTIDA = 0x8;
    
    /**
     * Solicitud para unirse a una partida aleatoria.
     */
    public static final int UNIRSE_RAPIDO = 0x9;

    /**
     * Solicitud para cerrar la sesión.
     */
    public static final int CERRAR_SESION = 0xA;
    
    /**
     * Solicitud para cambiar los datos del jugador
     */
    
    public static final int CAMBIO_DATOS = 0xB;
    
    /**
     * Solicitud para devolver los datos del jugador
     */
    public static final int DEVOLVER_DATOS = 0xC;
    
    /**
     * Indica que ha habido un error en el proceso.
     */
    public static final String LINEA_ERROR = "__!!__ERROR__!!__";

    public static final int MOVER_PERSONAJE = 0x10;
}
