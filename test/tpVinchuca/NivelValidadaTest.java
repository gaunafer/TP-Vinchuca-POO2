package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import org.mockito.Mock;
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

}
