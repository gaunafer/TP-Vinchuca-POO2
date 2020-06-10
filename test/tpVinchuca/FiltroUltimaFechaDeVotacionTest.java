package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class FiltroUltimaFechaDeVotacionTest {
	
	private Filtro filtro;
	private List<Muestra> muestras;
	private List<Muestra> muestrasFiltradas;
	
	@Mock
	private Muestra muestra = mock(Muestra.class);
	@Mock
	private Muestra muestra1 = mock(Muestra.class);
	@Mock
	private Muestra muestra2 = mock(Muestra.class);
	@Mock
	private Muestra muestra3 = mock(Muestra.class);
	
	
	
	@BeforeEach
	private void setUp() {
		filtro = new FiltroUltimaFechaDeVotacion(LocalDate.now().minusDays(1l));
		muestras = new ArrayList<Muestra>();
		muestras.add(muestra);
		muestras.add(muestra1);
		muestras.add(muestra2);
		muestras.add(muestra3);
		
		when(muestra.esMuestraVotada()).thenReturn(true);
		when(muestra1.esMuestraVotada()).thenReturn(true);
		when(muestra2.esMuestraVotada()).thenReturn(false);
		when(muestra3.esMuestraVotada()).thenReturn(true);
		
		when(muestra.getFechaUltimaVotacion()).thenReturn(LocalDate.now());
		when(muestra1.getFechaUltimaVotacion()).thenReturn(LocalDate.now());
		when(muestra3.getFechaUltimaVotacion()).thenReturn(LocalDate.now().minusDays(31l));
		
		muestrasFiltradas = new ArrayList<Muestra>();
		
		muestrasFiltradas.add(muestra);
		muestrasFiltradas.add(muestra1);
		
	}
	
	@Test
    public void seRequiereUnaListaDeMuestrasCuyaFechaDeUltimaVotacionSeaDeHoyEnAdelante() {
		
		assertEquals(muestrasFiltradas, filtro.criterioDeBusqueda(muestras));
		
	}


}
