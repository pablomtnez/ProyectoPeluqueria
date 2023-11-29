package domain;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class ClienteTest {
	
	private Cliente cliente;
	
	private String nombre = "nombre";
	private String apellido = "apellido";
	private Date fechaNacimiento = null;
	private int telefono = 111111111;
	private String email = "email@email.com";
	
	private String newEmail = "newEmail@email.com";

	@Before
	public void setUp() throws Exception {
		cliente = new Cliente(nombre, apellido, fechaNacimiento, telefono, email);
	}

	@Test
	public void testGetEmail() {
		assertEquals(email, cliente.getEmail());
	}

	@Test
	public void testSetEmail() {
		cliente.setEmail(newEmail);
		assertEquals(newEmail, cliente.getEmail());
	}

}
