package domain;

import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Clase Peluquero donde se especifica los atributos de un peluquero
 * Se utiliza para saber todos los peluqueros que componen la app
 * @author pablo
 */

public class Peluquero extends Persona{
	
	private float salario;
	static SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
	private static List<Peluquero> peluqueros = new ArrayList<>();

	/**
	 * Constructor Vacio
	 */
	public Peluquero() {
		super();
	}

	/**
	 * Constructor con los siguientes parametros
	 * @param nombre
	 * @param apellido
	 * @param fechaNacimiento
	 * @param telefono
	 * @param salario
	 */
	public Peluquero(String nombre, String apellido, Date fechaNacimiento, int telefono, float salario) {
		super(nombre, apellido, fechaNacimiento, telefono);
		this.salario = salario;
	}

	/**
	 * Metodo getSalario()
	 * @return
	 */
	public float getSalario() {
		return salario;
	}
	
	/**
	 * Metodo setSalario()
	 * @param salario
	 */
	public void setSalario(float salario) {
		this.salario = salario;
	}
	
	/**
	 * Metodo toString()
	 */
	@Override
	public String toString() {
		return "Peluquero [getNombre()=" + getNombre() + ", getApellido()=" + getApellido() 
		+ ", getFechaNacimiento()=" + getFechaNacimiento() + ", getTelefono()=" + getTelefono()
		+", getSalario()=" + getSalario() + "]";
	}
	
	public static void cargarPeluquerosEnLista(String nomFich) {
		peluqueros.clear();
		try {
			Scanner sc = new Scanner(new FileReader(nomFich));
			sc.nextLine();
			String linea;
			while(sc.hasNext()) {
				Date fechaNacimientoPeluqueroDate = new Date();
				linea = sc.nextLine();
				String[]partes = linea.split(";");
				String nombrePeluquero = partes[0];
				String apellidoPeluquero = partes[1];
				String fechaNacimientoPeluquero = partes[2];
				try {
					fechaNacimientoPeluqueroDate = formato.parse(fechaNacimientoPeluquero);
				} catch (ParseException e) {
					System.err.println("Error al leer la fecha: "+e.getMessage());
				}
				int telefonoPeluquero = Integer.parseInt(partes[3]);
				float salarioPeluquero = Float.parseFloat(partes[4]);
				Peluquero p = new Peluquero(nombrePeluquero, apellidoPeluquero, fechaNacimientoPeluqueroDate, telefonoPeluquero, salarioPeluquero);
				peluqueros.add(p);
			}
			sc.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Metodo getPeluqueros()
	 * @return
	 */
	public static List<Peluquero> getPeluqueros() {
		return peluqueros;
	}
	
	/**
	 * Metodo setPeluqueros()
	 * @param peluqueros
	 */
	public void setPeluqueros(List<Peluquero> peluqueros) {
		Peluquero.peluqueros = peluqueros;
	}

}
