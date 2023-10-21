package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class VentanaPeluquero extends JFrame{
	
	private JPanel contentPane;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPeluquero frame = new VentanaPeluquero();
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
	public VentanaPeluquero() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPeluquero.class.getResource("/images/logoPeluqueria.png")));
		setTitle ("PELUQUEROS");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(450, 100, 550, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//TITULO
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(new Color(205, 92, 92));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("Escoja a su peluquero: ");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 18));
		panelNorte.add(lblTitulo);
		
		//PELUQUEROS
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(2, 2, 15, 15));
		
		JPanel panelPeluqueroFoto1 = new JPanel();
		panelPeluqueroFoto1.setBackground(Color.WHITE);
		panelCentro.add(panelPeluqueroFoto1);
		panelPeluqueroFoto1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel labelPeluquero1 = new JLabel();
		labelPeluquero1.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon peluqueroChico = new ImageIcon(VentanaPeluquero.class.getResource("/images/peluqueroMasc.png"));
		labelPeluquero1.setIcon(peluqueroChico);
		panelPeluqueroFoto1.add(labelPeluquero1);
		
		JPanel panelPeluqueroFoto2 = new JPanel();
		panelPeluqueroFoto2.setBackground(Color.WHITE);
		panelCentro.add(panelPeluqueroFoto2);
		panelPeluqueroFoto2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel labelPeluquero2 = new JLabel();
		labelPeluquero2.setHorizontalAlignment(SwingConstants.CENTER);
		ImageIcon peluqueroChica = new ImageIcon(VentanaPeluquero.class.getResource("/images/peluqueroFem.png"));
		labelPeluquero2.setIcon(peluqueroChica);
		panelPeluqueroFoto2.add(labelPeluquero2);
		
		JPanel panelBotonPeluquero1 = new JPanel();
		panelCentro.add(panelBotonPeluquero1);
		panelBotonPeluquero1.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnPeluquero1 = new JButton();
		btnPeluquero1.setForeground(Color.WHITE);
		btnPeluquero1.setText("Alvaro");
		btnPeluquero1.setBackground(new Color(205, 92, 92));
		btnPeluquero1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaAgenda va = new VentanaAgenda();
				va.setVisible(true);
				setVisible(false);
			}
		});
		panelBotonPeluquero1.add(btnPeluquero1);
		
		JPanel panelBotonPeluquero2 = new JPanel();
		panelCentro.add(panelBotonPeluquero2);
		panelBotonPeluquero2.setLayout(new GridLayout(0, 1, 0, 0));
		
		JButton btnPeluquero2 = new JButton();
		btnPeluquero2.setForeground(Color.WHITE);
		btnPeluquero2.setBackground(new Color(205, 92, 92));
		btnPeluquero2.setText("Maria");
		btnPeluquero2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaAgenda va = new VentanaAgenda();
				va.setVisible(true);
				setVisible(false);
			}
		});
		panelBotonPeluquero2.add(btnPeluquero2);
		
		//Panel Sur
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		JButton botonMenu = new JButton("MENU");
		botonMenu.setBackground(new Color(205, 92, 92));
		botonMenu.setForeground(Color.WHITE);
		botonMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VentanaPrincipal vp = new VentanaPrincipal();
				vp.setVisible(true);
				setVisible(false);
			}
		});
		panelSur.add(botonMenu);
	}
}
