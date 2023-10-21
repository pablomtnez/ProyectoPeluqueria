package main;

import java.util.Date;

/**
 * Clase Peluquero donde se especifica los atributos de un peluquero
 * Se utiliza para saber todos los peluqueros que componen la app
 * @author pablo
 */

public class Peluquero extends Persona{
	
	private float salario;

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
}
