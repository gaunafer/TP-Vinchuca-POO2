package tpVinchuca;

import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NivelExpertoTest {
	@Mock
	Muestra muestra;
	@Mock
	Participante participante;
	@Mock
	Participante participante2;
	@Mock
	Votacion votacion1;
	@Mock
	Votacion votacion2;
	@Mock
	Votacion votacion3;
	NivelExperto nivelExperto;
	List<Votacion> votaciones;
	@BeforeEach
	public void setUp() {
		nivelExperto = new NivelExperto();
		votaciones = new ArrayList<Votacion>();
		muestra = mock(Muestra.class);
		participante = mock(Participante.class);
		participante2 = mock(Participante.class);
		votacion1 = mock(Votacion.class);
		votacion2 = mock(Votacion.class);
		votacion3 = mock(Votacion.class);
		when(muestra.getParticipante()).thenReturn(participante);
		when(muestra.getVotaciones()).thenReturn(votaciones);
				
	}
	@Test
	public void testGetVotacionesSoloTomaEnCuentaLasVotacionesRegistradasPorExpertos() {
		votaciones.add(votacion1);
		votaciones.add(votacion2);
		votaciones.add(votacion3);
		when(votacion1.getNivelDeConocimientoParticipante()).thenReturn("Nivel Experto");
		when(votacion2.getNivelDeConocimientoParticipante()).thenReturn("Nivel Experto");
		when(votacion3.getNivelDeConocimientoParticipante()).thenReturn("Nivel Basico");
		List<Votacion> vot = new ArrayList<Votacion>();
		vot = nivelExperto.getVotaciones(muestra);
		assertTrue(vot.contains(votacion1));
		assertTrue(vot.contains(votacion2));
		assertFalse(vot.contains(votacion3));
	}
	@Test
	public void testRegistrarVotacionElParticipanteNoPuedeVotarSiTieneNivelDeConocimientoBasico() throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		when(muestra.getParticipante()).thenReturn(participante);
		when(votacion1.getParticipante()).thenReturn(participante2);
		when(muestra.muestraVotadaPor(participante2)).thenReturn(false);
		when(participante2.getNivelDeConocimiento()).thenReturn("Nivel Basico");
		when(votacion1.getNivelDeConocimientoParticipante()).thenReturn("Nivel Basico");
		
		String exception = assertThrows(ErrorParticipanteNoPuedeVotarEstaMuestra.class,()->{nivelExperto.registrarVotacion(muestra, votacion1);}).getMessage();
		assertEquals("Error participante Nivel Basico no puede votar muestra nivel experto", exception);
	}
	@Test
	public void testRegistrarVotacionParticipanteNoPuedeVotarMuestraRegistradaPorSiMismo() throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		when(muestra.getParticipante()).thenReturn(participante);
		when(votacion1.getParticipante()).thenReturn(participante);
		when(muestra.muestraVotadaPor(participante2)).thenReturn(false);
		when(participante.getNivelDeConocimiento()).thenReturn("Nivel Experto");
		when(votacion1.getNivelDeConocimientoParticipante()).thenReturn("Nivel Basico");
		
		String exception = assertThrows(ErrorParticipanteNoPuedeVotarEstaMuestra.class,()->{nivelExperto.registrarVotacion(muestra, votacion1);}).getMessage();
		assertEquals("Error Participante no puede votar muestra creada por s� mismo", exception);
	}
	@Test
	public void testRegistrarVotacionParticipanteNoPuedeVolverAVotarEstaMuestra() throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		when(muestra.getParticipante()).thenReturn(participante);
		when(votacion1.getParticipante()).thenReturn(participante2);
		when(muestra.muestraVotadaPor(participante2)).thenReturn(true);
		when(participante2.getNivelDeConocimiento()).thenReturn("Nivel Experto");
		when(votacion1.getNivelDeConocimientoParticipante()).thenReturn("Nivel Experto");
		
		String exception = assertThrows(ErrorParticipanteNoPuedeVotarEstaMuestra.class,()->{nivelExperto.registrarVotacion(muestra, votacion1);}).getMessage();
		assertEquals("Error Participante no puede volver a votar esta muestra", exception);
	}
	@Test
	public void testRegistrarVotacionSeRegistraVotacion() throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		when(muestra.getParticipante()).thenReturn(participante);
		when(votacion1.getParticipante()).thenReturn(participante2);
		when(muestra.muestraVotadaPor(participante2)).thenReturn(false);
		when(participante2.getNivelDeConocimiento()).thenReturn("Nivel Experto");
		when(votacion1.getNivelDeConocimientoParticipante()).thenReturn("Nivel Experto");
		nivelExperto.registrarVotacion(muestra, votacion1);
		verify(muestra, times(1)).addVotacion(votacion1);
	}
	@Test
	public void testRegistrarVotacionSeRegistraVotacionYValidarMuestra() throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		votaciones.add(votacion2);
		when(votacion2.getOpinion()).thenReturn("Vinchuca");
		when(votacion1.getOpinion()).thenReturn("Vinchuca");
		
		when(muestra.getParticipante()).thenReturn(participante);
		when(muestra.getVeredicto()).thenReturn("Vinchuca");
		when(votacion1.getParticipante()).thenReturn(participante2);
		when(muestra.muestraVotadaPor(participante2)).thenReturn(false);
		when(participante2.getNivelDeConocimiento()).thenReturn("Nivel Experto");
		when(votacion1.getNivelDeConocimientoParticipante()).thenReturn("Nivel Experto");
		when(votacion2.getNivelDeConocimientoParticipante()).thenReturn("Nivel Experto");
		nivelExperto.registrarVotacion(muestra, votacion1);
		
		verify(muestra, times(1)).setNivelDeValidacionValidada();
		verify(muestra, times(1)).addVotacion(votacion1);
	}
	
}
