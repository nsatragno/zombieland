package com.rzg.zombieland.comunes.comunicacion.pojo;

import java.util.ArrayList;
import java.util.List;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.misc.Avatar;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;



/**
 * Contiene los datos que se envían para identificar a un jugador a través de la red.
 * @author nicolas
 *
 */
public class POJORegistro extends Enviable {
    // El nombre del jugador (el_groso_de_vl_99).
    private String nombre;
    
    // La clave del jugador.
    private String clave;
    
    // Pregunta y respuesta secretas de seguridad.
    private String preguntaSecreta;
    private String respuestaSecreta;
    
    // Avatar del jugador
    private Avatar avatarJugador;
    
    /**
     * Constructor por atributos.
     * @param nombre - el nombre del jugador que se registra.
     * @param clave - la clave del jugador que se registra.
     * @param preguntaSecreta - pregunta de seguridad.
     * @param respuestaSecreta - respuesta de seguridad
     * @throws ParametrosNoValidosException si alguno de los datos falta.
     */
    public POJORegistro(String nombre,
                       String clave,
                       String preguntaSecreta,
                       String respuestaSecreta,
                       Avatar avatarJugador) throws ParametrosNoValidosException {
        List<String> errores = new ArrayList<String>();
        
        if (nombre == null || nombre.isEmpty())
            errores.add("El nombre no puede ser vacío");
        this.nombre = nombre;
        
        if (clave == null || clave.isEmpty())
            errores.add("La clave no puede ser vacía");
        this.clave = clave;
        
        if (preguntaSecreta == null || preguntaSecreta.isEmpty())
            errores.add("La pregunta secreta no puede ser vacía");
        this.preguntaSecreta = preguntaSecreta;
        
        if (respuestaSecreta == null || respuestaSecreta.isEmpty())
            errores.add("La respuesta secreta no puede ser vacía");
        this.respuestaSecreta = respuestaSecreta;

        if(avatarJugador != null && !avatarJugador.esPersonaje())
        	errores.add("El avatar del jugador no es un personaje");
        this.avatarJugador = avatarJugador;
        
        if (errores.size() != 0)
            throw new ParametrosNoValidosException("registro de jugador", errores);
    }
    
    /**
     * @return el nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return la clave del jugador, ¡no el hash!
     */
    public String getClave() {
        return clave;
    }

    /**
     * @return la pregunta secreta elegida.
     */
    public String getPreguntaSecreta() {
        return preguntaSecreta;
    }

    /**
     * @return la respuesta secreta elegida.
     */
    public String getRespuestaSecreta() {
        return respuestaSecreta;
    }
    
    /**
     * 
     * @return el avatar del jugador.
     */
    public Avatar getAvatarJugador(){
    	return avatarJugador;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof POJORegistro)) return false;
        POJORegistro otro = (POJORegistro)obj;
        return nombre.equals(otro.nombre) &&
               clave.equals(otro.clave) &&
               preguntaSecreta.equals(otro.preguntaSecreta) &&
               respuestaSecreta.equals(otro.respuestaSecreta);
    }
}
