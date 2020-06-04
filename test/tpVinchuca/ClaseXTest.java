package tpVinchuca;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClaseXTest {
	
	private ClaseX claseX;
	
	private ZonaDeCobertura solano;
	
	@BeforeEach
	public void setUp() {
		claseX = new ClaseX();
		solano = mock(ZonaDeCobertura.class);

		claseX.agregarZonaDeCobertura(solano);
	}

	@Test
	public void testAgregarZonaDeCobertura() {
		
		assertTrue(claseX.esZonaDeMuestra(solano));
	}
	
	@Test
	public void testEliminarZonaDeCobertura() {
		claseX.eliminarZonaDeCobertura(solano);
		
		assertFalse(claseX.esZonaDeMuestra(solano));
	}
	
	@Test
	public void testMuestraValidada() {
		Muestra muestra = mock(Muestra.class);
		claseX.muestraValidada(muestra);
		
		verify(solano).muestraValidida(muestra);
	}

}
