package main;

import java.util.Date;

/**
 * Clase Persona con atributos
 * @author pablo
 * 
 */

public class Persona {
	
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private int telefono;
	
	/**
	 * Constructor por defecto
	 */
	public Persona() {
		
	}

	/**
	 * Constructor Persona con parametros
	 * @param nombre
	 * @param apellido
	 * @param fechaNacimiento
	 * @param telefono
	 */
	public Persona(String nombre, String apellido, Date fechaNacimiento, int telefono) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
	}
	

	/**
	 * Metodo getNombre()
	 * @return
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Metodo setNombre()
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Metodo getApellido()
	 * @return
	 */
	public String getApellido() {
		return apellido;
	}
	
	/**
	 * Metodo setApellido()
	 * @param apellido
	 */
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	/**
	 * Metodo getFechaNacimiento()
	 * @return
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Metodo setFechaNacimiento
	 * @param fechaNacimiento
	 */
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	/**
	 * Metodo getTelefono
	 * @return
	 */
	public int getTelefono() {
		return telefono;
	}
	
	/**
	 * Metodo setTelefono
	 * @param telefono
	 */
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
}
