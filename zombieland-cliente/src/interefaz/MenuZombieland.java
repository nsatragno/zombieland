package interefaz;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuZombieland extends JMenuBar {
	private static final long serialVersionUID = -2766617470499185657L;
	
	public MenuZombieland(){

		JMenu mnArchivo = new JMenu("Archivo");
		add(mnArchivo);

		JMenuItem mntmIniciarSesion = new JMenuItem("Iniciar Sesion");
		mnArchivo.add(mntmIniciarSesion);

		JMenuItem mntmRegistrarse = new JMenuItem("Registrarse");
		mnArchivo.add(mntmRegistrarse);

		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);

		JMenu mnPartida = new JMenu("Partida");
		add(mnPartida);

		JMenuItem mntmJugar = new JMenuItem("Jugar");
		mnPartida.add(mntmJugar);

		JMenuItem mntmVerPartidas = new JMenuItem("Ver Partidas");
		mnPartida.add(mntmVerPartidas);

		JMenu mnCuenta = new JMenu("Cuenta");
		add(mnCuenta);

		JMenuItem mntmDatos = new JMenuItem("Datos");
		mnCuenta.add(mntmDatos);

		JMenuItem mntmEstadsticas = new JMenuItem("Estad\u00EDsticas");
		mnCuenta.add(mntmEstadsticas);

		JMenu mnAyuda = new JMenu("Ayuda");
		add(mnAyuda);

		JMenuItem mntmCmoJugar = new JMenuItem("C\u00F3mo Jugar");
		mnAyuda.add(mntmCmoJugar);

		JMenuItem mntmGoogleame = new JMenuItem("Googleame");
		mnAyuda.add(mntmGoogleame);
	}

}
