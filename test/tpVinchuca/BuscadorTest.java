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


public class BuscadorTest {
	
	
	private Buscador buscador;
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
	private Muestra muestra = mock(Muestra.class);
	@Mock
	private Muestra muestra1 = mock(Muestra.class);
	@Mock
	private Votacion votacion = mock(Votacion.class);
	@Mock
	private Votacion votacion1 = mock(Votacion.class);
	@Mock
	private Participante juanPerez = mock(Participante.class);
	
	private List<Muestra> muestras;

	@BeforeEach
	public void setUp() {
		buscador = new Buscador();
		muestras = new ArrayList<Muestra>();
	}

	@Test
	public void seBuscaLaListaDeMuestrasQueCoincidanConLosParametrosDelFiltroIndicado() {
		
		muestras.add(muestra);
		muestras.add(muestra1);
		when(filtroFecha.criterioDeBusqueda(muestras)).thenReturn(muestras);
		
		assertEquals(muestras, buscador.buscar(muestras, filtroFecha));
	}
	
	@Test
	public void seBuscaLaListaDeVotacionesPorParticipanteDadoYEnElRangodeLaFechaIndicada() {
		
	}
}
