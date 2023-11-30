package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Toolkit;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class VentanaInicio extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaInicio frame = new VentanaInicio();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaInicio() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInicio.class.getResource("/images/logoPeluqueria.png")));
		setTitle("INICIO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Panel Norte
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(new Color(205, 92, 92));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		
		JLabel lblTitulo = new JLabel("Barbershop");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("MV Boli", Font.PLAIN, 26));
		panelNorte.add(lblTitulo);
		
		//Panel Centro
		JPanel panelCentro = new JPanel();
		contentPane.add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new GridLayout(2,0,0,10));
		
		JButton btnInicioSesion = new JButton("INICIAR SESION");
		btnInicioSesion.setBackground(SystemColor.textHighlightText);
		btnInicioSesion.setForeground(new Color(205, 92, 92));
		btnInicioSesion.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelCentro.add(btnInicioSesion);
		
		JButton btnRegistro = new JButton("REGISTRAR");
		btnRegistro.setBackground(SystemColor.textHighlightText);
		btnRegistro.setForeground(new Color(205, 92, 92));
		btnRegistro.setFont(new Font("Tahoma", Font.BOLD, 16));
		panelCentro.add(btnRegistro);
	}

}
