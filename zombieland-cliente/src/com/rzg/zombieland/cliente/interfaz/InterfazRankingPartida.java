package com.rzg.zombieland.cliente.interfaz;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.rzg.zombieland.cliente.misc.RutaImagen;

public class InterfazRankingPartida {

	private JFrame frmRanking;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazRankingPartida window = new InterfazRankingPartida();
					window.frmRanking.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InterfazRankingPartida() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmRanking = new JFrame();
		frmRanking.setTitle("Ranking");
		frmRanking.setBounds(100, 100, 800, 600);
		frmRanking.setResizable(false);
		frmRanking.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRanking.getContentPane().setLayout(null);
		
		JLabel lblPartida = new JLabel("Nombre_Partida");
		lblPartida.setBackground(Color.WHITE);
		lblPartida.setForeground(Color.BLACK);
		lblPartida.setFont(new Font("Trebuchet MS", Font.PLAIN, 36));
		lblPartida.setBounds(10, 0, 774, 81);
		frmRanking.getContentPane().add(lblPartida);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(RutaImagen.get("imagenes/fondoRankPartida.png")));
		label.setBounds(10, 335, 800, 255);
		frmRanking.getContentPane().add(label);
		
		table = new JTable();
		table.setEnabled(false);
		table.setShowVerticalLines(false);
		table.setBackground(new Color(192, 192, 192));
		table.setBorder(new EmptyBorder(0, 0, 0, 0));
		table.setShowHorizontalLines(false);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setForeground(Color.BLACK);
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Nombre de usuario", "Puntaje"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class<?>[] columnTypes = new Class[] {
				String.class, Integer.class
			};
			public Class<?> getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setPreferredWidth(570);
		table.setBounds(48, 144, 679, 160);
		frmRanking.getContentPane().add(table);
		
		JLabel lblNombreDeUsuario = new JLabel("Nombre de usuario");
		lblNombreDeUsuario.setBounds(48, 120, 159, 14);
		frmRanking.getContentPane().add(lblNombreDeUsuario);
		
		JLabel lblPuntaje = new JLabel("Puntaje");
		lblPuntaje.setBounds(651, 119, 76, 14);
		frmRanking.getContentPane().add(lblPuntaje);
		
		JButton btnRegresar = new JButton("Regresar");
		btnRegresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Volver a la ronda.
			}
		});
		btnRegresar.setBounds(386, 325, 182, 50);
		frmRanking.getContentPane().add(btnRegresar);
	}
}
