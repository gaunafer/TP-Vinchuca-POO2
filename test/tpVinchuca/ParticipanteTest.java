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
	
	@Mock
	private NivelDeConocimiento nivelDeConocimiento = mock(NivelDeConocimiento.class);

	private List<Votacion> votaciones;
	private List<Muestra> muestras;

	@BeforeEach
	public void setUp() {

		pepe = new Participante("pepe", new Basico(aplicacionVinchucas));
		pepa = new Participante("pepa", new ExpertoValidado(aplicacionVinchucas));
		pipo = new Participante("pipo", new Experto(aplicacionVinchucas));

		votaciones = new ArrayList<Votacion>();
		muestras = new ArrayList<Muestra>();

	/*	when(votacion1.getFechaDeCreacion()).thenReturn(LocalDate.now().minusMonths(1l));
		when(votacion2.getFechaDeCreacion()).thenReturn(LocalDate.now().plusMonths(1l));
		when(votacion3.getFechaDeCreacion()).thenReturn(LocalDate.now().minusMonths(1l));
		when(votacion4.getFechaDeCreacion()).thenReturn(LocalDate.now().minusMonths(1l));
		when(votacion5.getFechaDeCreacion()).thenReturn(LocalDate.now().minusMonths(1l));
		when(votacion6.getFechaDeCreacion()).thenReturn(LocalDate.now().minusMonths(1l));
		when(votacion7.getFechaDeCreacion()).thenReturn(LocalDate.now().minusMonths(1l));
		when(votacion8.getFechaDeCreacion()).thenReturn(LocalDate.now().minusMonths(1l));
		when(votacion9.getFechaDeCreacion()).thenReturn(LocalDate.now().minusMonths(1l));
		when(votacion10.getFechaDeCreacion()).thenReturn(LocalDate.now().minusMonths(1l));
		when(votacion11.getFechaDeCreacion()).thenReturn(LocalDate.now().minusMonths(1l));
		
		when(muestra1.getParticipante()).thenReturn(pepe);
		when(muestra2.getParticipante()).thenReturn(pepe);
		when(muestra3.getParticipante()).thenReturn(pepe);
		when(muestra4.getParticipante()).thenReturn(pepe);
		when(muestra5.getParticipante()).thenReturn(pepe);
		when(muestra6.getParticipante()).thenReturn(pepe);
		when(muestra7.getParticipante()).thenReturn(pepe);
		when(muestra8.getParticipante()).thenReturn(pepe);
		when(muestra9.getParticipante()).thenReturn(pepe);
		when(muestra10.getParticipante()).thenReturn(pepe);
		
		when(muestra11.getParticipante()).thenReturn(pepe);
		when(muestra1.getFecha()).thenReturn(LocalDate.now().minusMonths(2l));
		when(muestra2.getFecha()).thenReturn(LocalDate.now().minusMonths(1l));
		when(muestra3.getFecha()).thenReturn(LocalDate.now().minusMonths(1l));
		when(muestra4.getFecha()).thenReturn(LocalDate.now().minusMonths(1l));
		when(muestra5.getFecha()).thenReturn(LocalDate.now().minusMonths(1l));
		when(muestra6.getFecha()).thenReturn(LocalDate.now().minusMonths(1l));
		when(muestra7.getFecha()).thenReturn(LocalDate.now().minusMonths(1l));
		when(muestra8.getFecha()).thenReturn(LocalDate.now().minusMonths(1l));
		when(muestra9.getFecha()).thenReturn(LocalDate.now().minusMonths(1l));
		when(muestra10.getFecha()).thenReturn(LocalDate.now().minusMonths(1l));
		when(muestra11.getFecha()).thenReturn(LocalDate.now().minusMonths(1l)); */
		
		//when(nivelDeConocimiento.getCantidadDeVotacionesDeUnParticipantePertenecientesAlMesAnteriorAlMesEnCurso(pepe)).thenReturn(11l);
		
	}

	@Test
	public void seCreaUnParticipanteConSuAliasUnaListaVaciaDeVotacionesYConUnEstadoBasico() {

		assertEquals("pepe", pepe.getAlias());
		assertEquals("Nivel Basico", pepe.getNivelDeConocimiento());
	}

	@Test
    public void siElParticipanteHaceMasDe10VotacionesEnUnMismoMesSuEstadoEsExperto() {
		
		when(nivelDeConocimiento.getCantidadDeMuestrasDeUnParticipantePertenecientesAlMesAnteriorAlMesEnCurso(pepe)).thenReturn(11);
		when(nivelDeConocimiento.getCantidadDeVotacionesDeUnParticipanteA30DiasDeLaFechaActual(pepe)).thenReturn(21);
    	
     /*  votaciones.add(votacion1);
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
       
       muestras.add(muestra1);
       muestras.add(muestra2);
       muestras.add(muestra3);
       muestras.add(muestra4);
       muestras.add(muestra5);
       muestras.add(muestra6);
       muestras.add(muestra7);
       muestras.add(muestra8);
       muestras.add(muestra9);
       muestras.add(muestra10);
       muestras.add(muestra11);
   
       

       when(aplicacionVinchucas.getMuestrasDeParticipantePorFecha(pepe, LocalDate.now().minusMonths(1l))).thenReturn(muestras);
       pepe.actualizarEstado();*/
        

        assertEquals("Nivel Experto", pepe.getNivelDeConocimiento());
    }

	@Test
	public void siLaPersonaNoHizo10VotacionesEnUnMismoMesSuEstadoEsBasico() {
		
	/*	   votaciones.add(votacion1);
	       votaciones.add(votacion2);
	       votaciones.add(votacion3);
	       votaciones.add(votacion4);
	       
	       muestras.add(muestra1);
	       muestras.add(muestra2);
	       muestras.add(muestra3);
	       muestras.add(muestra4);
	       muestras.add(muestra5);

	
		when(aplicacionVinchucas.getMuestrasDeParticipantePorFecha(pepe, LocalDate.now())).thenReturn(muestras);
		pepe.actualizarEstado(); */

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
