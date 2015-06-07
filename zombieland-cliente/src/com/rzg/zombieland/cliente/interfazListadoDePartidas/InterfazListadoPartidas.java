package com.rzg.zombieland.cliente.interfazListadoDePartidas;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Interfaz de Listado de Partidas
 * @author Ivan
 */

public class InterfazListadoPartidas extends JFrame
{

	private JPanel contentPane;
	private JTable tablaPartidas;

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
					InterfazListadoPartidas frame = new InterfazListadoPartidas();
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
	public InterfazListadoPartidas()
	{
		setTitle("Listado de Partidas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
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
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(UIManager.getColor("Panel.background"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnUnirse = new JButton("Unirse");
		btnUnirse.setBounds(288, 430, 175, 40);
		contentPane.add(btnUnirse);
		
		JButton btnCrearPartida = new JButton("Crear Partida");
		btnCrearPartida.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnCrearPartida.setBounds(39, 30, 175, 40);
		contentPane.add(btnCrearPartida);
		
		JButton btnJugar = new JButton("JUGAR");
		btnJugar.setBounds(77, 430, 175, 40);
		contentPane.add(btnJugar);
		
		JButton btnObservar = new JButton("Observar");
		btnObservar.setBounds(512, 430, 175, 40);
		contentPane.add(btnObservar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setToolTipText("");
		scrollPane.setBounds(66, 95, 631, 318);
		contentPane.add(scrollPane);
		
		tablaPartidas = new JTable();
		tablaPartidas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Administrador", "Cant. Jugadores", "Estado"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false, false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablaPartidas.getColumnModel().getColumn(0).setPreferredWidth(150);
		tablaPartidas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPartidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPartidas.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tablaPartidas);
		
		JLabel label = new JLabel("RZG - 2015");
		label.setForeground(SystemColor.textInactiveText);
		label.setBounds(702, 516, 110, 14);
		contentPane.add(label);
	}
}
