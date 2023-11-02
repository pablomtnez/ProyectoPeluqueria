package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

import domain.Dia;
import domain.TipoCita;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import domain.Cliente;
import domain.Peluquero;
import domain.Cita;
import javax.swing.DefaultComboBoxModel;

public class VentanaAgenda extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(VentanaAgenda.class.getName());
	
	private JPanel contentPane;
	private JTextField textFieldIdI;
	private JComboBox<String> comboBoxClienteI, comboBoxPeluqueroI, comboBoxDiaI, comboBoxHoraI, 
	comboBoxMinI, comboBoxCitaI, comboBoxIdM, comboBoxClienteM, comboBoxPeluqueroM, comboBoxDiaM,
	comboBoxHoraM, comboBoxMinM, comboBoxCitaM;
	private DefaultMutableTreeNode dias,lunes, martes, miercoles, jueves, viernes;
	private DefaultTreeModel modeloArbol;
	private JTree arbol;
	private DefaultTableModel modeloTabla;
	private JTable tablaCitas;
//	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	private Dia dia;
	
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaAgenda frame = new VentanaAgenda();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public VentanaAgenda() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaAgenda.class.getResource("/images/logoPeluqueria.png")));
		setTitle("AGENDA");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 692);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Panel Norte
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(new Color(205, 92, 92));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblTitulo = new JLabel("AGENDA");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		panelNorte.add(lblTitulo);
		panelNorte.add(lblTitulo);
		
		//Panel Este
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
		panelEste.setLayout(new BorderLayout());
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panelEste.add(tabbedPane);
		
		//Panel Inicio
		JPanel panelInicio = new JPanel();
		panelInicio.setBackground(Color.WHITE);
		tabbedPane.addTab("INICIO",null, panelInicio, null);
		panelInicio.setLayout(new BorderLayout(0,0));
		
		JLabel lblImagen = new JLabel("");
		lblImagen.setIcon(new ImageIcon(VentanaAgenda.class.getResource("/images/logoPeluqueria.png")));
		panelInicio.add(lblImagen, BorderLayout.CENTER);
		
		//Panel Insertar
		JPanel panelInsertar = new JPanel();
		tabbedPane.addTab("INSERTAR", null, panelInsertar, null);
		panelInsertar.setLayout(new BorderLayout(0, 0));
		
		//Panel Norte Insertar 
		JPanel panelNorteI = new JPanel();
		panelNorteI.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelNorteI.setBackground(new Color(205, 92, 92));
		panelInsertar.add(panelNorteI, BorderLayout.NORTH);
		
		JLabel lblInsertarCita = new JLabel("INSERTAR CITA: ");
		lblInsertarCita.setForeground(Color.WHITE);
		lblInsertarCita.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		panelNorteI.add(lblInsertarCita);
		
		//Panel Sur Insertar
		JPanel panelSurI = new JPanel();
		panelSurI.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelSurI.setBackground(new Color(205, 92, 92));
		panelInsertar.add(panelSurI, BorderLayout.SOUTH);
		
		JButton btnInsertar = new JButton("INSERTAR");
		btnInsertar.setForeground(Color.BLACK);
		btnInsertar.setBackground(Color.WHITE);
		btnInsertar.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelSurI.add(btnInsertar); 
		
		//Panel Centro Insertar
		JPanel panelCentroI = new JPanel();
		panelCentroI.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelInsertar.add(panelCentroI, BorderLayout.CENTER);
		GridBagLayout gbl_pCentroI = new GridBagLayout();
		gbl_pCentroI.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pCentroI.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pCentroI.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pCentroI.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelCentroI.setLayout(gbl_pCentroI);
		
		JLabel lblNewLabelI = new JLabel(".");
		lblNewLabelI.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabelI = new GridBagConstraints();
		gbc_lblNewLabelI.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelI.gridx = 4;
		gbc_lblNewLabelI.gridy = 0;
		panelCentroI.add(lblNewLabelI, gbc_lblNewLabelI);
		
		JLabel lblIdI = new JLabel("ID");
		GridBagConstraints gbc_lblIdI = new GridBagConstraints();
		gbc_lblIdI.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdI.anchor = GridBagConstraints.EAST;
		gbc_lblIdI.gridx = 2;
		gbc_lblIdI.gridy = 1;
		panelCentroI.add(lblIdI, gbc_lblIdI);
		
		textFieldIdI = new JTextField();
		GridBagConstraints gbc_textFieldIdI = new GridBagConstraints();
		gbc_textFieldIdI.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldIdI.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldIdI.gridx = 4;
		gbc_textFieldIdI.gridy = 1;
		panelCentroI.add(textFieldIdI, gbc_textFieldIdI);
		
		JLabel lblNewLabel_I = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_I = new GridBagConstraints();
		gbc_lblNewLabel_I.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_I.gridx = 1;
		gbc_lblNewLabel_I.gridy = 2;
		panelCentroI.add(lblNewLabel_I, gbc_lblNewLabel_I);
		
		JLabel lblClienteI = new JLabel("CLIENTE");
		GridBagConstraints gbc_lblClienteI = new GridBagConstraints();
		gbc_lblClienteI.insets = new Insets(0, 0, 5, 5);
		gbc_lblClienteI.anchor = GridBagConstraints.EAST;
		gbc_lblClienteI.gridx = 2;
		gbc_lblClienteI.gridy = 2;
		panelCentroI.add(lblClienteI, gbc_lblClienteI);
		
		comboBoxClienteI = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxClienteI = new GridBagConstraints();
		gbc_comboBoxClienteI.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxClienteI.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxClienteI.gridx = 4;
		gbc_comboBoxClienteI.gridy = 2;
		panelCentroI.add(comboBoxClienteI, gbc_comboBoxClienteI);
		
		JLabel lblPeluqueroI = new JLabel("PELUQUERO");
		GridBagConstraints gbc_lblPeluqueroI = new GridBagConstraints();
		gbc_lblPeluqueroI.insets = new Insets(0, 0, 5, 5);
		gbc_lblPeluqueroI.anchor = GridBagConstraints.EAST;
		gbc_lblPeluqueroI.gridx = 2;
		gbc_lblPeluqueroI.gridy = 3;
		panelCentroI.add(lblPeluqueroI, gbc_lblPeluqueroI);
		
		comboBoxPeluqueroI = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxPeluqueroI = new GridBagConstraints();
		gbc_comboBoxPeluqueroI.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPeluqueroI.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPeluqueroI.gridx = 4;
		gbc_comboBoxPeluqueroI.gridy = 3;
		panelCentroI.add(comboBoxPeluqueroI, gbc_comboBoxPeluqueroI);
		
		JLabel lblDiaI = new JLabel("DIA");
		GridBagConstraints gbc_lblDiaI = new GridBagConstraints();
		gbc_lblDiaI.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiaI.anchor = GridBagConstraints.EAST;
		gbc_lblDiaI.gridx = 2;
		gbc_lblDiaI.gridy = 4;
		panelCentroI.add(lblDiaI, gbc_lblDiaI);
		
		comboBoxDiaI = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxDiaI = new GridBagConstraints();
		gbc_comboBoxDiaI.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDiaI.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDiaI.gridx = 4;
		gbc_comboBoxDiaI.gridy = 4;
		panelCentroI.add(comboBoxDiaI, gbc_comboBoxDiaI);
		
		JLabel lblHoraI = new JLabel("HORA");
		GridBagConstraints gbc_lblHoraI = new GridBagConstraints();
		gbc_lblHoraI.insets = new Insets(0, 0, 5, 5);
		gbc_lblHoraI.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblHoraI.gridx = 2;
		gbc_lblHoraI.gridy = 5;
		panelCentroI.add(lblHoraI, gbc_lblHoraI);
		
		comboBoxHoraI = new JComboBox<String>();
		comboBoxHoraI.setModel(new DefaultComboBoxModel<String>(new String[] {"10", "11", "12", "13", "14", "15", "16", "17", "18"}));
		GridBagConstraints gbc_comboBoxHoraI = new GridBagConstraints();
		gbc_comboBoxHoraI.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxHoraI.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxHoraI.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxHoraI.gridx = 3;
		gbc_comboBoxHoraI.gridy = 5;
		panelCentroI.add(comboBoxHoraI, gbc_comboBoxHoraI);
		
		JLabel lblMinI = new JLabel("MINUTO");
		GridBagConstraints gbc_lblMinI = new GridBagConstraints();
		gbc_lblMinI.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinI.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblMinI.gridx = 5;
		gbc_lblMinI.gridy = 5;
		panelCentroI.add(lblMinI, gbc_lblMinI);
		
		comboBoxMinI = new JComboBox<String>();
		comboBoxMinI.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "15", "30", "45"}));
		GridBagConstraints gbc_comboBoxMinI = new GridBagConstraints();
		gbc_comboBoxMinI.fill = GridBagConstraints.BOTH;
		gbc_comboBoxMinI.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxMinI.gridx = 6;
		gbc_comboBoxMinI.gridy = 5;
		panelCentroI.add(comboBoxMinI, gbc_comboBoxMinI);
		
		JLabel lblCitaI = new JLabel("CITA");
		GridBagConstraints gbc_lblCitaI = new GridBagConstraints();
		gbc_lblCitaI.insets = new Insets(0, 0, 5, 5);
		gbc_lblCitaI.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblCitaI.gridx = 2;
		gbc_lblCitaI.gridy = 6;
		panelCentroI.add(lblCitaI, gbc_lblCitaI);
		
		comboBoxCitaI = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxCitaI = new GridBagConstraints();
		gbc_comboBoxCitaI.fill = GridBagConstraints.BOTH;
		gbc_comboBoxCitaI.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCitaI.gridx = 4;
		gbc_comboBoxCitaI.gridy = 6;
		panelCentroI.add(comboBoxCitaI, gbc_comboBoxCitaI);
		
		//Panel Modificar
		JPanel panelModificar = new JPanel();
		tabbedPane.addTab("MODIFICAR", null, panelModificar, null);
		panelModificar.setLayout(new BorderLayout(0,0));
		
		//Panel Norte Modificar
		JPanel panelNorteM = new JPanel();
		panelNorteM.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelNorteM.setBackground(new Color(205, 92, 92));
		panelModificar.add(panelNorteM, BorderLayout.NORTH);
		
		JLabel lblModificarCita = new JLabel("MODIFICAR CITA: ");
		lblModificarCita.setForeground(Color.WHITE);
		lblModificarCita.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		panelNorteM.add(lblModificarCita);
		
		//Panel Sur Modificar
		JPanel panelSurM = new JPanel();
		panelSurM.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelSurM.setBackground(new Color(205, 92, 92));
		panelModificar.add(panelSurM, BorderLayout.SOUTH);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.setForeground(Color.BLACK);
		btnModificar.setBackground(Color.WHITE);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelSurM.add(btnModificar);
		
		//Panel Centro Modificar
		JPanel panelCentroM = new JPanel();
		panelCentroM.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelModificar.add(panelCentroM, BorderLayout.CENTER);
		GridBagLayout gbl_pCentroM = new GridBagLayout();
		gbl_pCentroM.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pCentroM.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pCentroM.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pCentroM.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelCentroM.setLayout(gbl_pCentroM);
		
		JLabel lblNewLabelM = new JLabel(".");
		lblNewLabelM.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabelM = new GridBagConstraints();
		gbc_lblNewLabelM.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelM.gridx = 4;
		gbc_lblNewLabelM.gridy = 0;
		panelCentroM.add(lblNewLabelM, gbc_lblNewLabelM);
		
		JLabel lblIdM = new JLabel("ID");
		GridBagConstraints gbc_lblIdM = new GridBagConstraints();
		gbc_lblIdM.insets = new Insets(0, 0, 5, 5);
		gbc_lblIdM.gridx = 2;
		gbc_lblIdM.gridy = 1;
		panelCentroM.add(lblIdM, gbc_lblIdM);
		
		comboBoxIdM = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxIdM = new GridBagConstraints();
		gbc_comboBoxIdM.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxIdM.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxIdM.gridx = 4;
		gbc_comboBoxIdM.gridy = 1;
		panelCentroM.add(comboBoxIdM, gbc_comboBoxIdM);
		
		JLabel lblNewLabel_M = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_M = new GridBagConstraints();
		gbc_lblNewLabel_M.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_M.gridx = 1;
		gbc_lblNewLabel_M.gridy = 2;
		panelCentroM.add(lblNewLabel_M, gbc_lblNewLabel_M);
		
		JLabel lblClienteM = new JLabel("CLIENTE");
		GridBagConstraints gbc_lblClienteM = new GridBagConstraints();
		gbc_lblClienteM.insets = new Insets(0, 0, 5, 5);
		gbc_lblClienteM.gridx = 2;
		gbc_lblClienteM.gridy = 2;
		panelCentroM.add(lblClienteM, gbc_lblClienteM);
		
		comboBoxClienteM = new JComboBox<String>();
		cargarComboBoxClientes();
		GridBagConstraints gbc_comboBoxClienteM = new GridBagConstraints();
		gbc_comboBoxClienteM.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxClienteM.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxClienteM.gridx = 4;
		gbc_comboBoxClienteM.gridy = 2;
		panelCentroM.add(comboBoxClienteM, gbc_comboBoxClienteM);
		
		JLabel lblPeluqueroM = new JLabel("PELUQUERO");
		GridBagConstraints gbc_lblPeluqueroM = new GridBagConstraints();
		gbc_lblPeluqueroM.insets = new Insets(0, 0, 5, 5);
		gbc_lblPeluqueroM.gridx = 2;
		gbc_lblPeluqueroM.gridy = 3;
		panelCentroM.add(lblPeluqueroM, gbc_lblPeluqueroM);
		
		comboBoxPeluqueroM = new JComboBox<String>();
		cargarComboBoxPeluqueros();
		GridBagConstraints gbc_comboBoxPeluqueroM = new GridBagConstraints();
		gbc_comboBoxPeluqueroM.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxPeluqueroM.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxPeluqueroM.gridx = 4;
		gbc_comboBoxPeluqueroM.gridy = 3;
		panelCentroM.add(comboBoxPeluqueroM, gbc_comboBoxPeluqueroM);
		
		JLabel lblDiaM = new JLabel("DIA");
		GridBagConstraints gbc_lblDiaM = new GridBagConstraints();
		gbc_lblDiaM.insets = new Insets(0, 0, 5, 5);
		gbc_lblDiaM.gridx = 2;
		gbc_lblDiaM.gridy = 4;
		panelCentroM.add(lblDiaM, gbc_lblDiaM);
		
		comboBoxDiaM = new JComboBox<String>();
		cargarComboBoxDia();
		GridBagConstraints gbc_comboBoxDiaM = new GridBagConstraints();
		gbc_comboBoxDiaM.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxDiaM.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxDiaM.gridx = 4;
		gbc_comboBoxDiaM.gridy = 4;
		panelCentroM.add(comboBoxDiaM, gbc_comboBoxDiaM);
		
		JLabel lblHoraM = new JLabel("HORA");
		GridBagConstraints gbc_lblHoraM = new GridBagConstraints();
		gbc_lblHoraM.insets = new Insets(0, 0, 5, 5);
		gbc_lblHoraM.gridx = 2;
		gbc_lblHoraM.gridy = 5;
		panelCentroM.add(lblHoraM, gbc_lblHoraM);
		
		comboBoxHoraM = new JComboBox<String>();
		comboBoxHoraM.setModel(new DefaultComboBoxModel<String>(new String[] {"10", "11", "12", "13", "14", "15", "16", "17", "18"}));
		GridBagConstraints gbc_comboBoxHoraM = new GridBagConstraints();
		gbc_comboBoxHoraM.anchor = GridBagConstraints.NORTH;
		gbc_comboBoxHoraM.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxHoraM.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxHoraM.gridx = 3;
		gbc_comboBoxHoraM.gridy = 5;
		panelCentroM.add(comboBoxHoraM, gbc_comboBoxHoraM);
		
		JLabel lblMinM = new JLabel("MINUTO");
		GridBagConstraints gbc_lblMinM = new GridBagConstraints();
		gbc_lblMinM.insets = new Insets(0, 0, 5, 5);
		gbc_lblMinM.anchor = GridBagConstraints.NORTH;
		gbc_lblMinM.gridx = 5;
		gbc_lblMinM.gridy = 5;
		panelCentroM.add(lblMinM, gbc_lblMinM);
		
		comboBoxMinM = new JComboBox<String>();
		comboBoxMinM.setModel(new DefaultComboBoxModel<String>(new String[] {"00", "15", "30", "45"}));
		GridBagConstraints gbc_comboBoxMinM = new GridBagConstraints();
		gbc_comboBoxMinM.fill = GridBagConstraints.BOTH;
		gbc_comboBoxMinM.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxMinM.gridx = 6;
		gbc_comboBoxMinM.gridy = 5;
		panelCentroM.add(comboBoxMinM, gbc_comboBoxMinM);
		
		JLabel lblCitaM = new JLabel("CITA");
		GridBagConstraints gbc_lblCitaM = new GridBagConstraints();
		gbc_lblCitaM.fill = GridBagConstraints.VERTICAL;
		gbc_lblCitaM.insets = new Insets(0, 0, 5, 5);
		gbc_lblCitaM.gridx = 2;
		gbc_lblCitaM.gridy = 6;
		panelCentroM.add(lblCitaM, gbc_lblCitaM);
		
		comboBoxCitaM = new JComboBox<String>();
		GridBagConstraints gbc_comboBoxCitaM = new GridBagConstraints();
		gbc_comboBoxCitaM.fill = GridBagConstraints.BOTH;
		gbc_comboBoxCitaM.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCitaM.gridx = 4;
		gbc_comboBoxCitaM.gridy = 6;
		panelCentroM.add(comboBoxCitaM, gbc_comboBoxCitaM);
		cargarComboBoxCita();
		
		//Panel Borrar
		JPanel panelBorrar = new JPanel();
		tabbedPane.addTab("BORRAR", null, panelBorrar, null);
		panelBorrar.setLayout(new BorderLayout(0, 0));
		
		//Panel Norte Borrar
		JPanel panelNorteB = new JPanel();
		panelNorteB.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelNorteB.setBackground(new Color(205, 92, 92));
		panelBorrar.add(panelNorteB, BorderLayout.NORTH);
		
		JLabel lblBorrarCita = new JLabel("BORRAR CITA: ");
		lblBorrarCita.setForeground(Color.WHITE);
		lblBorrarCita.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		panelNorteB.add(lblBorrarCita);
		
		//Panel Centro Borrar
		JPanel panelCentroB = new JPanel();
		panelCentroB.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelBorrar.add(panelCentroB, BorderLayout.CENTER);
				
		JLabel lblBorrar = new JLabel("Borrar la cita numero: ");
		panelCentroB.add(lblBorrar);
		lblBorrar.setFont(new Font("Tahoma", Font.ITALIC, 14));
				
		JLabel lblIdCita = new JLabel("");
		panelCentroB.add(lblIdCita);
		
		//Panel Sur Borrar
		JPanel panelSurB = new JPanel();
		panelSurB.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelSurB.setBackground(new Color(205, 92, 92));
		panelBorrar.add(panelSurB, BorderLayout.SOUTH);
						
		JButton btnBorrar = new JButton("BORRAR");
		btnBorrar.setForeground(Color.BLACK);
		btnBorrar.setBackground(Color.WHITE);
		btnBorrar.setFont(new Font("Tahoma", Font.BOLD, 11));
		panelSurB.add(btnBorrar);
		
		//Table Panel Centro
		String [] columnas = {"ID", "CLIENTE", "PELUQUERO", "DIA", "HORA", "CITA"};
		
		modeloTabla = new DefaultTableModel(columnas, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
				}
		};
		
		tablaCitas = new JTable(modeloTabla);
		
		JScrollPane scrollTabla = new JScrollPane(tablaCitas);
		scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollTabla, BorderLayout.CENTER);
		
		//Panel Sur
		JPanel panelSur = new JPanel();
		contentPane.add(panelSur, BorderLayout.SOUTH);
		panelSur.setLayout(new GridLayout(1, 3, 0, 0));
		
		//Panel Sur Buscador
		
		JPanel panelBuscar = new JPanel();
		panelBuscar.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelBuscar.setBackground(SystemColor.inactiveCaption);
		panelSur.add(panelBuscar);
				
		JLabel lblBuscador = new JLabel("Buscar por PELUQUERO: ");
		lblBuscador.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		panelBuscar.add(lblBuscador);
				
		JTextField textFieldBuscar = new JTextField(20);
		textFieldBuscar.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				selectRows(textFieldBuscar.getText());
				tablaCitas.repaint();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				selectRows(textFieldBuscar.getText());
				tablaCitas.repaint();				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				selectRows(textFieldBuscar.getText());
				tablaCitas.repaint();				
			}
		});
		panelBuscar.add(textFieldBuscar);
			
						
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
		
		//Arbol Ficheros 
		dias = new DefaultMutableTreeNode("Dias");
		lunes = new DefaultMutableTreeNode(Dia.LUNES);
		martes = new DefaultMutableTreeNode(Dia.MARTES);
		miercoles = new DefaultMutableTreeNode(Dia.MIERCOLES);
		jueves = new DefaultMutableTreeNode(Dia.JUEVES);
		viernes = new DefaultMutableTreeNode(Dia.VIERNES);
		
		modeloArbol = new DefaultTreeModel(dias);
		modeloArbol.insertNodeInto(lunes, dias, 0);	
		modeloArbol.insertNodeInto(martes, dias, 1);
		modeloArbol.insertNodeInto(miercoles, dias, 2);	
		modeloArbol.insertNodeInto(jueves, dias, 3);
		modeloArbol.insertNodeInto(viernes, dias, 4);
		
		arbol = new JTree(modeloArbol);
		arbol.setBackground(Color.WHITE);
		panelSur.add(arbol);
		
		arbol.addTreeSelectionListener(new TreeSelectionListener() {
			
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				TreePath tp = e.getPath();
				dia = (Dia) tp.getLastPathComponent();
				if(Cita.getMapaAgenda().containsKey(dia)) {
					List<Cita> listaCitas = Cita.obtenerListaCitas(dia);
					tablaCitas.setModel((TableModel) listaCitas);
				}
			}
		});
		
	}
		
	
	public void selectRows(String selectStr) {
		logger.info("User selecting rows by peluquero containing: " + selectStr);
	}
	
	private void cargarComboBoxCita() {
		for(TipoCita n : TipoCita.values()) {
			comboBoxCitaI.addItem(n.name());
			comboBoxCitaM.addItem(n.name());
		}
	}
	private void cargarComboBoxDia() {
		for(Dia n : Dia.values()) {
			comboBoxDiaI.addItem(n.name());
			comboBoxDiaM.addItem(n.name());
		}
	}
	
	private void cargarComboBoxClientes() {
		List<Cliente> clientes = new ArrayList<>();
		Cliente.cargarClientesEnLista("resources/data/Clientes.csv");
		clientes = Cliente.getClientes();
		
		for(Cliente c : clientes) {
			comboBoxClienteI.addItem(c.getNombre() + " " + c.getApellido());
			comboBoxClienteM.addItem(c.getNombre() + " "+ c.getApellido());
		}
	}
	
	private void cargarComboBoxPeluqueros() {
		List<Peluquero> peluqueros = new ArrayList<>();
		Peluquero.cargarPeluquerosEnLista("resources/data/Peluqueros.csv");
		peluqueros = Peluquero.getPeluqueros();
		
		for(Peluquero p : peluqueros) {
			comboBoxPeluqueroI.addItem(p.getNombre() + " " + p.getApellido());
			comboBoxPeluqueroM.addItem(p.getNombre() + " " + p.getApellido());
			
		}
		
	}
	
	//TIENE FALLOS
//	private void cargarArbol() {
//		int pos = 0;
//		for (Dia dia : Cita.getMapaAgenda().keySet()) {
//			DefaultMutableTreeNode n = new DefaultMutableTreeNode(dia);
//			modeloArbol.insertNodeInto(n, (DefaultMutableTreeNode)modeloArbol.getRoot(), pos);
//			pos++;
//		}
//	}
	
	
}
