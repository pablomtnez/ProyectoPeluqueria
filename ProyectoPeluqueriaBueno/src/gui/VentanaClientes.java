package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.SystemColor;

import javax.swing.AbstractCellEditor;
import javax.swing.DefaultCellEditor;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
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
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;

import com.toedter.calendar.JDateChooser;

import domain.Cliente;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import java.awt.Color;
import java.awt.Component;

public class VentanaClientes extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(VentanaClientes.class.getName());
	
	private JPanel contentPane;
	private DefaultTableModel modelo;
	private JTable tablaGestionClientes;
	private JTextField textFieldNombreI, textFieldApellidoI, textFieldTelefonoInsertar, textFieldMailInsertar, textFieldBuscar;
	private JDateChooser dateChooserFechaNacimientoInsertar, dateChooserFechaNacimientoModificar;
	private SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	private List<Cliente> listaClientes = new ArrayList<>();;
	private JButton btnBorrar;
	
	public VentanaClientes() {
		setTitle("CLIENTES");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaClientes.class.getResource("/images/logoPeluqueria.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1400, 655);
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
		
		//Tabla Panel Centro
		String [] columnas = {"NOMBRE", "APELLIDO", "FECHA NACIMIENTO", "TELEFONO", "EMAIL", "BORRAR"};
		cargarClientes("resources/data/Clientes.csv");
		
		modelo = new DefaultTableModel(columnas, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			public Class<?> getColumnClass(int columnas) {
				
				switch(columnas) {
				case 0:
					return String.class;
				case 1:
					return String.class;
				case 2:
					return Date.class;
				case 3:
					return Integer.class;
				case 4:
					return String.class;
				default:
					return Object.class;
				}
				
			}
			
			public boolean isCellEditable(int row, int column) {
				return true;
			}
			
		};
		
		tablaGestionClientes = new JTable(modelo);
		
		TableColumn dateColumn = tablaGestionClientes.getColumnModel().getColumn(2);
		dateColumn.setCellEditor(new DateCellEditor());
		dateColumn.setCellRenderer(new DateCellRender());
		
		btnBorrar = new JButton("BORRAR");
		TableColumn borrarColumn = tablaGestionClientes.getColumnModel().getColumn(5);
		borrarColumn.setCellRenderer(new ButtonRenderer());
        borrarColumn.setCellEditor(new ButtonEditor(new JCheckBox()));

		
		Collections.sort(listaClientes, new Comparator<Cliente>() {
			public int compare (Cliente o1,Cliente o2) {
				return o1.getApellido().compareTo(o2.getApellido());
			}
		});
		
		TableCellRenderer renderer = (table, value, selected, focus, row, column) ->{
			JLabel label = new JLabel(value.toString());
			if(!textFieldBuscar.getText().isBlank() && table.getValueAt(row, 3).toString().contains(textFieldBuscar.getText())) {
				label.setBackground(Color.GREEN);
			}
			
			label.setOpaque(true);
			
			return label;
		};
		
		tablaGestionClientes.setDefaultRenderer(Object.class, renderer);
		
		tablaGestionClientes.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		        int column = tablaGestionClientes.getColumnModel().getColumnIndexAtX(evt.getX());
		        int row = evt.getY() / tablaGestionClientes.getRowHeight();
		        // Verificar si se hizo clic en la columna "BORRAR"
		        if (column == 5 && row < tablaGestionClientes.getRowCount()) {
		            // Obtener el cliente de la lista y eliminar la fila
		            Cliente clienteAEliminar = listaClientes.get(row);
		            listaClientes.remove(clienteAEliminar);
		            modelo.removeRow(row);
		        }
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
			modelo.setValueAt(getCliente.getFechaNacimiento(), i, 2);
			modelo.setValueAt(getCliente.getTelefono(), i, 3);
			modelo.setValueAt(getCliente.getEmail(), i, 4);
			modelo.setValueAt("BORRAR", i, 5);
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
		
		textFieldBuscar = new JTextField(20);
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
		JButton botonMenu = new JButton("GUARDAR Y VOLVER AL MENU");
		botonMenu.setBackground(new Color(205, 92, 92));
		botonMenu.setForeground(Color.WHITE);
		botonMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (validarYGuardar()) {
                    JOptionPane.showMessageDialog(VentanaClientes.this,
                    		"Datos guardados en Clientes.csv", "Guardar", JOptionPane.INFORMATION_MESSAGE);
                }
				VentanaPrincipal vp = new VentanaPrincipal();
				vp.setVisible(true);
				setVisible(false);
			}
		});
		panelSur.add(botonMenu);
	}
	
	private void actualizarTabla(JTable tablaGestionClientes, DefaultTableModel modelo, List<Cliente> listaClientes) {
	    // Borra todas las filas existentes en el modelo
	    modelo.setRowCount(0);

	    for (int i = 0; i < listaClientes.size(); i++) {
	        Cliente getCliente = listaClientes.get(i);
	        // Agrega una nueva fila al modelo con los datos del cliente
	        modelo.addRow(new Object[]{
	                getCliente.getNombre(),
	                getCliente.getApellido(),
	                getCliente.getFechaNacimiento(),
	                getCliente.getTelefono(),
	                getCliente.getEmail(),
	                btnBorrar // Agrega el botón de borrar en la última columna
	        });
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
	
	public void selectRows(String selectStr) {
		logger.info("User selecting rows by cliente containing: " + selectStr);
	}
	
	private void cargarClientes(String filePath) {
//		Cliente.cargarClientesEnLista("resources/data/Clientes.csv");
		
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
			reader.readLine();
			String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 5) { // Asumiendo que hay 5 campos en cada línea (ajusta según tu CSV)
                    Cliente cliente = new Cliente();
                    cliente.setNombre(data[0]);
                    cliente.setApellido(data[1]);
                    cliente.setFechaNacimiento(sdf.parse(data[2])); // Asegúrate de manejar la excepción ParseException
                    cliente.setTelefono(Integer.parseInt(data[3]));
                    cliente.setEmail(data[4]);
                    listaClientes.add(cliente);
                } else {
                    // Manejar el caso en el que la línea del archivo no tenga el formato esperado
                    // Puedes imprimir un mensaje de error o lanzar una excepción según tus necesidades.
                    System.err.println("Error en el formato de la línea CSV: " + line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Manejar la excepción de lectura del archivo
        } catch (Exception e) {
            e.printStackTrace();
            // Manejar otras posibles excepciones (parsing de fecha, parsing de número, etc.)
        }
	}
	
	private class DateCellEditor extends AbstractCellEditor implements TableCellEditor{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public DateCellEditor() {
			dateChooserFechaNacimientoModificar = new JDateChooser();
			dateChooserFechaNacimientoModificar.setDateFormatString("dd-MM-yyyy");
		}
		
		@Override
		public Object getCellEditorValue() {
			return dateChooserFechaNacimientoModificar.getDate();
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			if(value instanceof Date) {
				dateChooserFechaNacimientoModificar.setDate((Date) value);
			}
			return dateChooserFechaNacimientoModificar;
		}
		
	}
	
	private class DateCellRender extends JDateChooser implements TableCellRenderer{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			if (value instanceof Date) {
				String fechaFormateada = sdf.format((Date) value);
				setDate(parseDate(fechaFormateada));
			}
			
			return this;
		}
		
		private Date parseDate(String fechaFormateada) {
			try {
				return sdf.parse(fechaFormateada);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		
	}
	
	private boolean validarYGuardar() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/data/Clientes.csv"))){
			for (int i = 0; i<modelo.getColumnCount(); i++) {
				writer.write(modelo.getColumnName(i));
				if(i<modelo.getColumnCount()-1) {
					writer.write(",");
				}
			}
			
			writer.newLine();
			for (int i = 0; i < modelo.getRowCount(); i++) {
			    for (int j = 0; j < 5; j++) {
					Object value = modelo.getValueAt(i, j);
					if(j == 0) {
						if(!(value instanceof String)){
							JOptionPane.showMessageDialog(this, "El nombre debe ser un String en la fila " + (i+1), "Error de validacion", JOptionPane.ERROR_MESSAGE);
							return false;
						}else {
							writer.write(value.toString());
						}
					}else if(j == 1) {
						if(!(value instanceof String)) {
							JOptionPane.showMessageDialog(this, "El apellido debe ser un String en la fila " + (i+1), "Error de validacion", JOptionPane.ERROR_MESSAGE);
							return false;
						}else {
							writer.write(value.toString());
						}
					}else if(j == 2){
						if(value instanceof Date) {
							writer.write(sdf.format((Date) value));
						}else {
							JOptionPane.showMessageDialog(this, "La fecha de nacimiento debe ser un Date en la fila " + (i+1), "Error de validacion", JOptionPane.ERROR_MESSAGE);
							return false;
						}
					}else if(j == 3) {
						if(!(value instanceof Integer)) {
							JOptionPane.showMessageDialog(this, "El numero debe ser un Integer en la fila " + (i+1), "Error de validacion", JOptionPane.ERROR_MESSAGE);
							return false;
						}else {
							writer.write(value.toString());
						}	
					}else if(j == 4) {
						if(!(value instanceof String)) {
							JOptionPane.showMessageDialog(this, "El email debe ser un String en la fila " + (i+1), "Error de validacion", JOptionPane.ERROR_MESSAGE);
							return false;
						}else {
							writer.write(value.toString());
						}
					}
					 if (j < modelo.getColumnCount() - 1) {
				            writer.write(",");
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
	
	// Clase para renderizar el botón en la tabla
    class ButtonRenderer extends JButton implements TableCellRenderer {

        public ButtonRenderer() {
            setOpaque(true);
            setBackground(new Color(205, 92, 92));
            setFont(new Font("Tahoma", Font.BOLD, 11));
            setForeground(Color.WHITE);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

 // Clase para manejar el editor del botón en la tabla
    class ButtonEditor extends DefaultCellEditor {
        protected JButton button;

        private String label;

        public ButtonEditor(JCheckBox checkBox) {
            super(checkBox);
            button = new JButton();
            button.setOpaque(true);
            button.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Acción cuando se hace clic en el botón
                    int selectedRow = tablaGestionClientes.getSelectedRow();
                    if (selectedRow != -1) {
                        // Obtén el cliente de la lista y elimina la fila
                        Cliente clienteAEliminar = listaClientes.get(selectedRow);
                        listaClientes.remove(clienteAEliminar);
                        modelo.removeRow(selectedRow);
                    }
                }
            });
        }

        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            label = (value == null) ? "" : value.toString();
            button.setText(label);
            return button;
        }

        public Object getCellEditorValue() {
            return new String(label);
        }
    }

}
