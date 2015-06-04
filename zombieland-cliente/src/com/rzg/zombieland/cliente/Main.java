package com.rzg.zombieland.cliente;

import java.awt.CardLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import com.rzg.zombieland.cliente.interfazInicioSesion.InterfazInicioSesion;
import com.rzg.zombieland.cliente.interfazListadoDePartidas.InterfazListadoPartidas;
import com.rzg.zombieland.cliente.interfazRegistro.InterfazRegistro;

public class Main extends JFrame {

    private CardLayout cardLayout;
    
    public Main() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(648, 480);
        InterfazInicioSesion frame = new InterfazInicioSesion(this);
        frame.setName("inicioSesion");
        InterfazRegistro frame2 = new InterfazRegistro();
        frame2.setName("registro");
        cardLayout = new CardLayout();
        setLayout(cardLayout);
        add(frame);
        add(frame2, "registro");
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu mnArchivo = new JMenu("Archivo");
        menuBar.add(mnArchivo);
        JMenuItem mntmIniciarSesion = new JMenuItem("Iniciar Sesion");
        mnArchivo.add(mntmIniciarSesion);
        JMenuItem mntmRegistrarse = new JMenuItem("Registrarse");
        mnArchivo.add(mntmRegistrarse);
        JMenuItem mntmSalir = new JMenuItem("Salir");
        mnArchivo.add(mntmSalir);
        JMenu mnPartida = new JMenu("Partida");
        menuBar.add(mnPartida);
        JMenuItem mntmJugar = new JMenuItem("Jugar");
        mnPartida.add(mntmJugar);
        JMenuItem mntmVerPartidas = new JMenuItem("Ver Partidas");
        mnPartida.add(mntmVerPartidas);
        JMenu mnCuenta = new JMenu("Cuenta");
        menuBar.add(mnCuenta);
        JMenuItem mntmDatos = new JMenuItem("Datos");
        mnCuenta.add(mntmDatos);
        JMenuItem mntmEstadsticas = new JMenuItem("Estad\u00EDsticas");
        mnCuenta.add(mntmEstadsticas);
        JMenu mnAyuda = new JMenu("Ayuda");
        menuBar.add(mnAyuda);
        JMenuItem mntmCmoJugar = new JMenuItem("C\u00F3mo Jugar");
        mnAyuda.add(mntmCmoJugar);
        JMenuItem mntmGoogleame = new JMenuItem("Googleame");
        mnAyuda.add(mntmGoogleame);
        setVisible(true);
    }
    
    /**
     * Launch the application.
     */
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                try
                {
                    Main main = new Main();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    public void irARegistro() {
        cardLayout.show(getContentPane(), "registro");
        //cardLayout.next(getContentPane());
    }
}
