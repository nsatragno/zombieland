package com.rzg.zombieland.cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
import javax.swing.table.AbstractTableModel;
import javax.swing.table.JTableHeader;

import org.jdeferred.DoneCallback;

import com.rzg.zombieland.cliente.comunicacion.ServicioCliente;
import com.rzg.zombieland.cliente.comunicacion.peticion.PeticionMovimiento;
import com.rzg.zombieland.cliente.meta.Estado;
import com.rzg.zombieland.cliente.meta.Estado.EscuchadorProyeccion;
import com.rzg.zombieland.cliente.meta.Estado.EscuchadorPuntaje;
import com.rzg.zombieland.cliente.misc.RutaImagen;
import com.rzg.zombieland.comunes.comunicacion.ProyeccionTablero;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOResultadoRonda;
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

	private final Timer timer = new Timer();
	// Constantes
	private static final int DIMENSION = 500; // Dimension en pixeles del
												// tablero
	private static final int MARGEN_IZQUIERDO = 10;
	private static final int MARGEN_SUPERIOR = 30;

    private static final int TAMAÑO_FILA = 15;

	private Map<Avatar, Image> img; // Avatares
	private ImageIcon fondo;

	private JButton moveDown;

	private JButton moveRight;

	private JButton moveLeft;

	private JButton moveUp;

    // Tenemos una referencia al dispatcher de flechas para activarlo solamente cuando se muestra
    // la pantalla.
    private DispatcherFlechas dispatcher;

    private JPanel panelJugadores;

    private int tiempoProyeccion;

    private JLabel labelTemporizador;

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
    
    private class ModeloPuntaje extends AbstractTableModel implements EscuchadorPuntaje {

        private static final long serialVersionUID = -4290128679203618750L;
        private static final int COLUMNA_JUGADOR = 0;
        private static final int CANTIDAD_COLUMNAS = 2;
        
        private List<Entry<String, Integer>> jugadores;
        
        @Override
        public int getRowCount() {
            if (jugadores == null)
                return 0;
            return jugadores.size();
        }

        @Override
        public int getColumnCount() {
            return CANTIDAD_COLUMNAS;
        }
        
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (jugadores == null)
                return null;
            if (columnIndex == COLUMNA_JUGADOR) {
                return jugadores.get(rowIndex).getKey();
            } else {
                return jugadores.get(rowIndex).getValue();
            }
        }

        @Override
        public void recibidoPuntaje(POJOResultadoRonda puntaje) {
            jugadores = new ArrayList<Entry<String, Integer>>();
            for (Entry<String, Integer> entry : puntaje.getPuntajes().entrySet())
                jugadores.add(entry);
            Collections.sort(jugadores, new Comparator<Entry<String, Integer>>() {

                @Override
                public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                    return o2.getValue().compareTo(o1.getValue());
                }
            });
            Rectangle rect = panelJugadores.getBounds();
            Dimension dim = rect.getSize();
            dim.setSize(dim.getWidth(), TAMAÑO_FILA * (jugadores.size() + 1));
            rect.setSize(dim);
            panelJugadores.setBounds(rect);
            fireTableDataChanged();
        }
        
        @Override
        public String getColumnName(int column) {
            if (column == COLUMNA_JUGADOR)
                return "Jugador";
            return "Puntaje";
        }
    }
    
	public InterfazTablero() {
	    dispatcher = new DispatcherFlechas();
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

		labelTemporizador = new JLabel("LALALA");
		labelTemporizador.setHorizontalAlignment(SwingConstants.CENTER);
		labelTemporizador.setBackground(Color.RED);
		labelTemporizador.setFont(new Font("Tahoma", Font.BOLD, 18));
		labelTemporizador.setForeground(Color.RED);
		labelTemporizador.setBounds(641, 220, 159, 45);
		add(labelTemporizador);

		panelJugadores = new JPanel();
		panelJugadores.setBounds(561, 34, 193, TAMAÑO_FILA);
		add(panelJugadores);
		panelJugadores.setLayout(new BorderLayout());

		ModeloPuntaje modelo = new ModeloPuntaje();
		Estado.getInstancia().setEscuchadorPuntaje(modelo);
		
		JTable table = new JTable();
		table.setRowSelectionAllowed(false);
		table.setGridColor(Color.BLACK);
		table.setRowHeight(TAMAÑO_FILA);
		table.setTableHeader(new JTableHeader(table.getColumnModel()) {
            private static final long serialVersionUID = 7787996933499011913L;

            @Override
		    public Dimension getPreferredSize() {
		        Dimension dim = super.getPreferredSize();
		        dim.height = TAMAÑO_FILA;
		        return dim;
		    }
		});
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setModel(modelo);
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(120);
		table.getColumnModel().getColumn(0).setMinWidth(50);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.setFocusable(false);
		table.setBounds(0, 0, 193, 304);
		panelJugadores.add(table.getTableHeader(), BorderLayout.NORTH);
		panelJugadores.add(table, BorderLayout.CENTER);

		JLabel labelFondo = new JLabel("");
		labelFondo.setIcon(new ImageIcon(RutaImagen
				.get("imagenes/Fondos/fondo-tablero.png")));
		labelFondo.setBounds(0, 0, 800, 600);
		add(labelFondo);

		addComponentListener(new ComponentListener() {
            
		    final KeyboardFocusManager manager = 
		            KeyboardFocusManager.getCurrentKeyboardFocusManager();
		    
		    @Override
            public void componentShown(ComponentEvent e) {
		        TimerTask task = new TimerTask() {
		            
		            @Override
		            public void run() {
		                EventQueue.invokeLater(new Runnable() {
		                    @Override
		                    public void run() {
		                        labelTemporizador.setText(Integer.toString(tic()));
		                    }
		                });
		            }
		        };
		        // Programo el timer para que actúe cada 1000ms.
		        timer.schedule(task, 0, 1000);
                if (!Estado.getInstancia().isEspectador())
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
		update(g);
	}
	public void update (Graphics g) {
		ProyeccionTablero proyeccion = Estado.getInstancia().getEstadoLobby()
				.getProyeccion();
		proyeccion.paint(g, img, DIMENSION, MARGEN_IZQUIERDO,
				MARGEN_SUPERIOR, fondo);
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

	/**
	 * Llamado cuando pasó una unidad de tiempo.
	 * @return el tiempo restante al siguiente turno.
	 */
    private int tic() {
        if (tiempoProyeccion == 0)
            return 0;
        return tiempoProyeccion--;
    }

	@Override
	public void notificarCambioEstadoEspectador(boolean espectador) {
		moveDown.setVisible(!espectador);
		moveUp.setVisible(!espectador);
		moveRight.setVisible(!espectador);
		moveLeft.setVisible(!espectador);
	}

	@Override
	public void notificarProyeccionActualizada(ProyeccionTablero proyeccion) {
	    tiempoProyeccion = proyeccion.getTiempoParaElSiguientePaso() / 1000;
	    labelTemporizador.setText(Integer.toString(tiempoProyeccion));
		repaint();
	}
}
