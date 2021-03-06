package com.rzg.zombieland.cliente.interfaz;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.rzg.zombieland.cliente.comunicacion.ServicioCliente;
import com.rzg.zombieland.comunes.comunicacion.HiloEscucha;
import com.rzg.zombieland.comunes.comunicacion.HiloListener;

/**
 * 
 */
public class Main extends JFrame implements HiloListener {

    /**
     * Pantalla en la que se establece la conexi�n con el servidor.
     */
    public static final String CONEXION = "conexion";
    
	/**
	 * Pantalla de registro de jugador. 
	 */
	public static final String REGISTRO = "registro";
	
	/**
	 * Pantalla de inicio de sesi�n.
	 */
	public static final String INICIO_SESION = "inicioSesion";
	
	/**
	 * Pantalla de listado de partidas.
	 */
	public static final String LISTADO_PARTIDAS = "listadoPartidas";
	
	/**
	 * Pantalla en la que los jugadores esperan que inicie la partida.
	 */
	public static final String LOBBY = "lobby";
	
	/**
	 *  Pantalla de tablero.
	 */
	public static final String TABLERO = "tablero";
	
	/**
	 * Pantalla de creaci�n de nueva partida.
	 */
	public static final String CREAR_PARTIDA = "crearPartida";
	
	/**
     * Pantalla de ranking del top de jugadores.
     */
    public static final String RANKING_GENERAL = "rankingGeneral";
	
    private static final long serialVersionUID = -8977109460614792967L;
	
    // Mantiene todas las pantallas del workflow principal en la misma ventana.
	private CardLayout cardLayout;

	// El frame principal de la aplicaci�n.
	private static Main frame;
	
	/**
	 * Launch the application.
	 * @param args 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new Main();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Navega a la pantalla dada.
	 * @param pantalla - obtener a partir de las constantes definidas en esta clase.
	 */
	public static void irA(final String pantalla) {
	    EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                frame.cardLayout.show(frame.getContentPane(), pantalla);
            }
        });
		
	}

	/**
	 * Create the frame.
	 */
	public Main() {
		setResizable(false);
		setTitle("Zombieland");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		JPanel contentPane = new JPanel();
		cardLayout = new CardLayout();
		contentPane.setLayout(cardLayout);
		contentPane.add(new InterfazConexionServidor(), CONEXION);
		contentPane.add(new InterfazInicioSesion(), INICIO_SESION);
		contentPane.add(new InterfazRegistro(), REGISTRO);
		contentPane.add(new InterfazListadoPartidas(), LISTADO_PARTIDAS);
		InterfazLobby interfazLobby = new InterfazLobby();
		contentPane.add(interfazLobby, LOBBY);
		contentPane.add(new InterfazTablero(), TABLERO);
		contentPane.add(new InterfazCrearPartida(), CREAR_PARTIDA);
		contentPane.add(new InterfazRankingGeneral(), RANKING_GENERAL);
		setJMenuBar(new MenuZombieland());
		setContentPane(contentPane);
		ServicioCliente.setListener(this);
		interfazLobby.setBotonEnviarMensajeComoDefault();
	}

    @Override
    public void hiloCerrado(HiloEscucha hilo) {
        JOptionPane.showMessageDialog(this,
                                      "Se perdi� la conexi�n con el servidor", 
                                      "Zombieland",
                                      JOptionPane.ERROR_MESSAGE);
        irA(CONEXION);
    }
}
