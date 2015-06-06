package com.rzg.zombieland.cliente.interfaz;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Interfaz completa de inicio de sesión.
 * @author Manuel
 */

public class InterfazInicioSesion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField userField;
	private JPasswordField passwordField_1;

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
		setLayout(null);
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuario.setBounds(251, 192, 92, 42);
		add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(251, 246, 110, 17);
		add(lblPassword);
		
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
		add(btnIngresar);
		
		userField = new JTextField();
		userField.setFont(new Font("Tahoma", Font.PLAIN, 18));
		userField.setBounds(389, 203, 139, 20);
		add(userField);
		userField.setColumns(10);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		passwordField_1.setBounds(389, 244, 139, 20);
		add(passwordField_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(InterfazInicioSesion.class.getResource("/com/rzg/zombieland/cliente/interfazInicioSesion/Zombieland.png")));
		lblNewLabel.setBounds(325, 11, 159, 179);
		add(lblNewLabel);
		
		JLabel lblMsg = new JLabel("No tenes un usuario?");
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMsg.setBounds(251, 379, 200, 37);
		add(lblMsg);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.irA(Main.REGISTRO);
			}
		});
		btnRegistrarse.setBounds(445, 398, 175, 40);
		add(btnRegistrarse);
		
		JLabel lblRzg = new JLabel("RZG - 2015");
		lblRzg.setForeground(SystemColor.controlShadow);
		lblRzg.setBounds(701, 526, 110, 14);
		add(lblRzg);
		
		JLabel lblUniteAZombieland = new JLabel("Unite a Zombieland!");
		lblUniteAZombieland.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUniteAZombieland.setBounds(251, 416, 219, 37);
		add(lblUniteAZombieland);
		
		JButton btnO = new JButton("Olvid\u00F3 su clave?");
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Se direccionará a la pantalla de recuperación de clave
				// haciendo uso de la pregunta de seguridad.
			}
		});
		btnO.setBounds(445, 292, 175, 40);
		add(btnO);
	}
}
