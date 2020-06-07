package tpVinchuca;

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
	
	
	@Test
	public void seCreaUnNivelExpertoySuNivelDeConocimientoEsNivelExperto() {
		
		assertEquals("Nivel Experto", expertoValidado.getNivelDeConocimiento());
	}
	
	@Test
	public void elNivelDeConocimientoSigueSiendoExpertoCuandoNoCumpleCon10MuestrasEnLos30DiasPosterioresALaFechaActual(){


	    when(aplicacionVinchucas.getVotacionDeParticipantePorfecha(juanaMolina)).thenReturn(votaciones);
	    when(aplicacionVinchucas.getMuestrasDeParticipantePorFecha(juanaMolina, LocalDate.now().minusMonths(1l))).thenReturn(muestras);
	    when(votaciones.size()).thenReturn(11);
	    when(muestras.size()).thenReturn(11);
	    
	    expertoValidado.verificarEstado(juanaMolina);
	    
	    verify(juanaMolina, never()).setNivelDeConocimiento(any(Basico.class));
	   
	}
	
	@Test
	public void elNivelDeConocimientoSigueSiendoExpertoValidadoCuandoNoCumpleCon20VotacionesEnLos30DiasPosterioresALaFechaActual() {



	    when(aplicacionVinchucas.getMuestrasDeParticipantePorFecha(juanaMolina, LocalDate.now().minusMonths(1l))).thenReturn(muestras);
	    when(aplicacionVinchucas.getVotacionDeParticipantePorfecha(juanaMolina)).thenReturn(votaciones);
	    when(votaciones.size()).thenReturn(11);
	    when(muestras.size()).thenReturn(11);
	    expertoValidado.verificarEstado(juanaMolina);
	    
	    verify(juanaMolina, never()).setNivelDeConocimiento(any(Basico.class));
	}
		
	
	@Test
	public void elNivelDeConocimientoSigueSiendoExpertoCuandoCumpleConMas20VotacionesY10MuestrasEnLos30DiasPosterioresALaFechaActual() {



	    when(aplicacionVinchucas.getMuestrasDeParticipantePorFecha(juanaMolina, LocalDate.now().minusMonths(1l))).thenReturn(muestras);
	    when(aplicacionVinchucas.getVotacionDeParticipantePorfecha(juanaMolina)).thenReturn(votaciones);
	    when(votaciones.size()).thenReturn(21);
	    when(muestras.size()).thenReturn(11);
	   
	    
	    expertoValidado.verificarEstado(juanaMolina);
	    
	    verify(juanaMolina, never()).setNivelDeConocimiento(any(Experto.class));
	   
	}


}
