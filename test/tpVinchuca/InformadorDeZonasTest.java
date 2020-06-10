package tpVinchuca;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class InformadorDeZonasTest {
	
	private InformadorDeZonas informadorDeZonas;
	
	private ZonaDeCobertura solano;
	private ZonaDeCobertura varela;
	private Muestra muestra;
	
	@BeforeEach
	public void setUp() {
		informadorDeZonas = new InformadorDeZonas();
		
		solano = mock(ZonaDeCobertura.class);
		varela = mock(ZonaDeCobertura.class);
		muestra = mock(Muestra.class);
	}
	
	// Verifica:
	// - el agregado de las zonas de cobertura a la lista
	// - la activacion del metodo muestraValidada para cada zona de cobertura de la lista
	@Test
	public void testSeValidaUnaMuestraYSeAvisaALasZonasDeCobertura() {
		informadorDeZonas.agregarZonaDeCobertura(solano);
		informadorDeZonas.agregarZonaDeCobertura(varela);
		
		informadorDeZonas.muestraValidada(muestra);
		
		verify(solano).muestraValidida(muestra);
		verify(varela).muestraValidida(muestra);
	}

	// Verifica:
	// - la eliminacion de zonas de cobertura de la lista
	// - al no haber zonas de cobertura en la lista, no se les avisa ante la validacion de una muestra
	@Test
	public void testSeValidaUnaMuestraPeroNoHayZonasDeCobertura() {
		informadorDeZonas.agregarZonaDeCobertura(solano);
		informadorDeZonas.agregarZonaDeCobertura(varela);
		
		informadorDeZonas.eliminarZonaDeCobertura(solano);
		informadorDeZonas.eliminarZonaDeCobertura(varela);
		
		informadorDeZonas.muestraValidada(muestra);
		
		verify(solano, never()).muestraValidida(muestra);
		verify(varela, never()).muestraValidida(muestra);
	}

}
