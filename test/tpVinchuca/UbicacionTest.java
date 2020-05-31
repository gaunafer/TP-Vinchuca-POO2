package tpVinchuca;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;

import org.junit.jupiter.api.*;

import static org.mockito.Mockito.*;



public class UbicacionTest {

	//ubicaciones posibles
	private Ubicacion constitucion;
	private Ubicacion solano;
	private Ubicacion varela;
	private Ubicacion parquePereyra;
	private List<Ubicacion> ubicaciones;
	
	//muestras en las ubicaciones posibles
	private Muestra muestraEnSolano;
	private Muestra muestraEnConstitucion;
	private Muestra muestraEnVarela;
	private Muestra muestraEnParquePereyra;
	private List<Muestra> muestras;
	

	@BeforeEach
	public void setUp() {
		//creacion de las ubicaciones
		constitucion = new Ubicacion(-34.627911, -58.380361);
		solano = new Ubicacion(-34.777857, -58.308091);
		varela = new Ubicacion(-34.795691, -58.287063);
		parquePereyra = new Ubicacion(-34.822964, -58.111839);
		
		//agregado de las ubicaciones a la lista ubicaciones
		ubicaciones = new ArrayList<Ubicacion>();
		ubicaciones.add(solano);
		ubicaciones.add(varela);
		ubicaciones.add(parquePereyra);
		
		//creacion de muestras
		muestraEnSolano = mock(Muestra.class);
		when(muestraEnSolano.getUbicacion()).thenReturn(solano);

		muestraEnConstitucion = mock(Muestra.class);
		when(muestraEnConstitucion.getUbicacion()).thenReturn(constitucion);

		muestraEnVarela = mock(Muestra.class);
		when(muestraEnVarela.getUbicacion()).thenReturn(varela);

		muestraEnParquePereyra = mock(Muestra.class);
		when(muestraEnParquePereyra.getUbicacion()).thenReturn(parquePereyra);
		
		//agregado de las muestras a la lista muestras
		muestras = new ArrayList<Muestra>();
		muestras.add(muestraEnParquePereyra);
		muestras.add(muestraEnVarela);
		muestras.add(muestraEnSolano);
	}
	
	
	
	// Testea la creacion de una ubicacion existente
	@Test
	public void testCreacionUbicacionExistente() {
		Double latitud = constitucion.getLatitud();
		Double longitud = constitucion.getLongitud();
		
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
	
	// Chequea el metodo calcularDistancia, con un margen de error de 0.02
	@Test
	public void testCalcularDistancia() {
		//execute
		Double distanciaConUbicacion2 = constitucion.calcularDistancia(solano); //resultado: 17.95
		Double distanciaConUbicacion3 = constitucion.calcularDistancia(varela); //resultado: 20.53
		Double distanciaConUbicacion4 = constitucion.calcularDistancia(parquePereyra); //resultado: 32.78
		
		//verify
		assertEquals(17.95, distanciaConUbicacion2, 0.02);
		assertEquals(20.53, distanciaConUbicacion3, 0.02);
		assertEquals(32.77, distanciaConUbicacion4, 0.02);
	}
	
	// Se testea que las ubicaciones a menos de 30 kilometros de ubicacion1 sean 2
	@Test
	public void testUbicacionesAMenosDe30Km() {		
		
		//execute ubicaciones a menos de 30km
		List<Ubicacion> ubicacionesAMenosDe = constitucion.ubicacionesAMenosDe(30d, ubicaciones);
		int sizeUbicacionesAMenosDe = ubicacionesAMenosDe.size();
		
		//verify
		assertEquals(2, sizeUbicacionesAMenosDe);
	}
	
	// Se testea que las ubicaciones a menos de 10 kilometros de ubicacion1 sean 0	
	@Test
	public void testUbicacionesAMenosDe10Km() {
		
		//execute ubicaciones a menos de 10km
		List<Ubicacion> ubicacionesAMenosDe = constitucion.ubicacionesAMenosDe(10d, ubicaciones);
		int sizeUbicacionesAMenosDe = ubicacionesAMenosDe.size();
		
		//verify
		assertEquals(0, sizeUbicacionesAMenosDe);
	}
	
	// Se testea la cantidad de muestras a menos de 25 km de la muestra en Constitucion
	@Test
	public void testMuentrasAMenosDe25Km() {
		
		List<Muestra> muestrasAMenosDe25km = constitucion.muestrasAMenosDe(25d, muestraEnConstitucion, muestras);
		int sizeMuestrasAMenosDe25km = muestrasAMenosDe25km.size(); 
		
		assertEquals(2, sizeMuestrasAMenosDe25km);
	}

	// Se testea la cantidad de muestras a menos de 15 km de la muestra en Constitucion
	@Test
	public void testMuentrasAMenosDe15Km() {
		
		List<Muestra> muestrasAMenosDe15km = constitucion.muestrasAMenosDe(15d, muestraEnConstitucion, muestras);
		int sizeMuestrasAMenosDe15km = muestrasAMenosDe15km.size(); 
		
		assertEquals(0, sizeMuestrasAMenosDe15km);
	}
	
}
