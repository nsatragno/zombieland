package com.rzg.zombieland.server.sesion;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.rzg.zombieland.comunes.comunicacion.pojo.POJORegistro;
import com.rzg.zombieland.comunes.misc.Avatar;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.comunicacion.peticion.PeticionActualizacionLobby;
import com.rzg.zombieland.server.meta.ResultadoJugador;
import com.rzg.zombieland.server.persistencia.JugadorDao;

/**
 * Modela al usuario del cliente de Zombieland.
 * 
 * @author nicolas
 *
 */
@Entity
public class Jugador {
	/**
	 * Verifica nombre y clave del jugador, y devuelve un objeto jugador si
	 * logró iniciar sesión.
	 * 
	 * @param nombre
	 * @param clave
	 * @return el jugador, o null si los parámetros de inicio de sesión no son
	 *         válidos.
	 */
	public static Jugador iniciarSesion(String nombre, String clave) {
		JugadorDao dao = new JugadorDao();
		Jugador jugador = dao.getObjeto(nombre);
		dao.cerrarSesion();
		if (jugador == null)
			return null;
		if (jugador.getClave().equals(clave))
			return jugador;
		return null;
	}

	// Nombre de usuario.
	@Id
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

	// La imagen que representa el personaje del jugador en la partida.
	@Column
	private Avatar avatar;

	// Resultados de partidas históricos.
	@OneToMany
	private List<ResultadoJugador> historicoPartidas;

	/**
	 * Constructor vacío para Hibernate.
	 */
	public Jugador() {

	}

	/**
	 * Crea un nuevo jugador. TODO verificar tamaños máximos de DB.
	 * 
	 * @param nombre
	 * @param clave
	 * @param validacionClave
	 * @param preguntaSecreta
	 * @param respuestaSecreta
	 * @throws ParametrosNoValidosException
	 *             si algún parámetro no es válido.
	 */
	public Jugador(String nombre, String clave, String validacionClave,
			String preguntaSecreta, String respuestaSecreta)
			throws ParametrosNoValidosException {
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

		List<Avatar> avatares = new ArrayList<>();
		for (Avatar avatar : Avatar.values()) {
		    if (avatar.esPersonaje())
		        avatares.add(avatar);
		}
		this.avatar = avatares.get(new Random().nextInt(avatares.size()));
		
		if (errores.size() > 0)
			throw new ParametrosNoValidosException("Jugador", errores);
	}

	/**
	 * Crea un jugador a través del POJO que lo representa, validando todos sus
	 * atributos.
	 * 
	 * @param registro
	 * @throws ParametrosNoValidosException
	 */
	public Jugador(POJORegistro registro) throws ParametrosNoValidosException {
		this(registro.getNombre(), registro.getClave(), registro.getClave(),
				registro.getPreguntaSecreta(), registro.getRespuestaSecreta());
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
		if (obj == null)
			return false;
		if (!(obj instanceof Jugador))
			return false;
		Jugador otro = (Jugador) obj;
		return nombre.equals(otro.nombre) && clave.equals(otro.clave)
				&& preguntaSecreta.equals(otro.preguntaSecreta)
				&& respuestaSecreta.equals(otro.respuestaSecreta)
				&& ranking == otro.ranking;
	}
	
	@Override
	public int hashCode() {
		return nombre.hashCode();
	}

	/**
	 * @return el nombre del jugador.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @return la pregunta de seguridad que el jugador eligió al registrarse.
	 */
	public String getPreguntaSecreta() {
		return preguntaSecreta;
	}

	/**
	 * @return la respuesta de seguridad que el jugador eligió al registrarse.
	 */
	public String getRespuestaSecreta() {
		return respuestaSecreta;
	}

	/**
	 * @return la clave en texto plano del jugador.
	 */
	public String getClave() {
		return clave;
	}

	/**
	 * Establece la clave del jugador.
	 * 
	 * @param clave
	 */
	public void setClave(String clave) {
		this.clave = clave;
	}

	/**
	 * Notifica al jugador del cambio de partida para que pueda tomar las acciones requeridas.
	 */
    public void notificarCambioPartida() {
        Sesion sesion = ServicioSesion.getInstancia().getSesion(this);
        try {
            sesion.enviarPeticion(
                    new PeticionActualizacionLobby(sesion.getPartida().getPOJO(this)));
        } catch (ZombielandException e) {
            Log.error("No se pudo enviar la notificación de cambio de partida al cliente");
            e.printStackTrace();
        }
    }

    /**
     * @return el avatar del jugador.
     */
    public Avatar getAvatar() {
        return avatar;
    }
}
