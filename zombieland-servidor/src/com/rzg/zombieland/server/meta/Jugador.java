package com.rzg.zombieland.server.meta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Modela al usuario del cliente de Zombieland.
 * 
 * @author nicolas
 *
 */
@Entity
public class Jugador {
    /**
     * Verifica nombre y clave del jugador, y devuelve un objeto jugador si logró
     * iniciar sesión. 
     * @param nombre
     * @param clave
     * @return
     */
    public static Jugador iniciarSesion(String nombre, String clave) {
        // TODO implementar.
        return null;
    }
    
    // ID que identifica unívocamente al jugador.
    @Id
    private UUID id;

    // Nombre de usuario.
    @Column
    private String nombre;

    // Clave de acceso al sistema.
    @Column
    private String clave;

    // Pregunta de seguridad que se envía al usuario en caso de errores al
    // iniciar sesión.
    @Column
    private String preguntaSecreta;

    // Respuesta a la pregunta secreta de inicio de sesión.
    @Column
    private String respuestaSecreta;

    // Indica el ranking del jugador en la tabla general.
    @Column
    private int ranking;

    // Resultados de partidas históricos.
    @OneToMany
    private List<ResultadoPartida> historicoPartidas;
    
    /**
     * Constructor vacío para Hibernate.
     */
    public Jugador() {
        
    }
    
    /**
     * Crea un nuevo jugador.
     * TODO verificar tamaños máximos de DB.
     * @param nombre
     * @param clave
     * @param validacionClave
     * @param preguntaSecreta
     * @param respuestaSecreta
     * @throws ZombielandException si algún parámetro no es válido.
     */
    public Jugador(String nombre, String clave, String validacionClave,
                   String preguntaSecreta, String respuestaSecreta) throws ZombielandException {
        List<String> errores = new ArrayList<String>();
        
        if (nombre == null || nombre.equals(""))
            errores.add("El nombre no puede estar vacío");
        this.nombre = nombre;
        
        if (clave == null || clave.equals(""))
            errores.add("La clave no puede estar vacía");
        if (!clave.equals(validacionClave))
            errores.add("La clave y la validación no coinciden");
        this.clave = clave;
        
        if (preguntaSecreta == null || preguntaSecreta.equals(""))
            errores.add("La pregunta secreta no puede estar vacía");
        this.preguntaSecreta = preguntaSecreta;
        
        if (respuestaSecreta == null || respuestaSecreta.equals(""))
            errores.add("La pregunta secreta no puede estar vacía");
        this.respuestaSecreta = respuestaSecreta;
        
        if (errores.size() > 0)
            throw new ZombielandException("Por favor, revise los campos indicados", errores);
        
        id = UUID.randomUUID();
    }
    
    public UUID getId() {
        return id;
    }

    /**
     * @return las partidas que lleva jugadas históricamente.
     */
    public int getPartidasJugadas() {
        // TODO implementar.
        return 0;
    }

    /**
     * @return las partidas que lleva ganadas históricamente.
     */
    public int getPartidasGanadas() {
        // TODO implementar.
        return 0;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (!(obj instanceof Jugador)) return false;
        Jugador otro = (Jugador)obj;
        return id.equals(otro.id) &&
               nombre.equals(otro.nombre) &&
               clave.equals(otro.clave) &&
               preguntaSecreta.equals(otro.preguntaSecreta) &&
               respuestaSecreta.equals(otro.respuestaSecreta) &&
               ranking == otro.ranking;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
