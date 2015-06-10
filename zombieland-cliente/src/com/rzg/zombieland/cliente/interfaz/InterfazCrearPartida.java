package com.rzg.zombieland.cliente.interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class InterfazCrearPartida extends JPanel
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 875007553477403006L;
/**
    * @author Ivan
    */
	private JTextField textFieldNombrePartida;
	private JTextField textFieldCantJugadores;
	private JTextField textFieldCantRondas;
	/**
	 * Create the frame.
	 */
	public InterfazCrearPartida() {
		setBounds(100, 100, 800, 600);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JLabel lblCreacinPartida = new JLabel("Personaliza tu Partida");
		lblCreacinPartida.setForeground(Color.LIGHT_GRAY);
		lblCreacinPartida.setFont(new Font("Verdana", Font.PLAIN, 26));
		lblCreacinPartida.setBounds(13, 28, 326, 44);
		add(lblCreacinPartida);
		
		JLabel lblLimiteJugadores = new JLabel("Cantidad de Jugadores:");
		lblLimiteJugadores.setForeground(Color.WHITE);
		lblLimiteJugadores.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblLimiteJugadores.setBounds(30, 170, 164, 14);
		add(lblLimiteJugadores);
				
		JLabel lblLimiteRondas = new JLabel("Cantidad de Rondas:");
		lblLimiteRondas.setForeground(Color.WHITE);
		lblLimiteRondas.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblLimiteRondas.setBounds(30, 230, 164, 14);
				add(lblLimiteRondas);
				
		JLabel lblNombrePartida = new JLabel("Nombre Partida:");
		lblNombrePartida.setForeground(Color.WHITE);
		lblNombrePartida.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblNombrePartida.setBounds(30, 110, 119, 14);
		add(lblNombrePartida);
				
				
		textFieldNombrePartida = new JTextField();
		textFieldNombrePartida.setForeground(Color.WHITE);
		textFieldNombrePartida.setBackground(Color.DARK_GRAY);
		textFieldNombrePartida.setBorder(new LineBorder(new Color(51, 102, 255), 2, true));
		textFieldNombrePartida.setOpaque(false);
		textFieldNombrePartida.setBounds(200, 110, 170, 20);
		add(textFieldNombrePartida);
		textFieldNombrePartida.setColumns(10);
				
		textFieldCantJugadores = new JTextField();
		textFieldCantJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCantJugadores.setOpaque(false);
		textFieldCantJugadores.setForeground(Color.WHITE);
		textFieldCantJugadores.setBackground(Color.DARK_GRAY);
		textFieldCantJugadores.setBorder(new LineBorder(new Color(51, 102, 255), 2, true));
		textFieldCantJugadores.setBounds(200, 170, 170, 20);
		add(textFieldCantJugadores);
		textFieldCantJugadores.setColumns(10);
				
		textFieldCantRondas = new JTextField();
		textFieldCantRondas.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldCantRondas.setOpaque(false);
		textFieldCantRondas.setForeground(Color.WHITE);
		textFieldCantRondas.setBackground(Color.DARK_GRAY);
		textFieldCantRondas.setBorder(new LineBorder(new Color(51, 102, 255), 2, true));
		textFieldCantRondas.setBounds(200, 230, 170, 20);
		add(textFieldCantRondas);
		textFieldCantRondas.setColumns(10);
				
		JButton btnAceptarCambios = new JButton("Aceptar");
		btnAceptarCambios.setBounds(50, 300, 175, 40);
		btnAceptarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.irA(Main.LOBBY);
			}
		});
		add(btnAceptarCambios);
				
		JButton btnCancelarCambios = new JButton("Cancelar");
		btnCancelarCambios.setBounds(250, 300, 175, 40);
		btnCancelarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.irA(Main.LISTADO_PARTIDAS);
			}
		});
		add(btnCancelarCambios);
		
		JLabel label = new JLabel("RZG - 2015");
		label.setForeground(SystemColor.textInactiveText);
		label.setBounds(637, 537, 63, 14);
		add(label);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon("/imagenes/zombie-fondo.png"));
		lblFondo.setBounds(0, 0, 944, 574);
		add(lblFondo);
	}
}