package com.rzg.zombieland.cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import org.jdeferred.DoneCallback;

import com.rzg.zombieland.cliente.comunicacion.ServicioCliente;
import com.rzg.zombieland.cliente.comunicacion.peticion.PeticionMovimiento;
import com.rzg.zombieland.cliente.meta.Estado;
import com.rzg.zombieland.cliente.meta.Estado.EscuchadorProyeccion;
import com.rzg.zombieland.cliente.misc.RutaImagen;
import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.Avatar;
import com.rzg.zombieland.comunes.misc.Movimiento.Direccion;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Interfaz de tablero.
 * 
 * @author Manuel
 */

public class InterfazTablero extends JPanel implements EscuchadorProyeccion {

	private static final long serialVersionUID = 1L;

	private JTable table;
	
	private final Timer timer = new Timer();
	// Constantes
	private static final int DIMENSION = 500; // Dimension en pixeles del
												// tablero
	private static final int MARGEN_IZQUIERDO = 10;
	private static final int MARGEN_SUPERIOR = 30;

	private Map<Avatar, Image> img; // Avatares
	private ImageIcon fondo;

    private JButton moveDown;

    private JButton moveRight;

    private JButton moveLeft;

    private JButton moveUp;
    
    // True si es la primera vez que se va a pintar el tablero para una partida, false de lo 
    // contrario.
    private boolean primeraVez;
    
    // Tenemos una referencia al dispatcher de flechas para activarlo solamente cuando se muestra
    // la pantalla.
    private DispatcherFlechas dispatcher;

    private class DispatcherFlechas implements KeyEventDispatcher {

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() != KeyEvent.KEY_RELEASED)
                return false;
            switch (e.getKeyCode()) {
                case KeyEvent.VK_LEFT:
                    e.consume();
                    mover(Direccion.OESTE);
                    return true;
                case KeyEvent.VK_RIGHT:
                    e.consume();
                    mover(Direccion.ESTE);
                    return true;
                case KeyEvent.VK_UP:
                    e.consume();
                    mover(Direccion.NORTE);
                    return true;
                case KeyEvent.VK_DOWN:
                    e.consume();
                    mover(Direccion.SUR);
                    return true;
            }
            return false;
        }
        
    }
    
	public InterfazTablero() {
	    dispatcher = new DispatcherFlechas();
	    primeraVez = true;
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		setBounds(100, 100, 800, 600);

		img = new HashMap<Avatar, Image>();
		for (Avatar avatar : Avatar.values()) {
			img.put(avatar,
					new ImageIcon(RutaImagen.get("imagenes/Avatares/"
							+ avatar.getSprite())).getImage());
		}

		fondo = new ImageIcon(RutaImagen.get("imagenes/Tablero/pasto.png"));

		moveUp = new JButton("");
		moveUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mover(Direccion.NORTE);
			}
		});

		moveUp.setIcon(new ImageIcon(RutaImagen
				.get("imagenes/Tablero/FlechaArriba.png")));
		moveUp.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		moveUp.setBounds(640, 377, 45, 45);
		add(moveUp);

		moveLeft = new JButton("");
		moveLeft.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mover(Direccion.OESTE);
			}
		});
		
		moveLeft.setIcon(new ImageIcon(RutaImagen
				.get("imagenes/Tablero/FlechaIzquierda.png")));
		moveLeft.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		moveLeft.setBounds(587, 423, 45, 45);
		add(moveLeft);

		moveRight = new JButton("");
		moveRight.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mover(Direccion.ESTE);
			}
		});
		moveRight.setIcon(new ImageIcon(RutaImagen
				.get("imagenes/Tablero/FlechaDerecha.png")));
		moveRight.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		moveRight.setBounds(689, 423, 45, 45);
		add(moveRight);

		moveDown = new JButton("");
		moveDown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mover(Direccion.SUR);
			}
		});
		moveDown.setIcon(new ImageIcon(RutaImagen
				.get("imagenes/Tablero/FlechaAbajo.png")));
		moveDown.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		moveDown.setBounds(640, 471, 45, 45);
		add(moveDown);

		JLabel label = new JLabel("RZG - 2015");
		label.setForeground(SystemColor.textInactiveText);
		label.setBounds(700, 515, 63, 14);
		add(label);
		
		final JLabel labelTemporizador = new JLabel("LALALA");
		labelTemporizador.setHorizontalAlignment(SwingConstants.CENTER);
		labelTemporizador.setBackground(Color.RED);
		labelTemporizador.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelTemporizador.setForeground(Color.RED);
		labelTemporizador.setBounds(641, 220, 159, 45);
		add(labelTemporizador);

		JPanel panelJug = new JPanel();
		panelJug.setBounds(561, 34, 193, 175);
		add(panelJug);
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
		table.setFocusable(false);
		table.setBounds(0, 0, 193, 304);
		panelJug.add(table.getTableHeader(), BorderLayout.NORTH);
		panelJug.add(table, BorderLayout.CENTER);

		JLabel labelFondo = new JLabel("");
		labelFondo.setIcon(new ImageIcon(RutaImagen
				.get("imagenes/Fondos/fondo-tablero.png")));
		labelFondo.setBounds(0, 0, 800, 600);
		add(labelFondo);
		

		TimerTask task = new TimerTask() {
			int tic = 0;
		// Acá va la lógica del temporizador. Ajustar el tiempo del tic al tiempo del juego.
			@Override
			public void run() {
				if (tic == 500) {
					labelTemporizador.setText("0");
					tic = 0;
				} else {
					labelTemporizador.setText("" + tic);
					tic++;
				}
			}
		};
		// Programo el timer para que actúe cada 1ms.
		timer.schedule(task, 0, 1);

		addComponentListener(new ComponentListener() {
            
		    final KeyboardFocusManager manager = 
		            KeyboardFocusManager.getCurrentKeyboardFocusManager();
		    
		    @Override
            public void componentShown(ComponentEvent e) {
                if (Estado.getInstancia().isEspectador())
                    return;
                manager.addKeyEventDispatcher(dispatcher);
            }
            
            @Override
            public void componentResized(ComponentEvent e) { }
            
            @Override
            public void componentMoved(ComponentEvent e) { }
            
            @Override
            public void componentHidden(ComponentEvent e) {
                manager.removeKeyEventDispatcher(dispatcher);
            }
        });
		Estado.getInstancia().addEscuchador(this);
	}

	public void paint(Graphics g) {
		super.paint(g);
		
		ProyeccionTablero proyeccion = Estado.getInstancia().getEstadoLobby()
				.getProyeccion();
		proyeccion.paint(g, img, DIMENSION, MARGEN_IZQUIERDO, MARGEN_SUPERIOR, fondo, primeraVez);
		primeraVez = false;
	}

	/**
	 * Envía una petición de movimiento.
	 * 
	 * @param este
	 */
	private void mover(Direccion direccion) {
		PeticionMovimiento peticion = new PeticionMovimiento(direccion);
		final InterfazTablero this_ = this;
		try {
			ServicioCliente.getInstancia().getHiloEscucha()
					.enviarPeticion(peticion);
			peticion.getRespuesta().then(new DoneCallback<RespuestaGenerica>() {
				@Override
				public void onDone(RespuestaGenerica respuesta) {
					if (!respuesta.fuePeticionExitosa()) {
						JOptionPane.showMessageDialog(this_,
								respuesta.getMensajeError(),
								"Zombieland tablero", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
		} catch (ZombielandException e) {
			JOptionPane.showMessageDialog(this, e.getMessage(),
					"Zombieland tablero", JOptionPane.ERROR_MESSAGE);
		}
	}

	@Override
	public void notificarCambioEstadoEspectador(boolean espectador) {
	    primeraVez = true;
	    moveDown.setVisible(!espectador);
	    moveUp.setVisible(!espectador);
	    moveRight.setVisible(!espectador);
	    moveLeft.setVisible(!espectador);
	}
	
	@Override
	public void notificarProyeccionActualizada(ProyeccionTablero proyeccion) {
		repaint();
	}
}
