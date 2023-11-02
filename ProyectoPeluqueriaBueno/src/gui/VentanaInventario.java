package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;

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
import javax.swing.table.TableCellRenderer;

import domain.Producto;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class VentanaInventario extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private static Logger logger = Logger.getLogger(VentanaInventario.class.getName());

	
	private JPanel contentPane;
	private DefaultTableModel modelo;
	private JTable tablaGestionProductos;
	private JTextField textFieldCodigoInsertar, textFieldNombreInsertar, textFieldDescripcionInsertar, textFieldPrecioInsertar, 
	textFieldCantidadInsertar, textFieldNombreModificar,textFieldDescripcionModificar,textFieldPrecioModificar, 
	textFieldCantidadModificar, textFieldBuscar;
	private JComboBox <Integer> comboBoxCodigoModificar;
	
	private List<Producto> listaProductos;

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					VentanaInventario frame = new VentanaInventario();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public VentanaInventario() {
		setTitle("INVENTARIO");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaInventario.class.getResource("/images/logoPeluqueria.png")));
		setBounds(100, 100, 1400, 598);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//Panel Norte
		JPanel panelNorte = new JPanel();
		panelNorte.setBackground(new Color(205, 92, 92));
		contentPane.add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new GridLayout(0, 1, 0, 0));
		
		JLabel lblTitulo = new JLabel("Inventario");
		lblTitulo.setForeground(Color.WHITE);
		lblTitulo.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		panelNorte.add(lblTitulo);
		
		//Panel Este
		JPanel panelEste = new JPanel();
		contentPane.add(panelEste, BorderLayout.EAST);
		panelEste.setLayout(new BorderLayout());
		
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
		panelInsertar.setBackground(Color.WHITE);
		tabbedPane.addTab("INSERTAR", null, panelInsertar, null);
		panelInsertar.setLayout(new BorderLayout(0, 0));
		
		//Panel Norte Insertar
		JPanel panelNorteI = new JPanel();
		panelNorteI.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelNorteI.setBackground(new Color(205, 92, 92));
		panelInsertar.add(panelNorteI, BorderLayout.NORTH);
		
		JLabel lblInsertarProducto = new JLabel("INSERTAR PRODUCTO: ");
		lblInsertarProducto.setForeground(Color.WHITE);
		lblInsertarProducto.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		panelNorteI.add(lblInsertarProducto);
		
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
				insertarProducto();
				actualizarTabla(tablaGestionProductos, modelo, listaProductos);
			}
		});
		
		panelSurI.add(btnInsertar);
		
		//Panel Centro Insertar
		JPanel panelCentroI = new JPanel();
		panelCentroI.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelInsertar.add(panelCentroI, BorderLayout.CENTER);
		GridBagLayout gbl_pCentroI = new GridBagLayout();
		gbl_pCentroI.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_pCentroI.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0 };
		gbl_pCentroI.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_pCentroI.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelCentroI.setLayout(gbl_pCentroI);
		
		JLabel lblNewLabelI = new JLabel(".");
		lblNewLabelI.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 3;
		gbc_lblNewLabel.gridy = 0;
		panelCentroI.add(lblNewLabelI, gbc_lblNewLabel);
		
		JLabel lblCodigo = new JLabel("CÓDIGO");
		GridBagConstraints gbc_lblCodigo = new GridBagConstraints();
		gbc_lblCodigo.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigo.anchor = GridBagConstraints.EAST;
		gbc_lblCodigo.gridx = 2;
		gbc_lblCodigo.gridy = 1;
		panelCentroI.add(lblCodigo, gbc_lblCodigo);
		
		textFieldCodigoInsertar = new JTextField();
		GridBagConstraints gbc_textFieldCodigoInsertar = new GridBagConstraints();
		gbc_textFieldCodigoInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCodigoInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCodigoInsertar.gridx = 3;
		gbc_textFieldCodigoInsertar.gridy = 1;
		panelCentroI.add(textFieldCodigoInsertar, gbc_textFieldCodigoInsertar);
		textFieldCodigoInsertar.setColumns(10);
		
		JLabel lblNewLabel_I = new JLabel("");
		GridBagConstraints gbc_lblNewLabel_I = new GridBagConstraints();
		gbc_lblNewLabel_I.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_I.gridx = 1;
		gbc_lblNewLabel_I.gridy = 2;
		panelCentroI.add(lblNewLabel_I, gbc_lblNewLabel_I);
		
		JLabel lblNombreI = new JLabel("NOMBRE");
		GridBagConstraints gbc_lblNombreI = new GridBagConstraints();
		gbc_lblNombreI.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreI.anchor = GridBagConstraints.EAST;
		gbc_lblNombreI.gridx = 2;
		gbc_lblNombreI.gridy = 2;
		panelCentroI.add(lblNombreI, gbc_lblNombreI);
	
		textFieldNombreInsertar = new JTextField();
		GridBagConstraints gbc_textFieldNombreInsertar = new GridBagConstraints();
		gbc_textFieldNombreInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombreInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreInsertar.gridx = 3;
		gbc_textFieldNombreInsertar.gridy = 2;
		panelCentroI.add(textFieldNombreInsertar, gbc_textFieldNombreInsertar);
		textFieldNombreInsertar.setColumns(10);
		
		JLabel lblDescripcion = new JLabel("DESCRIPCIÓN");
		GridBagConstraints gbc_lblDescripcion = new GridBagConstraints();
		gbc_lblDescripcion.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcion.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcion.gridx = 2;
		gbc_lblDescripcion.gridy = 3;
		panelCentroI.add(lblDescripcion, gbc_lblDescripcion);
		
		textFieldDescripcionInsertar = new JTextField();
		GridBagConstraints gbc_textFieldDescripcionInsertar = new GridBagConstraints();
		gbc_textFieldDescripcionInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDescripcionInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescripcionInsertar.gridx = 3;
		gbc_textFieldDescripcionInsertar.gridy = 3;
		panelCentroI.add(textFieldDescripcionInsertar, gbc_textFieldDescripcionInsertar);
		textFieldDescripcionInsertar.setColumns(10);
			
		JLabel lblPrecio = new JLabel("PRECIO");
		GridBagConstraints gbc_lblPrecio = new GridBagConstraints();
		gbc_lblPrecio.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrecio.anchor = GridBagConstraints.EAST;
		gbc_lblPrecio.gridx = 2;
		gbc_lblPrecio.gridy = 4;
		panelCentroI.add(lblPrecio, gbc_lblPrecio);
			
		textFieldPrecioInsertar = new JTextField();
		GridBagConstraints gbc_textFieldPrecioInsertar = new GridBagConstraints();
		gbc_textFieldPrecioInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPrecioInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPrecioInsertar.gridx = 3;
		gbc_textFieldPrecioInsertar.gridy = 4;
		panelCentroI.add(textFieldPrecioInsertar, gbc_textFieldPrecioInsertar);
		textFieldPrecioInsertar.setColumns(10);
		
		JLabel lblCantidad = new JLabel("CANTIDAD");
		GridBagConstraints gbc_lblCantidad = new GridBagConstraints();
		gbc_lblCantidad.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidad.anchor = GridBagConstraints.EAST;
		gbc_lblCantidad.gridx = 2;
		gbc_lblCantidad.gridy = 5;
		panelCentroI.add(lblCantidad, gbc_lblCantidad);
		
		textFieldCantidadInsertar = new JTextField();
		GridBagConstraints gbc_textFieldCantidadInsertar = new GridBagConstraints();
		gbc_textFieldCantidadInsertar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCantidadInsertar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCantidadInsertar.gridx = 3;
		gbc_textFieldCantidadInsertar.gridy = 5;
		panelCentroI.add(textFieldCantidadInsertar, gbc_textFieldCantidadInsertar);
		textFieldCantidadInsertar.setColumns(10);
		
		//Panel Modificar
		JPanel panelModificar = new JPanel();
		tabbedPane.addTab("MODIFICAR", null, panelModificar, null);
		panelModificar.setLayout(new BorderLayout(0, 0));
		
		//Panel Norte Modificar
		JPanel panelNorteM = new JPanel();
		panelNorteM.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelNorteM.setBackground(new Color(205, 92, 92));
		panelModificar.add(panelNorteM, BorderLayout.NORTH);
		
		JLabel lblModificarProducto = new JLabel("MODIFICAR PRODUCTO: ");
		lblModificarProducto.setForeground(Color.WHITE);
		lblModificarProducto.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		panelNorteM.add(lblModificarProducto);
		
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
				modificarProducto();
				actualizarTabla(tablaGestionProductos, modelo, listaProductos);
			}
		});
		
		panelSurM.add(btnModificar);
		
		//Panel Centro Modificar
		JPanel panelCentroM = new JPanel();
		panelCentroM.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelModificar.add(panelCentroM, BorderLayout.CENTER);
		GridBagLayout gbl_panelCentroM = new GridBagLayout();
		gbl_panelCentroM.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelCentroM.rowHeights = new int[] { 0, 0, 0, 0, 0, 0, 0, 0 };
		gbl_panelCentroM.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_panelCentroM.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelCentroM.setLayout(gbl_panelCentroM);
		
		JLabel lblNewLabelM = new JLabel(".");
		lblNewLabelM.setForeground(Color.WHITE);
		GridBagConstraints gbc_lblNewLabelM = new GridBagConstraints();
		gbc_lblNewLabelM.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabelM.gridx = 5;
		gbc_lblNewLabelM.gridy = 0;
		panelCentroM.add(lblNewLabelM, gbc_lblNewLabelM);
		
		JLabel lblCodigoM = new JLabel("CÓDIGO");
		GridBagConstraints gbc_lblCodigoM = new GridBagConstraints();
		gbc_lblCodigoM.insets = new Insets(0, 0, 5, 5);
		gbc_lblCodigoM.anchor = GridBagConstraints.EAST;
		gbc_lblCodigoM.gridx = 3;
		gbc_lblCodigoM.gridy = 1;
		panelCentroM.add(lblCodigoM, gbc_lblCodigoM);
		
		comboBoxCodigoModificar = new JComboBox<Integer>();
//		cargarCB_Codigo_Modificar();
		GridBagConstraints gbc_comboBoxCodigoModificar = new GridBagConstraints();
		gbc_comboBoxCodigoModificar.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCodigoModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBoxCodigoModificar.gridx = 5;
		gbc_comboBoxCodigoModificar.gridy = 1;
		panelCentroM.add(comboBoxCodigoModificar, gbc_comboBoxCodigoModificar);
			
		JLabel lblNombreM = new JLabel("NOMBRE");
		GridBagConstraints gbc_lblNombreM = new GridBagConstraints();
		gbc_lblNombreM.insets = new Insets(0, 0, 5, 5);
		gbc_lblNombreM.anchor = GridBagConstraints.EAST;
		gbc_lblNombreM.gridx = 3;
		gbc_lblNombreM.gridy = 2;
		panelCentroM.add(lblNombreM, gbc_lblNombreM);
		
		textFieldNombreModificar = new JTextField();
		GridBagConstraints gbc_textFieldNombreModificar = new GridBagConstraints();
		gbc_textFieldNombreModificar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldNombreModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldNombreModificar.gridx = 5;
		gbc_textFieldNombreModificar.gridy = 2;
		panelCentroM.add(textFieldNombreModificar, gbc_textFieldNombreModificar);
		textFieldNombreModificar.setColumns(10);
		
		JLabel lblDescripcionM = new JLabel("DESCRIPCIÓN");
		GridBagConstraints gbc_lblDescripcionM = new GridBagConstraints();
		gbc_lblDescripcionM.insets = new Insets(0, 0, 5, 5);
		gbc_lblDescripcionM.anchor = GridBagConstraints.EAST;
		gbc_lblDescripcionM.gridx = 3;
		gbc_lblDescripcionM.gridy = 3;
		panelCentroM.add(lblDescripcionM, gbc_lblDescripcionM);
		
		textFieldDescripcionModificar = new JTextField();
		GridBagConstraints gbc_textFieldDescripcionModificar = new GridBagConstraints();
		gbc_textFieldDescripcionModificar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldDescripcionModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDescripcionModificar.gridx = 5;
		gbc_textFieldDescripcionModificar.gridy = 3;
		panelCentroM.add(textFieldDescripcionModificar, gbc_textFieldDescripcionModificar);
		textFieldDescripcionModificar.setColumns(10);
		
		JLabel lblPacienteM = new JLabel("");
		GridBagConstraints gbc_lblPacienteM = new GridBagConstraints();
		gbc_lblPacienteM.insets = new Insets(0, 0, 5, 5);
		gbc_lblPacienteM.gridx = 2;
		gbc_lblPacienteM.gridy = 4;
		panelCentroM.add(lblPacienteM, gbc_lblPacienteM);
		
		JLabel lblPrecioM = new JLabel("PRECIO");
		GridBagConstraints gbc_lblPrecioM = new GridBagConstraints();
		gbc_lblPrecioM.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrecioM.anchor = GridBagConstraints.EAST;
		gbc_lblPrecioM.gridx = 3;
		gbc_lblPrecioM.gridy = 4;
		panelCentroM.add(lblPrecioM, gbc_lblPrecioM);
		
		textFieldPrecioModificar = new JTextField();
		GridBagConstraints gbc_textFieldPrecioModificar = new GridBagConstraints();
		gbc_textFieldPrecioModificar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPrecioModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPrecioModificar.gridx = 5;
		gbc_textFieldPrecioModificar.gridy = 4;
		panelCentroM.add(textFieldPrecioModificar, gbc_textFieldPrecioModificar);
		textFieldPrecioModificar.setColumns(10);
		
		JLabel lblCantidadM = new JLabel("CANTIDAD");
		GridBagConstraints gbc_lblCantidadM = new GridBagConstraints();
		gbc_lblCantidadM.insets = new Insets(0, 0, 5, 5);
		gbc_lblCantidadM.anchor = GridBagConstraints.EAST;
		gbc_lblCantidadM.gridx = 3;
		gbc_lblCantidadM.gridy = 5;
		panelCentroM.add(lblCantidadM, gbc_lblCantidadM);
		
		textFieldCantidadModificar = new JTextField();
		GridBagConstraints gbc_textFieldCantidadModificar = new GridBagConstraints();
		gbc_textFieldCantidadModificar.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldCantidadModificar.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldCantidadModificar.gridx = 5;
		gbc_textFieldCantidadModificar.gridy = 5;
		panelCentroM.add(textFieldCantidadModificar, gbc_textFieldCantidadModificar);
		textFieldCantidadModificar.setColumns(10);
		
		//Panel Borrar
		JPanel panelBorrar = new JPanel();
		tabbedPane.addTab("BORRAR", null, panelBorrar, null);
		panelBorrar.setLayout(new BorderLayout(0, 0));
		
		//Panel Norte Borrar
		JPanel panelNorteB = new JPanel();
		panelNorteB.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelNorteB.setBackground(new Color(205, 92, 92));
		panelBorrar.add(panelNorteB, BorderLayout.NORTH);
		
		JLabel lblBorrarProducto = new JLabel("BORRAR PRODUCTO: ");
		lblBorrarProducto.setForeground(Color.WHITE);
		lblBorrarProducto.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 18));
		panelNorteB.add(lblBorrarProducto);
		
		//Panel Centro Borrar
		JPanel panelCentroB = new JPanel();
		panelCentroB.setBorder(new LineBorder(SystemColor.activeCaptionText));
		panelBorrar.add(panelCentroB, BorderLayout.CENTER);
		
		JLabel lblBorrar = new JLabel("Borrar al producto: ");
		panelCentroB.add(lblBorrar);
		lblBorrar.setFont(new Font("Tahoma", Font.ITALIC, 14));
		
		JLabel lblNombreProdcuto = new JLabel("");
		panelCentroB.add(lblNombreProdcuto);
		
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
		String [] columnas = {"ID", "NOMBRE", "DESCRIPCIÓN", "PRECIO", "CANTIDAD"};
		cargarProductos();
		listaProductos = Producto.getProductos();
		
		modelo = new DefaultTableModel(columnas, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		
		tablaGestionProductos = new JTable(modelo);
		
		Collections.sort(listaProductos, new Comparator<Producto>() {
			@Override
			public int compare(Producto o1, Producto o2) {
				// TODO Auto-generated method stub
				return o1.getNombre().compareTo(o2.getNombre());
			}
			
		});
		
		JScrollPane scrollTabla  = new JScrollPane(tablaGestionProductos);
		scrollTabla.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollTabla.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollTabla, BorderLayout.CENTER);
		
		Object O [] = null;
		for (int i = 0; i<listaProductos.size(); i++) {
			modelo.addRow(O);
			Producto getProducto = listaProductos.get(i);
			modelo.setValueAt(getProducto.getId(), i, 0);
			modelo.setValueAt(getProducto.getNombre(), i, 1);
			modelo.setValueAt(getProducto.getDescripcion(), i, 2);
			modelo.setValueAt(getProducto.getPrecio(), i, 3);
			modelo.setValueAt(getProducto.getCantidad(), i, 4);
			
			comboBoxCodigoModificar.addItem(getProducto.getId());
		}
		
		TableCellRenderer renderer = (table, value, selected, focus, row, column) ->{
			JLabel label = new JLabel(value.toString());
			
			if(!textFieldBuscar.getText().isBlank() && table.getValueAt(row, 0).toString().contains(textFieldBuscar.getText())) {
				Color colorAzul = new Color(62, 185, 230);
				label.setBackground(colorAzul);
			}
			
			if(column == 4) {
				int cantidad = (int) modelo.getValueAt(row, 4);
				if(cantidad < 16) {
					label.setForeground(Color.RED);
				} else if (cantidad < 50) {
					Color colorNaranja = new Color(242, 195, 19);
					label.setForeground(colorNaranja);
				} else {
					Color colorVerde = new Color(50, 162, 41);
					label.setForeground(colorVerde);
				}
			} 
			
			if (column == 0 || column == 1 || column == 2 || column == 3 || column == 4) {
				label.setHorizontalAlignment(JLabel.CENTER);
			}
			
			label.setOpaque(true);
			
			return label;
		};
		
		tablaGestionProductos.setDefaultRenderer(Object.class, renderer);
		
		
		//Panel Sur
		JPanel panelSur = new JPanel();
		panelSur.setLayout(new GridLayout(1,2));
		contentPane.add(panelSur, BorderLayout.SOUTH);
		
		//Panel Buscar
		JPanel panelBuscar = new JPanel();
		panelBuscar.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelBuscar.setBackground(SystemColor.inactiveCaption);
		panelSur.add(panelBuscar, BorderLayout.NORTH);
		
		JLabel lblBuscador= new JLabel("Buscar por ID de producto: ");
		lblBuscador.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		panelBuscar.add(lblBuscador);
		
		textFieldBuscar = new JTextField(20);
		panelBuscar.add(textFieldBuscar);
		textFieldBuscar.getDocument().addDocumentListener(new DocumentListener() {
			
			@Override
			public void removeUpdate(DocumentEvent e) {
				selectRows(textFieldBuscar.getText());
				tablaGestionProductos.repaint();
			}
			
			@Override
			public void insertUpdate(DocumentEvent e) {
				selectRows(textFieldBuscar.getText());
				tablaGestionProductos.repaint();				
			}
			
			@Override
			public void changedUpdate(DocumentEvent e) {
				selectRows(textFieldBuscar.getText());
				tablaGestionProductos.repaint();				
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
	
	private void actualizarTabla(JTable tablaGestionProductos, DefaultTableModel modelo, List<Producto>listaProductos) {
	
		Object O [] = null;
		
		for(int i = 0; i < tablaGestionProductos.getRowCount(); i++) {
			modelo.removeRow(i);
			i -= 1;
		}
		
		for(int i = 0; i < listaProductos.size(); i++) {
			modelo.addRow(O);
			Producto getProducto = listaProductos.get(i);
			modelo.setValueAt(getProducto.getId(), i, 0);
			modelo.setValueAt(getProducto.getNombre(), i, 1);
			modelo.setValueAt(getProducto.getDescripcion(), i, 2);
			modelo.setValueAt(getProducto.getPrecio(), i, 3);
			modelo.setValueAt(getProducto.getCantidad(), i, 4);
			
			comboBoxCodigoModificar.addItem(getProducto.getId());
		}
		
	}
	
	private void insertarProducto() {
		
		if(!(textFieldCantidadInsertar.getText().isEmpty()) || !(textFieldCodigoInsertar.getText().isEmpty()) || !(textFieldDescripcionInsertar.getText().isEmpty()) || !(textFieldNombreInsertar.getText().isEmpty()) || !(textFieldPrecioInsertar.getText().isEmpty())) {
			
			Producto p = new Producto();
			String id = textFieldCodigoInsertar.getText();
			p.setId(Integer.parseInt(id));
			p.setNombre(textFieldNombreInsertar.getText());
			p.setDescripcion(textFieldDescripcionInsertar.getText());
			String precio = textFieldPrecioInsertar.getText();
			p.setPrecio(Float.parseFloat(precio));
			String cantidad = textFieldCantidadInsertar.getText();
			p.setCantidad(Integer.parseInt(cantidad));
			listaProductos.add(p);
		}else {
			JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR TODOS LOS VALORES", "FALTA DATOS", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void modificarProducto() {
		if(!(textFieldCantidadModificar.getText().isEmpty()) || !(textFieldDescripcionModificar.getText().isEmpty()) || !(textFieldNombreModificar.getText().isEmpty()) || !(textFieldPrecioModificar.getText().isEmpty())) {
			int id = (int) comboBoxCodigoModificar.getSelectedItem();
			if(id != 0) {
				String nombre = textFieldNombreModificar.getText();
				String desc = textFieldDescripcionModificar.getText();
				float precio = Float.parseFloat(textFieldPrecioModificar.getText());
				int cantidad = Integer.parseInt(textFieldCantidadModificar.getText());
				for(int i = 0; i < listaProductos.size(); i++) {
					Producto p = listaProductos.get(i);
					if(p.getId() == id) {
						p.setNombre(nombre);
						p.setDescripcion(desc);
						p.setPrecio(precio);
						p.setCantidad(cantidad);
					}else {
						i++;
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR UN ID VALIDO", "PRODUCTO NO ENCONTRADO", JOptionPane.ERROR_MESSAGE);
			}
		}else {
			JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR TODOS LOS VALORES",
					"FALTA DATOS", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void selectRows(String selectStr) {
		logger.info("User selecting rows by product containing: " + selectStr);
	}
	
	private void cargarProductos() {
		Producto.cargarProductosenLista("resources/data/Productos.txt");
	}

}
