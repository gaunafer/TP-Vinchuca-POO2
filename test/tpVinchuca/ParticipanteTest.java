package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.Mockito.*;

public class ParticipanteTest {

	private Participante pepe;
	private Participante pepa;
	private Participante pipo;
	private int mesPasado =  LocalDate.now().getMonthValue()-1;

	@Mock
	private Votacion votacion1 = mock(Votacion.class);
	@Mock
	public Votacion votacion2 = mock(Votacion.class);
	@Mock
	public Votacion votacion3 = mock(Votacion.class);
	@Mock
	public Votacion votacion4 = mock(Votacion.class);
	@Mock
	public Votacion votacion5 = mock(Votacion.class);
	@Mock
	public Votacion votacion6 = mock(Votacion.class);
	@Mock
	public Votacion votacion7 = mock(Votacion.class);
	@Mock
	public Votacion votacion8 = mock(Votacion.class);
	@Mock
	public Votacion votacion9 = mock(Votacion.class);
	@Mock
	public Votacion votacion10 = mock(Votacion.class);
	@Mock
	public Votacion votacion11 = mock(Votacion.class);
	@Mock
	private AplicacionVinchucas aplicacionVinchucas = mock(AplicacionVinchucas.class);

	private List<Votacion> votaciones;

	@BeforeEach
	public void setUp() {

		pepe = new Participante("pepe", new Basico(aplicacionVinchucas));
		pepa = new Participante("pepa", new ExpertoValidado(aplicacionVinchucas));
		pipo = new Participante("pipo", new Experto(aplicacionVinchucas));

		votaciones = new ArrayList<Votacion>();

		when(votacion1.getFechaDeCreacion()).thenReturn(LocalDate.of(2020, mesPasado, 6));
		when(votacion2.getFechaDeCreacion()).thenReturn(LocalDate.of(2020, mesPasado, 6));
		when(votacion3.getFechaDeCreacion()).thenReturn(LocalDate.of(2020, mesPasado, 6));
		when(votacion4.getFechaDeCreacion()).thenReturn(LocalDate.of(2020, mesPasado, 6));
		when(votacion5.getFechaDeCreacion()).thenReturn(LocalDate.of(2020, mesPasado, 6));
		when(votacion6.getFechaDeCreacion()).thenReturn(LocalDate.of(2020, mesPasado, 6));
		when(votacion7.getFechaDeCreacion()).thenReturn(LocalDate.of(2020, mesPasado, 6));
		when(votacion8.getFechaDeCreacion()).thenReturn(LocalDate.of(2020, mesPasado, 6));
		when(votacion9.getFechaDeCreacion()).thenReturn(LocalDate.of(2020, mesPasado, 6));
		when(votacion10.getFechaDeCreacion()).thenReturn(LocalDate.of(2020, mesPasado, 6));
		when(votacion11.getFechaDeCreacion()).thenReturn(LocalDate.of(2020, mesPasado, 6));

	}

	@Test
	public void seCreaUnParticipanteConSuAliasUnaListaVaciaDeVotacionesYConUnEstadoBasico() {

		assertEquals("pepe", pepe.getAlias());
		assertEquals("Nivel Basico", pepe.getNivelDeConocimiento());
	}

	@Test
    public void siElParticipanteHaceMasDe10VotacionesEnUnMismoMesSuEstadoEsExperto() {
    	
       votaciones.add(votacion1);
       votaciones.add(votacion2);
       votaciones.add(votacion3);
       votaciones.add(votacion4);
       votaciones.add(votacion5);
       votaciones.add(votacion6);
       votaciones.add(votacion7);
       votaciones.add(votacion8);
       votaciones.add(votacion9);
       votaciones.add(votacion10);
       votaciones.add(votacion11);

       when(aplicacionVinchucas.getVotacionesDeParticipantePorFecha(pepe, LocalDate.now().minusMonths(1l))).thenReturn(votaciones);
       pepe.actualizarEstado();
        

        assertEquals("Nivel Experto", pepe.getNivelDeConocimiento());
    }

	@Test
	public void siLaPersonaNoHizo10VotacionesEnUnMismoMesSuEstadoEsBasico() {
		
		   votaciones.add(votacion1);
	       votaciones.add(votacion2);
	       votaciones.add(votacion3);
	       votaciones.add(votacion4);

	
		when(aplicacionVinchucas.getVotacionesDeParticipantePorFecha(pepe, LocalDate.now())).thenReturn(votaciones);
		pepe.actualizarEstado();

		assertEquals("Nivel Basico", pepe.getNivelDeConocimiento());
	}

	@Test
	public void siLaPersonaEnEstadoExpertoEnElMesSiguienteNoHizo10VotacionesVuelveAEstadoEstadoEsBasico() {
		pepe.setNivelDeConocimiento(new Experto(aplicacionVinchucas));
		pepe.actualizarEstado();

		assertEquals("Nivel Basico", pepe.getNivelDeConocimiento());
	}

	@Test
	public void siLaPersonaHaceMasDe10VotacionesEnUnMesSuEstadoEsExperto() {
		pepe.actualizarEstado();

		assertEquals("Nivel Experto", pepa.getNivelDeConocimiento());
	}


	public Votacion crearVotacion(LocalDate fechaCreacion, ResultadoDeMuestra tipoResultadoMuestra,
			Participante participante) {
		Votacion votacion = new Votacion(tipoResultadoMuestra, participante);
		votacion.setFechaDeCreacion(fechaCreacion);
		return votacion;
	}

}
