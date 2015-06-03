package com.rzg.zombieland.cliente.interfazLobby;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

/**
 * Interfaz de Lobby.
 * @author Ivan
 */

public class InterfazLobby extends JFrame
{

	private JPanel contentPane;
	private JTable tableParametros;
	private JTable tableJugadores;
	private JTextField textFieldNombrePartida;
	private JTextField textFieldCantJugadores;
	private JTextField textFieldCantRondas;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					InterfazLobby frame = new InterfazLobby();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InterfazLobby()
	{
		setTitle("Lobby");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(50, 50, 1200, 1000);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnArchivo = new JMenu("Archivo");
		menuBar.add(mnArchivo);
		
		JMenuItem mntmIniciarSesion = new JMenuItem("Iniciar Sesion");
		mnArchivo.add(mntmIniciarSesion);
		
		JMenuItem mntmRegistrarse = new JMenuItem("Registrarse");
		mnArchivo.add(mntmRegistrarse);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mnArchivo.add(mntmSalir);
		
		JMenu mnPartida = new JMenu("Partida");
		menuBar.add(mnPartida);
		
		JMenuItem mntmJugar = new JMenuItem("Jugar");
		mnPartida.add(mntmJugar);
		
		JMenuItem mntmVerPartidas = new JMenuItem("Ver Partidas");
		mnPartida.add(mntmVerPartidas);
		
		JMenu mnCuenta = new JMenu("Cuenta");
		menuBar.add(mnCuenta);
		
		JMenuItem mntmDatos = new JMenuItem("Datos");
		mnCuenta.add(mntmDatos);
		
		JMenuItem mntmEstadsticas = new JMenuItem("Estad\u00EDsticas");
		mnCuenta.add(mntmEstadsticas);
		
		JMenu mnAyuda = new JMenu("Ayuda");
		menuBar.add(mnAyuda);
		
		JMenuItem mntmCmoJugar = new JMenuItem("C\u00F3mo Jugar");
		mnAyuda.add(mntmCmoJugar);
		
		JMenuItem mntmGoogleame = new JMenuItem("Googleame");
		mnAyuda.add(mntmGoogleame);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRecomendacion1 = new JLabel("- El juego comenzar\u00E1 cuando la cantidad de jugadores requerida esten en l\u00EDnea.");
		lblRecomendacion1.setBounds(320, 109, 497, 28);
		lblRecomendacion1.setVerticalAlignment(SwingConstants.TOP);
		contentPane.add(lblRecomendacion1);
		
		JLabel lblRecomendacion2 = new JLabel("- El juego consistir\u00E1 de las rondas indicadas por el administrador.");
		lblRecomendacion2.setBounds(320, 135, 386, 14);
		contentPane.add(lblRecomendacion2);
		
		JLabel lblRecomendacion3 = new JLabel("- El zombi busca humanos, el humano escapa de los zombies.");
		lblRecomendacion3.setBounds(320, 160, 389, 14);
		contentPane.add(lblRecomendacion3);
		
		JLabel lblRecomendacion4 = new JLabel("- Evita los obst\u00E1culos!");
		lblRecomendacion4.setBounds(320, 185, 389, 14);
		contentPane.add(lblRecomendacion4);
		
		JLabel lblRecomendacion5 = new JLabel("- Los turnos son de 5 segundos cada uno, as\u00ED que pensa tu movimiento r\u00E1pido!");
		lblRecomendacion5.setBounds(320, 210, 466, 14);
		contentPane.add(lblRecomendacion5);
		
		JLabel lblRecomendacion6 = new JLabel("- Si un humano es convertido la nueva tarea del jugador va a ser atrapar a los humanos.");
		lblRecomendacion6.setBounds(320, 235, 497, 14);
		contentPane.add(lblRecomendacion6);
		
		JLabel lblRecomendacion7 = new JLabel("- Una ronda termina cuando todos los humanos fueron convertidos a zombies.");
		lblRecomendacion7.setBounds(320, 285, 466, 14);
		contentPane.add(lblRecomendacion7);
		
		JLabel lblRecomendacion8 = new JLabel("- El juego termina cuando todas las rondas fueron terminadas.");
		lblRecomendacion8.setBounds(320, 310, 415, 14);
		contentPane.add(lblRecomendacion8);
		
		JLabel lblRecomendacion9 = new JLabel("- Acordate que tenes la opci\u00F3n de no hacer ning\u00FAn movimiento.");
		lblRecomendacion9.setBounds(320, 260, 402, 14);
		contentPane.add(lblRecomendacion9);
		
		JLabel lblTitulo = new JLabel("TITULO PARTIDA");
		lblTitulo.setBounds(10, 11, 595, 67);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 36));
		contentPane.add(lblTitulo);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 87, 300, 271);
		panel.setLayout(new BorderLayout());
		// El BorderLayout es para poder poner luego los encabezados de columna sin la necesidad
		// de usar un JScrollPane.
		contentPane.add(panel);
		
		tableParametros = new JTable();
		tableParametros.setGridColor(Color.BLACK);
		tableParametros.setRowSelectionAllowed(false);
		tableParametros.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableParametros.setModel(new DefaultTableModel(
			new Object[][] {
				{"Administrador", null},
				{"Cantidad de Jugadores", null},
				{"Cantidad requerida", null},
				{"Cantidad de Rondas", null},
				{"Estado", null},
			},
			new String[] {
				"", "Par\u00E1metros Juego"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableParametros.getColumnModel().getColumn(0).setPreferredWidth(140);
		tableParametros.getColumnModel().getColumn(0).setMinWidth(140);
		tableParametros.getColumnModel().getColumn(1).setPreferredWidth(225);
		tableParametros.getColumnModel().getColumn(1).setMinWidth(200);
		tableParametros.setBounds(0, 50, 290, 250);
		tableParametros.setRowHeight(50);
		tableParametros.getTableHeader().setReorderingAllowed(false);
		//Cuando agregamos la tabla aregamos por separado los encabezados y los datos por otra parte.
		//El constructor usado cambia y se le indica la posicion de cada elemento que tendra.
		panel.add(tableParametros.getTableHeader(), BorderLayout.NORTH);
		panel.add(tableParametros, BorderLayout.CENTER);
		
		JPanel panelJug = new JPanel();
		panelJug.setBounds(10, 434, 164, 266);
		panelJug.setLayout(new BorderLayout());
		contentPane.add(panelJug);
		
		tableJugadores = new JTable();
		tableJugadores.setGridColor(Color.BLACK);
		tableJugadores.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableJugadores.setRowHeight(50);
		tableJugadores.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"JUGADORES"
			}
		) {
			boolean[] columnEditables = new boolean[] {
				false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tableJugadores.getColumnModel().getColumn(0).setPreferredWidth(100);
		tableJugadores.getColumnModel().getColumn(0).setMinWidth(100);
		tableJugadores.setBounds(10, 434, 164, 50);
		panelJug.add(tableJugadores.getTableHeader(), BorderLayout.NORTH);
		panelJug.add(tableJugadores, BorderLayout.CENTER);
		
		// ESTO DEBERIA SER SOLO VISIBLE PARA EL ADMINISTRADOR DE LA PARTIDA
		
		JLabel lblLimiteJugadores = new JLabel("Cantidad de Jugadores:");
		lblLimiteJugadores.setForeground(Color.DARK_GRAY);
		lblLimiteJugadores.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblLimiteJugadores.setBounds(831, 172, 164, 14);
		contentPane.add(lblLimiteJugadores);
		
		JLabel lblLimiteRondas = new JLabel("Cantidad de Rondas:");
		lblLimiteRondas.setForeground(Color.DARK_GRAY);
		lblLimiteRondas.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblLimiteRondas.setBounds(831, 210, 164, 14);
		contentPane.add(lblLimiteRondas);
		
		JLabel lblNombrePartida = new JLabel("Nombre Partida:");
		lblNombrePartida.setForeground(Color.DARK_GRAY);
		lblNombrePartida.setFont(new Font("Sylfaen", Font.PLAIN, 16));
		lblNombrePartida.setBounds(831, 135, 119, 14);
		contentPane.add(lblNombrePartida);
		
		
		JLabel lblCreacinPartida = new JLabel("Creaci\u00F3n Partida");
		lblCreacinPartida.setFont(new Font("Verdana", Font.PLAIN, 26));
		lblCreacinPartida.setBounds(833, 48, 217, 51);
		contentPane.add(lblCreacinPartida);
		
		textFieldNombrePartida = new JTextField();
		textFieldNombrePartida.setBounds(1022, 132, 170, 20);
		contentPane.add(textFieldNombrePartida);
		textFieldNombrePartida.setColumns(10);
		
		textFieldCantJugadores = new JTextField();
		textFieldCantJugadores.setBounds(1022, 169, 170, 20);
		contentPane.add(textFieldCantJugadores);
		textFieldCantJugadores.setColumns(10);
		
		textFieldCantRondas = new JTextField();
		textFieldCantRondas.setBounds(1022, 207, 170, 20);
		contentPane.add(textFieldCantRondas);
		textFieldCantRondas.setColumns(10);
		
		JButton btnAceptarCambios = new JButton("Aceptar");
		btnAceptarCambios.setBounds(884, 276, 89, 23);
		contentPane.add(btnAceptarCambios);
		
		JButton btnCancelarCambios = new JButton("Cancelar");
		btnCancelarCambios.setBounds(1052, 276, 89, 23);
		contentPane.add(btnCancelarCambios);
		
		// TERMINA PARTE DE CREAR PARTIDA
		
		JLabel label = new JLabel("RZG - 2015");
		label.setForeground(SystemColor.textInactiveText);
		label.setBounds(1200, 697, 63, 14);
		contentPane.add(label);
		
		JLabel lblZombie = new JLabel("");
		lblZombie.setIcon(new ImageIcon(InterfazLobby.class.getResource("/com/rzg/zombieland/cliente/interfazLobby/zombie.png")));
		lblZombie.setBounds(900, 11, 401, 783);
		contentPane.add(lblZombie);
	}
}
