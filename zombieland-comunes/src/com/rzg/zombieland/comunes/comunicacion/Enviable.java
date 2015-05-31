package com.rzg.zombieland.comunes.comunicacion;

/**
 * Define un componente que puede enviarse a través de un socket. 
 * @author nicolas
 *
 */
public abstract class Enviable {
    /**
     * Crea un objeto enviable a partir de los bytes dados.
     * @return
     */
    protected Enviable(String bytes) { }
    
    /**
     * Constructor por defecto.
     * @param object
     */
    protected Enviable() { }
    
    /**
     * Convierte el objeto en bytes para su envío.
     * @return una cadena de bytes para su envío.
     */
    public abstract String serializar();
}
