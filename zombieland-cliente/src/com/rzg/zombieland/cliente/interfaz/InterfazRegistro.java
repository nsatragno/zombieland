package com.rzg.zombieland.cliente.interfaz;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 * Interfaz de registro de usuario.
 * @author Manuel
 */

public class InterfazRegistro extends JPanel {

	private static final long serialVersionUID = 3234547685088239182L;
	
	private final JTextField textField_1 = new JTextField();
	private final JPasswordField passwordField = new JPasswordField();
	private JPasswordField passwordField_1;
	private JTextField textField_2;

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
		setLayout(null);
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("imagenes/zombie.png"));
		label.setBounds(690, 214, 129, 353);
		add(label);
		
		JButton btnRegistrar = new JButton("Registrar!");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aquí se validarán los datos, se compararán los password
				// Se verificará que no haya otro usuario con el mismo nombre
				// Y si está todo en orden, se creará el nuevo usuario. Ademas de redireccionarlo
				// a la página de inicio de sesión.
			}
		});
		btnRegistrar.setBounds(20, 387, 175, 40);
		add(btnRegistrar);
		
		JButton btnVolverAlInicio = new JButton("Volver al inicio de sesi\u00F3n");
		btnVolverAlInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Al clickear aquí se volverá a la página de inicio de sesión
			}
		});
		btnVolverAlInicio.setBounds(20, 473, 175, 40);
		add(btnVolverAlInicio);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		lblNombreDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNombreDeUsuario.setBounds(10, 23, 160, 26);
		add(lblNombreDeUsuario);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(10, 58, 147, 18);
		add(lblPassword);
		
		JLabel lblConfirmaPassword = new JLabel("Confirma Password:");
		lblConfirmaPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConfirmaPassword.setBounds(10, 85, 160, 26);
		add(lblConfirmaPassword);
		
		JLabel lblPreguntaDeSeguridad = new JLabel("Pregunta de seguridad");
		lblPreguntaDeSeguridad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPreguntaDeSeguridad.setBounds(10, 122, 205, 26);
		add(lblPreguntaDeSeguridad);
		
		JLabel lblRespuestaDeSeguridad = new JLabel("Respuesta de seguridad:");
		lblRespuestaDeSeguridad.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblRespuestaDeSeguridad.setBounds(10, 160, 181, 26);
		add(lblRespuestaDeSeguridad);
		textField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_1.setBounds(225, 23, 157, 21);
		add(textField_1);
		textField_1.setColumns(10);
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField.setBounds(225, 56, 157, 21);
		add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		passwordField_1.setBounds(225, 89, 157, 21);
		add(passwordField_1);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"-- Seleccione", 
																"Cual es su color favorito?", 
																"Mejor amigo de la infancia?", 
																"A que escuela primaria fue?", 
																"Nombre de su primer mascota?"}));
		comboBox.setBounds(225, 127, 191, 20);
		add(comboBox);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_2.setBounds(225, 164, 157, 21);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblRzg = new JLabel("RZG - 2015");
		lblRzg.setForeground(SystemColor.controlShadow);
		lblRzg.setBounds(709, 526, 63, 14);
		add(lblRzg);
	}
}
