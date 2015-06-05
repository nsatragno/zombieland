package com.rzg.zombieland.cliente.modificarPartida;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.UIManager;
import javax.swing.SwingConstants;

public class ModificaPartida extends JFrame
{

	private JPanel contentPane;
	private JTextField textFieldNombrePartida;
	private JTextField textFieldCantJugadores;
	private JTextField textFieldCantRondas;

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
					ModificaPartida frame = new ModificaPartida();
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
	public ModificaPartida()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 100, 400, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// ESTO DEBERIA SER SOLO VISIBLE PARA EL ADMINISTRADOR DE LA PARTIDA
		
		JLabel lblCreacinPartida = new JLabel("Personaliza tu Partida");
		lblCreacinPartida.setFont(new Font("Verdana", Font.PLAIN, 26));
		lblCreacinPartida.setBounds(10, 20, 326, 44);
		contentPane.add(lblCreacinPartida);
		
		JLabel lblLimiteJugadores = new JLabel("Cantidad de Jugadores:");
		lblLimiteJugadores.setForeground(Color.DARK_GRAY);
		lblLimiteJugadores.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblLimiteJugadores.setBounds(30, 170, 164, 14);
		contentPane.add(lblLimiteJugadores);
				
		JLabel lblLimiteRondas = new JLabel("Cantidad de Rondas:");
		lblLimiteRondas.setForeground(Color.DARK_GRAY);
		lblLimiteRondas.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblLimiteRondas.setBounds(30, 230, 164, 14);
				contentPane.add(lblLimiteRondas);
				
		JLabel lblNombrePartida = new JLabel("Nombre Partida:");
		lblNombrePartida.setForeground(Color.DARK_GRAY);
		lblNombrePartida.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblNombrePartida.setBounds(30, 110, 119, 14);
		contentPane.add(lblNombrePartida);
				
				
		textFieldNombrePartida = new JTextField();
		textFieldNombrePartida.setForeground(Color.WHITE);
		textFieldNombrePartida.setBackground(Color.DARK_GRAY);
		textFieldNombrePartida.setBorder(new LineBorder(new Color(51, 102, 255), 2, true));
		textFieldNombrePartida.setOpaque(false);
		textFieldNombrePartida.setBounds(200, 110, 170, 20);
		contentPane.add(textFieldNombrePartida);
		textFieldNombrePartida.setColumns(10);
				
		textFieldCantJugadores = new JTextField();
		textFieldCantJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCantJugadores.setOpaque(false);
		textFieldCantJugadores.setForeground(Color.WHITE);
		textFieldCantJugadores.setBackground(Color.DARK_GRAY);
		textFieldCantJugadores.setBorder(new LineBorder(new Color(51, 102, 255), 2, true));
		textFieldCantJugadores.setBounds(200, 170, 170, 20);
		contentPane.add(textFieldCantJugadores);
		textFieldCantJugadores.setColumns(10);
				
		textFieldCantRondas = new JTextField();
		textFieldCantRondas.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCantRondas.setOpaque(false);
		textFieldCantRondas.setForeground(Color.WHITE);
		textFieldCantRondas.setBackground(Color.DARK_GRAY);
		textFieldCantRondas.setBorder(new LineBorder(new Color(51, 102, 255), 2, true));
		textFieldCantRondas.setBounds(200, 230, 170, 20);
		contentPane.add(textFieldCantRondas);
		textFieldCantRondas.setColumns(10);
				
		JButton btnAceptarCambios = new JButton("Aceptar");
		btnAceptarCambios.setBounds(50, 300, 89, 23);
		contentPane.add(btnAceptarCambios);
				
		JButton btnCancelarCambios = new JButton("Cancelar");
		btnCancelarCambios.setBounds(250, 300, 89, 23);
		contentPane.add(btnCancelarCambios);
		
		JLabel label = new JLabel("RZG - 2015");
		label.setForeground(SystemColor.textInactiveText);
		label.setBounds(319, 371, 63, 14);
		contentPane.add(label);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(ModificaPartida.class.getResource("/com/rzg/zombieland/cliente/modificarPartida/zombie-fondo.png")));
		lblFondo.setBounds(-100, 0, 500, 430);
		contentPane.add(lblFondo);
	}
}
