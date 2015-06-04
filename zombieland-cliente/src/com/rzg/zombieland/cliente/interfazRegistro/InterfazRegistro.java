package com.rzg.zombieland.cliente.interfazRegistro;

import java.awt.EventQueue;
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

import net.miginfocom.swing.MigLayout;

/**
 * Interfaz de registro de usuario.
 * @author Manuel
 */

public class InterfazRegistro extends JPanel {

	private JFrame frmZombielandV;
	private JTextField textField;
	private final JTextField textField_1 = new JTextField();
	private final JPasswordField passwordField = new JPasswordField();
	private JPasswordField passwordField_1 = new JPasswordField();
	private JTextField textField_2 = new JTextField();;

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
		JButton btnRegistrar = new JButton("Registrar!");
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aquí se validarán los datos, se compararán los password
				// Se verificará que no haya otro usuario con el mismo nombre
				// Y si está todo en orden, se creará el nuevo usuario. Ademas de redireccionarlo
				// a la página de inicio de sesión.
			}
		});
		
		JButton btnVolverAlInicio = new JButton("Volver al inicio de sesi\u00F3n");
		btnVolverAlInicio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Al clickear aquí se volverá a la página de inicio de sesión
			}
		});
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
		
		JLabel lblPassword = new JLabel("Password:");
		
		JLabel lblPreguntaDeSeguridad = new JLabel("Pregunta de seguridad");
		
		JLabel lblRespuestaDeSeguridad = new JLabel("Respuesta de seguridad:");
		textField_1.setColumns(10);
		textField_2.setColumns(10);
		passwordField.setColumns(10);
		passwordField_1.setColumns(10);
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"-- Seleccione", 
																"Cual es su color favorito?", 
																"Mejor amigo de la infancia?", 
																"A que escuela primaria fue?", 
																"Nombre de su primer mascota?"}));
		
		
		setLayout(new MigLayout("", "[grow]", "push[][][][][]push[]"));
		
		JLabel lblConfirmaPassword = new JLabel("Confirma Password:");
		
		JLabel imagenZombie = new JLabel("");
	      imagenZombie.setIcon(new ImageIcon(InterfazRegistro.class.getResource
	                ("/com/rzg/zombieland/cliente/interfazRegistro/zombie.png")));
	      
		add(lblNombreDeUsuario);
		add(textField_1, "wrap");
		
		add(lblPassword);
		add(passwordField, "wrap");
		
		add(lblConfirmaPassword);
		add(passwordField_1, "wrap");

        add(lblPreguntaDeSeguridad);
        add(comboBox, "wrap");
        
        add(lblRespuestaDeSeguridad);
        add(textField_2, "wrap");
		
		add(btnRegistrar);
		add(btnVolverAlInicio, "wrap");
		
		add(imagenZombie, "east");
	}
}
