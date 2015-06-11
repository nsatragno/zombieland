package com.rzg.zombieland.cliente.interfazRecuperaContrasenia;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Font;

public class RecuperaContrasenia extends JFrame
{

	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JComboBox<String> preguntaSeguridad;
	private JTextField textFieldResp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					RecuperaContrasenia frame = new RecuperaContrasenia();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RecuperaContrasenia()
	{
		setResizable(false);
		setTitle("Recuperaci\u00F3n de Contrase\u00F1a");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(275, 180, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(90, 75, 79, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblPregunta = new JLabel("Pregunta de Seguridad");
		lblPregunta.setBounds(90, 115, 132, 14);
		contentPane.add(lblPregunta);
		
		JLabel lblRespuesta = new JLabel("Respuesta");
		lblRespuesta.setBounds(90, 155, 110, 14);
		contentPane.add(lblRespuesta);
		
		JLabel labelContrasenia = new JLabel("");
		labelContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		labelContrasenia.setBorder(new LineBorder(new Color(0, 0, 0)));
		labelContrasenia.setBounds(120, 266, 270, 20);
		contentPane.add(labelContrasenia);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setBounds(270, 75, 125, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		preguntaSeguridad = new JComboBox<String>();
        preguntaSeguridad.setModel(new DefaultComboBoxModel<String>(new String[] { "",
                "Cual es su color favorito?", "Mejor amigo de la infancia?",
                "A que escuela primaria fue?", "Nombre de su primer mascota?" }));
        preguntaSeguridad.setBounds(270, 115, 191, 20);
        getContentPane().add(preguntaSeguridad);
		
		textFieldResp = new JTextField();
		textFieldResp.setColumns(10);
		textFieldResp.setBounds(270, 155, 125, 20);
		contentPane.add(textFieldResp);
		
		JLabel lblRzg = new JLabel("RZG - 2015");
        lblRzg.setForeground(SystemColor.controlShadow);
        lblRzg.setBounds(419, 301, 63, 14);
        getContentPane().add(lblRzg);
        
        JButton btnRecuperar = new JButton("Recuperar");
        btnRecuperar.setBounds(195, 205, 135, 30);
        contentPane.add(btnRecuperar);
        
        JLabel lblImagen = new JLabel("");
        lblImagen.setIcon(new ImageIcon("imagenes/zombie-silueta.png"));
        lblImagen.setBounds(0, -5, 110, 316);
        contentPane.add(lblImagen);
        
        JLabel lblTitulo = new JLabel("Recupera tu contrase\u00F1a");
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitulo.setBounds(90, 16, 340, 33);
        contentPane.add(lblTitulo);
	}
}
