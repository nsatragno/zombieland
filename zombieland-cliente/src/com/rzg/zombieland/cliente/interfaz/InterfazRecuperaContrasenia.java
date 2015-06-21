package com.rzg.zombieland.cliente.interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jdeferred.DoneCallback;

import com.rzg.zombieland.cliente.comunicacion.ServicioCliente;
import com.rzg.zombieland.cliente.comunicacion.peticion.PeticionInicioSesion;
import com.rzg.zombieland.cliente.comunicacion.peticion.PeticionPreguntaSeguridad;
import com.rzg.zombieland.cliente.meta.Estado;
import com.rzg.zombieland.cliente.misc.RutaImagen;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOInicioSesion;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJONombreUsuario;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaPreguntaSeg;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Interfaz de recuperar contraseña.
 * 
 * @author Ivan
 */

public class InterfazRecuperaContrasenia extends JFrame
{
    private static final long serialVersionUID = 5811662849518666164L;
    private JPanel contentPane;
	private JTextField textFieldUsuario;
	private JLabel preguntaSeguridad;
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
		
		preguntaSeguridad = new JLabel("");
		preguntaSeguridad.setBackground(Color.BLACK);
		preguntaSeguridad.setOpaque(false);
		preguntaSeguridad.setForeground(Color.WHITE);
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
    	btnRecuperar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				RecuperarPreguntaSeguridad();
			}
		});
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
        lblFondo.setIcon(new ImageIcon(RutaImagen.get("imagenes/Fondos/fondo-contraseña.png")));
        lblFondo.setBounds(-400, 0, 925, 386);
        contentPane.add(lblFondo);
	}
	/**
	 * Ingresa el nombre de usuario y debe encontrar la pregunta de seguridad
	 * correspondiente y su respuesta.
	 */
	public void RecuperarPreguntaSeguridad() {
		preguntaSeguridad.setText(" ");
		try {
			final POJONombreUsuario pojo = new POJONombreUsuario(
					textFieldUsuario.getText());
			PeticionPreguntaSeguridad peticion = new PeticionPreguntaSeguridad(pojo);
			ServicioCliente.enviarPeticion(peticion);
			final InterfazRecuperaContrasenia _this = this;
			
			peticion.getRespuesta().done(new DoneCallback<RespuestaPreguntaSeg>() {
				@Override
				public void onDone(RespuestaPreguntaSeg respuesta) {
					if (respuesta.fuePeticionExitosa()) {
						preguntaSeguridad.setText(
								respuesta.getPreguntaSeguridad().getPreguntaSeguridad());
						return;
					}
					JOptionPane.showMessageDialog(_this,
							respuesta.getMensajeError(),
							"Recuperar Contraseña Fallo",
							JOptionPane.WARNING_MESSAGE);
				}
			});	
			
		} catch (ParametrosNoValidosException e) {
			JOptionPane.showMessageDialog(this, e.getMensaje(),
					"Recuperar Contraseña Fallo", JOptionPane.WARNING_MESSAGE);
		} catch (ZombielandException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),
					"Recuperar Contraseña Fallo", JOptionPane.ERROR_MESSAGE);
		}
	}
	
}
