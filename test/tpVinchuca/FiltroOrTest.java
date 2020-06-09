package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class FiltroOrTest {
	
	private Filtro filtroOr;
	private List<Muestra> muestras;
	private List<Muestra> muestrasFiltradasPorPersona;
	private List<Muestra> muestrasFiltradasPorFecha;
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
	private FiltroFechaDeCreacionDesde filtroFecha = mock(FiltroFechaDeCreacionDesde.class);
	
	@BeforeEach
	public void setUp() {
		filtroOr = new FiltroOr(filtroFecha, filtroParticipante);
		
		muestras = new ArrayList<Muestra>();
		muestras.add(muestra);
		muestras.add(muestra1);
		muestras.add(muestra2);
		
		when(muestra.getParticipante()).thenReturn(troyMcClure);
		when(muestra1.getParticipante()).thenReturn(rafaGorgory);
		when(muestra2.getParticipante()).thenReturn(troyMcClure);
		
		when(muestra.getFecha()).thenReturn(LocalDate.now().minusDays(15l));
		when(muestra1.getFecha()).thenReturn(LocalDate.now().minusDays(29l));
		when(muestra2.getFecha()).thenReturn(LocalDate.now().minusDays(31l));
		
		muestrasFiltradasPorPersona = new ArrayList<Muestra>();
		
		muestrasFiltradasPorPersona.add(muestra);
		muestrasFiltradasPorPersona.add(muestra2);
		
		muestrasFiltradasPorFecha = new ArrayList<Muestra>();
		muestrasFiltradasPorFecha.add(muestra);
		muestrasFiltradasPorFecha.add(muestra1);
		
		muestrasFiltradas = new ArrayList<Muestra>();
		muestrasFiltradas.add(muestra);
		
		
		when(filtroFecha.criterioDeBusqueda(muestras)).thenReturn(muestrasFiltradasPorFecha);
		when(filtroParticipante.criterioDeBusqueda(muestras)).thenReturn(muestrasFiltradasPorPersona);		
		
		
	}
	
	@Test
	public void seFiltraUnaListaDeMuestrasYDevuelveUnaListaDeMuestrasFiltradasSegunElcriterioFecha() {
		
		assertEquals(muestrasFiltradas, filtroOr.criterioDeBusqueda(muestras));
		
	}



}
