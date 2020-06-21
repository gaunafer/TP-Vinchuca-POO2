package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;

public class OrganizacionTest {

	private Ubicacion ubicFlech;
	private IFuncionalidadExterna funcionalidad;
	private Organizacion flech;

	private Muestra muestra;
	private ZonaDeCobertura solano;
	
	@BeforeEach
	public void setUp() {
		ubicFlech = mock(Ubicacion.class);
		funcionalidad = mock(IFuncionalidadExterna.class);
		flech = new Organizacion(TipoDeOrganizacion.SANITARIA, ubicFlech, 25, funcionalidad, funcionalidad);
		
		muestra = mock(Muestra.class);
		solano = mock(ZonaDeCobertura.class);
	}
	
	// Testea el constructor sin funcionalidades externas de la clase Organizacion
	@Test
	public void testCreacionOrganizacionSinFuncionalidades() {
		Ubicacion ubicMsf = mock(Ubicacion.class);
		Organizacion msf = new Organizacion(TipoDeOrganizacion.SANITARIA, ubicMsf, 70);
		Ubicacion ubicacion = msf.getUbicacion();
		TipoDeOrganizacion tipoDeOrganizacion = msf.getTipoDeOrganizacion();
		Integer cantDeTrabajadores = msf.getCantidadDeTrabajadores();
		
		assertEquals(ubicMsf, ubicacion);
		assertEquals("SANITARIA", tipoDeOrganizacion.toString());
		assertEquals(70, cantDeTrabajadores);
	}

	// Testea el constructor con funcionalidades externas de la clase Organizacion
	@Test
	public void testCreacionOrganizacionConFuncionalidades() {
		
		Ubicacion ubicacion = flech.getUbicacion();
		TipoDeOrganizacion tipoDeOrganizacion = flech.getTipoDeOrganizacion();
		Integer cantDeTrabajadores = flech.getCantidadDeTrabajadores();
		
		assertEquals(ubicFlech, ubicacion);
		assertEquals("SANITARIA", tipoDeOrganizacion.toString());
		assertEquals(25, cantDeTrabajadores);
	}
	
	// Chequea que se envie el mensaje a la zona para que agregue a la organizacion
	// como observer de la misma
	@Test
	public void testOrganizacionSeAgregaComoObserverDeZona() {
		flech.interesEnZona(solano);
		
		verify(solano).agregarObserver(flech);
	}
	
	// Chequea que el metodo updateCreacionMuestra llame correctamente al nuevoEvento de la funcionalidadExterna
	@Test
	public void testUpdateCreacionMuestra() {
		
		flech.updateCreacionMuestra(muestra, solano);
		verify(funcionalidad).nuevoEvento(flech, solano, muestra);
	}
	
	// Chequea que el metodo updateValidacionMuestra llame correctamente al nuevoEvento de la funcionalidadExterna
	@Test
	public void testUpdateValidacionMuestra() {
		
		flech.updateValidacionMuestra(muestra, solano);
		verify(funcionalidad).nuevoEvento(flech, solano, muestra);
	}

}
