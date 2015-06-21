package com.rzg.zombieland.cliente.interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
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
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import org.jdeferred.DoneCallback;

import com.rzg.zombieland.cliente.comunicacion.ServicioCliente;
import com.rzg.zombieland.cliente.comunicacion.peticion.PeticionCreacionPartida;
import com.rzg.zombieland.cliente.meta.Estado;
import com.rzg.zombieland.cliente.misc.RutaImagen;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOCreacionPartida;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.ParametrosNoValidosException;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * UI de la creación de partida.
 */
public class InterfazCrearPartida extends JPanel
{
   /**
	 * 
	 */
	private static final long serialVersionUID = 875007553477403006L;
/**
    * @author Ivan
    */
	private JTextField nombrePartida;
	private JTextField cantidadJugadores;
	private JTextField cantidadRondas;
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
				
				
		nombrePartida = new JTextField();
		nombrePartida.setForeground(Color.WHITE);
		nombrePartida.setBackground(Color.DARK_GRAY);
		nombrePartida.setBorder(new LineBorder(new Color(51, 102, 255), 2, true));
		nombrePartida.setOpaque(false);
		nombrePartida.setBounds(200, 110, 170, 20);
		add(nombrePartida);
		nombrePartida.setColumns(10);
				
		cantidadJugadores = new JTextField();
		cantidadJugadores.setHorizontalAlignment(SwingConstants.CENTER);
		cantidadJugadores.setOpaque(false);
		cantidadJugadores.setForeground(Color.WHITE);
		cantidadJugadores.setBackground(Color.DARK_GRAY);
		cantidadJugadores.setBorder(new LineBorder(new Color(51, 102, 255), 2, true));
		cantidadJugadores.setBounds(200, 170, 170, 20);
		add(cantidadJugadores);
		cantidadJugadores.setColumns(10);
				
		cantidadRondas = new JTextField();
		cantidadRondas.setHorizontalAlignment(SwingConstants.CENTER);
		cantidadRondas.setOpaque(false);
		cantidadRondas.setForeground(Color.WHITE);
		cantidadRondas.setBackground(Color.DARK_GRAY);
		cantidadRondas.setBorder(new LineBorder(new Color(51, 102, 255), 2, true));
		cantidadRondas.setBounds(200, 230, 170, 20);
		add(cantidadRondas);
		cantidadRondas.setColumns(10);
				
		JButton btnAceptarCambios = new JButton("Aceptar");
		btnAceptarCambios.setBounds(50, 300, 175, 40);
		btnAceptarCambios.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			    crearPartida();
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
		label.setBounds(700, 515, 63, 14);
		add(label);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(RutaImagen.get("imagenes/Fondos/zombie-fondo.png")));
		lblFondo.setBounds(0, 0, 944, 574);
		add(lblFondo);
	}
	
	/**
	 * Realiza la petición de creación de partida al servidor.
	 */
	public void crearPartida() {
	    try {
	        final POJOCreacionPartida pojo = 
	                new POJOCreacionPartida(Integer.parseInt(cantidadRondas.getText()),
	                                        Integer.parseInt(cantidadJugadores.getText()),
	                                        nombrePartida.getText());
	        PeticionCreacionPartida peticion = new PeticionCreacionPartida(pojo);
	        ServicioCliente.enviarPeticion(peticion);
	        final Component this_ = this;
	        peticion.getRespuesta().done(new DoneCallback<RespuestaGenerica>() {
                
                @Override
                public void onDone(RespuestaGenerica respuesta) {
                    if (respuesta.fuePeticionExitosa()) {
                        POJOPartida estadoLobby = 
                                new POJOPartida(pojo, Estado.getInstancia().getNombreJugador());
                        Estado.getInstancia().setEstadoLobby(estadoLobby);
                        Estado.getInstancia().setEspectador(false);
                        Main.irA(Main.LOBBY);
                        return;
                    }
                    JOptionPane.showMessageDialog(this_,
                            respuesta.getMensajeError(),
                            "Creación de partida",
                            JOptionPane.ERROR_MESSAGE);
                }
            });
	    } catch (NumberFormatException e) {
	        JOptionPane.showMessageDialog(this,
	                                      "Los datos no son válidos",
	                                      "Creación de partida",
	                                      JOptionPane.WARNING_MESSAGE);
	    } catch (ParametrosNoValidosException e) {
	        JOptionPane.showMessageDialog(this,
                    e.getMensaje(),
                    "Creación de partida",
                    JOptionPane.WARNING_MESSAGE);
        } catch (ZombielandException e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(),
                    "Creación de partida",
                    JOptionPane.ERROR_MESSAGE);
        }
	}
}