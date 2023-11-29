package domain;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class PeluqueroTest {
	
	private Peluquero peluquero;
	
	private String nombre = "nombre";
	private String apellido = "apellido";
	private Date fechaNacimiento = null;
	private int telefono = 111111111;
	private float salario = 1000;
	
	private float newSalario = 2000;

	@Before
	public void setUp() throws Exception {
		peluquero = new Peluquero(nombre, apellido, fechaNacimiento, telefono, salario);
	}

	@Test
	public void testGetSalario() {
		assertEquals(salario, peluquero.getSalario(), 0);
	}

	@Test
	public void testSetSalario() {
		peluquero.setSalario(newSalario);
		assertEquals(newSalario, peluquero.getSalario(), 0);
	}

}
