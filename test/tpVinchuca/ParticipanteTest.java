package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.stubbing.OngoingStubbing;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ParticipanteTest {

	private Participante juanPerez;
	private Participante juanaMolina;
	private Participante marioBros;
	private Participante marioBrossFalso;
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
	@Mock
	private List<Muestra> muestras = mock(List.class);
	@Mock
	private List<Votacion> votaciones = mock(List.class);
	@Mock
	private AplicacionVinchucas aplicacion = mock(AplicacionVinchucas.class);
	
	
	//@Spy
	//private NivelDeConocimiento nivelDeConocimiento = mock(NivelDeConocimiento.class);
	@Spy
	private Basico nivelDeConocimiento;



	@BeforeEach
	public void setUp() {
		nivelDeConocimiento = new Basico(aplicacionVinchucas);
		juanPerez = new Participante("Juan Perez", nivelDeConocimiento);
		juanaMolina = new Participante("Juana Molina", new ExpertoValidado(aplicacionVinchucas));
		marioBros = new Participante("Mario Bros", new Experto(aplicacionVinchucas));	
		marioBrossFalso = new Participante("Mario Bros", new Experto(aplicacionVinchucas));
	}

	@Test
	public void seCreaUnParticipanteConSuAliasUnaListaVaciaDeVotacionesYConUnEstadoBasico() {

		assertEquals("Juan Perez", juanPerez.getAlias());
		assertEquals("Nivel Basico", juanPerez.getNivelDeConocimiento());
		assertEquals(false, juanPerez.esExperto());
	}
	
	@Test
	public void seCreaUnParticipanteConNivelExpertoPorErrorIgualmenteSuNivelDevalidacionSeSeteaEnBasico() {
		when(muestras.size()).thenReturn(0);
		when(votaciones.size()).thenReturn(0);
		when(aplicacionVinchucas.getMuestrasDeParticipantePorFecha(juanPerez, LocalDate.now().minusMonths(1l))).thenReturn(muestras);
		when(aplicacionVinchucas.getVotacionesDeParticipanteDeLosUltimos30Dias(juanPerez)).thenReturn(votaciones);
		when(nivelDeConocimiento.getCantidadDeMuestrasDeUnParticipanteA30DiasDeLaFechaActual(juanPerez)).thenReturn(0);
		when(nivelDeConocimiento.getCantidadDeVotacionesDeUnParticipanteA30DiasDeLaFechaActual(juanPerez)).thenReturn(0);
		
		assertEquals("Mario Bros", marioBros.getAlias());
		assertEquals("Nivel Basico", marioBros.getNivelDeConocimiento());
		assertEquals(false, marioBros.esExperto());
		
		
	}
	
	@Test
	public void seCreaUnParticipanteConSuAliasYConUnEstadoExpertoValidado() {

		assertEquals("Juana Molina", juanaMolina.getAlias());
		assertEquals("Nivel Experto", juanaMolina.getNivelDeConocimiento());
		assertEquals(true, juanaMolina.esExperto());
	}
	

	@Test
    public void siElParticipanteGeneraMasDe10MuestrasYRealiza20VotacionesEnLosUltimos30DiasYSeEjecutaLaFuncionActualizarEstadosDeLosParticipantesSuEstadoEsExperto() {

		when(muestras.size()).thenReturn(11);
		when(votaciones.size()).thenReturn(21);
		when(aplicacionVinchucas.getMuestrasDeParticipantePorFecha(juanPerez, LocalDate.now().minusMonths(1l))).thenReturn(muestras);
		when(aplicacionVinchucas.getVotacionesDeParticipanteDeLosUltimos30Dias(juanPerez)).thenReturn(votaciones);
		when(nivelDeConocimiento.getCantidadDeMuestrasDeUnParticipanteA30DiasDeLaFechaActual(juanPerez)).thenReturn(11);
		when(nivelDeConocimiento.getCantidadDeVotacionesDeUnParticipanteA30DiasDeLaFechaActual(juanPerez)).thenReturn(21);
		juanPerez.actualizarEstado();
		

       assertEquals("Nivel Experto", juanPerez.getNivelDeConocimiento());
		assertEquals(true, juanPerez.esExperto());
    }
	
	@Test
    public void siElParticipanteConNivelDeConocimientoBasicoGeneraMasDe10MuestrasYRealiza20VotacionesEnLosUltimos30DiasPeroNoHaSidoEjcutadaLaFuncionActualizarEstadoSuEstadoEsBasico() {

		when(muestras.size()).thenReturn(11);
		when(votaciones.size()).thenReturn(21);
		when(aplicacionVinchucas.getMuestrasDeParticipantePorFecha(juanPerez, LocalDate.now().minusMonths(1l))).thenReturn(muestras);
		when(aplicacionVinchucas.getVotacionesDeParticipanteDeLosUltimos30Dias(juanPerez)).thenReturn(votaciones);
		when(nivelDeConocimiento.getCantidadDeMuestrasDeUnParticipanteA30DiasDeLaFechaActual(juanPerez)).thenReturn(11);
		when(nivelDeConocimiento.getCantidadDeVotacionesDeUnParticipanteA30DiasDeLaFechaActual(juanPerez)).thenReturn(21);
		

        assertEquals("Nivel Basico", juanPerez.getNivelDeConocimiento());
		assertEquals(false, juanPerez.esExperto());
    }
 
	@Test
	public void siLaPersonaNoHizo10VotacionesEnUnMismoMesSuEstadoEsBasico() {
		
		assertEquals("Nivel Basico", juanPerez.getNivelDeConocimiento());
		assertEquals(false, juanPerez.esExperto());
	}

	@Test
	public void siLaPersonaEnEstadoExpertoEnElMesSiguienteNoHizo10VotacionesVuelveAEstadoEstadoEsBasico() {
		juanPerez.setNivelDeConocimiento(new Experto(aplicacionVinchucas));
		juanPerez.actualizarEstado();

		assertEquals("Nivel Basico", juanPerez.getNivelDeConocimiento());
		assertEquals(false, juanPerez.esExperto());
	}

	@Test
	public void siLaPersonaHaceMasDe10VotacionesEnUnMesSuEstadoEsExperto() {
		juanPerez.actualizarEstado();

		assertEquals("Nivel Experto", juanaMolina.getNivelDeConocimiento());
		assertEquals(true, juanaMolina.esExperto());
	}
	
	@Test
	public void corectoFuncionamientoDelOverridedelMetodoequals() {
		
		assertTrue(marioBros.equals(marioBrossFalso));
		assertTrue(marioBrossFalso.equals(marioBros));
		assertFalse(marioBros.equals(juanPerez));
	}


}
