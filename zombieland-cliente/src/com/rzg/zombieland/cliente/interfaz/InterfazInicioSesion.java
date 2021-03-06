package com.rzg.zombieland.cliente.interfaz;

import java.awt.EventQueue;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jdeferred.DoneCallback;

import com.rzg.zombieland.cliente.comunicacion.ServicioCliente;
import com.rzg.zombieland.cliente.comunicacion.peticion.PeticionInicioSesion;
import com.rzg.zombieland.cliente.meta.Estado;
import com.rzg.zombieland.cliente.misc.RutaImagen;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOInicioSesion;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Interfaz completa de inicio de sesi�n.
 * 
 * @author Manuel
 */

public class InterfazInicioSesion extends JPanel {

	private static final long serialVersionUID = 1L;
	private JTextField fieldUsuario;
	private JPasswordField fieldPassword;
	private InterfazRecuperaContrasenia recupera;

	/**
	 * Create the application.
	 */
	public InterfazInicioSesion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setLayout(null);
		JLabel lblUsuario = new JLabel("Usuario:");
		lblUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsuario.setBounds(251, 192, 92, 42);
		add(lblUsuario);

		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(251, 246, 110, 17);
		add(lblPassword);

		JButton btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login();
			}
		});
		btnIngresar.setBounds(184, 292, 175, 40);
		add(btnIngresar);

		fieldUsuario = new JTextField();
		fieldUsuario.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldUsuario.setBounds(389, 203, 139, 20);
		add(fieldUsuario);
		fieldUsuario.setColumns(10);

		fieldPassword = new JPasswordField();
		fieldPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		fieldPassword.setBounds(389, 244, 139, 20);
		add(fieldPassword);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(RutaImagen
				.get("imagenes/zombieland.png")));
		lblNewLabel.setBounds(325, 11, 159, 179);
		add(lblNewLabel);

		JLabel lblMsg = new JLabel("No tenes un usuario?");
		lblMsg.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblMsg.setBounds(251, 379, 200, 37);
		add(lblMsg);

		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Main.irA(Main.REGISTRO);
			}
		});
		btnRegistrarse.setBounds(445, 398, 175, 40);
		add(btnRegistrarse);

		JLabel lblRzg = new JLabel("RZG - 2015");
		lblRzg.setForeground(SystemColor.controlShadow);
		lblRzg.setBounds(700, 515, 110, 14);
		add(lblRzg);

		JLabel lblUniteAZombieland = new JLabel("Unite a Zombieland!");
		lblUniteAZombieland.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUniteAZombieland.setBounds(251, 416, 219, 37);
		add(lblUniteAZombieland);

		JButton btnO = new JButton("Olvid\u00F3 su clave?");
		btnO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (recupera == null)
					recupera = new InterfazRecuperaContrasenia();
				EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        recupera.setVisible(true);
                    }
                });
			}
		});
		btnO.setBounds(445, 292, 175, 40);
		add(btnO);
	}

	/**
	 * Realiza el inicio de sesi�n y muestra los carteles apropiados.
	 */
	public void login() {
		try {
			final POJOInicioSesion pojo = new POJOInicioSesion(
					fieldUsuario.getText(), new String(
							fieldPassword.getPassword()));
			PeticionInicioSesion peticion = new PeticionInicioSesion(pojo);
			ServicioCliente.enviarPeticion(peticion);
			final InterfazInicioSesion _this = this;
			peticion.getRespuesta().done(new DoneCallback<RespuestaGenerica>() {
				@Override
				public void onDone(RespuestaGenerica respuesta) {
					if (respuesta.fuePeticionExitosa()) {
						Estado.getInstancia()
								.setNombreJugador(pojo.getNombre());
						MenuZombieland.setInicioSesion(true);
						Main.irA(Main.LISTADO_PARTIDAS);
						return;
					}
					JOptionPane.showMessageDialog(_this,
							respuesta.getMensajeError(),
							"Inicio sesi�n Zombieland",
							JOptionPane.WARNING_MESSAGE);
				}
			});

		} catch (ParametrosNoValidosException e) {
			JOptionPane.showMessageDialog(this, e.getMensaje(),
					"Inicio sesi�n Zombieland", JOptionPane.WARNING_MESSAGE);
		} catch (ZombielandException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),
					"Inicio sesi�n Zombieland", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void paint(Graphics g) {
		ImageIcon imagenFondo = new ImageIcon(
				RutaImagen.get("imagenes/Fondos/fondo-inicio-sesion.png"));
		g.drawImage(imagenFondo.getImage(), 0, -50, 800, 600, null);
		setOpaque(false);
		super.paint(g);
	}
}
