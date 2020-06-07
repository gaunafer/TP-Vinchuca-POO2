package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

public class MuestraTest {
	private Muestra muestra1;
	@Mock
	private Participante persona = mock(Participante.class);
	@Mock
	private Ubicacion ubicacion;
	@Mock
	private Imagen imagen;
	@Mock
	private Votacion votacion = mock(Votacion.class);
	@Mock
	private Votacion votacion2 = mock(Votacion.class);
	private ResultadoDeMuestra opinion;
	
	@BeforeEach
	public void setUp() {
		opinion = ResultadoDeMuestra.VINCHUCA;
		when(persona.getAlias()).thenReturn("fer");
		when(persona.getNivelDeConocimiento()).thenReturn("Nivel Basico");
		when(votacion.getParticipante()).thenReturn(persona);
		when(votacion.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion2.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion2.getParticipante()).thenReturn(persona);
		muestra1 = new Muestra(imagen, persona, opinion, ubicacion);
	}
	
	@Test
	public void muestraConstructorTest() {
		assertEquals(persona.getAlias(), muestra1.getAliasParticipante());
		assertEquals(ubicacion, muestra1.getUbicacion());
		assertEquals(imagen, muestra1.getImagen());
		assertEquals("Vinchuca", muestra1.getVeredicto());
		assertEquals(LocalDate.now(), muestra1.getFecha());
	}
	@Test
	public void resultadoActualMuestraSinVotos() {
		assertEquals("Vinchuca", muestra1.getResultadoActual());
	}
	@Test
	public void muestraRegistradaComoVinchucaVotadaUnaVezComoChinche() {
		muestra1.registrarVotacion(votacion);
		assertEquals("Muestra Indefinida", muestra1.getResultadoActual());
	}
	@Test
	public void muestraRegistradaComoVinchucaConDosVotosChincheFoliadaEsChinche() {
		muestra1.registrarVotacion(votacion);
		muestra1.registrarVotacion(votacion2);
		assertEquals("Chinche Foliada", muestra1.getResultadoActual());
	}

}
