package domain;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

public class CitaTest {
	
	private Cita cita;
	
	private int id = 0;
	private String cliente = "Cliente";
	private String peluquero = "Peluquero";
	private TipoCita tipo = TipoCita.AFEITADO;
	private Dia dia = Dia.LUNES;
	private Date hora;
	SimpleDateFormat formato = new SimpleDateFormat("HH:mm");
	
	private int newId = 1;
	private String newCliente = "NewCliente";
	private String newPeluquero = "NewPeluquero";
	private TipoCita newTipo = TipoCita.COLOR;
	private Dia newDia = Dia.JUEVES;
	private Date newHora;

	@Before
	public void setUp() throws Exception {
		cita = new Cita(id, cliente, peluquero, tipo, dia, hora);
	}

	@Test
	public void testGetId() {
		assertEquals(id, cita.getId());
	}

	@Test
	public void testSetId() {
		cita.setId(newId);
		assertEquals(newId, cita.getId());
	}

	@Test
	public void testGetNomCliente() {
		assertEquals(cliente, cita.getNomCliente());
	}

	@Test
	public void testSetNomCliente() {
		cita.setNomCliente(newCliente);
		assertEquals(newCliente, cita.getNomCliente());
	}

	@Test
	public void testGetNomPeluquero() {
		assertEquals(peluquero, cita.getNomPeluquero());
	}

	@Test
	public void testSetNomPeluquero() {
		cita.setNomPeluquero(newPeluquero);
		assertEquals(newPeluquero, cita.getNomPeluquero());
	}

	@Test
	public void testGetTipo() {
		assertEquals(tipo, cita.getTipo());
	}

	@Test
	public void testSetTipo() {
		cita.setTipo(newTipo);
		assertEquals(newTipo, cita.getTipo());
	}

	@Test
	public void testGetDia() {
		assertEquals(dia, cita.getDia());
	}

	@Test
	public void testSetDia() {
		cita.setDia(newDia);
		assertEquals(newDia, cita.getDia());
	}

	@Test
	public void testGetHora() {
		assertEquals(hora, cita.getHora());
	}

	@Test
	public void testSetHora() {
		cita.setHora(newHora);
		assertEquals(newHora, cita.getHora());
	}
}
