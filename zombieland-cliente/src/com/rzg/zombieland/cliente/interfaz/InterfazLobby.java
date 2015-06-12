package com.rzg.zombieland.cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Interfaz de Lobby.
 * @author Ivan
 */

public class InterfazLobby extends JPanel
{
	private static final long serialVersionUID = 6954796419653149772L;
	private JTable tableParametros;
	private JTable tableJugadores;
	/**
	 * Create the frame.
	 */
	public InterfazLobby()
	{
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JLabel lblRecomendacion1 = new JLabel("- El juego comenzar\u00E1 cuando la cantidad de jugadores requerida esten en l\u00EDnea.");
		lblRecomendacion1.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRecomendacion1.setBounds(320, 89, 497, 20);
		lblRecomendacion1.setVerticalAlignment(SwingConstants.TOP);
		add(lblRecomendacion1);
		
		JLabel lblRecomendacion2 = new JLabel("- El juego consistir\u00E1 de las rondas indicadas por el administrador.");
		lblRecomendacion2.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRecomendacion2.setBounds(320, 114, 386, 14);
		add(lblRecomendacion2);
		
		JLabel lblRecomendacion3 = new JLabel("- El zombi busca humanos, el humano escapa de los zombies.");
		lblRecomendacion3.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRecomendacion3.setBounds(320, 139, 389, 14);
		add(lblRecomendacion3);
		
		JLabel lblRecomendacion4 = new JLabel("- Evita los obst\u00E1culos!");
		lblRecomendacion4.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRecomendacion4.setBounds(320, 164, 389, 14);
		add(lblRecomendacion4);
		
		JLabel lblRecomendacion5 = new JLabel("- Los turnos son de 5 segundos cada uno, as\u00ED que pensa tu movimiento r\u00E1pido!");
		lblRecomendacion5.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRecomendacion5.setBounds(320, 189, 466, 14);
		add(lblRecomendacion5);
		
		JLabel lblRecomendacion6 = new JLabel("- Si un humano es convertido la nueva tarea del jugador va a ser atrapar a los humanos.");
		lblRecomendacion6.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRecomendacion6.setBounds(320, 214, 497, 14);
		add(lblRecomendacion6);
		
		JLabel lblRecomendacion7 = new JLabel("- Una ronda termina cuando todos los humanos fueron convertidos a zombies.");
		lblRecomendacion7.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRecomendacion7.setBounds(320, 264, 466, 14);
		add(lblRecomendacion7);
		
		JLabel lblRecomendacion8 = new JLabel("- El juego termina cuando todas las rondas fueron terminadas.");
		lblRecomendacion8.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRecomendacion8.setBounds(320, 289, 415, 14);
		add(lblRecomendacion8);
		
		JLabel lblRecomendacion9 = new JLabel("- Acordate que tenes la opci\u00F3n de no hacer ning\u00FAn movimiento.");
		lblRecomendacion9.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblRecomendacion9.setBounds(320, 239, 402, 14);
		add(lblRecomendacion9);
		
		JLabel lblTitulo = new JLabel("TITULO PARTIDA");
		lblTitulo.setBounds(10, 11, 595, 67);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 36));
		add(lblTitulo);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 87, 300, 271);
		panel.setLayout(new BorderLayout());
		// El BorderLayout es para poder poner luego los encabezados de columna sin la necesidad
		// de usar un JScrollPane.
		add(panel);
		
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
		panelJug.setBounds(10, 389, 164, 125);
		panelJug.setLayout(new BorderLayout());
		add(panelJug);
		
		tableJugadores = new JTable();
		tableJugadores.setRowSelectionAllowed(false);
		tableJugadores.setGridColor(Color.BLACK);
		tableJugadores.setBorder(new LineBorder(new Color(0, 0, 0)));
		tableJugadores.setRowHeight(20);
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
		
		JLabel label = new JLabel("RZG - 2015");
		label.setForeground(SystemColor.textInactiveText);
		label.setBounds(700, 515, 63, 14);
		add(label);
		
		JLabel lblZombie = new JLabel("");
		lblZombie.setIcon(new ImageIcon("imagenes/zombie2.png"));
		lblZombie.setBounds(681, 199, 176, 408);
		add(lblZombie);
	}
}
