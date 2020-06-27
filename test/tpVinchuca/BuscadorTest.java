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

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Spy;

import tpVinchucas.filtro.Filtro;
import tpVinchucas.filtro.FiltroOr;


public class BuscadorTest {
	
	private Buscador buscador;
	private List<Votacion> votaciones;
	
	@Mock
	private Filtro filtro = mock(Filtro.class);
	@Mock
	private List<Muestra> muestras = mock(ArrayList.class);
	@Mock
    private FiltroOr filtroCombinado = mock(FiltroOr.class);
    @Mock
	private Votacion votacion = mock(Votacion.class);
	@Mock
	private Votacion votacion1 = mock(Votacion.class);
	@Mock
	private Votacion votacion2 = mock(Votacion.class);
	@Mock
	private Votacion votacion3 = mock(Votacion.class);
	@Mock
	private Votacion votacion4 = mock(Votacion.class);
	@Mock
	private Participante juanPerez = mock(Participante.class);
	@Mock
	private Participante anaFernandez = mock(Participante.class);
	@Mock
	private Participante camilaSaez = mock(Participante.class);

	
	@BeforeEach
	public void setUp() {
		buscador = new Buscador();
		votaciones = new ArrayList<Votacion>();
		
		when(votacion.getParticipante()).thenReturn(juanPerez);
		when(votacion1.getParticipante()).thenReturn(anaFernandez);
		when(votacion2.getParticipante()).thenReturn(juanPerez);
		when(votacion3.getParticipante()).thenReturn(camilaSaez);
		when(votacion4.getParticipante()).thenReturn(juanPerez);
		
		when(votacion.getFecha()).thenReturn(LocalDate.now().minusDays(20l));
		when(votacion1.getFecha()).thenReturn(LocalDate.now().minusDays(20l));
		when(votacion2.getFecha()).thenReturn(LocalDate.now().minusDays(20l));
		when(votacion3.getFecha()).thenReturn(LocalDate.now().minusMonths(2l));
		when(votacion4.getFecha()).thenReturn(LocalDate.now().minusMonths(2l));
		
		votaciones.add(votacion);
		votaciones.add(votacion1);
		votaciones.add(votacion2);
		votaciones.add(votacion3);
		votaciones.add(votacion4);
		
	}

	// Testea que el metodo buscar llame al metodo criterioDeBusqueda del filtro pasado como parametro
	@Test
	public void testSeBuscaLaListaDeMuestrasQueCoincidanConLosParametrosDelFiltroIndicado() {
		buscador.buscar(muestras, filtro);
		
		verify(filtro).criterioDeBusqueda(muestras);
	}
	
	/* Testea que las votaciones se filtren correctamente por participante y fecha
	con el metodo getVotacionesDeParticipanteEnLosUltimos30Dias.
	Para este caso, el resultado debe obviar una votacion que se corresponde con
	el participante pero no con la fecha, otra votacion que se corresponde con la
	fecha pero no con el participante, y otra que no cumple con ningun criterio.*/
	@Test
	public void testSeBuscaLaListaDeVotacionesPorParticipanteDadoEnElUltimoMesYSonDos() {
		
		List<Votacion> votacionesDeJuanPerez = buscador.getVotacionesDeParticipanteEnLosUltimos30Dias(votaciones, juanPerez);
		
		assertEquals(2, votacionesDeJuanPerez.size());
	}
	
}
