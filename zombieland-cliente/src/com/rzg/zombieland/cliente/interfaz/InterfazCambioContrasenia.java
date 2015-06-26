package com.rzg.zombieland.cliente.interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;

import org.jdeferred.DoneCallback;

import com.rzg.zombieland.cliente.comunicacion.ServicioCliente;
import com.rzg.zombieland.cliente.comunicacion.peticion.PeticionCambioPass;
import com.rzg.zombieland.cliente.misc.RutaImagen;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOCambioPass;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * 
 * @author Nicolas L
 *
 */
public class InterfazCambioContrasenia extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6846686861744486134L;
	private JPanel contentPane;
	private JPasswordField pass;
	private JPasswordField verificaPass;
	private String usuario;

	/**
	 * Create the frame.
	 */
	public InterfazCambioContrasenia(String usuario) {
		this.usuario = usuario;
		setTitle("Cambio de Contrase\u00F1a");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblIngreseNuevaContrasea = new JLabel(
				"Ingrese Nueva Contrase\u00F1a:");
		lblIngreseNuevaContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblIngreseNuevaContrasea.setForeground(Color.WHITE);
		lblIngreseNuevaContrasea.setBounds(22, 78, 200, 25);
		contentPane.add(lblIngreseNuevaContrasea);

		JLabel lblVerifiqueContrasea = new JLabel("Verifique Contrase\u00F1a:");
		lblVerifiqueContrasea.setForeground(Color.WHITE);
		lblVerifiqueContrasea.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblVerifiqueContrasea.setBounds(22, 133, 177, 31);
		contentPane.add(lblVerifiqueContrasea);

		pass = new JPasswordField();
		pass.setBounds(251, 80, 85, 20);
		contentPane.add(pass);

		verificaPass = new JPasswordField();
		verificaPass.setBounds(251, 138, 85, 20);
		contentPane.add(verificaPass);

		JButton btnCambiarContrasea = new JButton("Cambiar Contrase\u00F1a");
		btnCambiarContrasea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cambioContrasenia();
			}
		});
		btnCambiarContrasea.setBounds(131, 198, 155, 38);
		contentPane.add(btnCambiarContrasea);

		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(RutaImagen
				.get("imagenes/Fondos/zombie-est-jug.png")));
		lblFondo.setBounds(0, 0, 500, 475);
		contentPane.add(lblFondo);
	}

	public void cambioContrasenia() {
		if (!pass.getText().equals("")
				&& pass.getText().equals(verificaPass.getText())) {
			try {
				final POJOCambioPass pojo = new POJOCambioPass(usuario,
						pass.getText());
				PeticionCambioPass peticion = new PeticionCambioPass(pojo);
				ServicioCliente.enviarPeticion(peticion);

				peticion.getRespuesta().done(
						new DoneCallback<RespuestaGenerica>() {
							@Override
							public void onDone(RespuestaGenerica respuesta) {
								if (respuesta.fuePeticionExitosa()) {
									JOptionPane
											.showMessageDialog(
													contentPane,
													"La contraseña se modificó con éxito!",
													"Recuperar Contraseña",
													JOptionPane.WARNING_MESSAGE);
									dispose();
								} else
									JOptionPane.showMessageDialog(contentPane,
											respuesta.getMensajeError(),
											"Cambiar Contraseña Fallo",
											JOptionPane.WARNING_MESSAGE);
							}
						});

			} catch (ParametrosNoValidosException e) {
				JOptionPane.showMessageDialog(this, e.getMensaje(),
						"Recuperar Contraseña Fallo",
						JOptionPane.WARNING_MESSAGE);
			} catch (ZombielandException e) {
				JOptionPane
						.showMessageDialog(this, e.getMessage(),
								"Recuperar Contraseña Fallo",
								JOptionPane.ERROR_MESSAGE);
			}

		} else
			JOptionPane.showMessageDialog(this,
					"Ingresó una contraseña incorrecta.",
					"Recuperar Contraseña Fallo", JOptionPane.WARNING_MESSAGE);
		pass.setText("");
		verificaPass.setText("");
	}
}
