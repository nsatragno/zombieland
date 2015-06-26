package com.rzg.zombieland.cliente.interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.text.DefaultCaret;

import org.jdeferred.DoneCallback;

import com.rzg.zombieland.cliente.comunicacion.ServicioCliente;
import com.rzg.zombieland.cliente.comunicacion.peticion.PeticionAbandonarPartida;
import com.rzg.zombieland.cliente.comunicacion.peticion.PeticionMensajeChat;
import com.rzg.zombieland.cliente.meta.Estado;
import com.rzg.zombieland.cliente.meta.Estado.EscuchadorChat;
import com.rzg.zombieland.cliente.meta.Estado.EscuchadorEstadoLobby;
import com.rzg.zombieland.cliente.misc.RutaImagen;
import com.rzg.zombieland.comunes.comunicacion.pojo.POJOPartida;
import com.rzg.zombieland.comunes.comunicacion.respuesta.RespuestaGenerica;
import com.rzg.zombieland.comunes.misc.ZombielandException;

/**
 * Interfaz de Lobby.
 * @author Ivan
 */

public class InterfazLobby extends JPanel implements EscuchadorEstadoLobby, EscuchadorChat
{
    private class ModeloParametros extends AbstractTableModel {

        private final int ADMINISTRADOR = 0;
        private final int CANTIDAD_JUGADORES = 1;
        private final int CANTIDAD_REQUERIDA = 2;
        private final int CANTIDAD_RONDAS = 3;
        private final int ESTADO = 4;
        
        private static final long serialVersionUID = 8020504001144753275L;
        private final String[] nombreCampo = { "Administrador",
                                               "Cantidad de jugadores",
                                               "Cantidad requerida", 
                                               "Cantidad de rondas",
                                               "Estado" };
        private String[] parametros = { "", "", "", "", "" };

        public ModeloParametros(POJOPartida estadoLobby) {
            actualizarDatos(estadoLobby);
        }

        @Override
        public int getRowCount() {
            return 5;
        }

        @Override
        public int getColumnCount() {
            return 2;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (columnIndex == 0)
                return nombreCampo[rowIndex]; 
            return parametros[rowIndex];
        }
        
        @Override
        public String getColumnName(int column) {
            if (column == 1)
                return "Parámetros juego";
            return "";
        }
        
        /**
         * Actualiza los datos del modelo.
         * @param pojo
         */
        public void actualizarDatos(POJOPartida pojo) {
            if (pojo == null)
                return;
            parametros[ADMINISTRADOR] = pojo.getAdministrador();
            parametros[CANTIDAD_JUGADORES] = Integer.toString(pojo.getJugadores().size());
            parametros[CANTIDAD_REQUERIDA] = Integer.toString(pojo.getCantidadMaximaJugadores());
            parametros[CANTIDAD_RONDAS] = Integer.toString(pojo.getCantidadRondas());
            // TODO ver si es posible tener otro estado en este punto.
            parametros[ESTADO] = pojo.getEstado();
            fireTableDataChanged();
        }
    }
    
    private class ModeloLista implements ListModel<String> {
        List<String> jugadores;
        List<ListDataListener> listeners;
        
        public ModeloLista() {
            jugadores = new ArrayList<String>();
            listeners = new ArrayList<ListDataListener>();
        }
        
        @Override
        public int getSize() {
            return jugadores.size();
        }

        @Override
        public String getElementAt(int index) {
            return jugadores.get(index);
        }
        
        public void cambiarDatos(List<String> listado) {
            jugadores = listado;
            for (ListDataListener listener : listeners)
                listener.contentsChanged(new ListDataEvent(this, SOMEBITS, 0, jugadores.size() - 1));
        }

        @Override
        public void addListDataListener(ListDataListener l) {
            listeners.add(l);
        }

        @Override
        public void removeListDataListener(ListDataListener l) {
            listeners.remove(l);
        }
        
    }
    
	private static final long serialVersionUID = 6954796419653149772L;
    private ModeloParametros modeloTablaParametros;
    private ModeloLista modeloListaJugadores;
    private JLabel lblTitulo;
    private JTextArea chat;
    private JTextField mensaje;
    private JButton botonEnviarMensaje;
	/**
	 * Create the frame.
	 */
	public InterfazLobby()
	{
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);
		
		JLabel lblRecomendacion1 = new JLabel("- El juego comenzar\u00E1 cuando la cantidad de jugadores requerida esten en l\u00EDnea.");
		lblRecomendacion1.setForeground(Color.WHITE);
		lblRecomendacion1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRecomendacion1.setBounds(320, 84, 497, 30);
		add(lblRecomendacion1);
		
		JLabel lblRecomendacion2 = new JLabel("- El juego consistir\u00E1 de las rondas indicadas por el administrador.");
		lblRecomendacion2.setForeground(Color.WHITE);
		lblRecomendacion2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRecomendacion2.setBounds(320, 109, 386, 30);
		add(lblRecomendacion2);
		
		JLabel lblRecomendacion3 = new JLabel("- El zombi busca humanos, el humano escapa de los zombies.");
		lblRecomendacion3.setForeground(Color.WHITE);
		lblRecomendacion3.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRecomendacion3.setBounds(320, 134, 389, 30);
		add(lblRecomendacion3);
		
		JLabel lblRecomendacion4 = new JLabel("- Evita los obst\u00E1culos!");
		lblRecomendacion4.setForeground(Color.WHITE);
		lblRecomendacion4.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRecomendacion4.setBounds(320, 159, 389, 30);
		add(lblRecomendacion4);
		
		JLabel lblRecomendacion5 = new JLabel("- Los turnos son de 5 segundos cada uno, as\u00ED que pensa tu movimiento r\u00E1pido!");
		lblRecomendacion5.setForeground(Color.WHITE);
		lblRecomendacion5.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRecomendacion5.setBounds(320, 184, 466, 30);
		add(lblRecomendacion5);
		
		JLabel lblRecomendacion6 = new JLabel("- Si un humano es convertido la nueva tarea del jugador ser\u00E1 atrapar a los humanos.");
		lblRecomendacion6.setForeground(Color.WHITE);
		lblRecomendacion6.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRecomendacion6.setBounds(320, 209, 497, 30);
		add(lblRecomendacion6);
		
		JLabel lblRecomendacion9 = new JLabel("- Acordate que tenes la opci\u00F3n de no hacer ning\u00FAn movimiento.");
		lblRecomendacion9.setForeground(Color.WHITE);
		lblRecomendacion9.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRecomendacion9.setBounds(320, 234, 402, 30);
		add(lblRecomendacion9);
		
		JLabel lblRecomendacion7 = new JLabel("- Una ronda termina cuando todos los humanos fueron convertidos a zombies.");
		lblRecomendacion7.setForeground(Color.WHITE);
		lblRecomendacion7.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRecomendacion7.setBounds(320, 259, 466, 30);
		add(lblRecomendacion7);
		
		JLabel lblRecomendacion8 = new JLabel("- El juego termina cuando todas las rondas fueron terminadas.");
		lblRecomendacion8.setForeground(Color.WHITE);
		lblRecomendacion8.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblRecomendacion8.setBounds(320, 284, 415, 30);
		add(lblRecomendacion8);
		
		lblTitulo = new JLabel();
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setBounds(10, 11, 595, 67);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 36));
		add(lblTitulo);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 87, 300, 220);
		panel.setLayout(new BorderLayout());
		// El BorderLayout es para poder poner luego los encabezados de columna sin la necesidad
		// de usar un JScrollPane.
		add(panel);
		
		JTable tableParametros = new JTable();
		tableParametros.setGridColor(Color.BLACK);
        tableParametros.setRowSelectionAllowed(false);
        tableParametros.setBorder(new LineBorder(new Color(0, 0, 0)));
        modeloTablaParametros = new ModeloParametros(Estado.getInstancia().getEstadoLobby());
        tableParametros.setModel(modeloTablaParametros);
		tableParametros.getColumnModel().getColumn(0).setPreferredWidth(140);
		tableParametros.getColumnModel().getColumn(0).setMinWidth(140);
		tableParametros.getColumnModel().getColumn(1).setPreferredWidth(225);
		tableParametros.getColumnModel().getColumn(1).setMinWidth(200);
		tableParametros.setRowHeight(40);
		tableParametros.setBounds(0, 50, 290, 240);
		tableParametros.getTableHeader().setReorderingAllowed(false);
		
		panel.add(tableParametros.getTableHeader(), BorderLayout.NORTH);
		panel.add(tableParametros, BorderLayout.CENTER);
		
		JLabel jugadores = new JLabel("Jugadores");
		jugadores.setBounds(10, 320, 300, 20);
		jugadores.setForeground(Color.WHITE);
		add(jugadores);
		
		modeloListaJugadores = new ModeloLista();
		JList<String> listaJugadores = new JList<String>();
		listaJugadores.setBorder(new LineBorder(new Color(0, 0, 0)));
		listaJugadores.setBounds(10, 340, 300, 140);
		listaJugadores.setModel(modeloListaJugadores);
		add(listaJugadores, BorderLayout.CENTER);
		
		JLabel labelChat = new JLabel("Chat");
        labelChat.setBounds(320, 320, 300, 20);
        labelChat.setForeground(Color.WHITE);
        add(labelChat);
		
        chat = new JTextArea();
        chat.setEditable(false);
        chat.setLineWrap(true);
        DefaultCaret caret = (DefaultCaret)chat.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        
        JScrollPane scroll = new JScrollPane(chat);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setBounds(320, 340, 430, 100);
        add(scroll);
        
        mensaje = new JTextField();
        mensaje.setBounds(320, 445, 350, 30);
        add(mensaje);
        
        botonEnviarMensaje = new JButton();
        botonEnviarMensaje.setText("Enviar");
        botonEnviarMensaje.setBounds(670, 445, 80, 30);
        add(botonEnviarMensaje);
        
        final InterfazLobby _this = this;
        
        botonEnviarMensaje.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if (_this.isVisible())
                    enviarMensaje();
            }
        });
        
        JButton botonAbandonar = new JButton("Abandonar");
        botonAbandonar.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                abandonarPartida();
            }
        });
        botonAbandonar.setBounds(300, 490, 150, 40);
        add(botonAbandonar);
		
		JLabel label = new JLabel("RZG - 2015");
		label.setForeground(SystemColor.textInactiveText);
		label.setBounds(700, 515, 63, 14);
		add(label);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setIcon(new ImageIcon(RutaImagen.get("imagenes/Fondos/fondo-lobby.png")));
		lblFondo.setBounds(0, 0, 800, 600);
		add(lblFondo);
		Estado.getInstancia().addEscuchador(this);
		Estado.getInstancia().setEscuchadorChat(this);
		
		addComponentListener(new ComponentListener() {
            
            @Override
            public void componentShown(ComponentEvent e) {
                chat.setText("");
            }
            
            @Override
            public void componentResized(ComponentEvent e) { }
            
            @Override
            public void componentMoved(ComponentEvent e) { }
            
            @Override
            public void componentHidden(ComponentEvent e) { }
        });
	}
	
    @Override
    public void notificarLobbyActualizado(POJOPartida datos) {
        modeloTablaParametros.actualizarDatos(datos);
        modeloListaJugadores.cambiarDatos(datos.getJugadores());
        lblTitulo.setText(datos.getNombre());
        if (datos.getProyeccion() != null)
            Main.irA(Main.TABLERO);
    }
    
    private void abandonarPartida() {
        final Component _this = this;
        PeticionAbandonarPartida peticion = new PeticionAbandonarPartida();
        try {
            ServicioCliente.enviarPeticion(peticion);
            peticion.getRespuesta().then(new DoneCallback<RespuestaGenerica>() {
                
                @Override
                public void onDone(RespuestaGenerica respuesta) {
                    if (respuesta.fuePeticionExitosa()) {
                        Estado.getInstancia().setEstadoLobby(POJOPartida.PARTIDA_VACIA);
                        Main.irA(Main.LISTADO_PARTIDAS);
                    } else {
                        JOptionPane.showMessageDialog(_this,
                                                      respuesta.getMensajeError(),
                                                      "Lobby", 
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        } catch (ZombielandException e1) {
            JOptionPane.showMessageDialog(this,
                                          "No se pudo abandonar la partida",
                                          "Lobby",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Envía una petición de chat al servidor.
     * @param text
     */
    private void enviarMensaje() {
        if (mensaje.getText().isEmpty())
            return;
        final Component _this = this;
        PeticionMensajeChat peticion = new PeticionMensajeChat(mensaje.getText());
        try {
            ServicioCliente.enviarPeticion(peticion);
            peticion.getRespuesta().then(new DoneCallback<RespuestaGenerica>() {
                
                @Override
                public void onDone(RespuestaGenerica respuesta) {
                    if (respuesta.fuePeticionExitosa()) {
                        mensaje.setText("");
                    } else {
                        JOptionPane.showMessageDialog(_this,
                                                      respuesta.getMensajeError(),
                                                      "Lobby", 
                                                      JOptionPane.ERROR_MESSAGE);
                    }
                }
            });
        } catch (ZombielandException e) {
            JOptionPane.showMessageDialog(this,
                    "No se pudo enviar el mensaje de error: " + e.getMessage(),
                    "Lobby",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    @Override
    public void recibidoMensaje(String mensaje) {
        chat.append(mensaje + "\n");
    }
    
    /**
     * Hace que el botón de envío de mensaje sea el predeterminado.
     */
    public void setBotonEnviarMensajeComoDefault() {
        JRootPane rootPane = SwingUtilities.getRootPane(botonEnviarMensaje); 
        rootPane.setDefaultButton(botonEnviarMensaje);
    }
}
