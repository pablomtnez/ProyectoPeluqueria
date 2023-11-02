package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import domain.Cliente;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.awt.Color;
import java.awt.Component;

public class VentanaClientes extends JFrame{
	
	private static Logger logger = Logger.getLogger(VentanaClientes.class.getName());
	
	private JPanel contentPane;
	private DefaultTableModel modelo;
	private JTable tablaGestionClientes;
	private JTextField textFieldNombreI, textFieldApellidoI, textFieldTelefonoInsertar, textFieldMailInsertar, textFieldNombreM, textFieldApellidoM, textFieldTelefonoModificar;
	private JDateChooser dateChooserFechaNacimientoInsertar, dateChooserFechaNacimientoModificar;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	private JComboBox<String> comboMailModificar;
	
	private List<Cliente> listaClientes;
	private Date fechaNacM;
	
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaClientes frame = new VentanaClientes();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	
	public VentanaClientes() {
		setTitle("CLIENTES");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaClientes.class.getResource("/images/logoPeluqueria.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1173, 655);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Panel Norte
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(new Color(205, 92, 92));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblTitulo = new JLabel("CLIENTES");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		panelNorte.add(lblTitulo);
		
		//Panel Este
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		panelEste.add(tabbedPane, BorderLayout.CENTER);
		
		//Panel Inicio
		JPanel panelInicio = new JPanel();
		panelInicio.setBackground(Color.WHITE);
		tabbedPane.addTab("INICIO",null, panelInicio, null);
		panelInicio.setLayout(new BorderLayout(0, 0));
		
		JLabel lblImagen = new JLabel();
		lblImagen.setIcon(new ImageIcon(VentanaInventario.class.getResource("/images/logoPeluqueria.png")));
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
		
		JLabel lblInsertarCliente = new JLabel("INSERTAR CLIENTE: ");
		lblInsertarCliente.setForeground(Color.WHITE);
		lblInsertarCliente.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		panelNorteI.add(lblInsertarCliente);
		
		//Panel Sur Insertar
		JPanel panelSurI = new JPanel();
		panelSurI.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelSurI.setBackground(new Color(205, 92, 92));
		panelInsertar.add(panelSurI, BorderLayout.SOUTH);
		
		JButton btnInsertar = new JButton("INSERTAR");
		btnInsertar.setForeground(Color.BLACK);
		btnInsertar.setBackground(Color.WHITE);
		btnInsertar.setFont(new Font("Tahoma", Font.BOLD, 11));
		
		btnInsertar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertarCliente();
				actualizarTabla(tablaGestionClientes, modelo, listaClientes);
			}
		});
		
		panelSurI.add(btnInsertar); 
		
		//Panel Centro Insertar
		JPanel panelCentroI = new JPanel();
		panelCentroI.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelInsertar.add(panelCentroI);
		GridBagLayout gbl_panelCentroI = new GridBagLayout();
		gbl_panelCentroI.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelCentroI.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelCentroI.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelCentroI.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelCentroI.setLayout(gbl_panelCentroI);
		
		JLabel lblNewLabelI = new JLabel(".");
		lblNewLabelI.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabelI = new GridBagConstraints();
		gbc_lblNewLabelI.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelI.gridx = 3;
		gbc_lblNewLabelI.gridy = 0;
		panelCentroI.add(lblNewLabelI, gbc_lblNewLabelI);
		
		JLabel lblNombreI = new JLabel("NOMBRE");
		GridBagConstraints gbc_lblNombreI = new GridBagConstraints();
		gbc_lblNombreI.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreI.anchor = GridBagConstraints.EAST;
		gbc_lblNombreI.gridx = 2;
		gbc_lblNombreI.gridy = 1;
		panelCentroI.add(lblNombreI, gbc_lblNombreI);
		
		textFieldNombreI = new JTextField();
		GridBagConstraints gbc_textFieldNombreI = new GridBagConstraints();
		gbc_textFieldNombreI.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombreI.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreI.gridx = 3;
		gbc_textFieldNombreI.gridy = 1;
		panelCentroI.add(textFieldNombreI, gbc_textFieldNombreI);
		
		JLabel lblNewLabel_I = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_I = new GridBagConstraints();
		gbc_lblNewLabel_I.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_I.gridx = 1;
		gbc_lblNewLabel_I.gridy = 2;
		panelCentroI.add(lblNewLabel_I, gbc_lblNewLabel_I);
		
		JLabel lblAPellidoI = new JLabel("APELLIDO");
		GridBagConstraints gbc_lblAPellidoI = new GridBagConstraints();
		gbc_lblAPellidoI.insets = new Insets(0, 0, 5, 5);
		gbc_lblAPellidoI.anchor = GridBagConstraints.EAST;
		gbc_lblAPellidoI.gridx = 2;
		gbc_lblAPellidoI.gridy = 2;
		panelCentroI.add(lblAPellidoI, gbc_lblAPellidoI);
	
		textFieldApellidoI = new JTextField();
		GridBagConstraints gbc_textFieldApellidoI = new GridBagConstraints();
		gbc_textFieldApellidoI.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldApellidoI.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldApellidoI.gridx = 3;
		gbc_textFieldApellidoI.gridy = 2;
		panelCentroI.add(textFieldApellidoI, gbc_textFieldApellidoI);
		
		JLabel lblFechaNacimientoI = new JLabel("FECHA NACIMIENTO");
		GridBagConstraints gbc_lblFechaNacimientoI = new GridBagConstraints();
		gbc_lblFechaNacimientoI.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaNacimientoI.anchor = GridBagConstraints.EAST;
		gbc_lblFechaNacimientoI.gridx = 2;
		gbc_lblFechaNacimientoI.gridy = 3;
		panelCentroI.add(lblFechaNacimientoI, gbc_lblFechaNacimientoI);
		
		dateChooserFechaNacimientoInsertar = new JDateChooser("dd-MM-yyyy", "##-##-####", '_');
		GridBagConstraints gbc_dateChooserFechaNacimientoInsertar = new GridBagConstraints();
		gbc_dateChooserFechaNacimientoInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooserFechaNacimientoInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooserFechaNacimientoInsertar.gridx = 3;
		gbc_dateChooserFechaNacimientoInsertar.gridy = 3;
		panelCentroI.add(dateChooserFechaNacimientoInsertar, gbc_dateChooserFechaNacimientoInsertar);
	
		JLabel lblTelefonoI = new JLabel("TELEFONO");
		GridBagConstraints gbc_lblTelefonoI = new GridBagConstraints();
		gbc_lblTelefonoI.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefonoI.anchor = GridBagConstraints.EAST;
		gbc_lblTelefonoI.gridx = 2;
		gbc_lblTelefonoI.gridy = 4;
		panelCentroI.add(lblTelefonoI, gbc_lblTelefonoI);
		
		textFieldTelefonoInsertar = new JTextField();
		GridBagConstraints gbc_textFieldTelefonoInsertar = new GridBagConstraints();
		gbc_textFieldTelefonoInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTelefonoInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTelefonoInsertar.gridx = 3;
		gbc_textFieldTelefonoInsertar.gridy = 4;
		panelCentroI.add(textFieldTelefonoInsertar, gbc_textFieldTelefonoInsertar);
		textFieldTelefonoInsertar.setColumns(10);
		
		JLabel lblMailI = new JLabel("EMAIL");
		GridBagConstraints gbc_lblMailI = new GridBagConstraints();
		gbc_lblMailI.insets = new Insets(0, 0, 5, 5);
		gbc_lblMailI.anchor = GridBagConstraints.EAST;
		gbc_lblMailI.gridx = 2;
		gbc_lblMailI.gridy = 5;
		panelCentroI.add(lblMailI, gbc_lblMailI);
		
		textFieldMailInsertar = new JTextField();
		GridBagConstraints gbc_textFieldMailInsertar = new GridBagConstraints();
		gbc_textFieldMailInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldMailInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldMailInsertar.gridx = 3;
		gbc_textFieldMailInsertar.gridy = 5;
		panelCentroI.add(textFieldMailInsertar, gbc_textFieldMailInsertar);
		
		//Panel Modificar
		JPanel panelModificar = new JPanel();
		tabbedPane.addTab("MODIFICAR", null, panelModificar, null);
		panelModificar.setLayout(new BorderLayout(0, 0));
		
		//Panel Norte Modificar
		JPanel panelNorteM = new JPanel();
		panelNorteM.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelNorteM.setBackground(new Color(205, 92, 92));
		panelModificar.add(panelNorteM, BorderLayout.NORTH);
		
		JLabel lblModificarCliente = new JLabel("MODIFICAR CLIENTE: ");
		lblModificarCliente.setForeground(Color.WHITE);
		lblModificarCliente.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		panelNorteM.add(lblModificarCliente);
		
		//Panel Sur Modificar
		JPanel panelSurM = new JPanel();
		panelSurM.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelSurM.setBackground(new Color(205, 92, 92));
		panelModificar.add(panelSurM, BorderLayout.SOUTH);
		
		JButton btnModificar = new JButton("MODIFICAR");
		btnModificar.setForeground(Color.BLACK);
		btnModificar.setBackground(Color.WHITE);
		btnModificar.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnModificar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modificarCliente();
				actualizarTabla(tablaGestionClientes, modelo, listaClientes);
			}
		});
		panelSurM.add(btnModificar); 
		
		//Panel Centro Modificar
		JPanel pCentroM = new JPanel();
		pCentroM.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelModificar.add(pCentroM);
		GridBagLayout gbl_pCentroM = new GridBagLayout();
		gbl_pCentroM.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pCentroM.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_pCentroM.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pCentroM.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		pCentroM.setLayout(gbl_pCentroM);

		JLabel lblNewLabelM = new JLabel(".");
		lblNewLabelM.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabelM = new GridBagConstraints();
		gbc_lblNewLabelM.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelM.gridx = 5;
		gbc_lblNewLabelM.gridy = 0;
		pCentroM.add(lblNewLabelM, gbc_lblNewLabelM);
		
		JLabel lblNombreM = new JLabel("NOMBRE");
		GridBagConstraints gbc_lblNombreM = new GridBagConstraints();
		gbc_lblNombreM.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreM.anchor = GridBagConstraints.EAST;
		gbc_lblNombreM.gridx = 2;
		gbc_lblNombreM.gridy = 1;
		pCentroM.add(lblNombreM, gbc_lblNombreM);
		
		textFieldNombreM = new JTextField();
		GridBagConstraints gbc_textFieldNombreM = new GridBagConstraints();
		gbc_textFieldNombreM.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombreM.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreM.gridx = 3;
		gbc_textFieldNombreM.gridy = 1;
		pCentroM.add(textFieldNombreM, gbc_textFieldNombreM);
		
		JLabel lblNewLabel_M = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_M = new GridBagConstraints();
		gbc_lblNewLabel_M.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_M.gridx = 1;
		gbc_lblNewLabel_M.gridy = 2;
		pCentroM.add(lblNewLabel_M, gbc_lblNewLabel_M);
		
		JLabel lblAPellidoM = new JLabel("APELLIDO");
		GridBagConstraints gbc_lblAPellidoM = new GridBagConstraints();
		gbc_lblAPellidoM.insets = new Insets(0, 0, 5, 5);
		gbc_lblAPellidoM.anchor = GridBagConstraints.EAST;
		gbc_lblAPellidoM.gridx = 2;
		gbc_lblAPellidoM.gridy = 2;
		pCentroM.add(lblAPellidoM, gbc_lblAPellidoM);
	
		textFieldApellidoM = new JTextField();
		GridBagConstraints gbc_textFieldApellidoM = new GridBagConstraints();
		gbc_textFieldApellidoM.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldApellidoM.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldApellidoM.gridx = 3;
		gbc_textFieldApellidoM.gridy = 2;
		pCentroM.add(textFieldApellidoM, gbc_textFieldApellidoM);
		
		JLabel lblFechaNacimientoM = new JLabel("FECHA NACIMIENTO");
		GridBagConstraints gbc_lblFechaNacimientoM = new GridBagConstraints();
		gbc_lblFechaNacimientoM.insets = new Insets(0, 0, 5, 5);
		gbc_lblFechaNacimientoM.anchor = GridBagConstraints.EAST;
		gbc_lblFechaNacimientoM.gridx = 2;
		gbc_lblFechaNacimientoM.gridy = 3;
		pCentroM.add(lblFechaNacimientoM, gbc_lblFechaNacimientoM);
		
		dateChooserFechaNacimientoModificar = new JDateChooser("dd-MM-yyyy", "##-##-####", '_');
		GridBagConstraints gbc_dateChooserFechaNacimientoModificar = new GridBagConstraints();
		gbc_dateChooserFechaNacimientoModificar.insets = new Insets(0, 0, 5, 5);
		gbc_dateChooserFechaNacimientoModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_dateChooserFechaNacimientoModificar.gridx = 3;
		gbc_dateChooserFechaNacimientoModificar.gridy = 3;
		pCentroM.add(dateChooserFechaNacimientoModificar, gbc_dateChooserFechaNacimientoModificar);
	
		JLabel lblTelefonoM = new JLabel("TELEFONO");
		GridBagConstraints gbc_lblTelefonoM = new GridBagConstraints();
		gbc_lblTelefonoM.insets = new Insets(0, 0, 5, 5);
		gbc_lblTelefonoM.anchor = GridBagConstraints.EAST;
		gbc_lblTelefonoM.gridx = 2;
		gbc_lblTelefonoM.gridy = 4;
		pCentroM.add(lblTelefonoM, gbc_lblTelefonoM);
		
		textFieldTelefonoModificar = new JTextField();
		GridBagConstraints gbc_textFieldTelefonoModificar = new GridBagConstraints();
		gbc_textFieldTelefonoModificar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldTelefonoModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldTelefonoModificar.gridx = 3;
		gbc_textFieldTelefonoModificar.gridy = 4;
		pCentroM.add(textFieldTelefonoModificar, gbc_textFieldTelefonoModificar);
		textFieldTelefonoModificar.setColumns(10);
		
		JLabel lblMailM = new JLabel("EMAIL");
		GridBagConstraints gbc_lblMailM = new GridBagConstraints();
		gbc_lblMailM.insets = new Insets(0, 0, 5, 5);
		gbc_lblMailM.anchor = GridBagConstraints.EAST;
		gbc_lblMailM.gridx = 2;
		gbc_lblMailM.gridy = 5;
		pCentroM.add(lblMailM, gbc_lblMailM);
		
		comboMailModificar = new JComboBox<String>();
		GridBagConstraints gbc_comboMailModificar = new GridBagConstraints();
		gbc_comboMailModificar.insets = new Insets(0, 0, 5, 5);
		gbc_comboMailModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboMailModificar.gridx = 3;
		gbc_comboMailModificar.gridy = 5;
		pCentroM.add(comboMailModificar, gbc_comboMailModificar);
		
		//Panel Borrar
		JPanel panelBorrar = new JPanel();
		tabbedPane.addTab("BORRAR", null, panelBorrar, null);
		panelBorrar.setLayout(new BorderLayout(0, 0));
		
		//Panel Norte Borrar
		JPanel panelNorteB = new JPanel();
		panelNorteB.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelNorteB.setBackground(new Color(205, 92, 92));
		panelBorrar.add(panelNorteB, BorderLayout.NORTH);
		
		JLabel lblBorrarCliente = new JLabel("BORRAR CLIENTE: ");
		lblBorrarCliente.setForeground(Color.WHITE);
		lblBorrarCliente.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		panelNorteB.add(lblBorrarCliente);
		
		//Panel Centro Borrar
		JPanel panelCentroB = new JPanel();
		panelCentroB.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelBorrar.add(panelCentroB, BorderLayout.CENTER);
		
		JLabel lblBorrar = new JLabel("Borrar al cliente: ");
		panelCentroB.add(lblBorrar);
		lblBorrar.setFont(new Font("Tahoma", Font.ITALIC, 14));
		
		JLabel lblNombreCliente = new JLabel("");
		panelCentroB.add(lblNombreCliente);
		
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
		
		//Tabla Panel Centro
		String [] columnas = {"NOMBRE", "APELLIDO", "FECHA NACIMIENTO", "TELEFONO", "EMAIL"};
		cargarClientes();
		listaClientes = Cliente.getClientes();
		
		modelo = new DefaultTableModel(columnas, 0) {
			
			public boolean isCellEditable(int row, int column) {
				return false;
			}
			
		};
		
		tablaGestionClientes = new JTable(modelo);
		
		Collections.sort(listaClientes, new Comparator<Cliente>() {
			public int compare (Cliente o1,Cliente o2) {
				return o1.getApellido().compareTo(o2.getApellido());
			}
		});
		
		JScrollPane scrollTabla  = new JScrollPane(tablaGestionClientes);
		scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollTabla, BorderLayout.CENTER);
		
		Object O [] = null;
		for(int i = 0; i < listaClientes.size(); i++) {
			modelo.addRow(O);
			Cliente getCliente = listaClientes.get(i);
			modelo.setValueAt(getCliente.getNombre(), i, 0);
			modelo.setValueAt(getCliente.getApellido(), i, 1);
			modelo.setValueAt(sdf.format(getCliente.getFechaNacimiento()), i, 2);
			modelo.setValueAt(getCliente.getTelefono(), i, 3);
			modelo.setValueAt(getCliente.getEmail(), i, 4);
			
			
			comboMailModificar.addItem(getCliente.getEmail());
		}
		
		//Panel Sur
		JPanel panelSur = new JPanel();
		panelSur.setLayout(new GridLayout(1, 2));
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		//Panel Buscador
		JPanel panelBuscar = new JPanel();
		panelBuscar.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelBuscar.setBackground(SystemColor.inactiveCaption);
		panelSur.add(panelBuscar, BorderLayout.NORTH);
		
		JLabel lblNewLabel_2 = new JLabel("Buscar por TELEFONO de cliente: ");
		lblNewLabel_2.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		panelBuscar.add(lblNewLabel_2);
		
		JTextField textFieldBuscar = new JTextField(20);
		panelBuscar.add(textFieldBuscar);
		
		textFieldBuscar.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				selectRows(textFieldBuscar.getText());
				tablaGestionClientes.repaint();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				selectRows(textFieldBuscar.getText());
				tablaGestionClientes.repaint();				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				selectRows(textFieldBuscar.getText());
				tablaGestionClientes.repaint();				
			}
		});
		
		//Boton Menu				
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
	
	private void actualizarTabla(JTable tablaGestionClientes, DefaultTableModel modelo, List<Cliente> listaClientes) {
		
		Object O [] = null;
		
		for(int i = 0; i < tablaGestionClientes.getRowCount(); i++) {
			modelo.removeRow(i);
			i -= 1;
		}
		for(int i = 0; i < listaClientes.size(); i++) {
			modelo.addRow(O);
			Cliente getCliente = listaClientes.get(i);
			modelo.setValueAt(getCliente.getNombre(), i, 0);
			modelo.setValueAt(getCliente.getApellido(), i, 1);
			modelo.setValueAt(sdf.format(getCliente.getFechaNacimiento()), i, 2);
			modelo.setValueAt(getCliente.getTelefono(), i, 3);
			modelo.setValueAt(getCliente.getEmail(), i, 4);
			
			comboMailModificar.addItem(getCliente.getEmail());
		}
	}
	
	private void insertarCliente() {
		
		if(!(textFieldApellidoI.getText().isEmpty()) || !(textFieldMailInsertar.getText().isEmpty()) || !(textFieldNombreI.getText().isEmpty()) || (textFieldTelefonoInsertar.getText().isEmpty())) {
			
			Cliente c = new Cliente();
			c.setNombre(textFieldNombreI.getText());
			c.setApellido(textFieldApellidoI.getText());
			c.setFechaNacimiento(dateChooserFechaNacimientoInsertar.getDate());
			String telefono = textFieldTelefonoInsertar.getText();
			c.setTelefono(Integer.parseInt(telefono));
			c.setEmail(textFieldMailInsertar.getText());
			listaClientes.add(c);
		}else {
			JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR TODOS LOS VALORES", "FALTA DATOS", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	private void modificarCliente() {
		if(!(textFieldApellidoM.getText().isEmpty()) || !(textFieldNombreM.getText().isEmpty() || !(textFieldTelefonoModificar.getText().isEmpty()))){
			String emailCliente = comboMailModificar.getSelectedItem().toString();
			if(emailCliente != null) {
				String nombre = textFieldNombreM.getText();
				String apellido = textFieldApellidoM.getText();
				fechaNacM = dateChooserFechaNacimientoModificar.getDate();
				int telf = Integer.parseInt(textFieldTelefonoModificar.getText());
				for (int i = 0; i < listaClientes.size(); i++) {
					Cliente c = listaClientes.get(i);
					if(c.getEmail().equals(emailCliente)) {
						c.setNombre(nombre);
						c.setApellido(apellido);
						c.setFechaNacimiento(fechaNacM);
						c.setTelefono(telf);
						c.setEmail(emailCliente);
					}else {
						i++;
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR UN MAIL VALIDO", "USUARIO NO ENCONTRADO", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR TODOS LOS VALORES",
					"FALTA DATOS", JOptionPane.ERROR_MESSAGE);		}
		
	}
	
	public void selectRows(String selectStr) {
		logger.info("User selecting rows by cliente containing: " + selectStr);
	}
	
	private void cargarClientes() {
		Cliente.cargarClientesEnLista("resources/data/Clientes.csv");
	}
}
