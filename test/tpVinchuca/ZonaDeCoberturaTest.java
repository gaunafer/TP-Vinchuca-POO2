package tpVinchuca;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;

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
	private Muestra muestra;
	
	@BeforeEach
	public void setUp() {
		epicentroDominicoWilde = new Ubicacion(-34.688910, -58.314271);
		epicentroVarela = new Ubicacion(-34.811389, -58.274080);
		epicentroSolano = new Ubicacion(-34.782341, -58.315641);
		epicentroEzpeleta = new Ubicacion(-34.751968, -58.234205);
		
		
		dominicoWilde = new ZonaDeCobertura(epicentroDominicoWilde, 2d, "Costanera Dominico Wilde"); // acceso parque santo domingo
		varela = new ZonaDeCobertura(epicentroVarela, 4d, "Varela"); //epicentro en estacion
		solano = new ZonaDeCobertura(epicentroSolano, 3.5d, "Solano"); //epicentro en 844 y donato alvarez
		ezpeleta = new ZonaDeCobertura(epicentroEzpeleta, 4d, "Ezpeleta"); //epicentro en estacion
		
		flech = mock(Organizacion.class);
		muestra = mock(Muestra.class);
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
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			ZonaDeCobertura zona = new ZonaDeCobertura(epicentroDominicoWilde, 0d, "Costanera Dominico Wilde");
		});	
	}
	
	// Testea el agregado de una muestra a la lista de muestras de la zona de cobertura
	// Testea ademas que se convoque notifyCreacionMuestra a los observers de la zona
	@Test
	public void testAgregarMuestra() {
		dominicoWilde.agregarObserver(flech);
		
		dominicoWilde.agregarMuestra(muestra);
		
		assertTrue(dominicoWilde.contieneMuestra(muestra));
		verify(flech).updateCreacionMuestra(muestra, dominicoWilde);
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
	public void testMuestraValidada() {
		dominicoWilde.agregarObserver(flech);
		dominicoWilde.muestraValidida(muestra);
		
		verify(flech).updateValidacionMuestra(muestra, dominicoWilde);
	}
	
	// Testea el agregado de un observador a la lista observers de la zona de cobertura
	@Test
	public void testAgregarObserver() {
		dominicoWilde.agregarObserver(flech);
		
		assertTrue(dominicoWilde.contieneObserver(flech));
	}
	
	// Testea la eliminacion de un observador a la lista observers de la zona de cobertura
	@Test
	public void testEliminarObserver() {dominicoWilde.agregarObserver(flech);
		dominicoWilde.eliminarObserver(flech);
		
		assertFalse(dominicoWilde.contieneObserver(flech));
	}
	
	

}
