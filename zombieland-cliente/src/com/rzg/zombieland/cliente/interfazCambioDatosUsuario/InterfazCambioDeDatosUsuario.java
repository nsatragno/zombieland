package com.rzg.zombieland.cliente.interfazCambioDatosUsuario;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout.Group;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;

import javax.swing.JRadioButton;
import javax.swing.JPasswordField;
import javax.swing.DropMode;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.ComponentOrientation;
import java.awt.Cursor;
import java.awt.Font;

/**
 * 
 * FALTA: Auto completado con datos anteriores, es nesesario saber donde se
 * guardaron(Cuando se registro) Codificar boton Modificar Datos
 * 
 * @author Nicolas L
 *
 */
public class InterfazCambioDeDatosUsuario extends JFrame {

	private JPanel contentPane;
	private JTextField textUsuario;
	private JTextField textPreg;
	private JTextField textRta;
	private ButtonGroup Avatar = new ButtonGroup();
	private JPasswordField pass;
	private JPasswordField passVerificacion;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazCambioDeDatosUsuario frame = new InterfazCambioDeDatosUsuario();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazCambioDeDatosUsuario() {
		setTitle("Cambiar Datos De Usuario");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombreDeUsuario.setBounds(20, 22, 220, 22);
		contentPane.add(lblNombreDeUsuario);

		JLabel lblContrasea = new JLabel("Nueva Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblContrasea.setBounds(20, 76, 220, 22);
		contentPane.add(lblContrasea);

		JLabel lblVerificacionDeContrasea = new JLabel(
				"Verificacion de Contrase\u00F1a:");
		lblVerificacionDeContrasea.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblVerificacionDeContrasea.setBounds(20, 109, 261, 22);
		contentPane.add(lblVerificacionDeContrasea);

		JLabel lblPreguntaDeSeguridad = new JLabel("Pregunta de Seguridad:");
		lblPreguntaDeSeguridad.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPreguntaDeSeguridad.setBounds(20, 161, 220, 22);
		contentPane.add(lblPreguntaDeSeguridad);

		JLabel lblRespuestaDeSeguridad = new JLabel("Respuesta de Seguridad:");
		lblRespuestaDeSeguridad.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRespuestaDeSeguridad.setBounds(20, 194, 220, 22);
		contentPane.add(lblRespuestaDeSeguridad);

		textUsuario = new JTextField("nombreAct");
		// Cuando clickeo el campo se borra lo que esta escrito.

		textUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textUsuario.setText(" ");
			}
		});

		textUsuario.setBounds(305, 23, 160, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);

		textPreg = new JTextField("ACT");
		textPreg.setBounds(305, 164, 160, 20);
		contentPane.add(textPreg);
		textPreg.setColumns(10);
		// Cuando clickeo el campo se borra lo que esta escrito.

		textPreg.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textPreg.setText(" ");
			}
		});

		textRta = new JTextField("RTA");
		textRta.setBounds(305, 195, 160, 20);
		contentPane.add(textRta);
		textRta.setColumns(10);
		// Cuando clickeo el campo se borra lo que esta escrito.
		textRta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textRta.setText(" ");
			}
		});

		JButton btnModificar = new JButton("Modificar Datos");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aqui se realizan los cambios efectuados
				//
			}
		});
		btnModificar.setBounds(446, 491, 128, 23);
		contentPane.add(btnModificar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(672, 491, 89, 23);
		contentPane.add(btnCancelar);

		JLabel lblNewLabel = new JLabel("Seleccione Avatar:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(20, 248, 220, 22);
		contentPane.add(lblNewLabel);

		JRadioButton rdbtnPJ1 = new JRadioButton("");
		rdbtnPJ1.setBounds(30, 294, 47, 34);
		contentPane.add(rdbtnPJ1);

		JRadioButton rdbtnPJ2 = new JRadioButton("");
		rdbtnPJ2.setBounds(189, 294, 47, 34);
		contentPane.add(rdbtnPJ2);

		JRadioButton rdbtnPJ3 = new JRadioButton("");
		rdbtnPJ3.setBounds(334, 294, 47, 36);
		contentPane.add(rdbtnPJ3);

		// Agrego los rdbtn al grupo de botones, para que solo se pueda
		// seleccionar uno.
		Avatar.add(rdbtnPJ1);
		Avatar.add(rdbtnPJ2);
		Avatar.add(rdbtnPJ3);

		pass = new JPasswordField();
		pass.setToolTipText("");
		pass.setBounds(305, 79, 160, 20);
		contentPane.add(pass);

		// pass.setToolTipText("Mas de 4 digitos.");

		passVerificacion = new JPasswordField("");
		passVerificacion.setBounds(306, 112, 159, 20);
		contentPane.add(passVerificacion);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1
				.setIcon(new ImageIcon(
						InterfazCambioDeDatosUsuario.class
								.getResource("/com/rzg/zombieland/cliente/interfazCambioDatosUsuario/mujer.png")));
		lblNewLabel_1.setBounds(334, 361, 73, 74);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2
				.setIcon(new ImageIcon(
						InterfazCambioDeDatosUsuario.class
								.getResource("/com/rzg/zombieland/cliente/interfazCambioDatosUsuario/poli.png")));
		lblNewLabel_2.setBounds(20, 355, 79, 80);
		contentPane.add(lblNewLabel_2);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(
				InterfazCambioDeDatosUsuario.class
						.getResource("/com/rzg/zombieland/cliente/interfazCambioDatosUsuario/dsn.png")));
		label.setBounds(175, 341, 73, 94);
		contentPane.add(label);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

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
