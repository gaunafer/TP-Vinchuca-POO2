package tpVinchuca;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;

public class ZonaDeCoberturaTest {
	
	private ZonaDeCobertura dominicoWilde;
	private ZonaDeCobertura varela;
	private ZonaDeCobertura solano;
	private ZonaDeCobertura ezpeleta;
	
	private Ubicacion epicentroDominicoWilde;
	private Ubicacion epicentroVarela;
	private Ubicacion epicentroSolano;
	private Ubicacion epicentroEzpeleta;
	
	private Organizacion flech;
	
	private Muestra muestra1;
	private Muestra muestra2;
	private Muestra muestra3;
	private Muestra muestra4;
	private List<Muestra> muestras;
	
	@BeforeEach
	public void setUp() {
		epicentroDominicoWilde = new Ubicacion(-34.688910, -58.314271);
		epicentroVarela = new Ubicacion(-34.811389, -58.274080);
		epicentroSolano = new Ubicacion(-34.782341, -58.315641);
		epicentroEzpeleta = new Ubicacion(-34.751968, -58.234205);

		muestra1 = mock(Muestra.class, Mockito.RETURNS_DEEP_STUBS);
		muestra2 = mock(Muestra.class, Mockito.RETURNS_DEEP_STUBS);
		muestra3 = mock(Muestra.class, Mockito.RETURNS_DEEP_STUBS);
		muestra4 = mock(Muestra.class, Mockito.RETURNS_DEEP_STUBS);
		
		muestras = new ArrayList<Muestra>();
		muestras.add(muestra1);
		muestras.add(muestra2);
		muestras.add(muestra3);
		muestras.add(muestra4);
		
		dominicoWilde = new ZonaDeCobertura(epicentroDominicoWilde, 2d, "Costanera Dominico Wilde", muestras); // acceso parque santo domingo
		varela = new ZonaDeCobertura(epicentroVarela, 4d, "Varela", muestras); //epicentro en estacion
		solano = new ZonaDeCobertura(epicentroSolano, 3.5d, "Solano", muestras); //epicentro en 844 y donato alvarez
		ezpeleta = new ZonaDeCobertura(epicentroEzpeleta, 4d, "Ezpeleta", muestras); //epicentro en estacion
		
		flech = mock(Organizacion.class);
	}
	
	

	// Chequea la construccion de una instancia de clase ZonaDeCobertura
	@Test
	public void testCreacionZonaDeCoberturaCorrecta() {
		
		Ubicacion epicentro = dominicoWilde.getEpicentro(); 
		Double radio = dominicoWilde.getRadio();
		String nombre = dominicoWilde.getNombre();
		
		assertEquals(2, radio);
		assertEquals("Costanera Dominico Wilde", nombre);
		assertEquals(epicentro, epicentroDominicoWilde);
	}
	
	// Chequea la creacion de una instancia de clase ZonaDeCobertura con radio incorrecto y lanza excepcion
	@Test
	public void testCreacionZonaDeCoberturaIncorrecta() {
		Assertions.assertThrows(ErrorElRadioDebeSerMayorACero.class, () -> {
			new ZonaDeCobertura(epicentroDominicoWilde, 0d, "Costanera Dominico Wilde", muestras);
		});	
	}
	
	// Testea:
	// - el agregado de una organizacion a la lista de observers de la zona de cobertura
	// - el agregado de una muestra a la lista de muestras de la zona de cobertura
	// - que se convoque notifyCreacionMuestra a los observers de la zona
	@Test
	public void testSeCreaUnaMuestraYSeAvisaALosObservers() {
		dominicoWilde.agregarObserver(flech);
		dominicoWilde.agregarMuestra(muestra1);
		
		verify(flech).updateCreacionMuestra(muestra1, dominicoWilde);
	}
	
	// Testea el metodo zonasConLasQueSeSolapa, para un caso de una zona que se solapa con otras dos
	@Test
	public void testZonasConLasQueSeSolapaVarela() {
		//setUp
		List<ZonaDeCobertura> listaDeZonas = new ArrayList<ZonaDeCobertura>();
		listaDeZonas.add(dominicoWilde); //distancia Dominico-Varela: 14.03
		listaDeZonas.add(solano); //distancia Solano-Varela: 4.91
		listaDeZonas.add(ezpeleta); //distancia Ezpeleta-Varela: 7.48
		
		//execute
		List<ZonaDeCobertura> listaDeZonasConLasQueSeSolapa = varela.zonasConLasQueSeSolapa(listaDeZonas);
		Integer cantDeZonasConLasQueSeSolapa = listaDeZonasConLasQueSeSolapa.size(); 
		
		//verify
		assertEquals(2, cantDeZonasConLasQueSeSolapa);
	}

	// Testea el metodo zonasConLasQueSeSolapa, para un caso de una zona que no se solapa con ninguna
	@Test
	public void testZonasConLasQueSeSolapaDominicoWilde() {
		//setUp
		List<ZonaDeCobertura> listaDeZonas = new ArrayList<ZonaDeCobertura>();
		listaDeZonas.add(varela); //distancia Varela-Dominico: 14.03
		listaDeZonas.add(solano); //distancia Solano-Dominico: 10.37
		listaDeZonas.add(ezpeleta); //distancia Ezpeleta-Dominico: 10.09
		
		//execute
		List<ZonaDeCobertura> listaDeZonasConLasQueSeSolapa = dominicoWilde.zonasConLasQueSeSolapa(listaDeZonas);
		Integer cantDeZonasConLasQueSeSolapa = listaDeZonasConLasQueSeSolapa.size(); 
		
		//verify
		assertEquals(0, cantDeZonasConLasQueSeSolapa);
	}
	
	// Testea que se convoque notifyValidacionMuestra a los observers cuandose avisa que hay una muestraValidada
	@Test
	public void testSeValidaUnaMuestraYSeAvisaALosObservers() {
		dominicoWilde.agregarObserver(flech);
		dominicoWilde.muestraValidida(muestra1);
		
		verify(flech).updateValidacionMuestra(muestra1, dominicoWilde);
	}

	// Testea la eliminacion de un observer, por lo que no hay interacciones al 
	// validar una muestra
	@Test
	public void testSeValidaUnaMuestraPeroNoHayObservers() {
		dominicoWilde.agregarObserver(flech);
		dominicoWilde.eliminarObserver(flech);
		dominicoWilde.muestraValidida(muestra1);
		
		verify(flech, never()).updateValidacionMuestra(muestra1, dominicoWilde);
	}

	// Testea el metodo zonaContieneUbicacionDeMuestra para una muestra que se encuentra
	// ubicada dentro de la zona de cobertura
	@Test
	public void testZonaContieneUbicacionDeMuestra() {
		when(muestra1.getUbicacion().calcularDistancia(epicentroVarela)).thenReturn(3d);
		
		Boolean zonaContieneUbicacionDeMuestra = varela.zonaContieneUbicacionDeMuestra(muestra1);
		
		assertTrue(zonaContieneUbicacionDeMuestra);
	}
	
	// Testea el metodo zonaContieneUbicacionDeMuestra para una muestra que se encuentra
	// fuera de la zona de cobertura
	@Test
	public void testZonaNoContieneUbicacionDeMuestra() {
		when(muestra1.getUbicacion().calcularDistancia(epicentroVarela)).thenReturn(4.5d);
		
		Boolean zonaContieneUbicacionDeMuestra = varela.zonaContieneUbicacionDeMuestra(muestra1);
		
		assertFalse(zonaContieneUbicacionDeMuestra);
	}
	
	// Testea el cambio de epicentro de la zona, chequeando:
	// - que las muestras que ya no se encuentren en la zona sean eliminadas de la 
	// lista de muestras de la zona
	// - que las muestras que ya se encuentran en la lista de muestras de la zona y 
	// sigan perteneciendo a la misma despues del cambio sigan estando en la lista
	// - que se agreguen las muestras que antes no pertenecian a la zona y ahora si
	// Para ello se chequea que las muestras eliminen o no a la zona de su lista de 
	// zonas, y que las muestras nuevas agreguen a la zona
	@Test
	public void testSetUnNuevoEpicentro() {
		Ubicacion nuevoEpicentroDominicoWilde = mock(Ubicacion.class);
		
		Muestra muestra5 = mock(Muestra.class, Mockito.RETURNS_DEEP_STUBS);
		List<Muestra> todasLasMuestras = new ArrayList<Muestra>();
		todasLasMuestras.addAll(muestras);
		todasLasMuestras.add(muestra5);

		when(muestra1.getUbicacion().calcularDistancia(nuevoEpicentroDominicoWilde)).thenReturn(1.5d);
		when(muestra2.getUbicacion().calcularDistancia(nuevoEpicentroDominicoWilde)).thenReturn(2.5d);
		when(muestra3.getUbicacion().calcularDistancia(nuevoEpicentroDominicoWilde)).thenReturn(1d);
		when(muestra4.getUbicacion().calcularDistancia(nuevoEpicentroDominicoWilde)).thenReturn(4.5d);
		when(muestra5.getUbicacion().calcularDistancia(nuevoEpicentroDominicoWilde)).thenReturn(0.5d);
		
		dominicoWilde.setEpicentro(nuevoEpicentroDominicoWilde, todasLasMuestras);

		verify(muestra1, never()).eliminarZona(dominicoWilde);
		verify(muestra2).eliminarZona(dominicoWilde);
		verify(muestra3, never()).eliminarZona(dominicoWilde);
		verify(muestra4).eliminarZona(dominicoWilde);
		verify(muestra5).asignarZona(dominicoWilde);
	}

	// Testea la disminucion del radio de la zona, chequeando:
	// - que las muestras que ya no se encuentren en la zona sean eliminadas de la 
	// lista de muestras de la zona
	// - que las muestras que ya se encuentran en la lista de muestras de la zona y 
	// sigan perteneciendo a la misma despues del cambio sigan estando en la lista
	// - que las muestras que antes no pertenecian a la zona y ahora tampoco, no se 
	// agreguen a la lista
	// Para ello se chequea que las muestras eliminen o no a la zona de su lista de 
	// zonas, y que ninguna muestra nueva agregue a la zona
	@Test
	public void testSetUnRadioMenor() {
		Muestra muestra5 = mock(Muestra.class, Mockito.RETURNS_DEEP_STUBS);
		List<Muestra> todasLasMuestras = new ArrayList<Muestra>();
		todasLasMuestras.addAll(muestras);
		todasLasMuestras.add(muestra5);

		when(muestra1.getUbicacion().calcularDistancia(epicentroDominicoWilde)).thenReturn(1.5d);
		when(muestra2.getUbicacion().calcularDistancia(epicentroDominicoWilde)).thenReturn(2d);
		when(muestra3.getUbicacion().calcularDistancia(epicentroDominicoWilde)).thenReturn(0.5d);
		when(muestra4.getUbicacion().calcularDistancia(epicentroDominicoWilde)).thenReturn(1.7d);
		when(muestra5.getUbicacion().calcularDistancia(epicentroDominicoWilde)).thenReturn(3d);
		
		dominicoWilde.setRadio(1.5d, todasLasMuestras);

		verify(muestra1, never()).eliminarZona(dominicoWilde);
		verify(muestra2).eliminarZona(dominicoWilde);
		verify(muestra3, never()).eliminarZona(dominicoWilde);
		verify(muestra4).eliminarZona(dominicoWilde);
		verify(muestra5, never()).asignarZona(dominicoWilde);
	}

	// Testea el aumento del radio de la zona, chequeando:
	// - que las muestras que ya se encuentran en la lista de muestras de la zona y 
	// sigan perteneciendo a la misma despues del cambio sigan estando en la lista
	// - que se agreguen las muestras que antes no pertenecian a la zona y ahora si
	// Para ello se chequea que las muestras no eliminen a la zona de su lista de 
	// zonas, y que las muestras nuevas que pertenecen agreguen a la zona
	@Test
	public void testSetUnRadioMayor() {
		Muestra muestra5 = mock(Muestra.class, Mockito.RETURNS_DEEP_STUBS);
		List<Muestra> todasLasMuestras = new ArrayList<Muestra>();
		todasLasMuestras.addAll(muestras);
		todasLasMuestras.add(muestra5);

		when(muestra1.getUbicacion().calcularDistancia(epicentroDominicoWilde)).thenReturn(1.5d);
		when(muestra2.getUbicacion().calcularDistancia(epicentroDominicoWilde)).thenReturn(2d);
		when(muestra3.getUbicacion().calcularDistancia(epicentroDominicoWilde)).thenReturn(0.5d);
		when(muestra4.getUbicacion().calcularDistancia(epicentroDominicoWilde)).thenReturn(1.7d);
		when(muestra5.getUbicacion().calcularDistancia(epicentroDominicoWilde)).thenReturn(3d);
		
		dominicoWilde.setRadio(4d, todasLasMuestras);

		verify(muestra1, never()).eliminarZona(dominicoWilde);
		verify(muestra2, never()).eliminarZona(dominicoWilde);
		verify(muestra3, never()).eliminarZona(dominicoWilde);
		verify(muestra4, never()).eliminarZona(dominicoWilde);
		verify(muestra5).asignarZona(dominicoWilde);
	}
	
}
