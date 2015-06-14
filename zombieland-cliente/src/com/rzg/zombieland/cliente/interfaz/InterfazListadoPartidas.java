package com.rzg.zombieland.cliente.interfaz;

import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.AbstractTableModel;

import org.jdeferred.DoneCallback;

import com.rzg.zombieland.cliente.comunicacion.PeticionListadoPartidas;
import com.rzg.zombieland.cliente.comunicacion.ServicioCliente;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;
import com.rzg.zombieland.comunes.comunicacion.pojo.RespuestaListadoPartidas;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Interfaz de Listado de Partidas
 * 
 * @author Ivan
 */
public class InterfazListadoPartidas extends JPanel {

	private static final long serialVersionUID = -7079211493379843872L;
    private ModeloTabla modeloPartidas;
	
	private class ModeloTabla extends AbstractTableModel {

        private static final long serialVersionUID = 7813369511594114453L;

        private List<POJOPartida> partidas;
	    
	    private static final int COLUMNAS = 4;
	    
        private static final int NOMBRE = 0;
        private static final int ADMINISTRADOR = 1;
        private static final int CANTIDAD_JUGADORES = 2;
        private static final int ESTADO = 3;
	    
        /**
         * Actualiza el modelo de datos según las partidas dadas y notifica a la vista.
         * @param partidas
         */
        public void actualizar(List<POJOPartida> partidas) {
            this.partidas = partidas;
            fireTableDataChanged();
        }
        
        @Override
        public int getRowCount() {
            if (partidas == null)
                return 0;
            return partidas.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNAS;
        }
        
        @Override
        public String getColumnName(int column) {
            switch (column) {
            case NOMBRE:
                return "Nombre";
            case ADMINISTRADOR: 
                return "Administrador";
            case CANTIDAD_JUGADORES: 
                return "Cant. Jugadores";
            case ESTADO:
                return  "Estado";
            default:
                throw new ArrayIndexOutOfBoundsException();
            }
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            POJOPartida partida = partidas.get(rowIndex);
            switch (columnIndex) {
            case NOMBRE:
                return partida.getNombre();
            case ADMINISTRADOR:
                return partida.getAdministrador();
            case CANTIDAD_JUGADORES:
                return partida.getJugadores().size();
            case ESTADO:
                return partida.getEstado();
            default:
                throw new ArrayIndexOutOfBoundsException();
            }
        }
	    
	}

	/**
	 * Create the frame.
	 */
	public InterfazListadoPartidas() {
		setForeground(Color.WHITE);
		setBackground(UIManager.getColor("Panel.background"));
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		JButton btnUnirse = new JButton("Unirse");
		btnUnirse.setBounds(288, 430, 175, 40);
		btnUnirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.irA(Main.LOBBY);
			}
		});
		add(btnUnirse);

		JButton btnCrearPartida = new JButton("Crear Partida");
		btnCrearPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.irA(Main.CREAR_PARTIDA);
			}
		});
		btnCrearPartida.setBackground(UIManager
				.getColor("Button.disabledForeground"));
		btnCrearPartida.setBounds(39, 30, 175, 40);
		add(btnCrearPartida);

		JButton btnJugar = new JButton("JUGAR");
		btnJugar.setBounds(77, 430, 175, 40);
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.irA(Main.TABLERO);
			}
		});
		add(btnJugar);

		JButton btnObservar = new JButton("Observar");
		btnObservar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Main.irA(Main.LOBBY);
			}
		});
		btnObservar.setBounds(512, 430, 175, 40);
		add(btnObservar);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane
				.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane.setToolTipText("");
		scrollPane.setBounds(66, 95, 631, 318);
		add(scrollPane);

		JTable tablaPartidas = new JTable();
		modeloPartidas = new ModeloTabla();
        tablaPartidas.setModel(modeloPartidas);
		tablaPartidas.getColumnModel().getColumn(0).setPreferredWidth(150);
		tablaPartidas.getColumnModel().getColumn(1).setPreferredWidth(100);
		tablaPartidas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tablaPartidas.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tablaPartidas);
		
		JLabel label = new JLabel("RZG - 2015");
		label.setForeground(SystemColor.textInactiveText);
		label.setBounds(700, 515, 110, 14);
		add(label);

		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 800, 600);
		lblFondo.setIcon(new ImageIcon("imagenes/Fondos/fondo-lista-partidas.png"));
		add(lblFondo);
		addComponentListener(new ComponentShownListener() {
            @Override
            public void componentShown(ComponentEvent e) {
                actualizarModeloPartidas();
            }
        });
		
	}

    private void actualizarModeloPartidas() {
        PeticionListadoPartidas peticion = new PeticionListadoPartidas();
        final Component this_ = this;
        try {
            ServicioCliente.enviarPeticion(peticion);
            peticion.getRespuesta().then(new DoneCallback<RespuestaListadoPartidas>() {
                
                @Override
                public void onDone(RespuestaListadoPartidas respuesta) {
                    if (respuesta.fuePeticionExitosa())
                        modeloPartidas.actualizar(respuesta.getPartidas());
                    else
                        JOptionPane.showMessageDialog(this_,
                                                      respuesta.getMensajeError(), 
                                                      "Listado de partidas", 
                                                      JOptionPane.WARNING_MESSAGE);
                }
            });
        } catch (ZombielandException e) {
            JOptionPane.showMessageDialog(this,
                                          e.getMessage(), 
                                          "Listado de partidas", 
                                          JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
