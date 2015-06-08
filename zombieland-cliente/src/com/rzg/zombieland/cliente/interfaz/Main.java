package com.rzg.zombieland.cliente.interfaz;

import interefaz.MenuZombieland;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 */
public class Main extends JFrame {

    public static final String CONEXION = "conexion";
	public static final String REGISTRO = "registro";
	public static final String INICIO_SESION = "inicioSesion";
	
	private static final long serialVersionUID = -8977109460614792967L;
	private CardLayout cardLayout;

	// El frame principal de la aplicación.
	private static Main frame;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static void irA(String pantalla) {
		frame.cardLayout.show(frame.getContentPane(), pantalla);
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setTitle("Zombieland");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		JPanel contentPane = new JPanel();
		cardLayout = new CardLayout();
		contentPane.setLayout(cardLayout);
		contentPane.add(new InterfazConexionServidor(), CONEXION);
		contentPane.add(new InterfazInicioSesion(), INICIO_SESION);
		contentPane.add(new InterfazRegistro(), REGISTRO);
		setJMenuBar(new MenuZombieland());
		setContentPane(contentPane);
	}
}
