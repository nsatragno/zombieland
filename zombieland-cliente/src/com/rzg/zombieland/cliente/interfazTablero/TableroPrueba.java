package com.rzg.zombieland.cliente.interfazTablero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero;
import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero.POJOEntidad;
import com.rzg.zombieland.comunes.misc.Avatar;
import com.rzg.zombieland.comunes.misc.Coordenada;
import javax.swing.JLabel;

/**
 * Interfaz de tablero.
 * 
 * @author Manuel
 */

public class TableroPrueba extends JFrame {

	private static final long serialVersionUID = 1L;
	// Creo este objeto para poder crear objetos del tipo POJOEntidad
	// y armar la lista para construir la proyeccion de prueba.
	private static ProyeccionTablero proyeccion = new ProyeccionTablero();
	private ProyeccionTablero proyeccionPrueba;

	private JFrame frame;
	private JTable table;
	private JPanel contentPane;

	// Constantes
	private static final int DIMENSION = 500; // Dimension en pixeles del
												// tablero
	private static final int MARGEN_IZQUIERDO = 35;
	private static final int MARGEN_SUPERIOR = 55;
	private static int CASILLEROS = 10; // Casilleros del tablero a lo alto y a
										// lo ancho.

	// Este boolean evita repintar todas las veces el fondo.
	private boolean primeraVez = true;

	// variables de prueba para el movimiento.
	private int coordenadaX;
	private int coordenadaY;

	private Image[] img; // Avatares
	private ImageIcon fondo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TableroPrueba frame = new TableroPrueba();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */

	public TableroPrueba() {
		setTitle("Zombieland - Tablero de Juego");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setBounds(100, 100, 800, 600);

		img = new Image[4];
		for (int i = 0; i < 4; i++) {
			img[i] = new ImageIcon(
					TableroPrueba.class.getResource("/com/rzg/zombieland/"
							+ "cliente/interfazTablero/avatar" + (i + 1)
							+ ".png")).getImage();

		}

		fondo = new ImageIcon("imagenes/pasto.png");

		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Movimiento hacia arriba.
				if (coordenadaY - 1 >= 0)
					coordenadaY--;
				repaint();
			}
		});

		button.setIcon(new ImageIcon(
				TableroPrueba.class
						.getResource("/com/rzg/zombieland/cliente/interfazTablero/FlechaArriba.png")));
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setBounds(640, 377, 45, 45);
		getContentPane().add(button);

		// addKeyListener(new KeyAdapter() {
		// @Override
		// public void keyPressed(KeyEvent e) {
		// if (e.getKeyCode() == KeyEvent.VK_0) {
		// button.doClick();
		// }
		// }
		// });

		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Movimiento hacia la izquierda
				if (coordenadaX - 1 >= 0)
					coordenadaX--;
				repaint();
			}
		});
		// button_1.addKeyListener(new KeyAdapter() {
		// @Override
		// public void keyPressed(KeyEvent e) {
		// if (e.getKeyCode() == KeyEvent.VK_LEFT) {
		// x -= tamañoCasilleros;
		// repaint();
		// }
		// }
		// });
		button_1.setIcon(new ImageIcon(
				TableroPrueba.class
						.getResource("/com/rzg/zombieland/cliente/interfazTablero/FlechaIzquierda.png")));
		button_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_1.setBounds(587, 423, 45, 45);
		getContentPane().add(button_1);

		JButton button_2 = new JButton("");
		// button_2.addKeyListener(new KeyAdapter() {
		// @Override
		// public void keyPressed(KeyEvent e) {
		// x += tamañoCasilleros;
		// repaint();
		// }
		// });
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Movimiento hacia la derecha
				if (coordenadaX + 1 <= CASILLEROS - 1)
					coordenadaX++;
				repaint();
			}
		});
		button_2.setIcon(new ImageIcon(
				TableroPrueba.class
						.getResource("/com/rzg/zombieland/cliente/interfazTablero/FlechaDerecha.png")));
		button_2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_2.setBounds(689, 423, 45, 45);
		getContentPane().add(button_2);

		JButton button_3 = new JButton("");
		// button_3.addKeyListener(new KeyAdapter() {
		// @Override
		// public void keyPressed(KeyEvent e) {
		// if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		// y += tamañoCasilleros;
		// repaint();
		// }
		// }
		// });
		button_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Movimiento hacia abajo
				if (coordenadaY + 1 <= CASILLEROS - 1)
					coordenadaY++;
				repaint();
			}
		});
		button_3.setIcon(new ImageIcon(
				TableroPrueba.class
						.getResource("/com/rzg/zombieland/cliente/interfazTablero/FlechaAbajo.png")));
		button_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_3.setBounds(640, 471, 45, 45);
		getContentPane().add(button_3);

		JPanel panelJug = new JPanel();
		panelJug.setBounds(561, 34, 193, 175);
		getContentPane().add(panelJug);
		panelJug.setLayout(new BorderLayout());

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setGridColor(Color.BLACK);
		table.setRowHeight(31);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null }, },
				new String[] { "Personaje", "Puntaje" }) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] { String.class, Object.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}

			boolean[] columnEditables = new boolean[] { false, false };

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
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("imagenes/fondo1.png"));
		label.setBounds(0, 11, 784, 589);
		contentPane.add(label);
	}

	public void paint(Graphics g) {
		super.paint(g);
		java.util.List<POJOEntidad> entidades = new ArrayList<POJOEntidad>();
		entidades.add(proyeccion.new POJOEntidad("Player1", new Coordenada(
				coordenadaX, coordenadaY), Avatar.HOMBRE));
		entidades.add(proyeccion.new POJOEntidad("Player2",
				new Coordenada(7, 6), Avatar.MUJER));
		Coordenada esquinaSupIzq = new Coordenada(
				coordenadaX - 2 >= 0 ? coordenadaX - 2 : 0,
				coordenadaY - 2 >= 0 ? coordenadaY - 2 : 0);
		Coordenada esquinaInfDer = new Coordenada(
				coordenadaX + 3 <= CASILLEROS ? coordenadaX + 3 : CASILLEROS,
				coordenadaY + 3 <= CASILLEROS ? coordenadaY + 3 : CASILLEROS);
		ProyeccionTablero proyeccionPrueba = new ProyeccionTablero(CASILLEROS,
				esquinaSupIzq, esquinaInfDer, entidades);
		proyeccionPrueba.paint(g, img, DIMENSION, MARGEN_IZQUIERDO,
				MARGEN_SUPERIOR, fondo);
	}
}
