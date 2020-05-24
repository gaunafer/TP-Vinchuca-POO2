package tpVinchuca;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class ZonaDeCoberturaTest {
	
	private ZonaDeCobertura zona1;
	private ZonaDeCobertura zona2;
	private ZonaDeCobertura zona3;
	private ZonaDeCobertura zona4;
	
	//aca imagino que habria que usar mockito no?
	private Ubicacion epicentro1;
	private Ubicacion epicentro2;
	private Ubicacion epicentro3;
	private Ubicacion epicentro4;
	
	
	public void setUp() {
		epicentro1 = new Ubicacion(-34.688910, -58.314271);
		epicentro2 = new Ubicacion(-34.811389, -58.274080);
		epicentro3 = new Ubicacion(-34.688910, -58.314271);
		epicentro4 = new Ubicacion(-34.751968, -58.234205);
		
		// epicentros en estaciones
		zona1 = new ZonaDeCobertura(epicentro1, 2d, "Costanera Dominico Wilde"); // acceso parque santo domingo
		zona2 = new ZonaDeCobertura(epicentro2, 4d, "Varela"); //epicentro en estacion
		zona3 = new ZonaDeCobertura(epicentro3, 3.5d, "Solano"); //epicentro en 844 y donato alvarez
		zona4 = new ZonaDeCobertura(epicentro4, 4d, "Ezpeleta"); //epicentro en estacion
	}
	
	
	//no testeo epicentro porque seria testear Ubicacion, que ya esta testeada
	//no se me ocurrieron excepciones... probar que no se puede cargar un radio negativo/vacio quizas?
	public void testCreacionZonaDeCobertura() {
		//en teoria no habria que crear un metodo para poder hacer correr un test asi que no se si estan bien estos gets...
		Double radio = zona1.getRadio();
		String nombre = zona1.getNombre();
		
		//reformular el assert? pero contra que variable? el nombre quizas?
		assertEquals(2, radio);
		assertEquals("Costanera Dominico Wilde", nombre);
	}
	
	
	
	//metodo privado seSolapaCon(ZonaDeCobertura) sin testear
	
	
	//ejemplo de zona que se solapa con otras dos zonas
	public void testZonasConLasQueSeSolapa() {
		//setUp
		ArrayList<ZonaDeCobertura> listaDeZonas = new ArrayList();
		listaDeZonas.add(zona1); //distancia Dominico-Varela: 14.03
		listaDeZonas.add(zona3); //distancia Solano-Varela: 4.91
		listaDeZonas.add(zona4); //distancia Ezpeleta-Varela: 7.48
		
		//execute
		ArrayList<ZonaDeCobertura> listaDeZonasConLasQueSeSolapa = zona2.zonasConLasQueSeSolapa(listaDeZonas);
		Integer cantDeZonasConLasQueSeSolapa = listaDeZonasConLasQueSeSolapa.size(); 
		
		//verify
		assertEquals(2, cantDeZonasConLasQueSeSolapa);
	}
	

	//ejemplo de zona que no se solapa con ninguna zona
	public void testZonasConLasQueSeSolapa() {
		//setUp
		ArrayList<ZonaDeCobertura> listaDeZonas = new ArrayList();
		listaDeZonas.add(zona2); //distancia Varela-Dominico: 14.03
		listaDeZonas.add(zona3); //distancia Solano-Dominico: 10.37
		listaDeZonas.add(zona4); //distancia Ezpeleta-Dominico: 10.09
		
		//execute
		ArrayList<ZonaDeCobertura> listaDeZonasConLasQueSeSolapa = zona1.zonasConLasQueSeSolapa(listaDeZonas);
		Integer cantDeZonasConLasQueSeSolapa = listaDeZonasConLasQueSeSolapa.size(); 
		
		//verify
		assertEquals(0, cantDeZonasConLasQueSeSolapa);
	}

}
