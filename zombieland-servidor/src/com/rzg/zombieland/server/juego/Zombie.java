package com.rzg.zombieland.server.juego;

/**
 * Personaje cuyo único deseo en el mundo es comer cerebros. RAWR!
 * @author nicolas
 *
 */
public class Zombie extends Personaje {

    // TODO definir sprite.
    private final String SPRITE = "zombie.jpg";
    
    // Usuario que identifica al zombie. Puede servir más adelante para colocar
    // el nombre por encima.
    private String usuario;
    
    // Permite construir un zombie a través de un Jugador.
    public Zombie(String usuario) {
    	this.usuario = usuario;
    }
    
    @Override
    public String getSprite() {
        return SPRITE;
    }

    @Override
    public void colisionar(EntidadTablero entidad) {
        // TODO convertir entidad en zombie.
    }
    
    public String getUsuario(){
    	return usuario;
    }

}
