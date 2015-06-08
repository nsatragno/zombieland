package com.rzg.zombieland.cliente.interfazTablero;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero;
import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero.POJOEntidad;
import com.rzg.zombieland.comunes.misc.Coordenada;

/**
 * Interfaz de tablero.
 * 
 * @author Manuel
 */

public class TableroPrueba extends JFrame {

	private static ProyeccionTablero proyeccion = new ProyeccionTablero();
	private JFrame frame;
	private JTable table;
	private JPanel contentPane;
	private static int x = 40;
	private static int y = 60;
	private static int tamañoCasilleros = 40; // Es decir, casilleros de 20x20
	// Nótese que este valor será util para definir el movimiento de los jugadores,
	// el tamaño de la matriz y el tamaño de los avatars.
	private POJOEntidad matrizTablero[][]; // Matriz que representará a los elementos del tablero.

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setBounds(100, 100, 800, 600);

		JLabel label = new JLabel("");
		label.setBorder(new BevelBorder(BevelBorder.RAISED, Color.BLACK,
				Color.BLACK, Color.BLACK, Color.BLACK));
		label.setIcon(new ImageIcon(
				TableroPrueba.class
						.getResource("/com/rzg/zombieland/cliente/interfazTablero/Pasto.png")));
		label.setBounds(25, 25, proyeccion.getAncho(), proyeccion.getLargo());
		getContentPane().add(label);
		
		matrizTablero = 
				new POJOEntidad [proyeccion.getAncho()/tamañoCasilleros][proyeccion.getLargo()/tamañoCasilleros];
		matrizTablero[0][0] = proyeccion.new POJOEntidad("Player 1",proyeccion.getEsqSupIzq()); // Posicion inicial
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (y - tamañoCasilleros >= proyeccion.getEsqSupIzq().getY()) {
					matrizTablero[x/tamañoCasilleros - 1][y/tamañoCasilleros - 1] = null; //Java se encarga
					y -= tamañoCasilleros;
					matrizTablero[x/tamañoCasilleros - 1][y/tamañoCasilleros - 1] = 
							proyeccion.new POJOEntidad("Player 1",new Coordenada(x,y));
					repaint();
				}
			}
		});

		button.setIcon(new ImageIcon(
				TableroPrueba.class
						.getResource("/com/rzg/zombieland/cliente/interfazTablero/FlechaArriba.png")));
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setBounds(640, 377, 45, 45);
		getContentPane().add(button);

//		addKeyListener(new KeyAdapter() {
//			@Override
//			public void keyPressed(KeyEvent e) {
//				if (e.getKeyCode() == KeyEvent.VK_0) {
//					button.doClick();
//				}
//			}
//		});
		
		JButton button_1 = new JButton("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (x - tamañoCasilleros >= proyeccion.getEsqSupIzq().getX()) {
					matrizTablero[x/tamañoCasilleros - 1][y/tamañoCasilleros - 1] = null;
					x -= tamañoCasilleros;
					matrizTablero[x/tamañoCasilleros - 1][y/tamañoCasilleros - 1] = 
							proyeccion.new POJOEntidad("Player 1",new Coordenada(x,y));
					repaint();
				}
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
				if (x + tamañoCasilleros <= proyeccion.getEsqInfDer().getX() - tamañoCasilleros) {
					matrizTablero[x/tamañoCasilleros - 1][y/tamañoCasilleros - 1] = null;
					x += tamañoCasilleros;
					matrizTablero[x/tamañoCasilleros - 1][y/tamañoCasilleros - 1] = 
							proyeccion.new POJOEntidad("Player 1",new Coordenada(x,y));
					repaint();
				}
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
				if (y + tamañoCasilleros <= proyeccion.getEsqInfDer().getY() - tamañoCasilleros) {
					matrizTablero[x/tamañoCasilleros - 1][y/tamañoCasilleros - 1] = null;
					y += tamañoCasilleros;
					matrizTablero[x/tamañoCasilleros - 1][y/tamañoCasilleros - 1] = 
							proyeccion.new POJOEntidad("Player 1",new Coordenada(x,y));
					repaint();
				}
			}
		});
		button_3.setIcon(new ImageIcon(
				TableroPrueba.class
						.getResource("/com/rzg/zombieland/cliente/interfazTablero/FlechaAbajo.png")));
		button_3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button_3.setBounds(640, 471, 45, 45);
		getContentPane().add(button_3);

		JPanel panelJug = new JPanel();
		panelJug.setBounds(563, 46, 193, 175);
		getContentPane().add(panelJug);
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

	}
	
	public void paint(Graphics g)  // SE ENCARGA DE DIBUJAR LA PROYECCION. LLAMAR AL METODO PAINTPROYECCION.
	{
		super.paint(g);
		Image img = new ImageIcon(
				TableroPrueba.class.getResource("/com/rzg/zombieland/"
						+ "cliente/interfazTablero/poli.png")).getImage();
		g.drawImage(img, x, y, tamañoCasilleros, tamañoCasilleros, null);
	}
}
