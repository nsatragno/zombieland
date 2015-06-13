package com.rzg.zombieland.cliente.interfaz;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

/**
 * 
 * @author Nicolas L
 *
 */
public class InterfazRankingGeneral extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8691556315140111325L;

	/**
	 * Create the frame.
	 */
	public InterfazRankingGeneral() {
		setBackground(Color.BLACK);
		setBorder(new EmptyBorder(5, 5, 5, 5));
		setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane
				.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(0, 46, 797, 257);
		add(scrollPane);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		scrollPane.setViewportView(tabbedPane);

		JLabel lblNewLabel = new JLabel("Nombre de Usuario");
		lblNewLabel.setForeground(Color.GRAY);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel.setBounds(40, 21, 205, 27);
		add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Puntaje");
		lblNewLabel_1.setForeground(Color.GRAY);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblNewLabel_1.setBounds(668, 12, 95, 44);
		add(lblNewLabel_1);

		JButton btnVolver = new JButton("Volver al Lobby");
		btnVolver.setBounds(40, 325, 121, 35);
		add(btnVolver);
		
		JLabel label = new JLabel("RZG - 2015");
		label.setForeground(SystemColor.textInactiveText);
		label.setBounds(700, 515, 63, 14);
		add(label);

		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("imagenes/Fondos/zombieBanner.png"));
		lblNewLabel_2.setBounds(0, 304, 797, 247);
		add(lblNewLabel_2);
	}
}
