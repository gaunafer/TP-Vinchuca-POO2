package tpVinchuca.niveldeConocimiento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import tpVinchuca.AplicacionVinchucas;
import tpVinchuca.Muestra;
import tpVinchuca.Participante;
import tpVinchuca.Votacion;
import tpVinchucas.niveldeConocimiento.Basico;
import tpVinchucas.niveldeConocimiento.Experto;
import tpVinchucas.niveldeConocimiento.ExpertoValidado;
import tpVinchucas.niveldeConocimiento.NivelDeConocimiento;

public class ExpertoValidadoTest {
	
	@Mock
	private AplicacionVinchucas aplicacionVinchucas = mock(AplicacionVinchucas.class);
	

	private NivelDeConocimiento expertoValidado = new ExpertoValidado(aplicacionVinchucas);
	
	
	@Mock
	private Participante juanaMolina = mock(Participante.class);
	
	@Mock
	private List<Votacion> votaciones = mock(ArrayList.class);
	@Mock
	private List<Muestra> muestras = mock(ArrayList.class);
	@Mock
	ExpertoValidado nivelConocimientoExperto = mock(ExpertoValidado.class);
//	
//	@Test
//	public void seCreaUnNivelExpertoySuNivelDeConocimientoEsNivelExperto() {
//		
//		assertEquals(nivelConocimientoExperto, expertoValidado.getNivelDeConocimiento());
//	}
	
	@Test
	public void elNivelDeConocimientoSigueSiendoExpertoCuandoNoCumpleCon10MuestrasEnLos30DiasPosterioresALaFechaActual(){


	    when(aplicacionVinchucas.getVotacionesDeParticipanteDeLosUltimos30Dias(juanaMolina)).thenReturn(votaciones);
	    when(aplicacionVinchucas.getMuestrasDeParticipantePorFecha(juanaMolina, LocalDate.now().minusMonths(1l))).thenReturn(muestras);
	    when(votaciones.size()).thenReturn(11);
	    when(muestras.size()).thenReturn(11);
	    
	    expertoValidado.verificarEstado(juanaMolina);
	    
	    verify(juanaMolina, never()).setNivelDeConocimiento(any(Basico.class));
	   
	}
	
	@Test
	public void elNivelDeConocimientoSigueSiendoExpertoValidadoCuandoNoCumpleCon20VotacionesEnLos30DiasPosterioresALaFechaActual() {



	    when(aplicacionVinchucas.getMuestrasDeParticipantePorFecha(juanaMolina, LocalDate.now().minusMonths(1l))).thenReturn(muestras);
	    when(aplicacionVinchucas.getVotacionesDeParticipanteDeLosUltimos30Dias(juanaMolina)).thenReturn(votaciones);
	    when(votaciones.size()).thenReturn(11);
	    when(muestras.size()).thenReturn(11);
	    expertoValidado.verificarEstado(juanaMolina);
	    
	    verify(juanaMolina, never()).setNivelDeConocimiento(any(Basico.class));
	}
		
	
	@Test
	public void elNivelDeConocimientoSigueSiendoExpertoCuandoCumpleConMas20VotacionesY10MuestrasEnLos30DiasPosterioresALaFechaActual() {



	    when(aplicacionVinchucas.getMuestrasDeParticipantePorFecha(juanaMolina, LocalDate.now().minusMonths(1l))).thenReturn(muestras);
	    when(aplicacionVinchucas.getVotacionesDeParticipanteDeLosUltimos30Dias(juanaMolina)).thenReturn(votaciones);
	    when(votaciones.size()).thenReturn(21);
	    when(muestras.size()).thenReturn(11);
	   
	    
	    expertoValidado.verificarEstado(juanaMolina);
	    
	    verify(juanaMolina, never()).setNivelDeConocimiento(any(Experto.class));
	   
	}


}
