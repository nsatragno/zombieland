package com.rzg.zombieland.cliente.interfazInicioSesion;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.Canvas;

public class InterfazInicioSesion extends JPanel {

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
		frmZombielandV.setBounds(100, 100, 450, 300);
		frmZombielandV.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmZombielandV.getContentPane().setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setBounds(10, 161, 58, 14);
		frmZombielandV.getContentPane().add(lblUsuario);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setBounds(10, 192, 75, 14);
		frmZombielandV.getContentPane().add(lblPassword);
		
		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(76, 220, 89, 23);
		frmZombielandV.getContentPane().add(btnIngresar);
		
		userField = new JTextField();
		userField.setBounds(95, 158, 115, 20);
		frmZombielandV.getContentPane().add(userField);
		userField.setColumns(10);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(95, 189, 115, 20);
		frmZombielandV.getContentPane().add(passwordField_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(InterfazInicioSesion.class.getResource("/com/rzg/zombieland/cliente/interfazInicioSesion/Zombieland.png")));
		lblNewLabel.setBounds(215, 11, 232, 197);
		frmZombielandV.getContentPane().add(lblNewLabel);
	}
}
