package com.rzg.zombieland.server.juego;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.rzg.zombieland.comunes.misc.Coordenada;

public class TableroTest {

	@Test
	public void posicionZombieTest() {
		// Para saber si pone al zombi en el medio.
		java.util.List<Personaje> personajes = new ArrayList<Personaje>();
		personajes.add(new Humano());
		personajes.add(new Humano());
		Zombie zombie = new Zombie();
		Tablero tablero = new Tablero(10, personajes, zombie);
		Assert.assertEquals(tablero.getEntidadEn(new Coordenada(5, 5)), zombie);
	}

//	@Test
//	public void personajesEncerrados() {
//		// Para saber si quedó encerrado entre obstáculos el personaje.
//	}
}
