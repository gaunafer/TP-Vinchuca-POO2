package tpVinchuca.filtro;

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

import tpVinchuca.Muestra;
import tpVinchucas.filtro.Filtro;
import tpVinchucas.filtro.FiltroPorFechaDeUltimaVotacionDeMuestra;
import tpVinchucas.filtro.OperadorLogico;

public class FiltroPorFechaDeUltimaVotacionDeMuestraTest {
	
	private Filtro filtroFecha;
	private List<Muestra> muestras;
	
	@Mock
	private Muestra muestra = mock(Muestra.class);
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
	
	@BeforeEach
	public void setUp() {
		muestras = new ArrayList<Muestra>();
		muestras.add(muestra);
		muestras.add(muestra1);
		muestras.add(muestra2);
		muestras.add(muestra3);
		muestras.add(muestra4);
		muestras.add(muestra5);
		muestras.add(muestra6);
		muestras.add(muestra7);
		
		when(muestra.getFechaUltimaVotacion()).thenReturn(LocalDate.now().minusDays(15l));
		when(muestra1.getFechaUltimaVotacion()).thenReturn(LocalDate.now().minusDays(29l));
		when(muestra2.getFechaUltimaVotacion()).thenReturn(LocalDate.now().minusDays(31l));
		when(muestra3.getFechaUltimaVotacion()).thenReturn(LocalDate.now().minusDays(15l));
		when(muestra4.getFechaUltimaVotacion()).thenReturn(LocalDate.now().minusDays(16l));
		when(muestra5.getFechaUltimaVotacion()).thenReturn(LocalDate.now().minusDays(45l));
		when(muestra6.getFechaUltimaVotacion()).thenReturn(LocalDate.now().minusDays(4l));
		when(muestra7.getFechaUltimaVotacion()).thenReturn(LocalDate.now().minusDays(3l));
	}
	
	@Test
	public void testSeFiltranLasMuestrasConUltimaVotacionDeHaceMasDe20Dias() {
		filtroFecha = new FiltroPorFechaDeUltimaVotacionDeMuestra(OperadorLogico.MENORESA, LocalDate.now().minusDays(20l));
		List<Muestra> muestrasFiltradas = filtroFecha.criterioDeBusqueda(muestras);
		Integer cantidadDeMuestrasFiltradas = muestrasFiltradas.size();
		
		assertEquals(3, cantidadDeMuestrasFiltradas);
	}

	@Test
	public void testSeFiltranLasMuestrasConUltimaVotacionDeHaceMenosDe20Dias() {
		filtroFecha = new FiltroPorFechaDeUltimaVotacionDeMuestra(OperadorLogico.MAYORESA, LocalDate.now().minusDays(20l));
		List<Muestra> muestrasFiltradas = filtroFecha.criterioDeBusqueda(muestras);
		Integer cantidadDeMuestrasFiltradas = muestrasFiltradas.size();
		
		assertEquals(5, cantidadDeMuestrasFiltradas);
	}
	
	@Test
	public void testSeFiltranLasMuestrasConUltimaVotacionEnUnaFechaEspecifica() {
		filtroFecha = new FiltroPorFechaDeUltimaVotacionDeMuestra(OperadorLogico.IGUALESA, LocalDate.now().minusDays(15l));
		List<Muestra> muestrasFiltradas = filtroFecha.criterioDeBusqueda(muestras);
		Integer cantidadDeMuestrasFiltradas = muestrasFiltradas.size();
		
		assertEquals(2, cantidadDeMuestrasFiltradas);
	}
}