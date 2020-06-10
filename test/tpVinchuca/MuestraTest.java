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
	private ClasificacionDeFoto opinion;
	@Mock
	private Ubicacion ubicacion;
	@Mock
	private Imagen imagen;
	@Mock
	private ZonaDeCobertura zonaDeCobertura;
	@Mock
	private Participante persona;
	@Mock
	private Participante persona2;
	@Mock
	private Participante persona3;
	@Mock
	private Participante persona4;
	@Mock
	private Participante persona5;
	@Mock
	private Participante persona6;
	@Mock
	private Votacion votacion;
	@Mock
	private Votacion votacion2;
	@Mock
	private Votacion votacion3;
	@Mock
	private Votacion votacion4;
	@Mock
	private Votacion votacion5;
	@Mock
	private Votacion votacion6;
	
	@BeforeEach
	public void setUp() {
		opinion = ClasificacionDeFoto.VINCHUCA;
		zonaDeCobertura = mock(ZonaDeCobertura.class);
		persona = mock(Participante.class);
		persona2 = mock(Participante.class);
		persona3 = mock(Participante.class);
		persona4 = mock(Participante.class);
		persona5 = mock(Participante.class);
		persona6 = mock(Participante.class);
		votacion = mock(Votacion.class);
		votacion2 = mock(Votacion.class);
		votacion3 = mock(Votacion.class);
		votacion4 = mock(Votacion.class);
		votacion5 = mock(Votacion.class);
		votacion6 = mock(Votacion.class);
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
	public void muestraRegistradaComoVinchucaVotadaUnaVezComoChinchePorUsuarioBasicoEsIndefinida() throws Exception {
		when(votacion2.getParticipante()).thenReturn(persona2);
		when(votacion2.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion2.getNivelDeConocimientoParticipante()).thenReturn("Nivel Basico");
		muestra1.registrarVotacion(votacion2);
		assertEquals("Muestra Indefinida", muestra1.getResultadoActual());
	}
	@Test
	public void muestraRegistradaComoVinchucaConDosVotosChincheFoliadaEsChinche() throws Exception {
		when(votacion2.getParticipante()).thenReturn(persona2);
		when(votacion2.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion2.getNivelDeConocimientoParticipante()).thenReturn("Nivel Basico");
		when(votacion3.getParticipante()).thenReturn(persona3);
		when(votacion3.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion3.getNivelDeConocimientoParticipante()).thenReturn("Nivel Basico");
		
		muestra1.registrarVotacion(votacion2);
		muestra1.registrarVotacion(votacion3);
		assertEquals("Chinche Foliada", muestra1.getResultadoActual());
	}
	@Test
	public void muestraVotadaPorElParticipanteQueLaCreoLanzaExcepcion() {
		when(votacion.getParticipante()).thenReturn(persona);
		when(votacion.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion.getNivelDeConocimientoParticipante()).thenReturn("Nivel Basico");
		assertThrows(ErrorParticipanteNoPuedeVotarEstaMuestra.class,() ->{	muestra1.registrarVotacion(votacion);
		});
		System.out.println("hola");
	}
	@Test
	public void muestraCreadaPorExpertoSeCreaConNivelDeValidacionExperto() {
		when(persona4.getAlias()).thenReturn("pepe");
		when(persona4.getNivelDeConocimiento()).thenReturn("Nivel Experto");
		muestra2 = new Muestra(imagen, persona4, opinion,ubicacion);
		assertEquals("Nivel Experto", muestra2.getNivelDeValidacion());
	}
	@Test
	public void muestraCreadaPorParticipanteBasicoSeCreaConNivelDeValidacionBasico() {
		when(persona.getAlias()).thenReturn("fer");
		when(persona.getNivelDeConocimiento()).thenReturn("Nivel Basico");
		assertEquals("Nivel Basico", muestra1.getNivelDeValidacion());
	}
	@Test
	public void muestraSoloTieneEnCuentaOpinionDeExpertoParaElResultadoActual() throws Exception {
		when(votacion4.getParticipante()).thenReturn(persona4);
		when(votacion4.getOpinion()).thenReturn("Vinchuca");
		when(votacion4.getNivelDeConocimientoParticipante()).thenReturn("Nivel Basico");
		when(votacion5.getParticipante()).thenReturn(persona5);
		when(votacion5.getOpinion()).thenReturn("Vinchuca");
		when(votacion5.getNivelDeConocimientoParticipante()).thenReturn("Nivel Basico");
		when(votacion3.getParticipante()).thenReturn(persona3);
		when(votacion3.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion3.getNivelDeConocimientoParticipante()).thenReturn("Nivel Experto");
		muestra1.registrarVotacion(votacion4);
		muestra1.registrarVotacion(votacion5);
		muestra1.registrarVotacion(votacion3);
		assertEquals("Chinche Foliada", muestra1.getResultadoActual());
	}
	@Test
	public void muestraCreadaComoVinchucaQuedaValidadaComoChincheSiLaVotan2ExpertosComoChinche() throws Exception {
		when(votacion4.getParticipante()).thenReturn(persona4);
		when(votacion4.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion4.getNivelDeConocimientoParticipante()).thenReturn("Nivel Experto");
		when(votacion5.getParticipante()).thenReturn(persona5);
		when(votacion5.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion5.getNivelDeConocimientoParticipante()).thenReturn("Nivel Experto");
		when(persona5.getNivelDeConocimiento()).thenReturn("Nivel Experto");
		muestra1.registrarVotacion(votacion4);
		muestra1.registrarVotacion(votacion5);
		assertEquals("Chinche Foliada", muestra1.getResultadoActual());
		assertEquals("Nivel Validada", muestra1.getNivelDeValidacion());
	}
	@Test
	public void muestraValidadaNoPuedeSerVotada() throws Exception {
		when(votacion4.getParticipante()).thenReturn(persona4);
		when(votacion4.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion4.getNivelDeConocimientoParticipante()).thenReturn("Nivel Experto");
		when(votacion5.getParticipante()).thenReturn(persona5);
		when(votacion5.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion5.getNivelDeConocimientoParticipante()).thenReturn("Nivel Experto");
		when(persona5.getNivelDeConocimiento()).thenReturn("Nivel Experto");
		
		muestra1.registrarVotacion(votacion4);
		muestra1.registrarVotacion(votacion5);
		assertThrows(ErrorParticipanteNoPuedeVotarEstaMuestra.class,() ->{
			muestra1.registrarVotacion(votacion3);
			});
	}
	@Test
	public void testMuestraVotadaPorDosExpertosConLaMismaOpinionsSeValidaEInformaAZonasDeCobertura() throws Exception {
		when(votacion4.getParticipante()).thenReturn(persona4);
		when(votacion4.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion4.getNivelDeConocimientoParticipante()).thenReturn("Nivel Experto");
		when(votacion5.getParticipante()).thenReturn(persona5);
		when(persona5.getNivelDeConocimiento()).thenReturn("Nivel Experto");
		when(votacion5.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion5.getNivelDeConocimientoParticipante()).thenReturn("Nivel Experto");
		muestra1.asignarZona(zonaDeCobertura);
		muestra1.registrarVotacion(votacion4);
		muestra1.registrarVotacion(votacion5);
		assertEquals("Nivel Validada", muestra1.getNivelDeValidacion());
		verify(zonaDeCobertura, times(1)).muestraValidida(any(Muestra.class));
	}
	
	@Test
	public void fechaDeLaUltimaVotacionDeLaMuestra() {
		
	}
}
