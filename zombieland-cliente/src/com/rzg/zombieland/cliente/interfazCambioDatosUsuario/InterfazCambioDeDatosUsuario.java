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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuario.setBounds(10, 11, 163, 22);
		contentPane.add(lblNombreDeUsuario);

		JLabel lblContrasea = new JLabel("Nueva Contrase\u00F1a:");
		lblContrasea.setBounds(10, 62, 163, 22);
		contentPane.add(lblContrasea);

		JLabel lblVerificacionDeContrasea = new JLabel(
				"Verificacion de Contrase\u00F1a:");
		lblVerificacionDeContrasea.setBounds(10, 95, 163, 22);
		contentPane.add(lblVerificacionDeContrasea);

		JLabel lblPreguntaDeSeguridad = new JLabel("Pregunta de Seguridad:");
		lblPreguntaDeSeguridad.setBounds(10, 133, 138, 22);
		contentPane.add(lblPreguntaDeSeguridad);

		JLabel lblRespuestaDeSeguridad = new JLabel("Respuesta de Seguridad:");
		lblRespuestaDeSeguridad.setBounds(10, 166, 163, 22);
		contentPane.add(lblRespuestaDeSeguridad);

		textUsuario = new JTextField("nombreAct");
		// Cuando clickeo el campo se borra lo que esta escrito.

		textUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textUsuario.setText(" ");
			}
		});

		textUsuario.setBounds(183, 12, 86, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);

		textPreg = new JTextField("ACT");
		textPreg.setBounds(183, 134, 86, 20);
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
		textRta.setBounds(183, 167, 86, 20);
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
		btnModificar.setBounds(141, 214, 128, 23);
		contentPane.add(btnModificar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(320, 214, 89, 23);
		contentPane.add(btnCancelar);

		JLabel lblNewLabel = new JLabel("Seleccione Avatar:");
		lblNewLabel.setBounds(292, 11, 116, 22);
		contentPane.add(lblNewLabel);

		JRadioButton rdbtnPJ1 = new JRadioButton("");
		rdbtnPJ1.setBounds(381, 40, 47, 34);
		contentPane.add(rdbtnPJ1);

		JRadioButton rdbtnPJ2 = new JRadioButton("");
		rdbtnPJ2.setBounds(381, 95, 47, 34);
		contentPane.add(rdbtnPJ2);

		JRadioButton rdbtnPJ3 = new JRadioButton("");
		rdbtnPJ3.setBounds(381, 152, 47, 36);
		contentPane.add(rdbtnPJ3);

		// Agrego los rdbtn al grupo de botones, para que solo se pueda
		// seleccionar uno.
		Avatar.add(rdbtnPJ1);
		Avatar.add(rdbtnPJ2);
		Avatar.add(rdbtnPJ3);

		pass = new JPasswordField();
		pass.setToolTipText("");
		pass.setBounds(183, 63, 86, 20);
		contentPane.add(pass);

		// pass.setToolTipText("Mas de 4 digitos.");

		passVerificacion = new JPasswordField("");
		passVerificacion.setBounds(183, 96, 85, 20);
		contentPane.add(passVerificacion);

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
