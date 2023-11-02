package domain;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Clase Producto con atributos
 * @author pablo
 */

public class Producto {
	
	private int id;
	private String nombre;
	private String descripcion;
	private float precio;
	private int cantidad;
	
	private static List<Producto> productos = new ArrayList<>();
	
	/**
	 * Constructor vacio
	 */
	public Producto() {
		super();
	}

	/**
	 * Constructor con parametros
	 * @param id
	 * @param nombre
	 * @param descripcion
	 * @param precio
	 * @param cantidad
	 */
	public Producto(int id, String nombre, String descripcion, float precio, int cantidad) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.cantidad = cantidad;
	}

	/**
	 * Metodo getId()
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
	 * Metodo getDescripcion()
	 * @return
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Metodo setDescripcion()
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * Metodo getPrecio()
	 * @return
	 */
	public float getPrecio() {
		return precio;
	}

	/**
	 * Metodo setPrecio()
	 * @param precio
	 */
	public void setPrecio(float precio) {
		this.precio = precio;
	}

	/**
	 * Metodo getCantidad()
	 * @return
	 */
	public int getCantidad() {
		return cantidad;
	}

	/**
	 * Metodo setCantidad()
	 * @param cantidad
	 */
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/**
	 * Metodo toString()
	 */
	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", cantidad=" + cantidad + "]";
	}
	
	public static void cargarProductosenLista(String nomFich) {
		productos.clear();;
		try {
			Scanner sc = new Scanner(new FileReader(nomFich));
			sc.nextLine();
			String linea;
			while(sc.hasNext()) {
				linea = sc.nextLine();
				String [] partes = linea.split(";");
				int id = Integer.parseInt(partes [0]);
				String nombreProducto = partes[1];
				String descProducto = partes [2];
				float precioProducto = Float.parseFloat(partes[3]);
				int cantidadProducto = Integer.parseInt(partes[4]);
				Producto p = new Producto(id, nombreProducto, descProducto, precioProducto, cantidadProducto);
				productos.add(p);
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static List<Producto> getProductos() {
		return productos;
	}

	public static void setProductos(List<Producto> productos) {
		Producto.productos = productos;
	}
}
