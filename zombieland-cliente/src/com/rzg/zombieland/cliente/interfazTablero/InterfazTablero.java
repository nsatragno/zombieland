package com.rzg.zombieland.cliente.interfazTablero;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

/**
 * Interfaz de tablero.
 * @author Manuel
 */

public class InterfazTablero extends JPanel{

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InterfazTablero window = new InterfazTablero();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	
	public InterfazTablero() {
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */

	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("");
		label.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK, Color.BLACK, Color.BLACK, Color.BLACK));
		label.setIcon(new ImageIcon(InterfazTablero.class.getResource("/com/rzg/zombieland/cliente/interfazTablero/Pasto.png")));
		label.setBounds(33, 25, 500, 509);
		frame.getContentPane().add(label);
		
		JButton button = new JButton("");
		button.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_UP){
					// MOVER ARRIBA
				}
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// MOVER ARRIBA - Mismo efecto si apreto la tecla o hago clic en el boton.
			}
		});
		button.setIcon(new ImageIcon(InterfazTablero.class.getResource("/com/rzg/zombieland/cliente/interfazTablero/FlechaArriba.png")));
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setBounds(640, 395, 45, 45);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// MOVER IZQUIERDA
			}
		});
		button_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_LEFT) {
					// MOVER IZQUIERDA
				}
			}
		});
		button_1.setIcon(new ImageIcon(InterfazTablero.class.getResource("/com/rzg/zombieland/cliente/interfazTablero/FlechaIzquierda.png")));
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_1.setBounds(587, 441, 45, 45);
		frame.getContentPane().add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// MOVER DERECHA
			}
		});
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// MOVER DERECHA
			}
		});
		button_2.setIcon(new ImageIcon(InterfazTablero.class.getResource("/com/rzg/zombieland/cliente/interfazTablero/FlechaDerecha.png")));
		button_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_2.setBounds(689, 441, 45, 45);
		frame.getContentPane().add(button_2);
		
		JButton button_3 = new JButton("");
		button_3.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_DOWN) {
					// MOVER ABAJO
				}
			}
		});
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// MOVER ABAJO
			}
		});
		button_3.setIcon(new ImageIcon(InterfazTablero.class.getResource("/com/rzg/zombieland/cliente/interfazTablero/FlechaAbajo.png")));
		button_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_3.setBounds(640, 489, 45, 45);
		frame.getContentPane().add(button_3);
		
		JPanel panelJug = new JPanel();
		panelJug.setBounds(563, 46, 193, 175);
		frame.getContentPane().add(panelJug);
		panelJug.setLayout(new BorderLayout());
		
		table = new JTable();
		table.setGridColor(Color.BLACK);
		table.setRowHeight(30);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null},
				{null, null},
				{null, null},
				{null, null},
				{null, null},
			},
			new String[] {
				"Personaje", "Puntaje"
			}
		) {
			Class[] columnTypes = new Class[] {
				String.class, Object.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
			boolean[] columnEditables = new boolean[] {
				false, false
			};
			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(0).setMinWidth(50);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.setBounds(0, 0, 193, 304);
		panelJug.add(table.getTableHeader(), BorderLayout.NORTH);
		panelJug.add(table, BorderLayout.CENTER);
	}
}
