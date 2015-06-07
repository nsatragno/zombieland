package com.rzg.zombieland.cliente.interfazEstadisticaJugador;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Interfaz de registro de usuario.
 * @author Ivan
 */

public class InterfazEstadisticaJugador extends JFrame
{

	private JPanel contentPane;

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
					InterfazEstadisticaJugador frame = new InterfazEstadisticaJugador();
					frame.setVisible(true);
					frame.setResizable(false);
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
	public InterfazEstadisticaJugador()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEstadisticas = new JLabel("Estad\u00EDsticas");
		lblEstadisticas.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 30));
		lblEstadisticas.setBounds(27, 22, 238, 41);
		contentPane.add(lblEstadisticas);
		
		JLabel lblNombreJugador = new JLabel("Nombre Jugador");
		lblNombreJugador.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		lblNombreJugador.setBounds(134, 74, 262, 41);
		contentPane.add(lblNombreJugador);
		
		JLabel lblAvatarjugador = new JLabel("AvatarJugador");
		lblAvatarjugador.setBounds(17, 74, 107, 93);
		contentPane.add(lblAvatarjugador);
		
		JLabel lblPosicionRanking = new JLabel("Posici\u00F3n en el Ranking");
		lblPosicionRanking.setFont(new Font("Snap ITC", Font.PLAIN, 16));
		lblPosicionRanking.setBounds(83, 150, 221, 23);
		contentPane.add(lblPosicionRanking);
		
		JLabel lblPuntaje = new JLabel("Puntaje");
		lblPuntaje.setFont(new Font("Snap ITC", Font.PLAIN, 16));
		lblPuntaje.setBounds(83, 190, 201, 23);
		contentPane.add(lblPuntaje);
		
		JLabel lblCantidadGanados = new JLabel("Cantidad de Juegos Ganados");
		lblCantidadGanados.setFont(new Font("Snap ITC", Font.PLAIN, 16));
		lblCantidadGanados.setBounds(83, 230, 292, 23);
		contentPane.add(lblCantidadGanados);
		
		JLabel lblCantidadDeJuegos = new JLabel("Cantidad de Juegos");
		lblCantidadDeJuegos.setFont(new Font("Snap ITC", Font.PLAIN, 16));
		lblCantidadDeJuegos.setBounds(83, 270, 201, 23);
		contentPane.add(lblCantidadDeJuegos);
		
		JLabel label = new JLabel("RZG - 2015");
		label.setForeground(SystemColor.textInactiveText);
		label.setBounds(419, 416, 63, 14);
		contentPane.add(label);
		
		// DEPENDIENDO DE LA POSICION CAMBIAR EL COLOR
		JLabel lblPosicionJugador = new JLabel("POS");
		lblPosicionJugador.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosicionJugador.setBounds(390, 156, 46, 14);
		contentPane.add(lblPosicionJugador);
		
		JLabel lblPuntajeJugador = new JLabel("PUNT");
		lblPuntajeJugador.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntajeJugador.setBounds(390, 196, 46, 14);
		contentPane.add(lblPuntajeJugador);
		
		JLabel lblGanadosJugador = new JLabel("CANT");
		lblGanadosJugador.setHorizontalAlignment(SwingConstants.CENTER);
		lblGanadosJugador.setBounds(390, 236, 46, 14);
		contentPane.add(lblGanadosJugador);
		
		JLabel lblJugadosJugador = new JLabel("CANT");
		lblJugadosJugador.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugadosJugador.setBounds(390, 276, 46, 14);
		contentPane.add(lblJugadosJugador);
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(InterfazEstadisticaJugador.class.getResource("/com/rzg/zombieland/cliente/interfazEstadisticaJugador/zombie.png")));
		lblImagen.setBounds(39, 304, 277, 126);
		contentPane.add(lblImagen);
	}
}
