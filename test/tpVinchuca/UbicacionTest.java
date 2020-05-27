package tpVinchuca;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class UbicacionTest {

	//ubicaciones posibles
	private Ubicacion ubicacion1;
	private Ubicacion ubicacion2;
	private Ubicacion ubicacion3;
	private Ubicacion ubicacion4;

	@BeforeEach
	public void setUp() {
		ubicacion1 = new Ubicacion(-34.627911, -58.380361); //Constitucion
		ubicacion2 = new Ubicacion(-34.777857, -58.308091); //Solano
		ubicacion3 = new Ubicacion(-34.795691, -58.287063); //Varela
		ubicacion4 = new Ubicacion(-34.822964, -58.111839); //Parque Pereyra
	}
	
	
	
	// Testea la creacion de una ubicacion existente
	@Test
	public void testCreacionUbicacionExistente() {
		Double latitud = ubicacion1.getLatitud();
		Double longitud = ubicacion1.getLongitud();
		
		assertEquals(-34.627911, latitud);
		assertEquals(-58.380361, longitud);
	}
	
	// Falla al crear una ubicacion con una latitud menor que el limite permitido
	@Test
	public void testCreacionUbicacionInexistentePorLatitudMenorQueElLimite() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Ubicacion ubicacion = new Ubicacion(-94.603066, -58.443407);
		});		
	}
	
	// Falla al crear una ubicacion con una latitud mayor que el limite permitido
	@Test
	public void testCreacionUbicacionInexistentePorLatitudMayorQueElLimite() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Ubicacion ubicacion = new Ubicacion(94.627911, -58.380361);
		});	
	}
	
	// Falla al crear una ubicacion con una longiud menor que el limite permitido
	@Test
	public void testCreacionUbicacionInexistentePorLongitudMenorQueElLimite() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Ubicacion ubicacion = new Ubicacion(-34.603066, -198.443407);
		});
	}
	
	// Falla al crear una ubicacion con una longiud mayor que el limite permitido
	@Test
	public void testCreacionUbicacionInexistentePorLongitudMayorQueElLimite() {
		Assertions.assertThrows(IllegalArgumentException.class, () -> {
			Ubicacion ubicacion = new Ubicacion(-34.627911, 198.380361);
		});
	}
	
	@Test
	public void testCalcularDistancia() {
		//execute
		Double distanciaConUbicacion2 = ubicacion1.calcularDistancia(ubicacion2); //resultado: 17.95
		Double distanciaConUbicacion3 = ubicacion1.calcularDistancia(ubicacion3); //resultado: 20.53
		Double distanciaConUbicacion4 = ubicacion1.calcularDistancia(ubicacion4); //resultado: 32.78
		
		//verify
		assertEquals(17.95, distanciaConUbicacion2, 0.02);
		assertEquals(20.53, distanciaConUbicacion3, 0.02);
		assertEquals(32.77, distanciaConUbicacion4, 0.02);
	}
	
	//Se testea que las ubicaciones a menos de 30 kilometros de ubicacion1 sean 2
	@Test
	public void testUbicacionesAMenosDe30Km() {
		// setUp
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(ubicacion2);
		ubicaciones.add(ubicacion3);
		ubicaciones.add(ubicacion4);
		
		//execute
		//ubicaciones a menos de 30km
		List<Ubicacion> ubicacionesAMenosDe = ubicacion1.ubicacionesAMenosDe(30d, ubicaciones);
		int sizeUbicacionesAMenosDe = ubicacionesAMenosDe.size();
		
		//verify
		assertEquals(2, sizeUbicacionesAMenosDe);
	}
	
	//Se testea que las ubicaciones a menos de 10 kilometros de ubicacion1 sean 0	
	@Test
	public void testUbicacionesAMenosDe10Km() {
		// setUp
		List<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(ubicacion2);
		ubicaciones.add(ubicacion3);
		ubicaciones.add(ubicacion4);
		
		//ubicaciones a menos de 10km
		List<Ubicacion> ubicacionesAMenosDe = ubicacion1.ubicacionesAMenosDe(10d, ubicaciones);
		int sizeUbicacionesAMenosDe = ubicacionesAMenosDe.size();
		
		//verify
		assertEquals(0, sizeUbicacionesAMenosDe);
	}
	
	/*
	public void testMuentrasAMenosDe() {
		
		//muestra mockito?
		//la lista deberia tenerlas o acceder la clase Ubicacion
		
		ArrayList<Muestra> muestrasAMenosDe15km = muestrasAMenosDe(15, muestra): List;
		int sizeMuestrasAMenosDe15km = muestrasAMenosDe15km.size(); 
		
		assertEquals(, sizeMuestrasAMenosDe15km);
	}*/
	
}
