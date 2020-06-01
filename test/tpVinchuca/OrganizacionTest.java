package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;

public class OrganizacionTest {

	private Ubicacion ubicFlech;
	private Organizacion flech;

	private IFuncionalidadExterna funcionalidad;
	private Muestra muestra;
	private ZonaDeCobertura zona;
	
	@BeforeEach
	public void setUp() {
		ubicFlech = mock(Ubicacion.class);
		flech = new Organizacion(TipoDeOrganizacion.SANITARIA, ubicFlech, 25);
		
		funcionalidad = mock(IFuncionalidadExterna.class);
		muestra = mock(Muestra.class);
		zona = mock(ZonaDeCobertura.class);
	}
	
	// Testea el constructor de la clase Organizacion
	@Test
	public void testCreacionOrganizacion() {
		
		Ubicacion ubicacion = flech.getUbicacion();
		TipoDeOrganizacion tipoDeOrganizacion = flech.getTipoDeOrganizacion();
		Integer cantDeTrabajadores = flech.getCantidadDeTrabajadores();
		
		assertEquals(ubicFlech, ubicacion);
		assertEquals("SANITARIA", tipoDeOrganizacion.toString());
		assertEquals(25, cantDeTrabajadores);
	}
	
	// Chequea que el metodo updateCreacionMuestra llame correctamente al nuevoEvento de la funcionalidadExterna
	@Test
	public void testUpdateCreacionMuestra() {
		flech.setFuncionalidadCreacionDeMuestra(funcionalidad);
		
		flech.updateCreacionMuestra(muestra, zona);
		verify(funcionalidad).nuevoEvento(flech, zona, muestra);
	}
	
	// Chequea que el metodo updateValidacionMuestra llame correctamente al nuevoEvento de la funcionalidadExterna
	@Test
	public void testUpdateValidacionMuestra() {
		flech.setFuncionalidadValidacionDeMuestra(funcionalidad);
		
		flech.updateValidacionMuestra(muestra, zona);
		verify(funcionalidad).nuevoEvento(flech, zona, muestra);
	}

}
