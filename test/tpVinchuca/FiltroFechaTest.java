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

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Spy;
public class FiltroFechaTest {
	
	private Filtro filtroFecha;
	private List<Muestra> muestras;
	private List<Muestra> muestrasFiltradas;
	
	@Mock
	private Muestra muestra = mock(Muestra.class);
	@Mock
	private Muestra muestra1 = mock(Muestra.class);
	@Mock
	private Muestra muestra2 = mock(Muestra.class);
	
	@BeforeEach
	public void setUp() {
		filtroFecha = new FiltroFecha(LocalDate.now().minusDays(30l));
		muestras = new ArrayList<Muestra>();
		muestras.add(muestra);
		muestras.add(muestra1);
		muestras.add(muestra2);
		
		when(muestra.getFecha()).thenReturn(LocalDate.now().minusDays(15l));
		when(muestra1.getFecha()).thenReturn(LocalDate.now().minusDays(29l));
		when(muestra2.getFecha()).thenReturn(LocalDate.now().minusDays(31l));
		
		muestrasFiltradas = new ArrayList<Muestra>();
		
		muestrasFiltradas.add(muestra);
		muestrasFiltradas.add(muestra1);
		
	}
	
	@Test
	public void seFiltraUnaListaDeMuestrasYDevuelveUnaListaDeMuestrasFiltradasSegunElcriterioFecha() {
		
		assertEquals(muestrasFiltradas, filtroFecha.criterioDeBusqueda(muestras));
		
	}

}
