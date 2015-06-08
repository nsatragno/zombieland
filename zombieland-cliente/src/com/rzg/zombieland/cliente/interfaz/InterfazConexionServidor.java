package com.rzg.zombieland.cliente.interfaz;

import java.awt.Font;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.rzg.zombieland.cliente.comunicacion.ServicioCliente;
import com.rzg.zombieland.comunes.misc.ZombielandException;

import net.miginfocom.swing.MigLayout;

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
		botonConectar = new JButton("Conectar");
		botonConectar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				conectar();
			}
		});
		
		Label titulo = new Label("Conectar al servidor");
		titulo.setFont(new Font("tahoma", Font.BOLD,20));
		
		puerto = new JTextField("2048");
		puerto.setColumns(10);
		
		host = new JTextField("localhost");
        host.setColumns(10);
		
		setLayout(new MigLayout("", "push[align center][align center][align center]push", "push[][][]push"));
		add(titulo, "span 2, wrap");
		add(new Label("Host"));
		add(host, "wrap");
		add(new Label("Puerto"));
		add(puerto, "wrap");
		add(botonConectar, "aligny center, span 4");
	}
	
	/**
	 * Arranca y detiene al servidor.
	 */
	private void conectar() {
	    try {
	        ServicioCliente.crearInstancia(Integer.parseInt(puerto.getText()), host.getText());
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
}
