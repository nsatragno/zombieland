package com.rzg.zombieland.comunes.comunicacion;

/**
 * Define un componente que puede enviarse a través de un socket. 
 * @author nicolas
 *
 */
public abstract class Enviable {
    // TODO agregar autenticación.
    
    // Constantes de tipos de peticiones.
    public static final int TEST = 0x0;
    public static final int REGISTRAR_JUGADOR = 0x1;
    public static final int RESPUESTA = 0x2;
    
    // Mensaje de respuesta que significa que ha ocurrido un error.
    public static final String LINEA_ERROR = "__!!__ERROR__!!__";
}
