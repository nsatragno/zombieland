package com.rzg.zombieland.cliente.interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.rzg.zombieland.cliente.misc.RutaImagen;
import com.rzg.zombieland.comunes.misc.Log;

/**
 * Interfaz de registro de usuario.
 * @author Ivan
 */
public class InterfazEstadisticaJugador extends JFrame
{

    private static final long serialVersionUID = 1655454656454L;
    private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public InterfazEstadisticaJugador()
	{
		setTitle("Mis Estad\u00EDsticas");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 500, 475);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblEstadisticas = new JLabel("Estad\u00EDsticas");
		lblEstadisticas.setForeground(Color.DARK_GRAY);
		lblEstadisticas.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 30));
		lblEstadisticas.setBounds(27, 22, 238, 41);
		contentPane.add(lblEstadisticas);
		
		JLabel lblNombreJugador = new JLabel("Nombre Jugador");
		lblNombreJugador.setForeground(SystemColor.textHighlight);
		lblNombreJugador.setFont(new Font("Times New Roman", Font.PLAIN, 26));
		lblNombreJugador.setBounds(134, 74, 262, 41);
		contentPane.add(lblNombreJugador);
		
		JLabel lblAvatarjugador = new JLabel("AvatarJugador");
		lblAvatarjugador.setBounds(17, 74, 107, 93);
		contentPane.add(lblAvatarjugador);
		
		JLabel lblPosicionRanking = new JLabel("Posici\u00F3n en el Ranking");
		lblPosicionRanking.setForeground(new Color(0, 153, 204));
		lblPosicionRanking.setFont(new Font("Snap ITC", Font.PLAIN, 16));
		lblPosicionRanking.setBounds(83, 150, 221, 23);
		contentPane.add(lblPosicionRanking);
		
		JLabel lblPuntaje = new JLabel("Puntaje");
		lblPuntaje.setForeground(new Color(0, 153, 204));
		lblPuntaje.setFont(new Font("Snap ITC", Font.PLAIN, 16));
		lblPuntaje.setBounds(83, 190, 201, 23);
		contentPane.add(lblPuntaje);
		
		JLabel lblCantidadGanados = new JLabel("Cantidad de Juegos Ganados");
		lblCantidadGanados.setForeground(new Color(0, 153, 204));
		lblCantidadGanados.setFont(new Font("Snap ITC", Font.PLAIN, 16));
		lblCantidadGanados.setBounds(83, 230, 292, 23);
		contentPane.add(lblCantidadGanados);
		
		JLabel lblCantidadDeJuegos = new JLabel("Cantidad de Juegos");
		lblCantidadDeJuegos.setForeground(new Color(0, 153, 204));
		lblCantidadDeJuegos.setFont(new Font("Snap ITC", Font.PLAIN, 16));
		lblCantidadDeJuegos.setBounds(83, 270, 201, 23);
		contentPane.add(lblCantidadDeJuegos);
		
		JLabel label = new JLabel("RZG - 2015");
		label.setForeground(SystemColor.textInactiveText);
		label.setBounds(419, 416, 63, 14);
		contentPane.add(label);
		
		// DEPENDIENDO DE LA POSICION CAMBIAR EL COLOR
		JLabel lblPosicionJugador = new JLabel("POS");
		lblPosicionJugador.setForeground(SystemColor.activeCaptionText);
		lblPosicionJugador.setHorizontalAlignment(SwingConstants.CENTER);
		lblPosicionJugador.setBounds(390, 156, 46, 14);
		contentPane.add(lblPosicionJugador);
		
		JLabel lblPuntajeJugador = new JLabel("PUNT");
		lblPuntajeJugador.setForeground(SystemColor.activeCaptionText);
		lblPuntajeJugador.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuntajeJugador.setBounds(390, 196, 46, 14);
		contentPane.add(lblPuntajeJugador);
		
		JLabel lblGanadosJugador = new JLabel("CANT");
		lblGanadosJugador.setForeground(SystemColor.activeCaptionText);
		lblGanadosJugador.setHorizontalAlignment(SwingConstants.CENTER);
		lblGanadosJugador.setBounds(390, 236, 46, 14);
		contentPane.add(lblGanadosJugador);
		
		JLabel lblJugadosJugador = new JLabel("CANT");
		lblJugadosJugador.setForeground(SystemColor.activeCaptionText);
		lblJugadosJugador.setHorizontalAlignment(SwingConstants.CENTER);
		lblJugadosJugador.setBounds(390, 276, 46, 14);
		contentPane.add(lblJugadosJugador);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(RutaImagen.get("imagenes/Fondos/zombie-est-jug.png")));
		lblFondo.setBounds(0, 0, 500, 475);
		contentPane.add(lblFondo);
	}

	/**
	 * TODO escribir este método.
	 * Actualiza la interfaz de estadísticas.
	 */
    public void actualizar() {
        Log.error("MÉTODO ACTUALIZAR NO IMPLEMENTADO!!!1UNO");
    }
}
