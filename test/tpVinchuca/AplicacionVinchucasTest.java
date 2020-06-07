package tpVinchuca;

import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;

public class AplicacionVinchucasTest {
	
	private AplicacionVinchucas aplicacionVinchucas;
	private Buscador buscador;
	
	@BeforeEach
	public void setUp() {
		buscador = mock(Buscador.class);
		
		aplicacionVinchucas = new AplicacionVinchucas(buscador);
	}
	
	// Testea:
	// -el agregado de zonas de cobertura a la lista de zonas
	// -el agregado de muestras a la lista de muestras
	// -que al agregar una muestra, las zonas a las que pertenece la muestra la agreguen a su lista
	// -que ademas las clases que monitorean la validacion de muestras agreguen a las zonas a las que la muestra pertenece
	@Test
	public void testSeAgregaUnaMuestraQuePerteneceADosZonasDeCobertura() {
		Muestra muestra = mock(Muestra.class);
		ZonaDeCobertura varela = mock(ZonaDeCobertura.class);
		ZonaDeCobertura berazategui = mock(ZonaDeCobertura.class);
		
		aplicacionVinchucas.agregarZonaDeCobertura(varela);
		aplicacionVinchucas.agregarZonaDeCobertura(berazategui);
		
		when(varela.zonaContieneUbicacionDeMuestra(muestra)).thenReturn(true);
		when(berazategui.zonaContieneUbicacionDeMuestra(muestra)).thenReturn(true);

		aplicacionVinchucas.agregarMuestra(muestra);
		
		verify(varela).agregarMuestra(muestra);
		verify(berazategui).agregarMuestra(muestra);
		verify(muestra).asignarZona(varela);
		verify(muestra).asignarZona(berazategui);
	}

	// Testea:
	// -el agregado de zonas de cobertura a la lista de zonas
	// -el agregado de muestras a la lista de muestras
	// -que al agregar una muestra, las zonas a las que no pertenece la muestra no agreguen a la misma a su lista
	// -que ademas las clases que monitorean la validacion de muestras no agreguen a las zonas a las que la muestra no pertenece
	@Test
	public void testSeAgregaUnaMuestraQueNoPerteneceANingunaZonasDeCobertura() {
		Muestra muestra = mock(Muestra.class);
		ZonaDeCobertura varela = mock(ZonaDeCobertura.class);
		ZonaDeCobertura berazategui = mock(ZonaDeCobertura.class);
		
		aplicacionVinchucas.agregarZonaDeCobertura(varela);
		aplicacionVinchucas.agregarZonaDeCobertura(berazategui);
		
		when(varela.zonaContieneUbicacionDeMuestra(muestra)).thenReturn(false);
		when(berazategui.zonaContieneUbicacionDeMuestra(muestra)).thenReturn(false);

		aplicacionVinchucas.agregarMuestra(muestra);
		
		verify(varela, never()).agregarMuestra(muestra);
		verify(berazategui, never()).agregarMuestra(muestra);
		verify(muestra, never()).asignarZona(varela);
		verify(muestra, never()).asignarZona(berazategui);
	}

}
