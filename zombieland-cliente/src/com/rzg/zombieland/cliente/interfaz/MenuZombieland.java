package com.rzg.zombieland.cliente.interfaz;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.rzg.zombieland.cliente.interfazCambioDatosUsuario.InterfazCambioDeDatosUsuario;
import com.rzg.zombieland.cliente.interfazEstadisticaJugador.InterfazEstadisticaJugador;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class MenuZombieland extends JMenuBar {
	private static final long serialVersionUID = -2766617470499185657L;

	public MenuZombieland() {

		JMenu mnArchivo = new JMenu("Archivo");
		add(mnArchivo);

		JMenuItem mntmIniciarSesion = new JMenuItem("Iniciar Sesion");
		mntmIniciarSesion.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.irA(Main.INICIO_SESION);
			}
		});
		mnArchivo.add(mntmIniciarSesion);

		JMenuItem mntmRegistrarse = new JMenuItem("Registrarse");
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

		JMenuItem mntmJugar = new JMenuItem("Jugar");
		mntmJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// OJO aca hay que hacer que entre a una partida de forma
				// aleatoria.
				Main.irA(Main.LOBBY);
			}
		});
		mnPartida.add(mntmJugar);

		JMenuItem mntmVerPartidas = new JMenuItem("Ver Partidas");
		mntmVerPartidas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.irA(Main.LISTADO_PARTIDAS);
			}
		});
		mnPartida.add(mntmVerPartidas);

		JMenu mnCuenta = new JMenu("Cuenta");
		add(mnCuenta);

		final JMenuItem mntmDatos = new JMenuItem("Datos");
		mntmDatos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazCambioDeDatosUsuario CambioDatos = new InterfazCambioDeDatosUsuario();
				CambioDatos.addWindowListener(new WindowCloseListener() {

					@Override
					public void windowClosed(WindowEvent e) {
						mntmDatos.setEnabled(true);
					}
				});
				mntmDatos.setEnabled(false);
				CambioDatos.setVisible(true);
			}

		});
		mnCuenta.add(mntmDatos);

		final JMenuItem mntmEstadsticas = new JMenuItem("Estad\u00EDsticas");
		mntmEstadsticas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazEstadisticaJugador Estadisticas = new InterfazEstadisticaJugador();
				Estadisticas.addWindowListener(new WindowCloseListener() {

					@Override
					public void windowClosed(WindowEvent e) {
						mntmDatos.setEnabled(true);
					}
				});
				mntmEstadsticas.setEnabled(false);
				Estadisticas.setVisible(true);
			}

		});
		mnCuenta.add(mntmEstadsticas);

		JMenu mnAyuda = new JMenu("Ayuda");
		add(mnAyuda);

		JMenuItem mntmCmoJugar = new JMenuItem("C\u00F3mo Jugar");
		mntmCmoJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
		mnAyuda.add(mntmCmoJugar);

		JMenuItem mntmGoogleame = new JMenuItem("Googleame");
		mnAyuda.add(mntmGoogleame);
	}

	private void abrirCambioDeDatos() {

	}

}
