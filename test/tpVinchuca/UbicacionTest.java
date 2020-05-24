package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

public class UbicacionTest {

	//ubicaciones posibles
	private Ubicacion ubicacion1;
	private Ubicacion ubicacion2;
	private Ubicacion ubicacion3;
	private Ubicacion ubicacion4;
	
	//ubicaciones imposibles, solo se usan en un test cada uno, quizas no deberian estar en el setUp
	private Ubicacion ubicacion5;
	private Ubicacion ubicacion6;
	private Ubicacion ubicacion7;
	private Ubicacion ubicacion8;

	
	
	@BeforeEach
	public void setUp() {
		//primer double latitud, segundo double longitud
		
		//ubicaciones posibles
		ubicacion1 = new Ubicacion(-34.627911, -58.380361); //Constitucion
		ubicacion2 = new Ubicacion(-34.777857, -58.308091); //Solano
		ubicacion3 = new Ubicacion(-34.795691, -58.287063); //Varela
		ubicacion4 = new Ubicacion(-34.822964, -58.111839); //Parque Pereyra
	}
	
	
	
	
	@Test
	public void testCreacionUbicacion1() {
		Double latUbicacion1 = ubicacion1.getLatitud();
		Double longUbicacion1 = ubicacion1.getLongitud();
		
		assertEquals(-34.627911, latUbicacion1);
		assertEquals(-58.380361, longUbicacion1);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreacionUbicacion5() {
		ubicacion5 = new Ubicacion(-94.603066, -58.443407);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreacionUbicacion6() {
		ubicacion6 = new Ubicacion(94.627911, -58.380361);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreacionUbicacion7() {
		ubicacion7 = new Ubicacion(-34.603066, -198.443407);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreacionUbicacion8() {
		ubicacion8 = new Ubicacion(-34.627911, 198.380361);
	}
	
	public void testCalcularDistancia() {
		//execute
		Double distanciaConUbicacion2 = ubicacion1.calcularDistancia(ubicacion2); //resultado: 17.95
		Double distanciaConUbicacion3 = ubicacion1.calcularDistancia(ubicacion3); //resultado: 20.53
		Double distanciaConUbicacion4 = ubicacion1.calcularDistancia(ubicacion4); //resultado: 32.78
		
		//verify
		//ver margen de error -> se agrega el punto decimal a lo ultimo dentro del parentesis del assert
		assertEquals(17.95, distanciaConUbicacion2, 0.02);
		assertEquals(20.53, distanciaConUbicacion3, 0.02);
		assertEquals(32.78, distanciaConUbicacion4, 0.02);
	}
	
	
	public void testUbicacionesAMenosDe() {
		// setUp
		ArrayList<Ubicacion> ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(ubicacion2);
		ubicaciones.add(ubicacion3);
		ubicaciones.add(ubicacion4);
		
		//execute
		//ubicaciones a menos de 30km
		ArrayList<Ubicacion> ubicacionesAMenosDe30 = ubicacion1.ubicacionesAMenosDe(30d, ubicaciones);
		int sizeUbicacionesAMenosDe30 = ubicacionesAMenosDe30.size();
		
		//ubicaciones a menos de 10km
		ArrayList<Ubicacion> ubicacionesAMenosDe10 = ubicacion1.ubicacionesAMenosDe(10d, ubicaciones);
		int sizeUbicacionesAMenosDe10 = ubicacionesAMenosDe10.size();
		
		//verify
		assertEquals(2, sizeUbicacionesAMenosDe30);
		assertEquals(0, sizeUbicacionesAMenosDe10);
	}
	
	public void testMuentrasAMenosDe() {
		
		//muestra mockito?
		//la lista deberia tenerlas o acceder la clase Ubicacion
		
		ArrayList<Muestra> muestrasAMenosDe15km = muestrasAMenosDe(15, muestra): List;
		int sizeMuestrasAMenosDe15km = muestrasAMenosDe15km.size(); 
		
		assertEquals(, sizeMuestrasAMenosDe15km);
	}
	
}
