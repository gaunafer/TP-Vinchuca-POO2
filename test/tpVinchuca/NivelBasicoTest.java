package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.graalvm.compiler.debug.Assertions;
import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class NivelBasicoTest {
	NivelBasico basico = mock(NivelBasico.class);;
	@Mock
	Muestra muestra = mock(Muestra.class);;
	@Mock
	Votacion votacion = mock(Votacion.class);
	@Mock
	Participante participante = mock(Participante.class);;
	@Mock
	Participante participante2 = mock(Participante.class);;
//	@Rule
//	public ExpectedException a = ExpectedException.none();

	@BeforeEach
	public void setup() {

		basico = new NivelBasico();
	}
	
	@Test
	public void testNivelDeValidacion() {
		assertEquals("Nivel Basico", basico.getNivelDeValidacion());
	}
	@Test
	public void testGetVotaciones() {
		List<Votacion> votaciones = new ArrayList<Votacion>();
		when(muestra.getVotaciones()).thenReturn(votaciones);
		assertEquals(votaciones, basico.getVotaciones(muestra));
	}
	@Test
	public void registrarVotacionLanzaExcepcionSiElParticipanteQueVotaEsElQueCreoLaMuestra() throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		when(muestra.getParticipante()).thenReturn(participante);
		when(votacion.getParticipante()).thenReturn(participante);
		
		String exception = assertThrows(ErrorParticipanteNoPuedeVotarEstaMuestra.class,()->{basico.registrarVotacion(muestra, votacion);}).getMessage();
		assertEquals("Error participante no puede votar muestra creada por sí mismo", exception);
	}
	@Test
	public void registrarVotacionLanzaExcepcionSiElParticipanteYaVotoLaMuestra() throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		when(muestra.getParticipante()).thenReturn(participante);
		when(votacion.getParticipante()).thenReturn(participante2);
		when(muestra.muestraVotadaPor(participante2)).thenReturn(true);
		
		String exception = assertThrows(ErrorParticipanteNoPuedeVotarEstaMuestra.class,()->{basico.registrarVotacion(muestra, votacion);}).getMessage();
		assertEquals("Error el participante no pueve volver a votar esta muestra", exception);

	}
	@Test
	public void registrarVotacionAgregaVotacionAMuestraYVerificaSiElParticipanteEsExperto() throws Exception{
		when(muestra.getParticipante()).thenReturn(participante);
		when(participante.getAlias()).thenReturn("fer");
		when(votacion.getParticipante()).thenReturn(participante2);
		when(participante2.getAlias()).thenReturn("nati");

		basico.registrarVotacion(muestra, votacion);
		
		verify(muestra, times(1)).addVotacion(any(Votacion.class));
		verify(votacion, times(1)).getNivelDeConocimientoParticipante();
	}
	@Test
	public void registrarVotacionSeteaMuestraNivelExpertoSiElParticipanteEsExperto() throws ErrorParticipanteNoPuedeVotarEstaMuestra{
		when(muestra.getParticipante()).thenReturn(participante);
		when(participante.getAlias()).thenReturn("fer");
		when(votacion.getParticipante()).thenReturn(participante2);
		when(participante2.getAlias()).thenReturn("nati");
		when(votacion.getNivelDeConocimientoParticipante()).thenReturn("Nivel Experto");
		basico.registrarVotacion(muestra, votacion);
		verify(muestra, times(1)).setNivelDeValidacionExperto();
	}
}
