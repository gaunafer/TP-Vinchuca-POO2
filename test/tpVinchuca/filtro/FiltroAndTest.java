package tpVinchuca.filtro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import tpVinchuca.Muestra;
import tpVinchuca.Participante;
import tpVinchucas.filtro.Filtro;
import tpVinchucas.filtro.FiltroAnd;
import tpVinchucas.filtro.FiltroParticipante;
import tpVinchucas.filtro.FiltroPorFechaDeCreacionDeMuestra;

public class FiltroAndTest {
	
	private Filtro filtroAnd;
	private List<Muestra> muestras;
	private List<Muestra> muestrasFiltradas;
	
	@Mock
	private Muestra muestra = mock(Muestra.class);
	@Mock
	private Muestra muestra1 = mock(Muestra.class);
	@Mock
	private Muestra muestra2 = mock(Muestra.class);
	@Mock
	private Participante troyMcClure = mock(Participante.class);
	@Mock
	private Participante rafaGorgory = mock(Participante.class);
	@Mock
	private FiltroParticipante filtroParticipante = mock(FiltroParticipante.class);
	@Mock
	private FiltroPorFechaDeCreacionDeMuestra filtroFecha = mock(FiltroPorFechaDeCreacionDeMuestra.class);
	
	@BeforeEach
	public void setUp() {
		filtroAnd = new FiltroAnd(filtroFecha, filtroParticipante);
		
		muestras = new ArrayList<Muestra>();
		muestras.add(muestra);
		muestras.add(muestra1);
		muestras.add(muestra2);
		
		when(muestra.getParticipante()).thenReturn(troyMcClure);
		when(muestra1.getParticipante()).thenReturn(rafaGorgory);
		when(muestra2.getParticipante()).thenReturn(troyMcClure);
		
		muestrasFiltradas = new ArrayList<Muestra>();
		
		muestrasFiltradas.add(muestra);
		muestrasFiltradas.add(muestra2);
		
		when(filtroFecha.criterioDeBusqueda(muestras)).thenReturn(muestras);
		when(filtroParticipante.criterioDeBusqueda(muestras)).thenReturn(muestrasFiltradas);		
		
		
	}
	
	@Test
	public void seFiltraUnaListaDeMuestrasYDevuelveUnaListaDeMuestrasFiltradasSegunElcriterioFecha() {
		
		assertEquals(muestrasFiltradas, filtroAnd.criterioDeBusqueda(muestras));
		
	}


}
