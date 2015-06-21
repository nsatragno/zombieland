package com.rzg.zombieland.comunes.misc;

public enum Avatar {
	POLICIA("avatar-poli.png", true),
	HOMBRE("avatar-dsn.png", true),
	MUJER("avatar-mujer.png", true),
	OBSTACULO("avatar4.png", false),
	ZOMBIE("zombie.png", false);

	private final String sprite;
	private final boolean esPersonaje;
	
	private Avatar(String sprite, boolean esPersonaje) {
	    this.sprite = sprite;
	    this.esPersonaje = esPersonaje;
	}
	
    public String getSprite() {
        return sprite;
    }

    public boolean esPersonaje() {
        return esPersonaje;
    }
}
