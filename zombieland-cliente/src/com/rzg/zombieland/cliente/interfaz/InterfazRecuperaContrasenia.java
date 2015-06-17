package com.rzg.zombieland.cliente.interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.rzg.zombieland.cliente.misc.RutaImagen;

/**
 * Interfaz de recuperar contraseņa.
 * 
 * @author Ivan
 */

public class InterfazRecuperaContrasenia extends JFrame
{
	private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JComboBox<String> preguntaSeguridad;
	private JTextField textFieldResp;

	/**
	 * Create the frame.
	 */
	public InterfazRecuperaContrasenia()
	{
		setResizable(false);
		setTitle("Recuperaci\u00F3n de Contrase\u00F1a");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(275, 180, 500, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setForeground(Color.WHITE);
		lblUsuario.setBounds(90, 75, 79, 14);
		contentPane.add(lblUsuario);
		
		JLabel lblPregunta = new JLabel("Pregunta de Seguridad");
		lblPregunta.setForeground(Color.WHITE);
		lblPregunta.setBounds(90, 115, 132, 14);
		contentPane.add(lblPregunta);
		
		JLabel lblRespuesta = new JLabel("Respuesta");
		lblRespuesta.setForeground(Color.WHITE);
		lblRespuesta.setBounds(90, 155, 110, 14);
		contentPane.add(lblRespuesta);
		
		JLabel labelContrasenia = new JLabel("");
		labelContrasenia.setForeground(Color.RED);
		labelContrasenia.setHorizontalAlignment(SwingConstants.CENTER);
		labelContrasenia.setBorder(new LineBorder(Color.GRAY));
		labelContrasenia.setBounds(120, 266, 270, 20);
		contentPane.add(labelContrasenia);
		
		textFieldUsuario = new JTextField();
		textFieldUsuario.setOpaque(false);
		textFieldUsuario.setBorder(new LineBorder(Color.GRAY));
		textFieldUsuario.setForeground(Color.WHITE);
		textFieldUsuario.setBounds(270, 75, 125, 20);
		contentPane.add(textFieldUsuario);
		textFieldUsuario.setColumns(10);
		
		preguntaSeguridad = new JComboBox<String>();
		preguntaSeguridad.setBackground(Color.BLACK);
		preguntaSeguridad.setOpaque(false);
		preguntaSeguridad.setForeground(Color.WHITE);
        preguntaSeguridad.setModel(new DefaultComboBoxModel<String>(new String[] { "",
                "Cual es su color favorito?", "Mejor amigo de la infancia?",
                "A que escuela primaria fue?", "Nombre de su primer mascota?" }));
        preguntaSeguridad.setBounds(270, 115, 191, 20);
        getContentPane().add(preguntaSeguridad);
		
		textFieldResp = new JTextField();
		textFieldResp.setForeground(Color.WHITE);
		textFieldResp.setBorder(new LineBorder(Color.GRAY));
		textFieldResp.setOpaque(false);
		textFieldResp.setColumns(10);
		textFieldResp.setBounds(270, 155, 125, 20);
		contentPane.add(textFieldResp);
		
		JLabel lblRzg = new JLabel("RZG - 2015");
        lblRzg.setForeground(SystemColor.controlShadow);
        lblRzg.setBounds(421, 293, 63, 14);
        getContentPane().add(lblRzg);
        
        JButton btnRecuperar = new JButton("Recuperar");
        btnRecuperar.setBounds(195, 205, 135, 30);
        contentPane.add(btnRecuperar);
        
        JLabel lblTitulo = new JLabel("Recupera tu contrase\u00F1a");
        lblTitulo.setForeground(Color.LIGHT_GRAY);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 22));
        lblTitulo.setBounds(90, 16, 340, 33);
        contentPane.add(lblTitulo);
        
        JLabel lblImagen = new JLabel("");
        lblImagen.setIcon(new ImageIcon(RutaImagen.get("imagenes/zombie-silueta.png")));
        lblImagen.setBounds(-5, -30, 100, 386);
        contentPane.add(lblImagen);
        
        JLabel lblFondo = new JLabel("");
        lblFondo.setIcon(new ImageIcon(RutaImagen.get("imagenes/Fondos/fondo-contraseņa.png")));
        lblFondo.setBounds(-400, 0, 925, 386);
        contentPane.add(lblFondo);
	}
}
