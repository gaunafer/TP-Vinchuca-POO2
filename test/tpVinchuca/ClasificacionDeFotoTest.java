package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClasificacionDeFotoTest {
	ClasificacionDeFoto vinchuca;
	ClasificacionDeFoto muestraIndefinida;
	ClasificacionDeFoto imagenPocoLegible;
	
	@BeforeEach
	public void setUp() {
		vinchuca = ClasificacionDeFoto.VINCHUCA;
		muestraIndefinida = ClasificacionDeFoto.INDEFINIDA;
		imagenPocoLegible = ClasificacionDeFoto.IMAGEN_POCO_CLARA;
	}
	@Test
	public void testEnum() {
		assertEquals("Vinchuca", vinchuca.getValor());
		assertEquals("Muestra Indefinida", muestraIndefinida.getValor());
		assertEquals("Imagen poco clara", imagenPocoLegible.getValor());
	}
}
