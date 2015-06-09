package com.rzg.zombieland.cliente.interfaz;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jdeferred.DoneCallback;

import com.rzg.zombieland.cliente.comunicacion.PeticionRegistro;
import com.rzg.zombieland.cliente.comunicacion.ServicioCliente;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJORegistro;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaRegistro;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Interfaz de registro de usuario.
 * 
 * @author Manuel
 */

public class InterfazRegistro extends JPanel {

    private static final long serialVersionUID = 3234547685088239182L;

    private final JTextField nombreDeUsuario = new JTextField();
    private final JPasswordField password = new JPasswordField();
    private JPasswordField verificacionPassword;
    private JTextField respuestaSeguridad;

    private JComboBox<String> preguntaSeguridad;

    private JLabel imagenCargando;

    /**
     * Create the application.
     */
    public InterfazRegistro() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        setLayout(null);
        JLabel label = new JLabel("");
        label.setIcon(new ImageIcon("imagenes/zombie.png"));
        label.setBounds(690, 214, 129, 353);
        add(label);

        JButton btnRegistrar = new JButton("Registrar!");
        btnRegistrar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                registrarJugador();
            }
        });
        btnRegistrar.setBounds(20, 387, 175, 40);
        add(btnRegistrar);

        JButton btnVolverAlInicio = new JButton("Volver al inicio de sesi\u00F3n");
        btnVolverAlInicio.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                Main.irA(Main.INICIO_SESION);
            }
        });
        btnVolverAlInicio.setBounds(20, 473, 175, 40);
        add(btnVolverAlInicio);

        JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario:");
        lblNombreDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNombreDeUsuario.setBounds(10, 23, 160, 26);
        add(lblNombreDeUsuario);
        
        ImageIcon icon = new ImageIcon("imagenes/loading.gif");
        imagenCargando = new JLabel(icon);
        imagenCargando.setText("Cargando...");
        imagenCargando.setBounds(100, 387, 400, 40);
        imagenCargando.setVisible(false);
        add(imagenCargando);

        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPassword.setBounds(10, 58, 147, 18);
        add(lblPassword);

        JLabel lblConfirmaPassword = new JLabel("Confirma Password:");
        lblConfirmaPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblConfirmaPassword.setBounds(10, 85, 160, 26);
        add(lblConfirmaPassword);

        JLabel lblPreguntaDeSeguridad = new JLabel("Pregunta de seguridad");
        lblPreguntaDeSeguridad.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPreguntaDeSeguridad.setBounds(10, 122, 205, 26);
        add(lblPreguntaDeSeguridad);

        JLabel lblRespuestaDeSeguridad = new JLabel("Respuesta de seguridad:");
        lblRespuestaDeSeguridad.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblRespuestaDeSeguridad.setBounds(10, 160, 181, 26);
        add(lblRespuestaDeSeguridad);
        nombreDeUsuario.setFont(new Font("Tahoma", Font.PLAIN, 16));
        nombreDeUsuario.setBounds(225, 23, 157, 21);
        add(nombreDeUsuario);
        nombreDeUsuario.setColumns(10);
        password.setFont(new Font("Tahoma", Font.PLAIN, 16));
        password.setBounds(225, 56, 157, 21);
        add(password);

        verificacionPassword = new JPasswordField();
        verificacionPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        verificacionPassword.setBounds(225, 89, 157, 21);
        add(verificacionPassword);

        preguntaSeguridad = new JComboBox<String>();
        preguntaSeguridad.setModel(new DefaultComboBoxModel<String>(new String[] { "",
                "Cual es su color favorito?", "Mejor amigo de la infancia?",
                "A que escuela primaria fue?", "Nombre de su primer mascota?" }));
        preguntaSeguridad.setBounds(225, 127, 191, 20);
        add(preguntaSeguridad);

        respuestaSeguridad = new JTextField();
        respuestaSeguridad.setFont(new Font("Tahoma", Font.PLAIN, 16));
        respuestaSeguridad.setBounds(225, 164, 157, 21);
        add(respuestaSeguridad);
        respuestaSeguridad.setColumns(10);

        JLabel lblRzg = new JLabel("RZG - 2015");
        lblRzg.setForeground(SystemColor.controlShadow);
        lblRzg.setBounds(709, 526, 63, 14);
        add(lblRzg);
    }

    /**
     * Registra a un jugador según los datos dados.
     */
    private void registrarJugador() {
        if (!Arrays.equals(password.getPassword(), verificacionPassword.getPassword())) {
            JOptionPane.showMessageDialog(getParent(), "Las claves no son iguales");
            return;
        }

        try {
            POJORegistro pojoRegistro;
            pojoRegistro = new POJORegistro(nombreDeUsuario.getText(), new String(
                    password.getPassword()), (String) preguntaSeguridad.getSelectedItem(),
                    respuestaSeguridad.getText());
            PeticionRegistro peticion = new PeticionRegistro(pojoRegistro);
            ServicioCliente.getInstancia().getHiloEscucha().enviarPeticion(peticion);
            imagenCargando.setVisible(true);
            peticion.getRespuesta().then(new DoneCallback<RespuestaRegistro>() {
				@Override
				public void onDone(RespuestaRegistro respuesta) {
					manejarRespuestaRegistro(respuesta);
				};
            });
        } catch (ParametrosNoValidosException e) {
            JOptionPane.showMessageDialog(getParent(), e.getMensaje(), "Registro Zombieland",
                    JOptionPane.WARNING_MESSAGE);
        } catch (ZombielandException e) {
            JOptionPane.showMessageDialog(getParent(), e.getMessage(), "Registro Zombieland",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Maneja una respuesta de registro.
     * 
     * @param registro
     */
    private void manejarRespuestaRegistro(RespuestaRegistro registro) {
        imagenCargando.setVisible(false);
        if (registro.fuePeticionExitosa()) {
            JOptionPane.showMessageDialog(getParent(), "Registro exitoso", "Registro Zombieland",
                    JOptionPane.INFORMATION_MESSAGE);
            Main.irA(Main.INICIO_SESION);
            return;
        }
        // TODO enviar mensaje de error desde el server.
        JOptionPane.showMessageDialog(getParent(),
                "No se pudo realizar el registro: " + registro.getMensajeError(),
                "Registro Zombieland", JOptionPane.INFORMATION_MESSAGE);

    }

}
