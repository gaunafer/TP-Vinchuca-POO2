package tpVinchuca;

import static org.junit.jupiter.api.Assertions.*;

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
	}
	
	

	//no se me ocurrieron excepciones... probar que no se puede cargar un radio negativo/vacio quizas?
	@Test
	public void testCreacionZonaDeCobertura() {
		
		Double radio = dominicoWilde.getRadio();
		String nombre = dominicoWilde.getNombre();
		
		assertEquals(2, radio);
		assertEquals("Costanera Dominico Wilde", nombre);
	}
		
	
	//ejemplo de zona que se solapa con otras dos zonas
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
	

	//ejemplo de zona que no se solapa con ninguna zona
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

}
