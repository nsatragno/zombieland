package com.rzg.zombieland.comunes.misc;

import java.util.List;

/**
 * Excepción con un mensaje amigable para el usuario referida a la no validez de parámetros.
 * Debería ser lanzada desde cualquier constructor que reciba parámetros dados por el usuario.
 * @author nicolas
 *
 */
public class ParametrosNoValidosException extends ZombielandException {
    private static final long serialVersionUID = 2748734264163995657L;

    // Listado de parámetros no válidos.
    private List<String> parametros;
    
    // Nombre amigable para el usuario del objeto que se valida. 
    private String nombreObjeto;
    
    /**
     * Construye una excepción de parámetros no válidos.
     * @param nombreObjeto - nombre amigable para el usuario del objeto cuyos parámetros no son
     *                       válidos.
     * @param parametros - listado de errores de parámetros no válidos del objeto. Por ejemplo,
     *                     "El nombre no puede ser vacío."
     */
    public ParametrosNoValidosException(String nombreObjeto, List<String> parametros) {
        super("Parámetros no válidos para " + nombreObjeto);
        this.nombreObjeto = nombreObjeto;
        this.parametros = parametros;
        Log.info(getMensaje());
    }
    
    /**
     * @return un mensaje amigable para el usuario del error.
     */
    public String getMensaje() {
        StringBuilder mensaje = new StringBuilder("Parámetros no válidos para ");
        mensaje.append(nombreObjeto);
        mensaje.append("\n");
        for (String parametro : parametros) {
            mensaje.append(parametro);
            mensaje.append("\n");
        }
        return mensaje.toString();
    }
    
    /**
     * @return la cantidad de parámetros no válidos. 
     */
    public int getCantidadParametros() {
        return parametros.size();
    }
}
