package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
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
	textFieldCantidadInsertar, textFieldBuscar;
	private List<Producto> listaProductos = new ArrayList<Producto>();
	
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
		
		//Tabla Panel Centro
		String [] columnas = {"ID", "NOMBRE", "DESCRIPCIÓN", "PRECIO", "CANTIDAD"};
		cargarProductos("resources/data/Productos.txt");
		
		modelo = new DefaultTableModel(columnas, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			public Class<?> getColumnClass(int columnas) {
				switch(columnas) {
					case 0:
						return Integer.class;
					case 1:
						return String.class;
					case 2:
						return String.class;
					case 3:
						return Double.class;
					case 4:
						return Integer.class;
					default:
						return Object.class;
				}
			}

			public boolean isCellEditable(int row, int column) {
				return true;
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
		JButton botonMenu = new JButton("GUARDAR Y VOLVER AL MENU");
		botonMenu.setBackground(new Color(205, 92, 92));
		botonMenu.setForeground(Color.WHITE);
		botonMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validarYGuardar()) {
                    JOptionPane.showMessageDialog(VentanaInventario.this,
                    		"Datos guardados en datos.csv", "Guardar", JOptionPane.INFORMATION_MESSAGE);
                }
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
			p.setPrecio(Double.parseDouble(precio));
			String cantidad = textFieldCantidadInsertar.getText();
			p.setCantidad(Integer.parseInt(cantidad));
			listaProductos.add(p);
		}else {
			JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR TODOS LOS VALORES", "FALTA DATOS", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void selectRows(String selectStr) {
		logger.info("User selecting rows by product containing: " + selectStr);
	}
	
	private void cargarProductos(String filePath) {
//		Producto.cargarProductosenLista("resources/data/Productos.txt");
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
			reader.readLine();
			String line;
            while ((line = reader.readLine()) != null) {
            	String[] data = line.split(";");
            	 if (data.length == 5) {
            		 Producto producto = new Producto();
            		 producto.setId(Integer.parseInt(data[0]));
            		 producto.setNombre(data[1]);
            		 producto.setDescripcion(data[2]);
            		 producto.setPrecio(Double.parseDouble(data[3]));
            		 producto.setCantidad(Integer.parseInt(data[4]));
            		 listaProductos.add(producto);
            	 }else {
                     // Manejar el caso en el que la línea del archivo no tenga el formato esperado
                     // Puedes imprimir un mensaje de error o lanzar una excepción según tus necesidades.
                     System.err.println("Error en el formato de la línea txt: " + line);
            	 }
            }
		} catch (IOException e) {
			 e.printStackTrace();
		}catch(Exception e) {
            e.printStackTrace();
            // Manejar otras posibles excepciones (parsing de fecha, parsing de número, etc.)
        }
			
	}
	
	private boolean validarYGuardar() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/data/Productos.txt"))){
			for (int i = 0; i<modelo.getColumnCount(); i++) {
				writer.write(modelo.getColumnName(i));
				if(i<modelo.getColumnCount()-1) {
					writer.write(",");
				}
			}
			writer.newLine();
			for (int i = 0; i < modelo.getRowCount(); i++) {
				for (int j = 0; j < modelo.getColumnCount(); j++) {
					Object value = modelo.getValueAt(i, j);
					if(j == 0) {
						if(!(value instanceof Integer)){
							JOptionPane.showMessageDialog(this, "El id debe ser un Integer en la fila " + (i+1), "Error de validacion", JOptionPane.ERROR_MESSAGE);
							return false;
						}else {
							writer.write(value.toString());
						}
					}else if(j == 1) {
						if(!(value instanceof String)) {
							JOptionPane.showMessageDialog(this, "El nombre debe ser un String en la fila " + (i+1), "Error de validacion", JOptionPane.ERROR_MESSAGE);
							return false;
						}else {
							writer.write(value.toString());
						}
					}else if(j == 2) {
						if(!(value instanceof String)) {
							JOptionPane.showMessageDialog(this, "La descripcion debe ser un String en la fila " + (i+1), "Error de validacion", JOptionPane.ERROR_MESSAGE);
							return false;
						}else {
							writer.write(value.toString());
						}
					}else if(j == 3) {
						if(!(value instanceof Double)) {
							JOptionPane.showMessageDialog(this, "El precio debe ser un Double en la fila " + (i+1), "Error de validacion", JOptionPane.ERROR_MESSAGE);
							return false;
						}else {
							writer.write(value.toString());
						}	
					}else if(j == 4) {
						if(!(value instanceof Integer)) {
							JOptionPane.showMessageDialog(this, "La cantidad debe ser un Integer en la fila " + (i+1), "Error de validacion", JOptionPane.ERROR_MESSAGE);
							return false;
						}else {
							writer.write(value.toString());
						}	
					}
					 if (j < modelo.getColumnCount() - 1) {
				            writer.write(";");
					}
				}
				writer.newLine();
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al guardar los datos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
		}
		
	}

}
