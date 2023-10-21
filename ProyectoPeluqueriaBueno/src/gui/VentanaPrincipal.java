package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;

public class VentanaPrincipal extends JFrame {
	
	private JPanel contentPane;
	private JButton btnAgenda, btnInventario, btnCliente;
	private JLabel labelLogo;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public VentanaPrincipal() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/images/logoPeluqueria.png")));
		components();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void components() {
		setTitle("MENU PRINCIPAL");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 816, 535);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//TITULO
		JPanel panelNorte = new JPanel();
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("¡Bienvenido a Barbershop!");
		lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		panelNorte.add(lblTitulo);
		
		//BOTONES
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(3, 1, 15, 15));
		
		JPanel panelAgenda = new JPanel();
		panelCentro.add(panelAgenda);
		panelAgenda.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnAgenda = new JButton("AGENDA");
		btnAgenda.setForeground(Color.WHITE);
		btnAgenda.setBackground(new Color(205, 92, 92));
		btnAgenda.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPeluquero vp = new VentanaPeluquero();
				vp.setVisible(true);
				setVisible(false);
			}
		});
		panelAgenda.add(btnAgenda);
		
		JPanel panelBotonInventario = new JPanel();
		panelCentro.add(panelBotonInventario);
		panelBotonInventario.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnInventario = new JButton("INVENTARIO");
		btnInventario.setForeground(Color.WHITE);
		btnInventario.setBackground(new Color(205, 92, 92));
		btnInventario.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaInventario vi = new VentanaInventario();
				vi.setVisible(true);
				setVisible(false);
			}
		});
		panelBotonInventario.add(btnInventario);
		
		JPanel panelBotonCliente = new JPanel();
		panelCentro.add(panelBotonCliente);
		panelBotonCliente.setLayout(new GridLayout(0, 1, 0, 0));
		
		btnCliente = new JButton("CLIENTES");
		btnCliente.setForeground(Color.WHITE);
		btnCliente.setBackground(new Color(205, 92, 92));
		btnCliente.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaClientes vc = new VentanaClientes();
				vc.setVisible(true);
				setVisible(false);
			}
		});
		panelBotonCliente.add(btnCliente);
		
		//PANEL LOGO
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
		panelEste.setLayout(new GridLayout(0, 1, 0, 0));
		
		labelLogo = new JLabel();
		labelLogo.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/images/logoPeluqueria.png")));
		panelEste.add(labelLogo);	
	}

}