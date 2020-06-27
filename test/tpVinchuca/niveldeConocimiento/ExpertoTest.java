package tpVinchuca.niveldeConocimiento;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Spy;

import tpVinchuca.AplicacionVinchucas;
import tpVinchuca.Muestra;
import tpVinchuca.Participante;
import tpVinchuca.Votacion;
import tpVinchucas.niveldeConocimiento.Basico;
import tpVinchucas.niveldeConocimiento.Experto;
import tpVinchucas.niveldeConocimiento.NivelDeConocimiento;

import static org.mockito.Mockito.*;

public class ExpertoTest {
	
	@Mock
	private AplicacionVinchucas aplicacionVinchucas = mock(AplicacionVinchucas.class);
	

	private NivelDeConocimiento experto = new Experto(aplicacionVinchucas);
	
	
	@Mock
	private Participante pepe = mock(Participante.class);
	
	@Mock
	private List<Votacion> votaciones = mock(ArrayList.class);
	@Mock
	private List<Muestra> muestras = mock(ArrayList.class);
	
	
	@Test
	public void seCreaUnNivelExpertoySuNivelDeConocimientoEsNivelexperto() {
		
		assertEquals("Nivel Experto", experto.getNivelDeConocimiento());
	}
	
	@Test
	public void elNivelDeConocimientoDejaDeSerExpertoCuandoNoCumpleCon10MuestrasEnLos30DiasPosterioresALaFechaActual(){


	    when(aplicacionVinchucas.getVotacionesDeParticipanteDeLosUltimos30Dias(pepe)).thenReturn(votaciones);
	    when(aplicacionVinchucas.getMuestrasDeParticipantePorFecha(pepe, LocalDate.now().minusMonths(1l))).thenReturn(muestras);
	    when(votaciones.size()).thenReturn(11);
	    when(muestras.size()).thenReturn(11);
	    
	    experto.verificarEstado(pepe);
	    
	    verify(pepe).setNivelDeConocimiento(any(Basico.class));
	   
	}
	
	@Test
	public void elNivelDeConocimientoDejaDeSerExpertoCuandoNoCumpleCon20VotacionesEnLos30DiasPosterioresALaFechaActual() {



	    when(aplicacionVinchucas.getMuestrasDeParticipantePorFecha(pepe, LocalDate.now().minusMonths(1l))).thenReturn(muestras);
	    when(aplicacionVinchucas.getVotacionesDeParticipanteDeLosUltimos30Dias(pepe)).thenReturn(votaciones);
	    when(votaciones.size()).thenReturn(11);
	    when(muestras.size()).thenReturn(11);
	    experto.verificarEstado(pepe);
	    
	    verify(pepe).setNivelDeConocimiento(any(Basico.class));
	}
		
	
	@Test
	public void elNivelDeConocimientoSigueSiendoExpertoCuandoCumpleConMas20VotacionesY10MuestrasEnLos30DiasPosterioresALaFechaActual() {



	    when(aplicacionVinchucas.getMuestrasDeParticipantePorFecha(pepe, LocalDate.now().minusMonths(1l))).thenReturn(muestras);
	    when(aplicacionVinchucas.getVotacionesDeParticipanteDeLosUltimos30Dias(pepe)).thenReturn(votaciones);
	    when(votaciones.size()).thenReturn(21);
	    when(muestras.size()).thenReturn(11);
	   
	    
	    experto.verificarEstado(pepe);
	    
	    verify(pepe, never()).setNivelDeConocimiento(any(Basico.class));
	    assertEquals("Nivel Experto", experto.getNivelDeConocimiento());
	}

}
