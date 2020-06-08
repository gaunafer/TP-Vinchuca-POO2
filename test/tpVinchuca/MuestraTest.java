package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.time.LocalDate;

public class MuestraTest {
	private Muestra muestra1;
	private Muestra muestra2;
	@Mock
	private Participante persona = mock(Participante.class);
	@Mock
	private Participante persona2 = mock(Participante.class);
	@Mock
	private Participante persona3 = mock(Participante.class);
	@Mock
	private Participante persona4 = mock(Participante.class);
	@Mock
	private Participante persona5 = mock(Participante.class);
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
	@Mock
	private Votacion votacion4 = mock(Votacion.class);
	private ResultadoDeMuestra opinion;
	@Mock
	private Votacion votacion5 = mock(Votacion.class);
	
	@BeforeEach
	public void setUp() {
		opinion = ResultadoDeMuestra.VINCHUCA;
		when(persona.getAlias()).thenReturn("fer");
		when(persona.getNivelDeConocimiento()).thenReturn("Nivel Basico");
		
		when(persona2.getAlias()).thenReturn("nati");
		when(persona2.getNivelDeConocimiento()).thenReturn("Nivel Basico");
		
		when(persona3.getAlias()).thenReturn("cin");
		when(persona3.getNivelDeConocimiento()).thenReturn("Nivel Basico");

		when(persona4.getAlias()).thenReturn("pepe");
		when(persona4.getNivelDeConocimiento()).thenReturn("Nivel Experto");
		
		when(persona5.getAlias()).thenReturn("montoto");
		when(persona5.getNivelDeConocimiento()).thenReturn("Nivel Experto");
		
		when(votacion.getParticipante()).thenReturn(persona);
		when(votacion.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion.getNivelDeConocimientoParticipante()).thenReturn("Nivel Basico");
		
		when(votacion2.getParticipante()).thenReturn(persona2);
		when(votacion2.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion2.getNivelDeConocimientoParticipante()).thenReturn("Nivel Basico");
		
		when(votacion3.getParticipante()).thenReturn(persona3);
		when(votacion3.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion3.getNivelDeConocimientoParticipante()).thenReturn("Nivel Basico");
		
		when(votacion4.getParticipante()).thenReturn(persona4);
		when(votacion4.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion4.getNivelDeConocimientoParticipante()).thenReturn("Nivel Experto");
		
		when(votacion5.getParticipante()).thenReturn(persona5);
		when(votacion5.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion5.getNivelDeConocimientoParticipante()).thenReturn("Nivel Experto");
		
		muestra1 = new Muestra(imagen, persona, opinion, ubicacion);
		muestra2 = new Muestra(imagen, persona4, opinion,ubicacion);
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
		muestra1.registrarVotacion(votacion2);
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
		assertThrows(ErrorParticipanteNoPuedeVotarMuestraPublicadaPorSiMismo.class,() ->{	muestra1.registrarVotacion(votacion);
		});
	}
	@Test
	public void muestraCreadaPorExpertoSeCreaConNivelDeValidacionExperto() {
		assertEquals("Nivel Experto", muestra2.getNivelDeValidacion());
		
	}
	@Test
	public void muestraCreadaPorParticipanteBasicoSeCreaConNivelDeValidacionBasico() {
		assertEquals("Nivel Basico", muestra1.getNivelDeValidacion());
	}
	@Test
	public void muestraCreadaComoVinchucaQuedaValidadaComoChincheSiLaVotan2ExpertosComoChinche() throws Exception {
		muestra1.registrarVotacion(votacion4);
		muestra1.registrarVotacion(votacion5);
		assertEquals("Chinche Foliada", muestra1.getResultadoActual());
		assertEquals("Nivel Validada", muestra1.getNivelDeValidacion());
	}
}
