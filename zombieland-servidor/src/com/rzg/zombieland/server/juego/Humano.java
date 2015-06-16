package com.rzg.zombieland.server.juego;

/**
 * Cobarde animal que solo sabe correr por su vida.
 * @author nicolas
 *
 */
public class Humano extends Personaje {

    // TODO definir sprite.
    private final String SPRITE = "humano.png";
    
    // Usuario que identifica al humano. Puede servir más adelante para colocar
    // el nombre por encima.
    private String usuario;
    
    // Permite construir un humano a través de un Jugador.
    public Humano(String usuario) {
    	this.usuario = usuario;
    }
    
    @Override
    public String getSprite() {
        return SPRITE;
    }

	@Override
	public void colisionar(EntidadTablero entidad) {
		// Si hay una colision llamada por un humano que recibió por parámetro un
		// zombie, hay cosas que hacer.
		if (entidad.getClass() == Zombie.class) {
			// Cambio al humano por un nuevo zombie.
			entidad = new Zombie(usuario);
		}
	}
    
    public String getUsuario(){
    	return usuario;
    }
    
    public boolean esPersonaje(){
    	return true;
    }
}
