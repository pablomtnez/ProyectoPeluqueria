package domain;

import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

/**
 * Clase Cita donde se gestiona la informacion de una cita de un cliente
 * 
 * @author pablo
 */

public class Cita {
	
	private int id;
	private String nomCliente;
	private String nomPeluquero;
	private TipoCita tipo;
	private Dia dia;
	private Date hora;
	private static SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
	private static Map<Dia, Set<Cita>> mapaAgenda = new HashMap<>();
	
	/**
	 * Constructor vacio
	 */
	public Cita() {
		super();
	}

	/**
	 * Constructor con parametros
	 * @param id
	 * @param nomCliente
	 * @param nomPeluquero
	 * @param tipo
	 * @param dia
	 * @param hora
	 */
	public Cita(int id, String nomCliente, String nomPeluquero, TipoCita tipo, Dia dia, Date hora) {
		super();
		this.id = id;
		this.nomCliente = nomCliente;
		this.nomPeluquero = nomPeluquero;
		this.tipo = tipo;
		this.dia = dia;
		this.hora = hora;
	}
	
	/**
	 * Metodo getId();
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Metodo setId()
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * metodo getNomCliente()
	 * @return
	 */
	public String getNomCliente() {
		return nomCliente;
	}
	
	/**
	 * Metodo setNomCliente
	 * @param nomCliente
	 */
	public void setNomCliente(String nomCliente) {
		this.nomCliente = nomCliente;
	}

	/**
	 * Metodo getNomPeluquero
	 * @return
	 */
	public String getNomPeluquero() {
		return nomPeluquero;
	}

	/**
	 * Metodo setNomPeluquero
	 * @param nomPeluquero
	 */
	public void setNomPeluquero(String nomPeluquero) {
		this.nomPeluquero = nomPeluquero;
	}

	/**
	 * Metodo getTipo()
	 * @return
	 */
	public TipoCita getTipo() {
		return tipo;
	}

	/**
	 * Metodo setTipo()
	 * @param tipo
	 */
	public void setTipo(TipoCita tipo) {
		this.tipo = tipo;
	}
	
	/**
	 * Metodo getDia()
	 * @return
	 */
	public Dia getDia() {
		return dia;
	}

	/**
	 * Metodo setDia()
	 * @param dia
	 */
	public void setDia(Dia dia) {
		this.dia = dia;
	}

	/**
	 * Metodo getHora()
	 * @return
	 */
	public Date getHora() {
		return hora;
	}

	/**
	 * Metodo setHora()
	 * @param hora
	 */
	public void setHora(Date hora) {
		this.hora = hora;
	}

	@Override
	public String toString() {
		return "Cita [id=" + id + ", nomCliente=" + nomCliente + ", nomPeluquero=" + nomPeluquero + ", tipo=" + tipo
				+ ", dia=" + dia + ", hora=" + hora + "]";
	}
	
	public static List<Cita> obtenerListaCitas(Dia dia){
		List<Cita> listaCitas = new ArrayList<Cita>();
		for(Cita c: mapaAgenda.get(dia)) {
			listaCitas.add(c);
		}
		return listaCitas;
	}
	
	public static Map<Dia, Set<Cita>> getMapaAgenda() {
		return mapaAgenda;
	}

	public static void setMapaAgenda(Map<Dia, Set<Cita>> mapaAgenda) {
		Cita.mapaAgenda = mapaAgenda;
	}

	public static void cargarFicheroEnMapa(String nomFich) {
		try {
			Scanner sc = new Scanner(new FileReader(nomFich));
			sc.nextLine();
			String linea;
			while(sc.hasNext()) {
				Dia diaCita = null;
				TipoCita tipo = null;
				Date horaCita = new Date();
				linea = sc.nextLine();
				String[]partes = linea.split(";");
				int id = Integer.parseInt(partes[0]);
				String cliente = partes[1];
				String peluquero = partes[2];
				if(partes[3].equals("LUNES")) {
					diaCita = Dia.LUNES;
				}else if(partes[3].equals("MARTES")) {
					diaCita = Dia.MARTES;
				}else if(partes[3].equals("MIERCOLES")) {
					diaCita = Dia.MIERCOLES;
				}else if(partes[3].equals("JUEVES")) {
					diaCita = Dia.JUEVES;
				}else if(partes[3].equals("VIERNES")) {
					diaCita = Dia.VIERNES;
				}
				String horaString = partes[4];
				horaCita = formato.parse(horaString);
				if(partes[5].equals("CORTE")) {
					tipo = TipoCita.CORTE;
				}else if(partes[5].equals("LAVADO")) {
					tipo = TipoCita.LAVADO;
				}else if(partes[5].equals("AFEITADO")) {
					tipo = TipoCita.AFEITADO;
				}else if(partes[5].equals("DEPILACION")) {
					tipo = TipoCita.DEPILACION;
				}else if(partes[5].equals("COLOR")) {
					tipo = TipoCita.COLOR;
				}else if(partes[5].equals("OTROS")) {
					tipo = TipoCita.OTROS;
				}
				Cita c = new Cita(id, cliente, peluquero, tipo, diaCita, horaCita);
				if(!mapaAgenda.containsKey(diaCita)) {
					mapaAgenda.put(diaCita, new HashSet<Cita>());
				}
				mapaAgenda.get(diaCita).add(c);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
