package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.Mock;
import org.mockito.Spy;

import static org.mockito.Mockito.*;

public class ExpertoTest {
	
	@Mock
	private AplicacionVinchucas aplicacionVinchucas = mock(AplicacionVinchucas.class);
	
	@Mock
	private Participante pepe = mock(Participante.class);
	
	@Mock
	private Muestra muestra1 = mock(Muestra.class);
	@Mock
	private Muestra muestra2 = mock(Muestra.class);
	@Mock
	private Muestra muestra3 = mock(Muestra.class);
	@Mock
	private Muestra muestra4 = mock(Muestra.class);
	@Mock
	private Muestra muestra5 = mock(Muestra.class);
	@Mock
	private Muestra muestra6 = mock(Muestra.class);
	@Mock
	private Muestra muestra7 = mock(Muestra.class);
	@Mock
	private Muestra muestra8 = mock(Muestra.class);
	@Mock
	private Muestra muestra9 = mock(Muestra.class);
	@Mock
	private Muestra muestra10 = mock(Muestra.class);
	@Mock
	private Muestra muestra11 = mock(Muestra.class);
	
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
	
	
	private NivelDeConocimiento experto = new Experto(aplicacionVinchucas);
	
	private List<Votacion> votaciones;
	private List<Muestra> muestras;
	
	@BeforeEach
	public void setUp() {
		votaciones = new ArrayList<Votacion>();
		muestras = new ArrayList<Muestra>();
	}
	
	@Test
	public void seCreaUnNivelExpertoySuNivelDeConocimientoEsNivelexperto() {
		
		assertEquals("Nivel Experto", experto.getNivelDeConocimiento());
	}
	
	@Test
	public void elNivelDeConocimientoDejaDeSerExpertoCuandoNoCumpleCon10MuestrasEnLos30DiasPosterioresALaFechaActual(){
		
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
		
		when(muestra1.getFecha()).thenReturn(LocalDate.now().minusMonths(1l));
		when(muestra2.getFecha()).thenReturn(LocalDate.now().minusMonths(1l));
		when(muestra3.getFecha()).thenReturn(LocalDate.now().minusMonths(1l));
		when(muestra4.getFecha()).thenReturn(LocalDate.now().minusMonths(1l));
		when(muestra5.getFecha()).thenReturn(LocalDate.now().minusMonths(1l));
		when(muestra6.getFecha()).thenReturn(LocalDate.now().minusMonths(1l));
		when(muestra7.getFecha()).thenReturn(LocalDate.now().minusMonths(1l));
		when(muestra8.getFecha()).thenReturn(LocalDate.now().minusMonths(1l));
		when(muestra9.getFecha()).thenReturn(LocalDate.now().minusMonths(1l));
		when(muestra10.getFecha()).thenReturn(LocalDate.now().minusMonths(2l));
		when(muestra11.getFecha()).thenReturn(LocalDate.now().minusMonths(2l));
		
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
		
		when(aplicacionVinchucas.getMuestrasDeParticipantePorFecha(eq(pepe),any())).thenReturn(muestras);
		
		when(votacion1.getFechaDeCreacion()).thenReturn(LocalDate.now().minusMonths(1l));
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
	    
	    when(aplicacionVinchucas.getVotacionesDeParticipanteEnLosUltimos30Dias(pepe)).thenReturn(votaciones);
	    //when(pepe.getNivelDeConocimiento()).thenReturn(experto.getNivelDeConocimiento());
	    pepe.actualizarEstado();
	    experto.verificarEstado(pepe);
	    verify(pepe).setNivelDeConocimiento(experto);
	   
	}
	
	@Test
	public void elNivelDeConocimientoDejaDeSerExpertoCuandoNoCumpleCon20VotacionesEnLos30DiasPosterioresALaFechaActual() {
		
		
	}
	
	@Test
	public void elNivelDeConocimientoDejaDeSerExpertoCuandoCumpleCon20VotacionesY10MuestrasEnLos30DiasPosterioresALaFechaActual() {
		
		
	}

}
