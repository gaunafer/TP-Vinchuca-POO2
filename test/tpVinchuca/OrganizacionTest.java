package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.*;

public class OrganizacionTest {
	
	// Testea el constructor de la clase Organizacion
	@Test
	public void testCreacionOrganizacion() {
		Ubicacion ubicFlech = mock(Ubicacion.class);
		Organizacion flech = new Organizacion(TipoDeOrganizacion.SANITARIA, ubicFlech, 25);
		
		Ubicacion ubicacion = flech.getUbicacion();
		TipoDeOrganizacion tipoDeOrganizacion = flech.getTipoDeOrganizacion();
		Integer cantDeTrabajadores = flech.getCantidadDeTrabajadores();
		
		assertEquals(ubicFlech, ubicacion);
		assertEquals("SANITARIA", tipoDeOrganizacion.toString());
		assertEquals(25, cantDeTrabajadores);
	}

}
