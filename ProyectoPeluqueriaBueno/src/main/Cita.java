package main;

import java.util.Date;

/**
 * Clase Cita donde se gestiona la informacion de una cita de un cliente
 * 
 * @author pablo
 */

public class Cita {
	
	private int id;
	private String nomCliente;
	private String nomPeluquero;
	private int telfCliente;
	private String mailCliente;
	private TipoCita tipo;
	private Date fecha;
	
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
	 * @param telfCliente
	 * @param mailCliente
	 * @param tipo
	 * @param fecha
	 */
	public Cita(int id, String nomCliente, String nomPeluquero, int telfCliente, String mailCliente, TipoCita tipo,
			Date fecha) {
		super();
		this.id = id;
		this.nomCliente = nomCliente;
		this.nomPeluquero = nomPeluquero;
		this.telfCliente = telfCliente;
		this.mailCliente = mailCliente;
		this.tipo = tipo;
		this.fecha = fecha;
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
	 * Metodo getTelfCliente
	 * @return
	 */
	public int getTelfCliente() {
		return telfCliente;
	}

	/**
	 * Metodo setTelfCliente()
	 * @param telfCliente
	 */
	public void setTelfCliente(int telfCliente) {
		this.telfCliente = telfCliente;
	}

	/**
	 * Metodo getMailCliente()
	 * @return
	 */
	public String getMailCliente() {
		return mailCliente;
	}
	
	/**
	 * Metodo setMailCliente()
	 * @param mailCliente
	 */
	public void setMailCliente(String mailCliente) {
		this.mailCliente = mailCliente;
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
	 * Metodo getFecha()
	 * @return
	 */
	public Date getFecha() {
		return fecha;
	}

	/**
	 * Metodo setFecha()
	 * @param fecha
	 */
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	/**
	 * Metodo toString()
	 */
	@Override
	public String toString() {
		return "Cita [id=" + id + ", nomCliente=" + nomCliente + ", nomPeluquero=" + nomPeluquero + ", telfCliente="
				+ telfCliente + ", mailCliente=" + mailCliente + ", tipo=" + tipo + ", fecha=" + fecha + "]";
	}	
}
