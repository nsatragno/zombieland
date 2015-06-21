package com.rzg.zombieland.cliente.interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.HashMap;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jdeferred.DoneCallback;

import com.rzg.zombieland.cliente.comunicacion.ServicioCliente;
import com.rzg.zombieland.cliente.comunicacion.peticion.PeticionObtenerDatosJugador;
import com.rzg.zombieland.cliente.meta.Estado;
import com.rzg.zombieland.cliente.misc.RutaImagen;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJORegistro;
import com.rzg.zombieland.comunes.misc.ZombielandException;
import com.rzg.zombieland.comunes.misc.Avatar;

import javax.swing.SwingConstants;

/**
 * 
 * 
 * @author Nicolas L
 *
 */
public class InterfazCambioDeDatosUsuario extends JFrame implements WindowListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4342197949176383612L;
	private JPanel contentPane;
	private JTextField textUsuario;
	private JTextField textRta;
	private ButtonGroup Avatar = new ButtonGroup();
	private JPasswordField pass;
	private JPasswordField passVerificacion;
	private JComboBox <String> preguntaSeguridad;
	private JRadioButton rdbtnPJ1;
	private JRadioButton rdbtnPJ2;
	private JRadioButton rdbtnPJ3;

	/**
	 * Create the frame.
	 */
	public InterfazCambioDeDatosUsuario() {
		setResizable(false);
		setTitle("Cambiar Datos De Usuario");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		addWindowListener(this);

		JLabel lblNombreDeUsuario = new JLabel("Nombre de Usuario:");
		lblNombreDeUsuario.setForeground(Color.WHITE);
		lblNombreDeUsuario.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombreDeUsuario.setBounds(27, 62, 220, 22);
		contentPane.add(lblNombreDeUsuario);

		JLabel lblContrasea = new JLabel("Nueva Contrase\u00F1a:");
		lblContrasea.setForeground(Color.WHITE);
		lblContrasea.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblContrasea.setBounds(27, 150, 220, 22);
		contentPane.add(lblContrasea);

		JLabel lblVerificacionDeContrasea = new JLabel(
				"Verificacion de Contrase\u00F1a:");
		lblVerificacionDeContrasea.setForeground(Color.WHITE);
		lblVerificacionDeContrasea.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblVerificacionDeContrasea.setBounds(27, 183, 261, 22);
		contentPane.add(lblVerificacionDeContrasea);

		JLabel lblPreguntaDeSeguridad = new JLabel("Pregunta de Seguridad:");
		lblPreguntaDeSeguridad.setForeground(Color.WHITE);
		lblPreguntaDeSeguridad.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblPreguntaDeSeguridad.setBounds(27, 272, 220, 22);
		contentPane.add(lblPreguntaDeSeguridad);

		JLabel lblRespuestaDeSeguridad = new JLabel("Respuesta de Seguridad:");
		lblRespuestaDeSeguridad.setForeground(Color.WHITE);
		lblRespuestaDeSeguridad.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRespuestaDeSeguridad.setBounds(27, 305, 220, 22);
		contentPane.add(lblRespuestaDeSeguridad);

		textUsuario = new JTextField("");
		textUsuario.setHorizontalAlignment(SwingConstants.CENTER);
		textUsuario.setBorder(new LineBorder(Color.BLACK));
		textUsuario.setForeground(Color.WHITE);
		textUsuario.setOpaque(false);
		textUsuario.setBounds(312, 65, 160, 20);
		contentPane.add(textUsuario);
		textUsuario.setColumns(10);
		
		pass = new JPasswordField();
		pass.setBorder(new LineBorder(Color.BLACK));
		pass.setForeground(Color.WHITE);
		pass.setOpaque(false);
		pass.setToolTipText("");
		pass.setBounds(312, 153, 160, 20);
		contentPane.add(pass);

		// pass.setToolTipText("Mas de 4 digitos.");

		passVerificacion = new JPasswordField("");
		passVerificacion.setBorder(new LineBorder(Color.BLACK));
		passVerificacion.setForeground(Color.WHITE);
		passVerificacion.setOpaque(false);
		passVerificacion.setBounds(313, 186, 159, 20);
		contentPane.add(passVerificacion);
		
		preguntaSeguridad = new JComboBox<String>();
        preguntaSeguridad.setBorder(new LineBorder(new Color(51, 153, 51)));
        preguntaSeguridad.setForeground(Color.WHITE);
        preguntaSeguridad.setBackground(Color.BLACK);
        preguntaSeguridad.setModel(Estado.preguntas);
        preguntaSeguridad.setBounds(312, 275, 225, 20);
        getContentPane().add(preguntaSeguridad);

		textRta = new JTextField("");
		textRta.setHorizontalAlignment(SwingConstants.CENTER);
		textRta.setBorder(new LineBorder(Color.BLACK));
		textRta.setForeground(Color.WHITE);
		textRta.setOpaque(false);
		textRta.setBounds(312, 306, 160, 20);
		contentPane.add(textRta);
		textRta.setColumns(10);

		JButton btnModificar = new JButton("Modificar Datos");
		btnModificar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Aqui se realizan los cambios efectuados
				//
			}
		});
		btnModificar.setBounds(62, 456, 175, 40);
		contentPane.add(btnModificar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancelar.setBounds(548, 456, 175, 40);
		contentPane.add(btnCancelar);

		JLabel lblNewLabel = new JLabel("Seleccione Avatar:");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setBounds(554, 22, 220, 22);
		contentPane.add(lblNewLabel);
		
		JLabel lblAvatarPoli = new JLabel("");
		lblAvatarPoli
				.setIcon(new ImageIcon(RutaImagen.get("imagenes/Avatares/avatar-poli.png")));
		lblAvatarPoli.setBounds(695, 76, 79, 80);
		contentPane.add(lblAvatarPoli);

		rdbtnPJ1 = new JRadioButton("");
		rdbtnPJ1.setOpaque(false);
		rdbtnPJ1.setBounds(630, 97, 67, 54);
		contentPane.add(rdbtnPJ1);
		
		JLabel lblAvatarDsn = new JLabel("");
		lblAvatarDsn.setIcon(new ImageIcon(RutaImagen.get("imagenes/Avatares/avatar-dsn.png")));
		lblAvatarDsn.setBounds(695, 194, 73, 94);
		contentPane.add(lblAvatarDsn);

		rdbtnPJ2 = new JRadioButton("");
		rdbtnPJ2.setOpaque(false);
		rdbtnPJ2.setBounds(630, 220, 67, 54);
		contentPane.add(rdbtnPJ2);
		
		JLabel lblAvatarMujer = new JLabel("");
		lblAvatarMujer
				.setIcon(new ImageIcon(RutaImagen.get("imagenes/Avatares/avatar-mujer.png")));
		lblAvatarMujer.setBounds(695, 341, 73, 74);
		contentPane.add(lblAvatarMujer);

		rdbtnPJ3 = new JRadioButton("");
		rdbtnPJ3.setOpaque(false);
		rdbtnPJ3.setBounds(630, 357, 67, 54);
		contentPane.add(rdbtnPJ3);

		// Agrego los rdbtn al grupo de botones, para que solo se pueda
		// seleccionar uno.
		Avatar.add(rdbtnPJ1);
		Avatar.add(rdbtnPJ2);
		Avatar.add(rdbtnPJ3);
		
		JLabel label_1 = new JLabel("RZG - 2015");
		label_1.setForeground(SystemColor.textInactiveText);
		label_1.setBounds(700, 515, 63, 14);
		contentPane.add(label_1);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(-500, 0, 1300, 600);
		lblFondo.setIcon(new ImageIcon(RutaImagen.get("imagenes/Fondos/fondo-cambio-datos.png")));
		getContentPane().add(lblFondo);

	}
	
	private void cambiarDatos(){
		
	}

	@Override
	public void windowActivated(WindowEvent arg0)
	{
		try {
			PeticionObtenerDatosJugador peticion = new PeticionObtenerDatosJugador();
			ServicioCliente.getInstancia().getHiloEscucha().enviarPeticion(peticion);
			peticion.getRespuesta().then(new DoneCallback<POJORegistro> () {
				@Override
				public void onDone(POJORegistro datos){
					textUsuario.setText(datos.getNombre());
					pass.setText(datos.getClave());
					passVerificacion.setText(datos.getClave());
					//TODO avatar
					int indice = 0;
					boolean encontro = false;
					while(!encontro &&  indice < preguntaSeguridad.getItemCount()) {
						if(datos.getPreguntaSecreta().
								equals(preguntaSeguridad.getItemAt(indice)))
							encontro = true;
						else indice++;
					}
					preguntaSeguridad.setSelectedIndex(indice);
					textRta.setText(datos.getRespuestaSecreta());
					HashMap<Avatar, JRadioButton> mapa = new HashMap<Avatar, JRadioButton>();
					mapa.put(Avatar.POLICIA, rdbtnPJ1);
					mapa.put(Avatar.HOMBRE, rdbtnPJ2);
					mapa.put(Avatar.MUJER, rdbtnPJ3);
				}
			});
		} catch (ZombielandException e1) {
			
		}
		
	}

	@Override
	public void windowClosed(WindowEvent e)
	{
		textUsuario.setText("");
		pass.setText("");
		passVerificacion.setText("");
		preguntaSeguridad.setSelectedIndex(-1);
		textRta.setText("");
		
	}

	@Override
	public void windowClosing(WindowEvent e)
	{
		
	}

	@Override
	public void windowDeactivated(WindowEvent e)
	{
		
	}

	@Override
	public void windowDeiconified(WindowEvent e)
	{
		
	}

	@Override
	public void windowIconified(WindowEvent e)
	{
		
	}

	@Override
	public void windowOpened(WindowEvent e)
	{
		
	}
}
