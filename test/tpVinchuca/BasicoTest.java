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

public class BasicoTest {
	
	@Mock
	private AplicacionVinchucas aplicacionVinchucas = mock(AplicacionVinchucas.class);
	

	private NivelDeConocimiento basico = new Basico(aplicacionVinchucas);
	
	
	@Mock
	private Participante juanPerez = mock(Participante.class);
	
	@Mock
	private List<Votacion> votaciones = mock(ArrayList.class);
	@Mock
	private List<Muestra> muestras = mock(ArrayList.class);
	
	
	@Test
	public void seCreaUnNivelExpertoySuNivelDeConocimientoEsNivelBasico() {
		
		assertEquals("Nivel Basico", basico.getNivelDeConocimiento());
	}
	
	@Test
	public void elNivelDeConocimientoDejaDeSerBasicoCuandoCumpleConGenerarMasDe10MuestrasYMasDe20VotosEnLosUltimos30Dias(){


	    when(aplicacionVinchucas.getVotacionDeParticipantePorfecha(juanPerez)).thenReturn(votaciones);
	    when(aplicacionVinchucas.getMuestrasDeParticipantePorFecha(juanPerez, LocalDate.now().minusMonths(1l))).thenReturn(muestras);
	    when(votaciones.size()).thenReturn(21);
	    when(muestras.size()).thenReturn(11);
	    
	    basico.verificarEstado(juanPerez);
	    
	    verify(juanPerez).setNivelDeConocimiento(any(Experto.class));
	   
	}
	
	
	@Test
	public void elNivelDeConocimientoSigueSiendoBasicoCuandoNoRealizaMas20VotacionesEnLosUltimos30Dias() {



	    when(aplicacionVinchucas.getMuestrasDeParticipantePorFecha(juanPerez, LocalDate.now().minusMonths(1l))).thenReturn(muestras);
	    when(aplicacionVinchucas.getVotacionDeParticipantePorfecha(juanPerez)).thenReturn(votaciones);
	    when(votaciones.size()).thenReturn(9);
	    when(muestras.size()).thenReturn(11);
	   
	    
	    basico.verificarEstado(juanPerez);
	    
	    verify(juanPerez, never()).setNivelDeConocimiento(any(Basico.class));
	    assertEquals("Nivel Basico", basico.getNivelDeConocimiento());
	}
	
	@Test
	public void elNivelDeConocimientoSigueSiendoBasicoCuandoNoRealizaMas11MuestrasEnLosUltimos30Dias() {



	    when(aplicacionVinchucas.getMuestrasDeParticipantePorFecha(juanPerez, LocalDate.now().minusMonths(1l))).thenReturn(muestras);
	    when(aplicacionVinchucas.getVotacionDeParticipantePorfecha(juanPerez)).thenReturn(votaciones);
	    when(votaciones.size()).thenReturn(34);
	    when(muestras.size()).thenReturn(9);
	   
	    
	    basico.verificarEstado(juanPerez);
	    
	    verify(juanPerez, never()).setNivelDeConocimiento(any(Basico.class));
	    assertEquals("Nivel Basico", basico.getNivelDeConocimiento());
	}

}
