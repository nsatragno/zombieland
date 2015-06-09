package com.rzg.zombieland.comunes.comunicacion.pojo;

import java.util.ArrayList;
import java.util.List;

import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;

/**
 * Empaqueta los datos que se usan para iniciar la sesión de un usuario.
 * @author nicolas
 *
 */
public class POJOInicioSesion {
    // Nombre del jugador.
    private String nombre;
    
    // Clave del jugador, en texto plano.
    private String clave;
    
    /**
     * Construye el POJO de inicio de sesión.
     * @param nombre - el nombre del jugador.
     * @param clave - clave del jugador en texto plano.
     * @throws ParametrosNoValidosException si alguno de los parámetros falta.
     */
    public POJOInicioSesion(String nombre, String clave) throws ParametrosNoValidosException {
        List<String> errores = new ArrayList<String>();
        
        if (nombre.isEmpty())
            errores.add("Nombre");
        this.nombre = nombre;
        
        if (clave.isEmpty())
            errores.add("Clave");
        this.clave = clave;
        
        if (errores.size() != 0)
            throw new ParametrosNoValidosException("Inicio de sesión", errores);
    }
    
    /**
     * @return el nombre de jugador.
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * @return la clave del jugador.
     */
    public String getClave() {
        return clave;
    }
}
