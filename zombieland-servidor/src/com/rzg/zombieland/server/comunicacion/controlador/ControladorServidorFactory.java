package com.rzg.zombieland.server.comunicacion.controlador;

import com.rzg.zombieland.comunes.comunicacion.Enviable;
import com.rzg.zombieland.comunes.comunicacion.HiloEscucha;
import com.rzg.zombieland.comunes.comunicacion.HiloListener;
import com.rzg.zombieland.comunes.controlador.Controlador;
import com.rzg.zombieland.comunes.controlador.Controlador.ComandoDesconocidoException;
import com.rzg.zombieland.comunes.controlador.ControladorFactory;
import com.rzg.zombieland.comunes.controlador.ControladorTest;
import com.rzg.zombieland.server.sesion.ManejadorSesion;
import com.rzg.zombieland.server.sesion.Sesion;

/**
 * Crea controladores de acuerdo al código de comando.
 * @author nicolas
 *
 */
public class ControladorServidorFactory implements ControladorFactory, ManejadorSesion, HiloListener {
    
    // La sesión del jugador.
    private Sesion sesion;
    
    // El hilo que usa esta fábrica de controladores.
    private HiloEscucha hilo;
    
    /**
     * @param linea
     * @return un controlador de acuerdo a la línea leída.
     * @throws ComandoDesconocidoException si el código no se corresponde con ningún controlador
     *         existente.
     */
    @Override
    public Controlador crear(int codigo) throws ComandoDesconocidoException {
        switch (codigo) {
        case Enviable.TEST:
            return new ControladorTest();
        case Enviable.REGISTRAR_JUGADOR:
            return new ControladorRegistro();
        case Enviable.INICIAR_SESION:
            return new ControladorInicioSesion(this, hilo);
        case Enviable.CREAR_PARTIDA:
            return new ControladorCrearPartida(this);
        case Enviable.UNIRSE_PARTIDA:
            return new ControladorUnirsePartida(this);
        case Enviable.ABANDONAR_PARTIDA:
            return new ControladorAbandonarPartida(this);
        case Enviable.UNIRSE_RAPIDO:
            return new ControladorUnirseRapido(this);
        case Enviable.CERRAR_SESION:
            return new ControladorCerrarSesion(this);
        default:
            throw new ComandoDesconocidoException(
                    String.format("El código 0x%X no corresponde con "
                                + "ninguno de los comandos conocidos", codigo));
        }
    }
    
    public void setHiloEscucha(HiloEscucha hilo) {
    	this.hilo = hilo;
    	hilo.addListener(this);
    }

    @Override
    public void setSesion(Sesion sesion) {
        sesion.addListener(this);
        this.sesion = sesion;
    }

    @Override
    public Sesion getSesion() {
        return sesion;
    }

    @Override
    public void notificarSesionCerrada(Sesion sesion) {
        this.sesion = null;
    }

    @Override
    public void hiloCerrado(HiloEscucha hilo) {
        if (sesion != null)
            sesion.cerrar();
    }
}
