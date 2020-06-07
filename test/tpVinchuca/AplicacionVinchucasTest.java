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
	private List<Votacion> votaciones = mock(ArrayList.class);
	
	private AplicacionVinchucas aplicacion = new AplicacionVinchucas(null); 
	
	@BeforeEach
	public void setUp() {
		
		when(muestras.size()).thenReturn(11);
		when(votaciones.size()).thenReturn(21);
	
		
		
		
	}


}
