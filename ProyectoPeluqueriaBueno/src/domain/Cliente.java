package domain;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * Clase Cliente donde se especifica una serie de parámetros
 * @author pablo
 * 
 * 
 */

public class Cliente extends Persona{
	
	private String email;
	static SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
	private static List<Cliente> clientes = new ArrayList<>();


	/**
	 * Constructor vacio
	 */
	public Cliente() {
		super();
	}

	/**
	 * Constructor con los siguientes parametros
	 * @param nombre
	 * @param apellido
	 * @param fechaNacimiento
	 * @param telefono
	 * @param email
	 */
	public Cliente(String nombre, String apellido, Date fechaNacimiento, int telefono, String email) {
		super(nombre, apellido, fechaNacimiento, telefono);
		this.email = email;
	}

	/**
	 * Metodo getEmail()
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo setEmail()
	 * @param email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Metodo toString()
	 */
	@Override
	public String toString() {
		return "Cliente ["
				+ ", Nombre=" + getNombre() + ", Apellido=" + getApellido() + ", Fecha Nacimiento=" + getFechaNacimiento() 
				+ ", Telefono=" + getTelefono() + ", Email=" + getEmail() + "]";
	}
	
	public static void cargarClientesEnLista(String nomfich) {
		clientes.clear();
		try {
			Scanner sc = new Scanner(new FileReader(nomfich));
			sc.nextLine();
			String linea;
			while(sc.hasNext()) {
				Date fechaNacimientoClienteDate = new Date();
				linea = sc.nextLine();
				String [] partes = linea.split(";");
				String nombreCliente = partes[0];
				String apellidoCliente = partes[1];
				String fechaNacimientoCliente = partes[2];
				try {
					fechaNacimientoClienteDate = formato.parse(fechaNacimientoCliente);
				} catch (ParseException e) {
					System.err.println("Error al leer la fecha: "+e.getMessage());
				}
				int telefonoCliente = Integer.parseInt(partes[3]);
				String emailCliente = partes[4];
				Cliente c = new Cliente(nombreCliente, apellidoCliente, fechaNacimientoClienteDate, telefonoCliente, emailCliente);
				clientes.add(c);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static List<Cliente> getClientes() {
		return clientes;
	}

	public static void setClientes(List<Cliente> clientes) {
		Cliente.clientes = clientes;
	}
}
