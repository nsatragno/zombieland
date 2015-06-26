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
     * Solicitud para establecer el siguiente movimiento del personaje.
     */
    public static final int MOVER_PERSONAJE = 0xD;
    
    /**
     * Solicitud para actualizar la proyección enviada por el servidor.
     */
	public static final int ACTUALIZACION_PROYECCION = 0xE;
	
	 /**
     * Solicitud para Obtener la pregunta de seguridad de un Usuario especifico.
     */
	
	public static final int PREGUNTA_SEGURIDAD = 0xF;
	
	/**
     * Solicitud para realizar un cambio de contraseña de un usuario que no la recuerda.
     */
	
	public static final int CAMBIOS_CONTRASEÑA = 0x10;
	
	/**
	 * Solicitud para enviar un mensaje de chat.
	 */
	public static final int ENVIAR_MENSAJE_CHAT = 0x11;
	
	/**
	 * Solicitud para recibir un mensaje de chat. 
	 */
	public static final int RECIBIR_MENSAJE_CHAT = 0x12;
	
	/**
	 * Solicitud para actualizar el puntaje de la partida.
	 */
	public static final int RECIBIR_PUNTAJE_PARTIDA = 0x13;
	
	/**
     * Indica que ha habido un error en el proceso.
     */
    public static final String LINEA_ERROR = "__!!__ERROR__!!__";

    

    

    


}
