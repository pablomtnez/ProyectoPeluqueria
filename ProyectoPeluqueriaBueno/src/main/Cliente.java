package main;

import java.util.Date;

/**
 * Clase Cliente donde se especifica una serie de parámetros
 * @author pablo
 * 
 * 
 */

public class Cliente extends Persona{
	
	private String email;


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
	
	
	
	
	
	
	
	
}
