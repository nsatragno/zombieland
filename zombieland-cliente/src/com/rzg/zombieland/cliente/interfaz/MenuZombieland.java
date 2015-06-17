package com.rzg.zombieland.cliente.interfaz;

import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.net.URI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

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
	 */
	public static boolean inicio = false;
	private static JMenuItem mntmIniciarSesion;
	private static JMenuItem mntmRegistrarse;
	private static JMenuItem mntmDatos;
	private static JMenuItem mntmEstadsticas;
	private static JMenuItem mntmJugar;
	private static JMenuItem mntmVerPartidas;

	public MenuZombieland() {

		JMenu mnArchivo = new JMenu("Archivo");
		add(mnArchivo);

		mntmIniciarSesion = new JMenuItem("Iniciar Sesion");
		mntmIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.irA(Main.INICIO_SESION);
			}
		});
		mnArchivo.add(mntmIniciarSesion);

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

		final JMenuItem mntmEstadsticasGlob = new JMenuItem(
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

		mntmIniciarSesion.setEnabled(!inicio);
		mntmRegistrarse.setEnabled(!inicio);
		mntmDatos.setEnabled(inicio);
		mntmEstadsticas.setEnabled(inicio);
		mntmJugar.setEnabled(inicio);
		mntmVerPartidas.setEnabled(inicio);
	}

	public static void setInicioSesion(boolean estado) {
		inicio = estado;
		mntmIniciarSesion.setEnabled(!inicio);
		mntmRegistrarse.setEnabled(!inicio);
		mntmDatos.setEnabled(inicio);
		mntmEstadsticas.setEnabled(inicio);
		mntmJugar.setEnabled(inicio);
		mntmVerPartidas.setEnabled(inicio);

	}
}
