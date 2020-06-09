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

public class FiltroFechaHastaTest {
		
		private Filtro filtroFechaHasta;
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
			filtroFechaHasta = new FiltroFechaDeCreacionHasta(LocalDate.now().minusDays(30l));
			muestras = new ArrayList<Muestra>();
			muestras.add(muestra);
			muestras.add(muestra1);
			muestras.add(muestra2);
			
			when(muestra.getFecha()).thenReturn(LocalDate.now().minusDays(31l));
			when(muestra1.getFecha()).thenReturn(LocalDate.now().minusDays(45l));
			when(muestra2.getFecha()).thenReturn(LocalDate.now().minusDays(29l));
			
			muestrasFiltradas = new ArrayList<Muestra>();
			
			muestrasFiltradas.add(muestra);
			muestrasFiltradas.add(muestra1);
			
		}
		
		@Test
		public void seFiltraUnaListaDeMuestrasYDevuelveUnaListaDeMuestrasFiltradasSegunElcriterioFecha() {
			
			assertEquals(muestrasFiltradas, filtroFechaHasta.criterioDeBusqueda(muestras));
			
		}


}
