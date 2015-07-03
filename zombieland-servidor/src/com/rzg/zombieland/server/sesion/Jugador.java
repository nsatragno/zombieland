package com.rzg.zombieland.server.sesion;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.rzg.zombieland.comunes.comunicacion.pojo.POJORegistro;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOResultadoRonda;
import com.rzg.zombieland.comunes.misc.Avatar;
import com.rzg.zombieland.comunes.misc.Log;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.server.comunicacion.peticion.PeticionActualizacionLobby;
import com.rzg.zombieland.server.comunicacion.peticion.PeticionRecibirMensajeChat;
import com.rzg.zombieland.server.comunicacion.peticion.PeticionRecibirPuntajePartida;
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
		Jugador jugador = dao.getJugadorPorNombre(nombre);
		dao.cerrarSesion();
		if (jugador == null)
			return null;
		if (jugador.getClave().equals(clave))
			return jugador;
		return null;
	}
	
	// ID único de jugador.
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	// Nombre de usuario.
	@Column(unique = true)
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
		this.historicoPartidas = new ArrayList<ResultadoJugador>();
		
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
		if(registro.getAvatarJugador() != null)
			this.avatar = registro.getAvatarJugador();
	}
	

    /**
     * Clona al jugador. Deberíamos refactorizar para no repetir código.
     *
     * @param jugador
     */
    public Jugador(Jugador jugador) {
        this.nombre = jugador.nombre;
        this.clave = jugador.clave;
        this.preguntaSecreta = jugador.preguntaSecreta;
        this.respuestaSecreta = jugador.respuestaSecreta;
        this.avatar = jugador.avatar;
        this.ranking = jugador.ranking;
        this.historicoPartidas = jugador.historicoPartidas;
        this.id = jugador.id;
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

	/* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public final boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Jugador))
            return false;
        Jugador other = (Jugador) obj;
        if (avatar != other.avatar)
            return false;
        if (clave == null) {
            if (other.clave != null)
                return false;
        } else if (!clave.equals(other.clave))
            return false;
        if (id != other.id)
            return false;
        if (nombre == null) {
            if (other.nombre != null)
                return false;
        } else if (!nombre.equals(other.nombre))
            return false;
        if (preguntaSecreta == null) {
            if (other.preguntaSecreta != null)
                return false;
        } else if (!preguntaSecreta.equals(other.preguntaSecreta))
            return false;
        if (ranking != other.ranking)
            return false;
        if (respuestaSecreta == null) {
            if (other.respuestaSecreta != null)
                return false;
        } else if (!respuestaSecreta.equals(other.respuestaSecreta))
            return false;
        return true;
    }
	
	/* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((avatar == null) ? 0 : avatar.hashCode());
        result = prime * result + ((clave == null) ? 0 : clave.hashCode());
        result = prime * result + id;
        result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
        result = prime * result + ((preguntaSecreta == null) ? 0 : preguntaSecreta.hashCode());
        result = prime * result + ranking;
        result = prime * result + ((respuestaSecreta == null) ? 0 : respuestaSecreta.hashCode());
        return result;
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
	 * Establece el nombre del jugador
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	 * Establece la pregunta secreta del jugador
	 * 
	 * @param preguntaSecreta
	 */
	public void setPreguntaSecreta(String preguntaSecreta) {
		this.preguntaSecreta = preguntaSecreta;
	}
	
	/**
	 * Establece la respuesta secreta del jugador
	 * 
	 * @param respuestaSecreta
	 */
	public void setRespuestaSecreta(String respuestaSecreta) {
		this.respuestaSecreta = respuestaSecreta;
	}
	
	/**
	 * Establece el avatar del jugador
	 * 
	 * @param avatar
	 */
	public void setAvatar (Avatar avatar) {
		this.avatar = avatar;
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

	public POJORegistro getPOJO() {
		POJORegistro registro;
		try {
			registro = new POJORegistro(this.nombre, this.clave, this.preguntaSecreta,
										this.respuestaSecreta, this.avatar);
			return registro;
		} catch (ParametrosNoValidosException e) {
			Log.error("El POJO devolvio una excepcion" + e.getMensaje());
			throw new InvalidParameterException();
		}
	}

    public Integer getId() {
        return id;
    }

    /**
     * Establece manualmente el ID del jugador.
     * (Para test).
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Envía un mensaje de chat al jugador.
     * @param mensaje
     */
    public void enviarMensajeChat(String mensaje) {
        Sesion sesion = ServicioSesion.getInstancia().getSesion(this);
        try {
            sesion.enviarPeticion(
                    new PeticionRecibirMensajeChat(mensaje));
        } catch (ZombielandException e) {
            Log.error("No se pudo enviar el mensaje de chat al cliente");
            e.printStackTrace();
        }
    }

    /**
     * Envía un mensaje con el puntaje parcial de la partida.
     * @param resultado
     */
    public void notificarPuntajePartida(POJOResultadoRonda resultado) {
        Sesion sesion = ServicioSesion.getInstancia().getSesion(this);
        try {
            sesion.enviarPeticion(
                    new PeticionRecibirPuntajePartida(resultado));
        } catch (ZombielandException e) {
            Log.error("No se pudo enviar el puntajde de partida al cliente");
            e.printStackTrace();
        }
    }
}
