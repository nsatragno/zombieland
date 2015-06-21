package com.rzg.zombieland.cliente.interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import com.rzg.zombieland.cliente.comunicacion.ServicioCliente;
import com.rzg.zombieland.cliente.misc.RutaImagen;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Interfaz que lanza un HiloEscucha según parámetros de usuario.
 * @author nicolas
 *
 */
public class InterfazConexionServidor extends JPanel {
    private static final long serialVersionUID = -7187972946308372118L;

    // Entrada para el host.
    private JTextField host;
	
	// Entrada para el puerto.
	private JTextField puerto;
	
	// Con este botón se conecta al servidor.
	private JButton botonConectar;
	private JLabel label;
	
	/**
	 * Create the application.
	 */
	public InterfazConexionServidor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		setLayout(null);
		
		JLabel titulo = new JLabel("Conectar al servidor");
		titulo.setForeground(Color.WHITE);
		titulo.setBounds(25, 24, 227, 40);
		titulo.setFont(new Font("tahoma", Font.BOLD,20));
		add(titulo);
		
		JLabel lblPuerto = new JLabel ("Puerto:");
		lblPuerto.setBounds(35, 87, 80, 30);
		lblPuerto.setHorizontalAlignment(SwingConstants.CENTER);
		lblPuerto.setForeground(Color.WHITE);
		add(lblPuerto);
		
		puerto = new JTextField("2048");
		puerto.setHorizontalAlignment(SwingConstants.CENTER);
		puerto.setBorder(new LineBorder(Color.DARK_GRAY));
		puerto.setForeground(Color.WHITE);
		puerto.setBackground(Color.BLACK);
		puerto.setBounds(135, 87, 117, 30);
		puerto.setColumns(10);
		add(puerto);
		
		JLabel lblHost = new JLabel ("Host:");
		lblHost.setBounds(35, 120, 80, 30);
		lblHost.setHorizontalAlignment(SwingConstants.CENTER);
		lblHost.setForeground(Color.WHITE);
		add(lblHost);
		
		host = new JTextField("localhost");
		host.setHorizontalAlignment(SwingConstants.CENTER);
		host.setBorder(new LineBorder(Color.DARK_GRAY));
		host.setBackground(Color.BLACK);
		host.setForeground(Color.WHITE);
		host.setBounds(135, 120, 117, 30);
        host.setColumns(10);
        add(host);
		
		botonConectar = new JButton("Conectar");
		botonConectar.setBounds(80, 200, 120, 30);
		botonConectar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				conectar();
			}
		});
		add(botonConectar);
		
		label = new JLabel("RZG - 2015");
		label.setForeground(SystemColor.textInactiveText);
		label.setBounds(700, 515, 63, 14);
		add(label);
	}
	
	/**
	 * Arranca y detiene al servidor.
	 */
	private void conectar() {
	    try {
	        ServicioCliente.crearInstancia(Integer.parseInt(puerto.getText()), host.getText());
	        MenuZombieland.setConexcionServidor(true);
	        Main.irA(Main.INICIO_SESION);
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this,
	                                      "El puerto no es válido",
	                                      "Conexión Zombieland",
	                                      JOptionPane.WARNING_MESSAGE);
	    } catch (ZombielandException e) {
	        JOptionPane.showMessageDialog(this,
                                          e.getMessage(),
                                          "Conexión Zombieland",
                                          JOptionPane.ERROR_MESSAGE);
	    }
	}
	
	@Override
	public void paint (Graphics g){
		ImageIcon imagenFondo = new ImageIcon(RutaImagen.get("imagenes/Fondos/fondoServidor.png"));
		g.drawImage(imagenFondo.getImage(), 0, -35, 800, 600, null);
		setOpaque(false);
		super.paint(g);
	}
}
