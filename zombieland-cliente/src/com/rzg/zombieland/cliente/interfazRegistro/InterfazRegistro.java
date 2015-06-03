package com.rzg.zombieland.cliente.interfazRegistro;

import java.awt.EventQueue;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Interfaz de registro de usuario.
 * @author Manuel
 */

public class InterfazRegistro extends JPanel {

	private JFrame frmZombielandV;
	private JTextField textField;
	private final JTextField textField_1 = new JTextField();
	private final JPasswordField passwordField = new JPasswordField();
	private JPasswordField passwordField_1;
	private JTextField textField_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazRegistro window = new InterfazRegistro();
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
	public InterfazRegistro() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmZombielandV = new JFrame();
		frmZombielandV.setTitle("Zombieland v.1.0 - Registro");
		frmZombielandV.setBounds(100, 100, 450, 325);
		frmZombielandV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmZombielandV.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(InterfazRegistro.class.getResource
				("/com/rzg/zombieland/cliente/interfazRegistro/zombie.png")));
		label.setBounds(337, 0, 105, 262);
		frmZombielandV.getContentPane().add(label);
		
		JButton btnRegistrar = new JButton("Registrar!");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aquí se validarán los datos, se compararán los password
				// Se verificará que no haya otro usuario con el mismo nombre
				// Y si está todo en orden, se creará el nuevo usuario. Ademas de redireccionarlo
				// a la página de inicio de sesión.
			}
		});
		btnRegistrar.setBounds(20, 159, 112, 37);
		frmZombielandV.getContentPane().add(btnRegistrar);
		
		JButton btnVolverAlInicio = new JButton("Volver al inicio de sesi\u00F3n");
		btnVolverAlInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Al clickear aquí se volverá a la página de inicio de sesión
			}
		});
		btnVolverAlInicio.setBounds(10, 228, 189, 23);
		frmZombielandV.getContentPane().add(btnVolverAlInicio);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		lblNombreDeUsuario.setBounds(10, 23, 122, 14);
		frmZombielandV.getContentPane().add(lblNombreDeUsuario);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 48, 122, 14);
		frmZombielandV.getContentPane().add(lblPassword);
		
		JLabel lblConfirmaPassword = new JLabel("Confirma Password:");
		lblConfirmaPassword.setBounds(10, 73, 122, 14);
		frmZombielandV.getContentPane().add(lblConfirmaPassword);
		
		JLabel lblPreguntaDeSeguridad = new JLabel("Pregunta de seguridad");
		lblPreguntaDeSeguridad.setBounds(10, 98, 139, 14);
		frmZombielandV.getContentPane().add(lblPreguntaDeSeguridad);
		
		JLabel lblRespuestaDeSeguridad = new JLabel("Respuesta de seguridad:");
		lblRespuestaDeSeguridad.setBounds(10, 123, 160, 14);
		frmZombielandV.getContentPane().add(lblRespuestaDeSeguridad);
		textField_1.setBounds(180, 23, 139, 17);
		frmZombielandV.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		passwordField.setBounds(180, 48, 139, 17);
		frmZombielandV.getContentPane().add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(180, 73, 139, 17);
		frmZombielandV.getContentPane().add(passwordField_1);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"-- Seleccione", 
																"Cual es su color favorito?", 
																"Mejor amigo de la infancia?", 
																"A que escuela primaria fue?", 
																"Nombre de su primer mascota?"}));
		comboBox.setBounds(148, 95, 191, 20);
		frmZombielandV.getContentPane().add(comboBox);
		
		textField_2 = new JTextField();
		textField_2.setBounds(180, 123, 139, 17);
		frmZombielandV.getContentPane().add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblRzg = new JLabel("RZG - 2015");
		lblRzg.setForeground(SystemColor.controlShadow);
		lblRzg.setBounds(287, 248, 63, 14);
		frmZombielandV.getContentPane().add(lblRzg);
		
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
