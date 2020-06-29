package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import tpVinchuca.nivelDeValidacion.NivelExperto;
import tpVinchuca.nivelDeValidacion.NivelValidada;
import tpVinchucas.error.ErrorParticipanteNoPuedeVotarEstaMuestra;
import tpVinchucas.niveldeConocimiento.Basico;
import tpVinchucas.niveldeConocimiento.Experto;
import tpVinchucas.niveldeConocimiento.ExpertoValidado;

import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MuestraTest {
	
	private Muestra muestra1;
	private Muestra muestra2;
	private ClasificacionDeFoto opinion;
	private List<Votacion> votaciones;
	@Mock
	private Ubicacion ubicacion;
	@Mock
	private Imagen imagen;
	@Mock
	private ZonaDeCobertura sarandi;
	@Mock
	private ZonaDeCobertura donBosco;
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
	
	@Mock
	private AplicacionVinchucas aplicacionVinchucas = mock(AplicacionVinchucas.class);
	@Spy
	private Experto nivelDeConocimientoExperto;
	@Spy
	private Basico nivelDeConocimientoBasico;
	@Spy
	private ExpertoValidado nivelDeConocimientoExpertoValidado;
	
	@BeforeEach
	public void setUp() {
		nivelDeConocimientoExpertoValidado = new ExpertoValidado(aplicacionVinchucas);
		nivelDeConocimientoExperto = new Experto(aplicacionVinchucas);
		nivelDeConocimientoBasico = new Basico(aplicacionVinchucas);
		opinion = ClasificacionDeFoto.VINCHUCA_INFESTANS;
		sarandi = mock(ZonaDeCobertura.class);
		donBosco = mock(ZonaDeCobertura.class);
		persona = mock(Participante.class);
		when(persona.getNivelDeConocimiento()).thenReturn(nivelDeConocimientoBasico);
		persona2 = mock(Participante.class);
		persona3 = mock(Participante.class);
		persona4 = mock(Participante.class);
		when(persona4.getNivelDeConocimiento()).thenReturn(nivelDeConocimientoExperto);
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
		votaciones = new ArrayList<Votacion>();
	}
	
	@Test
	public void muestraConstructorTest() {
		assertEquals(persona.getAlias(), muestra1.getAliasParticipante());
		assertEquals(ubicacion, muestra1.getUbicacion());
		assertEquals(imagen, muestra1.getImagen());
		assertEquals("Vinchuca Infestans", muestra1.getVeredicto());
		assertEquals(LocalDate.now(), muestra1.getFecha());
	}
	@Test
	public void resultadoActualMuestraRegistradaComoVinchucaSinVotosEsVinchuca() {
		assertEquals("Vinchuca Infestans", muestra1.getResultadoActual());
	}
	
	@Test
	public void muestraRegistradaComoVinchucaVotadaUnaVezComoChinchePorUsuarioBasicoEsIndefinida() throws Exception {
		when(votacion2.getParticipante()).thenReturn(persona2);
		when(votacion2.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion2.participanteEsExpertoAlMomentoDeVotar()).thenReturn(false);
		muestra1.addVotacion(votacion2);
		assertEquals("Muestra Indefinida", muestra1.getResultadoActual());
	}
	
	@Test
	public void muestraRegistradaComoVinchucaConDosVotosChincheFoliadaEsChinche() throws Exception {
		when(votacion2.getParticipante()).thenReturn(persona2);
		when(votacion2.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion2.participanteEsExpertoAlMomentoDeVotar()).thenReturn(false);
		when(votacion3.getParticipante()).thenReturn(persona3);
		when(votacion3.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion3.participanteEsExpertoAlMomentoDeVotar()).thenReturn(false);
		
		muestra1.addVotacion(votacion2);
		muestra1.addVotacion(votacion3);
		assertEquals("Chinche Foliada", muestra1.getResultadoActual());
	}
	
	/*
	@Test
	public void muestraVotadaPorElParticipanteQueLaCreoLanzaExcepcion() {
		when(votacion.getParticipante()).thenReturn(persona);
		when(votacion.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion.participanteEsExpertoAlMomentoDeVotar()).thenReturn(false);
		assertThrows(ErrorParticipanteNoPuedeVotarEstaMuestra.class,() ->{	muestra1.registrarVotacion(votacion);
		});
	}*/
	
	/*
	@Test
	public void muestraCreadaPorExpertoSeCreaConNivelDeValidacionExperto() {
		when(persona4.getAlias()).thenReturn("pepe");
		when(persona4.getNivelDeConocimiento()).thenReturn(nivelDeConocimientoExperto);
		muestra2 = new Muestra(imagen, persona4, opinion,ubicacion);
		assertEquals("Nivel Experto", muestra2.getNivelDeValidacion());
	}
	*/
	
	/*
	@Test
	public void muestraCreadaPorParticipanteBasicoSeCreaConNivelDeValidacionBasico() {
		when(persona.getAlias()).thenReturn("fer");
		assertEquals(, muestra1.getNivelDeValidacion());
	}*/
	
	@Test
	public void muestraSoloTieneEnCuentaOpinionDeExpertoParaElResultadoActual() throws Exception {
		when(votacion4.getParticipante()).thenReturn(persona4);
		when(votacion4.getOpinion()).thenReturn("Vinchuca");
		when(votacion4.participanteEsExpertoAlMomentoDeVotar()).thenReturn(false);
		when(votacion5.getParticipante()).thenReturn(persona5);
		when(votacion5.getOpinion()).thenReturn("Vinchuca");
		when(votacion5.participanteEsExpertoAlMomentoDeVotar()).thenReturn(false);
		when(votacion3.getParticipante()).thenReturn(persona3);
		when(votacion3.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion3.participanteEsExpertoAlMomentoDeVotar()).thenReturn(true);
		when(persona.getNivelDeConocimiento()).thenReturn(nivelDeConocimientoBasico);
		muestra1 = new Muestra(imagen, persona, opinion, ubicacion);
		muestra1.addVotacion(votacion4);
		muestra1.addVotacion(votacion5);
		muestra1.addVotacion(votacion3);
		muestra1.setNivelDeValidacion(new NivelExperto());
		assertEquals("Chinche Foliada", muestra1.getResultadoActual());
	}
	/*
	@Test
	public void muestraCreadaComoVinchucaQuedaValidadaComoChincheSiLaVotan2ExpertosComoChinche() throws Exception {
		when(votacion4.getParticipante()).thenReturn(persona4);
		when(votacion4.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion4.participanteEsExpertoAlMomentoDeVotar()).thenReturn(true);
		when(votacion5.getParticipante()).thenReturn(persona5);
		when(votacion5.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion5.participanteEsExpertoAlMomentoDeVotar()).thenReturn(true);
		when(persona5.getNivelDeConocimiento()).thenReturn(nivelDeConocimientoExperto);
		when(persona4.getNivelDeConocimiento()).thenReturn(nivelDeConocimientoExperto);
		when(persona.getNivelDeConocimiento()).thenReturn(nivelDeConocimientoBasico);
		muestra1 = new Muestra(imagen, persona, opinion, ubicacion);
		muestra1.registrarVotacion(votacion4);
		muestra1.registrarVotacion(votacion5);
		
		assertEquals("Chinche Foliada", muestra1.getResultadoActual());
		assertEquals("Nivel Validada", muestra1.getNivelDeValidacion());
	}
	*/
	
	/*
	@Test
	public void muestraValidadaNoPuedeSerVotada() throws Exception {
		when(votacion4.getParticipante()).thenReturn(persona4);
		when(votacion4.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion4.participanteEsExpertoAlMomentoDeVotar()).thenReturn(true);
		when(votacion5.getParticipante()).thenReturn(persona5);
		when(votacion5.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion5.participanteEsExpertoAlMomentoDeVotar()).thenReturn(true);
		when(persona5.getNivelDeConocimiento()).thenReturn(nivelDeConocimientoExperto);
		
		muestra1.registrarVotacion(votacion4);
		muestra1.registrarVotacion(votacion5);
		assertThrows(ErrorParticipanteNoPuedeVotarEstaMuestra.class,() ->{
			muestra1.registrarVotacion(votacion3);
			});
	}
	*/
	
	/*
	@Test
	public void testMuestraVotadaPorDosExpertosConLaMismaOpinionsSeValidaEInformaAZonasDeCobertura() throws Exception {
		when(votacion4.getParticipante()).thenReturn(persona4);
		when(votacion4.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion4.participanteEsExpertoAlMomentoDeVotar()).thenReturn(true);
		when(votacion5.getParticipante()).thenReturn(persona5);
		when(persona5.getNivelDeConocimiento()).thenReturn(nivelDeConocimientoExperto);
		when(votacion5.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion5.participanteEsExpertoAlMomentoDeVotar()).thenReturn(true);
		muestra1.asignarZona(sarandi);
		muestra1.asignarZona(donBosco);
		muestra1.eliminarZona(donBosco);
		muestra1.registrarVotacion(votacion4);
		muestra1.registrarVotacion(votacion5);
		assertEquals("Nivel Validada", muestra1.getNivelDeValidacion());
		verify(sarandi, times(1)).muestraValidida(any(Muestra.class));
		verify(donBosco, never()).muestraValidida(any(Muestra.class));
	}*/
	
	@Test
	public void fechaDeLaUltimaVotacionDeLaMuestra() {
		when(votacion.getFecha()).thenReturn(LocalDate.now().minusDays(2l));
		when(votacion2.getFecha()).thenReturn(LocalDate.now().minusDays(1l));
		votaciones.add(votacion);
		votaciones.add(votacion2);
		muestra1.addVotacion(votacion);
		muestra1.addVotacion(votacion2);
			
		assertEquals(votacion2.getFecha(), muestra1.getFechaUltimaVotacion());	
		
	}
	
	@Test
	public void laMuestraNoTieneVotaciones() {
		
		assertFalse(muestra1.esMuestraVotada());
	}
	
	@Test
	public void laMuestraTieneVotaciones() {
		votaciones.add(votacion);
		votaciones.add(votacion2);
		muestra2.addVotacion(votacion);
		muestra2.addVotacion(votacion2);
		
		assertTrue(muestra2.esMuestraVotada());
	}
	

	@Test
	public void testGetVotacionesExpertas() {
		muestra1.addVotacion(votacion);
		muestra1.addVotacion(votacion2);
		muestra1.addVotacion(votacion3);
		when(votacion.participanteEsExpertoAlMomentoDeVotar()).thenReturn(true);
		when(votacion2.participanteEsExpertoAlMomentoDeVotar()).thenReturn(true);
		when(votacion3.participanteEsExpertoAlMomentoDeVotar()).thenReturn(false);
		
		votaciones = muestra1.getVotacionesExpertas();
		
		assertTrue(votaciones.contains(votacion));
		assertTrue(votaciones.contains(votacion2));
		assertFalse(votaciones.contains(votacion3));
	}

	// Se chequea:
	// - el agregado de zonas al informador de zonas
	// - el aviso enviado a las zonas de que la muestra ha sido validada
	@Test
	public void testSeLeAvisaALasZonasQueLaMuestraFueValidada() {
		muestra1.asignarZona(donBosco);
		muestra1.asignarZona(sarandi);
		
		muestra1.informarVerificacion();

		verify(donBosco).muestraValidida(muestra1);
		verify(sarandi).muestraValidida(muestra1);
	}
	
	// Se chequea:
	// - la eliminacion de zonas del informador de zonas
	// - el aviso enviado a las zonas de que la muestra ha sido validada
	@Test
	public void testSeEliminaZonaYSeLeAvisaALasZonasQueQuedanQueLaMuestraFueValidada() {
		muestra1.asignarZona(donBosco);
		muestra1.asignarZona(sarandi);
		muestra1.eliminarZona(donBosco);
		
		muestra1.informarVerificacion();
		
		verify(donBosco, never()).muestraValidida(muestra1);;
		verify(sarandi).muestraValidida(muestra1);
	}

	// Testea el metodo muestraVotadaPor, si la persona ha votado la muestra
	@Test
	public void testMuestraFueVotadaPorPersona4() {
		when(votacion4.getParticipante()).thenReturn(persona4);
		muestra1.addVotacion(votacion4);
		
		assertTrue(muestra1.muestraVotadaPor(persona4));
	}
	
	// Testea el metodo muestraVotadaPor, si la persona ha votado la muestra
	@Test
	public void testMuestraNoFueVotadaPorPersona3() {
		when(votacion4.getParticipante()).thenReturn(persona4);
		muestra1.addVotacion(votacion4);
		
		assertFalse(muestra1.muestraVotadaPor(persona3));
	}
	
	// Chequea que la muestra tenga dos opiniones iguales
	@Test
	public void testTieneDosOpinionesExpertasIguales() {
		when(votacion4.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion4.participanteEsExpertoAlMomentoDeVotar()).thenReturn(true);
		when(votacion3.getOpinion()).thenReturn("Vinchuca");
		when(votacion3.participanteEsExpertoAlMomentoDeVotar()).thenReturn(true);
		when(votacion5.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion5.participanteEsExpertoAlMomentoDeVotar()).thenReturn(true);
		
		muestra1.addVotacion(votacion4);
		muestra1.addVotacion(votacion5);
		
		assertTrue(muestra1.tieneDosOpinionesExpertas(votacion4));
	}
	
	// Chequea que la muestra no tenga dos opiniones iguales
	@Test
	public void testNoTieneDosOpinionesExpertasIguales() {
		when(votacion4.getOpinion()).thenReturn("Chinche Foliada");
		when(votacion4.participanteEsExpertoAlMomentoDeVotar()).thenReturn(true);
		when(votacion3.getOpinion()).thenReturn("Vinchuca");
		when(votacion3.participanteEsExpertoAlMomentoDeVotar()).thenReturn(true);
		when(votacion5.getOpinion()).thenReturn("Phitia Chinche");
		when(votacion5.participanteEsExpertoAlMomentoDeVotar()).thenReturn(true);
		
		muestra1.addVotacion(votacion4);
		muestra1.addVotacion(votacion5);
		
		assertFalse(muestra1.tieneDosOpinionesExpertas(votacion4));
	}
	
	// Chequea que estando la muestra en nivelBasico, devuelva false a estar validada
	@Test
	public void testMuestraEsBasicaYNoEstaValidada() {
		assertFalse(muestra1.estaValidada());
	}
	
	// Chequea que estando la muestra en nivelExperto, devuelva false a estar validada
	@Test
	public void testMuestraEsExpertoYNoEstaValidada() {
		assertFalse(muestra2.estaValidada());
	}
	
	// Chequea que seteando estado a validada, devuelva que la muestra esta validada
	@Test
	public void testMuestraEstaValidada() {
		muestra1.setNivelDeValidacion(new NivelValidada());
		
		assertTrue(muestra1.estaValidada());
	}
}
