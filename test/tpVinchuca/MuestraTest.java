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
	private Participante persona2 = mock(Participante.class);
	@Mock
	private Participante persona3 = mock(Participante.class);
	@Mock
	private Ubicacion ubicacion;
	@Mock
	private Imagen imagen;
	@Mock
	private Votacion votacion = mock(Votacion.class);
	@Mock
	private Votacion votacion2 = mock(Votacion.class);
	@Mock
	private Votacion votacion3 = mock(Votacion.class);
	private ResultadoDeMuestra opinion;
	
	@BeforeEach
	public void setUp() {
		opinion = ResultadoDeMuestra.VINCHUCA;
		when(persona.getAlias()).thenReturn("fer");
		when(persona.getNivelDeConocimiento()).thenReturn("Nivel Basico");
		
		when(persona2.getAlias()).thenReturn("nati");
		when(persona2.getNivelDeConocimiento()).thenReturn("Nivel Basico");
		
		when(persona3.getAlias()).thenReturn("cin");
		when(persona3.getNivelDeConocimiento()).thenReturn("Nivel Basico");
		
		when(votacion.getParticipante()).thenReturn(persona);
		when(votacion.getOpinion()).thenReturn("Chinche Foliada");
		
		when(votacion2.getParticipante()).thenReturn(persona2);
		when(votacion2.getOpinion()).thenReturn("Chinche Foliada");
		
		when(votacion3.getParticipante()).thenReturn(persona3);
		when(votacion3.getOpinion()).thenReturn("Chinche Foliada");
		
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
	public void resultadoActualMuestraRegistradaComoVinchucaSinVotosEsVinchuca() {
		assertEquals("Vinchuca", muestra1.getResultadoActual());
	}
	@Test
	public void muestraRegistradaComoVinchucaVotadaUnaVezComoChinche() throws Exception {
		muestra1.registrarVotacion(votacion);
		assertEquals("Muestra Indefinida", muestra1.getResultadoActual());
	}
	@Test
	public void muestraRegistradaComoVinchucaConDosVotosChincheFoliadaEsChinche() throws Exception {
		muestra1.registrarVotacion(votacion2);
		muestra1.registrarVotacion(votacion3);
		assertEquals("Chinche Foliada", muestra1.getResultadoActual());
	}
	@Test
	public void muestraVotadaPorElParticipanteQueLaCreoLanzaExcepcion() {
		
	}
}
