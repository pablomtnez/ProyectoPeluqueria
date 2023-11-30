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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import domain.Cita;
import domain.Dia;
import domain.TipoCita;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.logging.Logger;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;

public class VentanaAgenda extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = Logger.getLogger(VentanaAgenda.class.getName());
	
	private JPanel contentPane;
	private JTextField textFieldIdI;
	private JComboBox<String> comboBoxClienteI, comboBoxPeluqueroI, comboBoxHoraI, comboBoxMinI;
	private JComboBox<String> comboBoxPeluquero = new JComboBox<String>();;
	private JComboBox<TipoCita> comboBoxCitaI;
	private JComboBox<Dia> comboBoxDiaI;
	private JComboBox<Dia> comboDia = new JComboBox<Dia>(Dia.values());
	private DefaultTableModel modeloTabla;
	private JTable tablaCitas;
	private HashMap<Dia, ArrayList<Cita>> mapaAgenda = new HashMap<Dia, ArrayList<Cita>>();
	private ArrayList<Cita> citas = new ArrayList<Cita>();
	private SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
	
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
		btnInsertar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				insertarCita();
				actualizarTabla(modeloTabla, mapaAgenda, (Dia) comboDia.getSelectedItem());
				 JOptionPane.showMessageDialog(VentanaAgenda.this,
                 		"Se ha insertado el cliente correctamente", "Insertar", JOptionPane.INFORMATION_MESSAGE);
				
			}
		});
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
		
		comboBoxDiaI = new JComboBox<Dia>(Dia.values());
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
		
		comboBoxCitaI = new JComboBox<TipoCita>(TipoCita.values());
		GridBagConstraints gbc_comboBoxCitaI = new GridBagConstraints();
		gbc_comboBoxCitaI.fill = GridBagConstraints.BOTH;
		gbc_comboBoxCitaI.insets = new Insets(0, 0, 5, 5);
		gbc_comboBoxCitaI.gridx = 4;
		gbc_comboBoxCitaI.gridy = 6;
		panelCentroI.add(comboBoxCitaI, gbc_comboBoxCitaI);
		
		//Table Panel Centro
		String [] columnas = {"ID", "CLIENTE", "PELUQUERO", "DIA", "HORA", "CITA"};
		
		
		modeloTabla = new DefaultTableModel(columnas, 0) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			
			public Class<?> getColumnClass(int columnas) {
				switch(columnas) {
				case 0: return Integer.class;
				case 1:	return String.class;
				case 2: return String.class;
				case 3: return Dia.class;
				case 4: return String.class;
				case 5: return TipoCita.class;
				default: return Object.class;
				}
			}

			public boolean isCellEditable(int row, int column) {
				return true;
				}
		};
		
		tablaCitas = new JTable(modeloTabla);
		cargarCitasYClientesYPeluqueros("resources/data/Agenda.csv", "resources/data/Clientes.csv", "resources/data/Peluqueros.csv");
		mostrarCitasTabla();
		
		int columnaCita = modeloTabla.findColumn("CITA");
		TipoCita[] arrayCitas = TipoCita.values();
		JComboBox<TipoCita> comboBoxTipoCita = new JComboBox<>(arrayCitas);
		TableColumn citaColumn = tablaCitas.getColumnModel().getColumn(columnaCita);
		citaColumn.setCellEditor(new DefaultCellEditor(comboBoxTipoCita));
		
		
		int columnaPeluquero = modeloTabla.findColumn("PELUQUERO");
		TableColumn peluqueroColumn = tablaCitas.getColumnModel().getColumn(columnaPeluquero);
		peluqueroColumn.setCellEditor(new DefaultCellEditor(comboBoxPeluquero));
		
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
				
		JLabel lblBuscador = new JLabel("Filtrar por DIA: ");
		lblBuscador.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 13));
		panelBuscar.add(lblBuscador);
		
		 comboDia.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                mostrarCitasTabla();
	            }
	        });
		panelBuscar.add(comboDia);
			
						
		JButton botonMenu = new JButton("GUARDAR Y VOLVER AL MENU");
		botonMenu.setBackground(new Color(205, 92, 92));
		botonMenu.setForeground(Color.WHITE);
		botonMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(guardarCitas()) {
					JOptionPane.showMessageDialog(VentanaAgenda.this,
                    		"Datos guardados en Agenda.csv", "Guardar", JOptionPane.INFORMATION_MESSAGE);
                }
				VentanaPrincipal vp = new VentanaPrincipal();
				vp.setVisible(true);
				setVisible(false);
			}
		});
		panelSur.add(botonMenu);
	}
		
	
	public void selectRows(String selectStr) {
		logger.info("User selecting rows by peluquero containing: " + selectStr);
	}
	
	private void cargarCitas(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
			reader.readLine();
			String line;
			while((line = reader.readLine()) != null) {
				String [] data = line.split(";");
				if(data.length == 6){
					Cita cita = new Cita();
					cita.setId(Integer.parseInt(data[0]));
					cita.setNomCliente(data[1]);
					cita.setNomPeluquero(data[2]);
					cita.setDia(Dia.valueOf(data[3]));
					cita.setHora(sdf.parse(data[4]));
					cita.setTipo(TipoCita.valueOf(data[5]));
					if(!mapaAgenda.containsKey(cita.getDia())) {
						mapaAgenda.put(cita.getDia(), new ArrayList<Cita>());
					}
					mapaAgenda.get(cita.getDia()).add(cita);
				}else {
					System.err.println("Error en el formato de la línea CSV: " + line);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}catch(Exception e) {
            e.printStackTrace();
		}
	}
	
	private void cargarClientes(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
			reader.readLine();
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(",");
	            if (data.length == 5) {
	            	String nomCliente = data[0] + " " + data [1];
	            	comboBoxClienteI.addItem(nomCliente);
	            } else {
	                System.err.println("Error en el formato de la línea CSV (Clientes): " + line);
	            }
			}
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private void cargarPeluqueros(String filePath) {
		try (BufferedReader reader = new BufferedReader(new FileReader(filePath))){
			reader.readLine();
			String line;
			while ((line = reader.readLine()) != null) {
				String[] data = line.split(";");
				if (data.length == 5) {
					String nomPeluquero = data[0] + " " + data [1];
					comboBoxPeluqueroI.addItem(nomPeluquero);
					comboBoxPeluquero.addItem(nomPeluquero);
				} else {
	                System.err.println("Error en el formato de la línea CSV (Peluqueros): " + line);
	            }
			}
	    } catch (IOException e) {
	        e.printStackTrace();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	private void cargarCitasYClientesYPeluqueros(String filePathCitas, String filePathClientes, String filePathPeluqueros) {
		cargarCitas(filePathCitas);
		cargarClientes(filePathClientes);
		cargarPeluqueros(filePathPeluqueros);
	}
	
	private void mostrarCitasTabla() {
		DefaultTableModel modelo = (DefaultTableModel) tablaCitas.getModel();
        modelo.setRowCount(0);
        Dia dia = (Dia) comboDia.getSelectedItem();
        citas = mapaAgenda.get(dia);
        citas.sort(new CitaComparator());
        for (Cita cita : citas) {
            Object[] rowData = {cita.getId(), cita.getNomCliente(), cita.getNomPeluquero(),
                    cita.getDia(), sdf.format(cita.getHora()), cita.getTipo()};
            modelo.addRow(rowData);
        }
    }
	
	private void actualizarTabla(DefaultTableModel modelo, HashMap<Dia, ArrayList<Cita>> mapaCitas, Dia dia) {
	    citas = mapaCitas.get(dia);

	    // Limpiar el modelo antes de agregar nuevas filas
	    modelo.setRowCount(0);
	    citas.sort(new CitaComparator());
	    for (Cita cita : citas) {
	        Object[] rowData = {cita.getId(), cita.getNomCliente(), cita.getNomPeluquero(),
	                cita.getDia(), sdf.format(cita.getHora()), cita.getTipo()};
	        modelo.addRow(rowData);
	    }
	}
	
	private void insertarCita() {
		
		if(!(textFieldIdI.getText().isEmpty())) {
			
			Cita c = new Cita();
			c.setId(Integer.parseInt(textFieldIdI.getText()));
			c.setNomCliente(comboBoxClienteI.getSelectedItem().toString());
			c.setNomPeluquero(comboBoxPeluqueroI.getSelectedItem().toString());
			c.setDia((Dia) comboBoxDiaI.getSelectedItem());
			String hora = comboBoxHoraI.getSelectedItem().toString() + ":" + comboBoxMinI.getSelectedItem().toString();
			try {
				c.setHora(sdf.parse(hora));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			c.setTipo((TipoCita) comboBoxCitaI.getSelectedItem());
			if(!mapaAgenda.containsKey(c.getDia())) {
				mapaAgenda.put(c.getDia(), new ArrayList<Cita>());
			}
			mapaAgenda.get(c.getDia()).add(c);
		}else{
			JOptionPane.showMessageDialog(null, "DEBES DE INTRODUCIR TODOS LOS VALORES", "FALTA DATOS", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	
	public class CitaComparator implements Comparator<Cita> {
	    @Override
	    public int compare(Cita cita1, Cita cita2) {
	        return cita1.getHora().compareTo(cita2.getHora());
	    }
	}
	
	private boolean guardarCitas() {
	    try (BufferedWriter writer = new BufferedWriter(new FileWriter("resources/data/Agenda.csv"))) {
	        // Escribir encabezados
	        String[] columnNames = {"ID", "CLIENTE", "PELUQUERO", "DIA", "HORA", "CITA"};
	        for (int i = 0; i < columnNames.length; i++) {
	            writer.write(columnNames[i]);
	            if (i < columnNames.length - 1) {
	                writer.write(";");
	            }
	        }
	        writer.newLine();

	        // Iterar sobre las citas en el mapa
	        for (Dia dia : mapaAgenda.keySet()) {
	            for (Cita cita : mapaAgenda.get(dia)) {
	                // Escribir datos de cada cita
	                writer.write(cita.getId() + ";");
	                writer.write(cita.getNomCliente() + ";");
	                writer.write(cita.getNomPeluquero() + ";");
	                writer.write(cita.getDia().name() + ";");
	                writer.write(sdf.format(cita.getHora()) + ";");
	                writer.write(cita.getTipo().name());
	                writer.newLine();
	            }
	        }

	        return true;
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(this, "Error al guardar los datos", "Error", JOptionPane.ERROR_MESSAGE);
	        return false;
	    }
	}
}
