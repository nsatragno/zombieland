package com.rzg.zombieland.cliente.interfazInicioSesion;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Font;

/**
 * Interfaz completa de inicio de sesión.
 * @author Manuel
 */

public class InterfazInicioSesion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JFrame frmZombielandV;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField userField;
	private JPasswordField passwordField_1;
	private static final int intentos = 0; // Se incrementará cuando el password sea incorrecto

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazInicioSesion window = new InterfazInicioSesion();
					window.frmZombielandV.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazInicioSesion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmZombielandV = new JFrame();
		frmZombielandV.setTitle("Zombieland v1.0 - Inicio de sesi\u00F3n");
		frmZombielandV.setBounds(100, 100, 800, 600);
		frmZombielandV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmZombielandV.getContentPane().setLayout(null);
		frmZombielandV.setResizable(false);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuario.setBounds(251, 192, 92, 42);
		frmZombielandV.getContentPane().add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(251, 246, 110, 17);
		frmZombielandV.getContentPane().add(lblPassword);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aquí se validarán los datos (Usuario - Contraseña)
				// Si son correctos, se enviará al lobby de partidas.
				// Si son incorrectos, se enviará un mensaje de error
				// en lblError, y se incrementará en 1 el contador de 'intentos'.
				// Si 'intentos' = 3, se tomarán las medidas propuestas
				// de la pregunta y respuesta de seguridad.
			}
		});
		btnIngresar.setBounds(184, 292, 175, 40);
		frmZombielandV.getContentPane().add(btnIngresar);
		
		userField = new JTextField();
		userField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		userField.setBounds(389, 203, 139, 20);
		frmZombielandV.getContentPane().add(userField);
		userField.setColumns(10);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField_1.setBounds(389, 244, 139, 20);
		frmZombielandV.getContentPane().add(passwordField_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(InterfazInicioSesion.class.getResource("/com/rzg/zombieland/cliente/interfazInicioSesion/Zombieland.png")));
		lblNewLabel.setBounds(325, 11, 159, 179);
		frmZombielandV.getContentPane().add(lblNewLabel);
		
		JLabel lblMsg = new JLabel("No tenes un usuario?");
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMsg.setBounds(251, 379, 200, 37);
		frmZombielandV.getContentPane().add(lblMsg);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Aquí se enviará a la página de creación de un usuario nuevo.
			}
		});
		btnRegistrarse.setBounds(445, 398, 175, 40);
		frmZombielandV.getContentPane().add(btnRegistrarse);
		
		JLabel lblRzg = new JLabel("RZG - 2015");
		lblRzg.setForeground(SystemColor.controlShadow);
		lblRzg.setBounds(701, 526, 110, 14);
		frmZombielandV.getContentPane().add(lblRzg);
		
		JLabel lblUniteAZombieland = new JLabel("Unite a Zombieland!");
		lblUniteAZombieland.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUniteAZombieland.setBounds(251, 416, 219, 37);
		frmZombielandV.getContentPane().add(lblUniteAZombieland);
		
		JButton btnO = new JButton("Olvid\u00F3 su clave?");
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Se direccionará a la pantalla de recuperación de clave
				// haciendo uso de la pregunta de seguridad.
			}
		});
		btnO.setBounds(445, 292, 175, 40);
		frmZombielandV.getContentPane().add(btnO);
		
		JMenuBar menuBar = new JMenuBar();
		frmZombielandV.setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmIniciarSesion = new JMenuItem("Iniciar Sesion");
		mnArchivo.add(mntmIniciarSesion);
		
		JMenuItem mntmRegistrarse = new JMenuItem("Registrarse");
		mnArchivo.add(mntmRegistrarse);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);
		
		JMenu mnPartida = new JMenu("Partida");
		menuBar.add(mnPartida);
		
		JMenuItem mntmJugar = new JMenuItem("Jugar");
		mnPartida.add(mntmJugar);
		
		JMenuItem mntmVerPartidas = new JMenuItem("Ver Partidas");
		mnPartida.add(mntmVerPartidas);
		
		JMenu mnCuenta = new JMenu("Cuenta");
		menuBar.add(mnCuenta);
		
		JMenuItem mntmDatos = new JMenuItem("Datos");
		mnCuenta.add(mntmDatos);
		
		JMenuItem mntmEstadsticas = new JMenuItem("Estad\u00EDsticas");
		mnCuenta.add(mntmEstadsticas);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmCmoJugar = new JMenuItem("C\u00F3mo Jugar");
		mnAyuda.add(mntmCmoJugar);
		
		JMenuItem mntmGoogleame = new JMenuItem("Googleame");
		mnAyuda.add(mntmGoogleame);
	}
}
