package com.rzg.zombieland.cliente.interfaz;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * 
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
	 * Create the frame.
	 */
	public InterfazCambioDeDatosUsuario() {
		setResizable(false);
		setTitle("Cambiar Datos De Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombreDeUsuario.setBounds(27, 62, 220, 22);
		contentPane.add(lblNombreDeUsuario);

		JLabel lblContrasea = new JLabel("Nueva Contrase\u00F1a:");
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblContrasea.setBounds(27, 150, 220, 22);
		contentPane.add(lblContrasea);

		JLabel lblVerificacionDeContrasea = new JLabel(
				"Verificacion de Contrase\u00F1a:");
		lblVerificacionDeContrasea.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblVerificacionDeContrasea.setBounds(27, 183, 261, 22);
		contentPane.add(lblVerificacionDeContrasea);

		JLabel lblPreguntaDeSeguridad = new JLabel("Pregunta de Seguridad:");
		lblPreguntaDeSeguridad.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPreguntaDeSeguridad.setBounds(27, 272, 220, 22);
		contentPane.add(lblPreguntaDeSeguridad);

		JLabel lblRespuestaDeSeguridad = new JLabel("Respuesta de Seguridad:");
		lblRespuestaDeSeguridad.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRespuestaDeSeguridad.setBounds(27, 305, 220, 22);
		contentPane.add(lblRespuestaDeSeguridad);

		textUsuario = new JTextField("nombreAct");
		// Cuando clickeo el campo se borra lo que esta escrito.

		textUsuario.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textUsuario.setText(" ");
			}
		});

		textUsuario.setBounds(312, 65, 160, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);

		textPreg = new JTextField("ACT");
		textPreg.setBounds(312, 275, 160, 20);
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
		textRta.setBounds(312, 306, 160, 20);
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
		btnModificar.setBounds(62, 456, 175, 40);
		contentPane.add(btnModificar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(548, 456, 175, 40);
		contentPane.add(btnCancelar);

		JLabel lblNewLabel = new JLabel("Seleccione Avatar:");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(554, 22, 220, 22);
		contentPane.add(lblNewLabel);

		JRadioButton rdbtnPJ1 = new JRadioButton("");
		rdbtnPJ1.setBounds(616, 97, 47, 34);
		contentPane.add(rdbtnPJ1);

		JRadioButton rdbtnPJ2 = new JRadioButton("");
		rdbtnPJ2.setBounds(616, 220, 47, 34);
		contentPane.add(rdbtnPJ2);

		JRadioButton rdbtnPJ3 = new JRadioButton("");
		rdbtnPJ3.setBounds(616, 357, 47, 36);
		contentPane.add(rdbtnPJ3);

		// Agrego los rdbtn al grupo de botones, para que solo se pueda
		// seleccionar uno.
		Avatar.add(rdbtnPJ1);
		Avatar.add(rdbtnPJ2);
		Avatar.add(rdbtnPJ3);

		pass = new JPasswordField();
		pass.setToolTipText("");
		pass.setBounds(312, 153, 160, 20);
		contentPane.add(pass);

		// pass.setToolTipText("Mas de 4 digitos.");

		passVerificacion = new JPasswordField("");
		passVerificacion.setBounds(313, 186, 159, 20);
		contentPane.add(passVerificacion);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1
				.setIcon(new ImageIcon("imagenes/mujer.png"));
		lblNewLabel_1.setBounds(695, 341, 73, 74);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2
				.setIcon(new ImageIcon("imagenes/poli.png"));
		lblNewLabel_2.setBounds(695, 76, 79, 80);
		contentPane.add(lblNewLabel_2);

		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("imagenes/dsn.png"));
		label.setBounds(695, 194, 73, 94);
		contentPane.add(label);

	}
}
