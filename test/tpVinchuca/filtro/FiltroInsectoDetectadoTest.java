package tpVinchuca.filtro;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import tpVinchuca.ClasificacionDeFoto;
import tpVinchuca.Muestra;
import tpVinchuca.Participante;
import tpVinchucas.filtro.Filtro;
import tpVinchucas.filtro.FiltroInsectoDetectado;

public class FiltroInsectoDetectadoTest {
	
	private Filtro filtro;
	private List<Muestra> muestras;
	private List<Muestra> muestrasFiltradas;
	private ClasificacionDeFoto insecto;
	
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
	
	@BeforeEach
	public void setUp() {
		insecto = ClasificacionDeFoto.VINCHUCA_INFESTANS;
		filtro = new FiltroInsectoDetectado(insecto);
		muestras = new ArrayList<Muestra>();
		muestras.add(muestra);
		muestras.add(muestra1);
		muestras.add(muestra2);
		
		when(muestra.getResultadoActual()).thenReturn("Vinchuca Infestans");
		when(muestra1.getResultadoActual()).thenReturn("Vinchuca Gasayana");
		when(muestra2.getResultadoActual()).thenReturn("Vinchuca Infestans");
		
	
		muestrasFiltradas = new ArrayList<Muestra>();
		
		muestrasFiltradas.add(muestra);
		muestrasFiltradas.add(muestra2);
		
	}
	
	@Test
	public void seFiltraUnaListaDeMuestrasYDevuelveUnaListaDeMuestrasFiltradasSegunElcriterioFecha() {
		
		assertEquals(muestrasFiltradas, filtro.criterioDeBusqueda(muestras));
		
	}



}
