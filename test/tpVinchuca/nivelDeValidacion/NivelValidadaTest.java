package tpVinchuca.nivelDeValidacion;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import org.mockito.Mock;

import tpVinchuca.Muestra;
import tpVinchuca.Participante;
import tpVinchuca.Votacion;
import tpVinchuca.nivelDeValidacion.NivelValidada;
import tpVinchucas.error.ErrorParticipanteNoPuedeVotarEstaMuestra;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NivelValidadaTest {
	@Mock
	Muestra muestra;
	@Mock
	Participante participante;
	@Mock
	Votacion votacion1;
	NivelValidada nivelValidada;
	
	@BeforeEach
	public void setUp() {
		muestra = mock(Muestra.class);
		votacion1 = mock(Votacion.class);
		participante = mock(Participante.class);
		nivelValidada = new NivelValidada();
	}
	@Test
	public void testGetNivelDeValidacion() {
		assertEquals(nivelValidada.getNivelDeValidacion(),"Nivel Validada");
	}
	@Test
	public void testNoSePuedeBotarMuestraValidada() {
		String exception = assertThrows(ErrorParticipanteNoPuedeVotarEstaMuestra.class,()->{nivelValidada.registrarVotacion(muestra, votacion1);}).getMessage();
		assertEquals("No se puede votar una muestra validada", exception);
	}
	
	@Test
	public void testEstaValidada() {
		assertTrue(nivelValidada.estaValidada());
	}

}
