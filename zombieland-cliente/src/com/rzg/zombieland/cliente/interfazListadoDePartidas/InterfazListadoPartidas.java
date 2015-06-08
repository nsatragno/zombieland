package com.rzg.zombieland.cliente.interfazListadoDePartidas;

import java.awt.Color;
import java.awt.SystemColor;

import javax.swing.JButton;
import javax.swing.JLabel;
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
public class InterfazListadoPartidas extends JPanel
{

	private static final long serialVersionUID = -7079211493379843872L;
	private JTable tablaPartidas;
	/**
	 * Create the frame.
	 */
	public InterfazListadoPartidas()
	{
		setForeground(Color.WHITE);
		setBackground(UIManager.getColor("Panel.background"));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JButton btnUnirse = new JButton("Unirse");
		btnUnirse.setBounds(288, 430, 175, 40);
		add(btnUnirse);
		
		JButton btnCrearPartida = new JButton("Crear Partida");
		btnCrearPartida.setBackground(UIManager.getColor("Button.disabledForeground"));
		btnCrearPartida.setBounds(39, 30, 175, 40);
		add(btnCrearPartida);
		
		JButton btnJugar = new JButton("JUGAR");
		btnJugar.setBounds(77, 430, 175, 40);
		add(btnJugar);
		
		JButton btnObservar = new JButton("Observar");
		btnObservar.setBounds(512, 430, 175, 40);
		add(btnObservar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setToolTipText("");
		scrollPane.setBounds(66, 95, 631, 318);
		add(scrollPane);
		
		tablaPartidas = new JTable();
		tablaPartidas.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Nombre", "Administrador", "Cant. Jugadores", "Estado"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -6605453439839848288L;
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
		add(label);
	}
}
