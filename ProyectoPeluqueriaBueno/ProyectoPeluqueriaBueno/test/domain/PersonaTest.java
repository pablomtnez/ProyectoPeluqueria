package domain;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class PersonaTest {
	
	private Persona persona;
	
	private String nombre = "nombre";
	private String apellido = "apellido";
	private Date fechaNacimiento = null;
	private int telefono = 111111111;
	
	private String newNombre = "New nombre";
	private String newApellido = "New apellido";
	private Date newFechaNacimiento = null;
	private int newTelefono = 111111112;

	@Before
	public void setUp() throws Exception {
		persona = new Persona(nombre, apellido, fechaNacimiento, telefono);
	}

	@Test
	public void testGetNombre() {
		assertEquals(nombre, persona.getNombre());
	}

	@Test
	public void testSetNombre() {
		persona.setNombre(newNombre);
		assertEquals(newNombre, persona.getNombre());
	}

	@Test
	public void testGetApellido() {
		assertEquals(apellido, persona.getApellido());
	}

	@Test
	public void testSetApellido() {
		persona.setApellido(newApellido);
		assertEquals(newApellido, persona.getApellido());
	}

	@Test
	public void testGetFechaNacimiento() {
		assertEquals(fechaNacimiento, persona.getFechaNacimiento());
	}

	@Test
	public void testSetFechaNacimiento() {
		persona.setFechaNacimiento(newFechaNacimiento);
		assertEquals(newFechaNacimiento, persona.getFechaNacimiento());
	}

	@Test
	public void testGetTelefono() {
		assertEquals(telefono, persona.getTelefono());
	}

	@Test
	public void testSetTelefono() {
		persona.setTelefono(newTelefono);
		assertEquals(newTelefono, persona.getTelefono());
	}
}
