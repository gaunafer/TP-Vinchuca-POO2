package tpVinchuca;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
public class VotacionTest {
	
	// Testea la creacion de una votacion
	@Test
	public void testCreacionDeMuestra() {
		Participante emma = mock(Participante.class);
		when(emma.getNivelDeConocimiento()).thenReturn("Nivel basico");
		
		Votacion votacion = new Votacion(emma, ClasificacionDeFoto.VINCHUCA_INFESTANS);
		Participante participante = votacion.getParticipante();
		String resultadoDeVotacion = votacion.getOpinion();
		LocalDate fecha = votacion.getFecha();
		String nivelConocimientoParticipante = votacion.getNivelDeConocimientoParticipante();
		
		assertEquals(emma, participante);
		assertEquals("Vinchuca Infestans", resultadoDeVotacion);
		assertEquals(LocalDate.now(), fecha);
		assertEquals("Nivel basico", nivelConocimientoParticipante);
	}
}