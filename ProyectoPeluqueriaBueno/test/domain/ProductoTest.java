package domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProductoTest {
	
	private Producto producto;
	
	private int id = 0;
	private String nombre = "nombre";
	private String descripcion = "desc";
	private float precio = 1;
	private int cantidad = 1;
	
	private int newId = 1;
	private String newNombre = "New nombre";
	private String newDescripcion = "New desc";
	private float newPrecio = 5;
	private int newCantidad = 5;

	@Before
	public void setUp() throws Exception {
		producto = new Producto(id, nombre, descripcion, precio, cantidad);
	}

	@Test
	public void testGetId() {
		assertEquals(id, producto.getId());
	}

	@Test
	public void testSetId() {
		producto.setId(newId);
		assertEquals(newId, producto.getId());
	}

	@Test
	public void testGetNombre() {
		assertEquals(nombre, producto.getNombre());
	}

	@Test
	public void testSetNombre() {
		producto.setNombre(newNombre);
		assertEquals(newNombre, producto.getNombre());
	}

	@Test
	public void testGetDescripcion() {
		assertEquals(descripcion, producto.getDescripcion());
	}

	@Test
	public void testSetDescripcion() {
		producto.setDescripcion(newDescripcion);
		assertEquals(newDescripcion, producto.getDescripcion());
	}

	@Test
	public void testGetPrecio() {
		assertEquals(precio, producto.getPrecio(), 0);
	}

	@Test
	public void testSetPrecio() {
		producto.setPrecio(newPrecio);
		assertEquals(newPrecio, producto.getPrecio(), 0);
	}

	@Test
	public void testGetCantidad() {
		assertEquals(cantidad, producto.getCantidad());
	}

	@Test
	public void testSetCantidad() {
		producto.setCantidad(newCantidad);
		assertEquals(newCantidad, producto.getCantidad());
	}

}
