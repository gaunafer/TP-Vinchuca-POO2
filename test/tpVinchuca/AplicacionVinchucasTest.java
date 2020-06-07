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


public class AplicacionVinchucasTest {
	
	@Mock
	private Buscador buscador= mock(Buscador.class);
	@Mock
	private FiltroFecha filtroFecha = mock(FiltroFecha.class);
	@Mock
	private FiltroAnd and = mock(FiltroAnd.class);
	@Mock
	private FiltroCombinado filtroCombinado = mock(FiltroCombinado.class);
	@Mock
	private FiltroMuestraValida filtroMuestraValida = mock(FiltroMuestraValida.class);
	@Mock
	private FiltroParticipante filtroParticipante = mock(FiltroParticipante.class);
	@Mock
	private List<Muestra> muestras = mock(ArrayList.class);
	@Mock
	private Muestra muestra = mock(Muestra.class);
	@Mock
	private Muestra muestra1 = mock(Muestra.class);
	@Mock
	private Votacion votacion = mock(Votacion.class);
	@Mock
	private Votacion votacion1 = mock(Votacion.class);

	private List<Votacion> votaciones ;
	@Mock
	private Participante juanPerez = mock(Participante.class);
	
	private List<Muestra> muestrasConVotaciones;
	
	
	private AplicacionVinchucas aplicacion; 
	@BeforeEach
	public void setUp() {
			aplicacion = new AplicacionVinchucas(buscador); 
			votaciones = new ArrayList<Votacion>();
			muestrasConVotaciones = new ArrayList<Muestra>();	
	}
	
	@Test
	public void seCreaUnaAplicacionVinchucasCorrectamente() {
		assertEquals(buscador, aplicacion.getBuscador());
		assertEquals(new ArrayList<Muestra>(), aplicacion.getMuestras());
	}
	
	@Test
	public void retornaLaListaDeMuestrasFiltradasPorFecha() {
		
		when(muestra.getParticipante()).thenReturn(juanPerez);
		muestrasConVotaciones.add(muestra);
		aplicacion.añadirMuestra(muestra);
		when(buscador.buscar(muestrasConVotaciones, filtroFecha)).thenReturn(muestrasConVotaciones);
		when(buscador.buscar(muestrasConVotaciones, and)).thenReturn(muestrasConVotaciones);
		when(buscador.buscar(muestrasConVotaciones, filtroParticipante)).thenReturn(muestrasConVotaciones);
		when(filtroFecha.criterioDeBusqueda(muestrasConVotaciones)).thenReturn(muestrasConVotaciones);
		when(filtroParticipante.criterioDeBusqueda(muestrasConVotaciones)).thenReturn(muestrasConVotaciones);
		when(and.criterioDeBusqueda(muestrasConVotaciones)).thenReturn(muestrasConVotaciones);
	
		
		
		assertEquals(muestrasConVotaciones, aplicacion.getMuestrasDeParticipantePorFecha(juanPerez, LocalDate.now()));
	}
	
	@Test 
	public void votacionesTotalesDelaAplicacion() {
		
		votaciones.add(votacion);
		votaciones.add(votacion1);
		when(muestra.getVotaciones()).thenReturn(votaciones);
	
		aplicacion.añadirMuestra(muestra);
		
		
		assertEquals(votaciones, aplicacion.getVotaciones());
		
	}
	
	@Test
	public void seObtienLaListaDeVotacionesDeUnaListaDeMuestras() {
		aplicacion.añadirMuestra(muestra);
		aplicacion.añadirMuestra(muestra1);
		

		when(buscador.getVotacionesDeParticipanteEnLosUltimos30Dias(votaciones,juanPerez)).thenReturn(votaciones);
		when(muestra.getVotaciones()).thenReturn(votaciones);
	
		when(muestra.getParticipante()).thenReturn(juanPerez);
	
	
		assertEquals(votaciones, aplicacion.getVotacionDeParticipantePorfecha(juanPerez));
	}


}
