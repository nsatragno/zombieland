package com.rzg.zombieland.cliente.interfaz;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.rzg.zombieland.cliente.comunicacion.ServicioCliente;
import com.rzg.zombieland.cliente.comunicacion.peticion.PeticionCierreSesion;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Menu Completo.
 * 
 * @author Nicolas
 *
 */
public class MenuZombieland extends JMenuBar {
	private static final long serialVersionUID = -2766617470499185657L;

	// Interfaces secundarias.
	private InterfazEstadisticaJugador estadisticas;
	private InterfazCambioDeDatosUsuario cambioDatos;
	/**
	 * Atributos necesarios para poder modificar el bloqueo de los botones.
	 * @inicio se inicializa en false los botones estan desabilitados
	 */
	public static boolean inicio = false;
	private static JMenuItem iniciarSesion;
	private static JMenuItem mntmRegistrarse;
	private static JMenuItem mntmDatos;
	private static JMenuItem mntmEstadsticas;
	private static JMenuItem mntmJugar;
	private static JMenuItem mntmVerPartidas;
	private static JMenuItem mntmEstadsticasGlob;

	public MenuZombieland() {

		JMenu mnArchivo = new JMenu("Archivo");
		add(mnArchivo);

		iniciarSesion = new JMenuItem("Iniciar Sesion");
		iniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			    cerrarSesion();
			}
		});
		mnArchivo.add(iniciarSesion);

		mntmRegistrarse = new JMenuItem("Registrarse");
		mntmRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.irA(Main.REGISTRO);
			}
		});
		mnArchivo.add(mntmRegistrarse);
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		mnArchivo.add(mntmSalir);

		JMenu mnPartida = new JMenu("Partida");
		add(mnPartida);

		mntmJugar = new JMenuItem("Jugar");
		mntmJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO aca hay que hacer que entre a una partida de forma
				// aleatoria.
				Main.irA(Main.LOBBY);
			}
		});
		mnPartida.add(mntmJugar);

		mntmVerPartidas = new JMenuItem("Ver Partidas");
		mntmVerPartidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.irA(Main.LISTADO_PARTIDAS);
			}
		});
		mnPartida.add(mntmVerPartidas);

		JMenu mnCuenta = new JMenu("Cuenta");
		add(mnCuenta);

		mntmDatos = new JMenuItem("Datos");
		mntmDatos.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (cambioDatos == null)
					cambioDatos = new InterfazCambioDeDatosUsuario();
				cambioDatos.setVisible(true);
			}

		});
		mnCuenta.add(mntmDatos);

		final JMenu mnEstadsticas = new JMenu("Estad\u00EDsticas");
		mntmEstadsticas = new JMenuItem("Mis Estad\u00EDsticas");
		mntmEstadsticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (estadisticas == null)
					estadisticas = new InterfazEstadisticaJugador();
				estadisticas.actualizar();
				estadisticas.setVisible(true);
			}

		});

		mntmEstadsticasGlob = new JMenuItem(
				"Estad\u00EDsticas Globales");
		mntmEstadsticasGlob.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.irA(Main.RANKING_GENERAL);
				// TODO VER cuando se actualiza el ranking general
			}

		});

		mnEstadsticas.add(mntmEstadsticas);
		mnEstadsticas.add(mntmEstadsticasGlob);
		mnCuenta.add(mnEstadsticas);

		JMenu mnAyuda = new JMenu("Ayuda");
		add(mnAyuda);

		JMenuItem mntmCmoJugar = new JMenuItem("C\u00F3mo Jugar");
		mntmCmoJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop
						.getDesktop() : null;
				if (desktop != null
						&& desktop.isSupported(Desktop.Action.BROWSE)) {
					try {
						desktop.open(new File("ZOMBIELAND-README.pdf"));
					} catch (Exception ex) {
						System.err.println(ex.getMessage());
					}
				}
			}
		});
		mnAyuda.add(mntmCmoJugar);

		JMenuItem mntmGoogleame = new JMenuItem("Googleame");
		mntmGoogleame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Desktop desktop = Desktop.isDesktopSupported() ? Desktop
						.getDesktop() : null;
				if (desktop != null
						&& desktop.isSupported(Desktop.Action.BROWSE)) {
					try {
						desktop.browse(new URI("http://www.google.com"));
					} catch (Exception ex) {
						System.err.println(ex.getMessage());
					}
				}
			}

		});
		mnAyuda.add(mntmGoogleame);

		iniciarSesion.setEnabled(inicio);
		mntmRegistrarse.setEnabled(inicio);
		mntmDatos.setEnabled(inicio);
		mntmEstadsticas.setEnabled(inicio);
		mntmJugar.setEnabled(inicio);
		mntmVerPartidas.setEnabled(inicio);
		mntmEstadsticasGlob.setEnabled(inicio);
	}
	/**
	 * 
	 * @param inicio ->true, la casilla esta habilitada
	 */
	public static void setInicioSesion(boolean estado) {
		inicio = estado;
	    iniciarSesion.setText(estado ? "Cerrar sesión" : "Iniciar sesión");
		mntmRegistrarse.setEnabled(!inicio);
		mntmDatos.setEnabled(inicio);
		mntmEstadsticas.setEnabled(inicio);
		mntmJugar.setEnabled(inicio);
		mntmVerPartidas.setEnabled(inicio);

	}
	
	/**
	 * 
	 * @param estado, se habilitan los botones despues de 
	 * iniciar el servidor correctamente
	 */
	public static void setConexcionServidor(boolean estado){
		inicio = estado;
		iniciarSesion.setEnabled(inicio);
		mntmRegistrarse.setEnabled(inicio);
		mntmEstadsticasGlob.setEnabled(inicio);
	}
	
	/**
	 * Envía a la pantalla de inicio de sesión y cierra la existente si estaba abierta.
	 */
	public void cerrarSesion() {
	    if (inicio) {
            try {
                ServicioCliente.enviarPeticion(new PeticionCierreSesion());
            } catch (ZombielandException e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
            setInicioSesion(false);
        }
        Main.irA(Main.INICIO_SESION);
	}
}
