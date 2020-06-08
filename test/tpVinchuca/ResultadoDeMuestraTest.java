package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ResultadoDeMuestraTest {
	ResultadoDeMuestra vinchuca;
	ResultadoDeMuestra muestraIndefinida;
	ResultadoDeMuestra imagenPocoLegible;
	
	@BeforeEach
	public void setUp() {
		vinchuca = ResultadoDeMuestra.VINCHUCA;
		muestraIndefinida = ResultadoDeMuestra.INDEFINIDA;
		imagenPocoLegible = ResultadoDeMuestra.IMAGEN_POCO_LEGIBLE;
	}
	@Test
	public void testEnum() {
		assertEquals("Vinchuca", vinchuca.getValor());
		assertEquals("Muestra Indefinida", muestraIndefinida.getValor());
		assertEquals("Imagen poco legible", imagenPocoLegible.getValor());
	}
}
