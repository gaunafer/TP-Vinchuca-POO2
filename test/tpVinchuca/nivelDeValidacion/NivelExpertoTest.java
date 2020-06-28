package tpVinchuca.nivelDeValidacion;

import org.mockito.Mock;

import tpVinchuca.Muestra;
import tpVinchuca.Participante;
import tpVinchuca.Votacion;
import tpVinchuca.nivelDeValidacion.NivelExperto;
import tpVinchucas.error.ErrorParticipanteNoPuedeVotarEstaMuestra;
import tpVinchucas.niveldeConocimiento.Basico;
import tpVinchucas.niveldeConocimiento.Experto;

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
	NivelExperto nivelValidacionExperto;
	List<Votacion> votaciones;
	@BeforeEach
	public void setUp() {
		nivelValidacionExperto = new NivelExperto();
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
	public void testRegistrarVotacionElParticipanteNoPuedeVotarSiTieneNivelDeConocimientoBasico() throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		when(muestra.getParticipante()).thenReturn(participante);
		when(votacion1.getParticipante()).thenReturn(participante2);
		when(muestra.muestraVotadaPor(participante2)).thenReturn(false);
		when(votacion1.participanteEsExpertoAlMomentoDeVotar()).thenReturn(false);
		
		String exception = assertThrows(ErrorParticipanteNoPuedeVotarEstaMuestra.class,()->{nivelValidacionExperto.registrarVotacion(muestra, votacion1);}).getMessage();
		assertEquals("Error participante Nivel Basico no puede votar muestra nivel experto", exception);
	}
	
	@Test
	public void testRegistrarVotacionParticipanteNoPuedeVotarMuestraRegistradaPorSiMismo() throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		when(muestra.getParticipante()).thenReturn(participante);
		when(votacion1.getParticipante()).thenReturn(participante);
		when(muestra.muestraVotadaPor(participante2)).thenReturn(false);
		when(votacion1.participanteEsExpertoAlMomentoDeVotar()).thenReturn(true);
		
		String exception = assertThrows(ErrorParticipanteNoPuedeVotarEstaMuestra.class,()->{nivelValidacionExperto.registrarVotacion(muestra, votacion1);}).getMessage();
		assertEquals("Error Participante no puede votar muestra creada por s� mismo", exception);
	}
	
	@Test
	public void testRegistrarVotacionParticipanteNoPuedeVolverAVotarEstaMuestra() throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		when(muestra.getParticipante()).thenReturn(participante);
		when(votacion1.getParticipante()).thenReturn(participante2);
		when(muestra.muestraVotadaPor(participante2)).thenReturn(true);
		when(votacion1.participanteEsExpertoAlMomentoDeVotar()).thenReturn(true);
		
		String exception = assertThrows(ErrorParticipanteNoPuedeVotarEstaMuestra.class,()->{nivelValidacionExperto.registrarVotacion(muestra, votacion1);}).getMessage();
		assertEquals("Error Participante no puede volver a votar esta muestra", exception);
	}
	
	@Test
	public void testRegistrarVotacionSeRegistraVotacion() throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		when(muestra.getParticipante()).thenReturn(participante);
		when(votacion1.getParticipante()).thenReturn(participante2);
		when(muestra.muestraVotadaPor(participante2)).thenReturn(false);
		when(votacion1.participanteEsExpertoAlMomentoDeVotar()).thenReturn(true);
		Experto nivelConocimientoExperto = mock(Experto.class);
		when(votacion1.getNivelDeConocimientoParticipanteAlVotar()).thenReturn(nivelConocimientoExperto);
		
		nivelValidacionExperto.registrarVotacion(muestra, votacion1);
		
		verify(muestra, times(1)).addVotacion(votacion1);
		verify(nivelConocimientoExperto).actualizarNivelValidacionMuestra(muestra);
	}
	
}
