package com.rzg.zombieland.cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero;
import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero.POJOEntidad;
import com.rzg.zombieland.comunes.misc.Avatar;
import com.rzg.zombieland.comunes.misc.Coordenada;

/**
 * Interfaz de tablero.
 * 
 * @author Manuel
 */

public class InterfazTablero extends JPanel {

	private static final long serialVersionUID = 1L;
	// Creo este objeto para poder crear objetos del tipo POJOEntidad
	// y armar la lista para construir la proyeccion de prueba.
	private static ProyeccionTablero proyeccion = new ProyeccionTablero();
	@SuppressWarnings("unused")
	private ProyeccionTablero proyeccionPrueba;
	
	private JTable table;

	// Constantes
	private static final int DIMENSION = 500; // Dimension en pixeles del
												// tablero
	private static final int MARGEN_IZQUIERDO = 10;
	private static final int MARGEN_SUPERIOR = 30;
	private static int CASILLEROS = 10; // Casilleros del tablero a lo alto y a
										// lo ancho.

	// variables de prueba para el movimiento.
	private int coordenadaX;
	private int coordenadaY;

	private Image[] img; // Avatares
	private ImageIcon fondo;

	public InterfazTablero() {
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		setBounds(100, 100, 800, 600);

		img = new Image[4];
		for (int i = 0; i < 4; i++) {
			img[i] = new ImageIcon("imagenes/avatar" + (i + 1) + ".png").getImage();

		}

		fondo = new ImageIcon("imagenes/pasto.png");

		JButton moveUp = new JButton("");
		moveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// Movimiento hacia arriba.
				if (coordenadaY - 1 >= 0)
					coordenadaY--;
				repaint();
			}
		});

		moveUp.setIcon(new ImageIcon("imagenes/FlechaArriba.png"));
		moveUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		moveUp.setBounds(640, 377, 45, 45);
		add(moveUp);

		// addKeyListener(new KeyAdapter() {
		// @Override
		// public void keyPressed(KeyEvent e) {
		// if (e.getKeyCode() == KeyEvent.VK_0) {
		// button.doClick();
		// }
		// }
		// });

		JButton moveLeft = new JButton("");
		moveLeft.addActionListener(new ActionListener() {
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
		moveLeft.setIcon(new ImageIcon("imagenes/FlechaIzquierda.png"));
		moveLeft.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		moveLeft.setBounds(587, 423, 45, 45);
		add(moveLeft);

		JButton moveRight = new JButton("");
		// button_2.addKeyListener(new KeyAdapter() {
		// @Override
		// public void keyPressed(KeyEvent e) {
		// x += tamañoCasilleros;
		// repaint();
		// }
		// });
		moveRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Movimiento hacia la derecha
				if (coordenadaX + 1 <= CASILLEROS - 1)
					coordenadaX++;
				repaint();
			}
		});
		moveRight.setIcon(new ImageIcon("imagenes/FlechaDerecha.png"));
		moveRight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		moveRight.setBounds(689, 423, 45, 45);
		add(moveRight);

		JButton moveDown = new JButton("");
		// button_3.addKeyListener(new KeyAdapter() {
		// @Override
		// public void keyPressed(KeyEvent e) {
		// if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		// y += tamañoCasilleros;
		// repaint();
		// }
		// }
		// });
		moveDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Movimiento hacia abajo
				if (coordenadaY + 1 <= CASILLEROS - 1)
					coordenadaY++;
				repaint();
			}
		});
		moveDown.setIcon(new ImageIcon("imagenes/FlechaAbajo.png"));
		moveDown.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		moveDown.setBounds(640, 471, 45, 45);
		add(moveDown);
		
		JLabel label = new JLabel("RZG - 2015");
		label.setForeground(SystemColor.textInactiveText);
		label.setBounds(700, 515, 63, 14);
		add(label);

		JPanel panelJug = new JPanel();
		panelJug.setBounds(561, 34, 193, 175);
		add(panelJug);
		panelJug.setLayout(new BorderLayout());

		table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setGridColor(Color.BLACK);
		table.setRowHeight(30);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(new DefaultTableModel(
				new Object[][] { { null, null }, { null, null },
						{ null, null }, { null, null }, { null, null }, },
				new String[] { "Personaje", "Puntaje" }) {
			private static final long serialVersionUID = 1L;
			@SuppressWarnings("rawtypes")
			Class[] columnTypes = new Class[] { String.class, Object.class };

			@SuppressWarnings({ "unchecked", "rawtypes" })
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
		
		JLabel labelFondo = new JLabel("");
		labelFondo.setIcon(new ImageIcon("imagenes/fondo1.png"));
		labelFondo.setBounds(0, 0, 800, 600);
		add(labelFondo);
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
