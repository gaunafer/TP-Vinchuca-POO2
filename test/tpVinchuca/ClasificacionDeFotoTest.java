package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClasificacionDeFotoTest {
	ClasificacionDeFoto vinchuca;
	ClasificacionDeFoto muestraIndefinida;
	ClasificacionDeFoto imagenPocoLegible;
	ClasificacionDeFoto vinchucaInfestans;
	ClasificacionDeFoto vinchucaSordida;
	ClasificacionDeFoto vinchucaGasayana;
	ClasificacionDeFoto chincheFoliada;
	ClasificacionDeFoto phitiaChinche;
	ClasificacionDeFoto ninguna;
	
	@BeforeEach
	public void setUp() {
		vinchuca = ClasificacionDeFoto.VINCHUCA;
		muestraIndefinida = ClasificacionDeFoto.INDEFINIDA;
		imagenPocoLegible = ClasificacionDeFoto.IMAGEN_POCO_CLARA;
	    vinchucaInfestans = ClasificacionDeFoto.VINCHUCA_INFESTANS;
	    vinchucaSordida = ClasificacionDeFoto.VINCHUCA_SORDIDA;
	    vinchucaGasayana = ClasificacionDeFoto.VINCHUCA_GUASAYANA;
	    chincheFoliada = ClasificacionDeFoto.CHINCHE_FOLIADA;
	    phitiaChinche = ClasificacionDeFoto.PHITIA_CHINCHE;
	    ninguna = ClasificacionDeFoto.NINGUNA;
	}
	@Test
	public void testEnum() {
		assertEquals("Vinchuca", vinchuca.getValor());
		assertEquals("Muestra Indefinida", muestraIndefinida.getValor());
		assertEquals("Imagen poco clara", imagenPocoLegible.getValor());
		
		assertEquals("Vinchuca Infestans", vinchucaInfestans.getValor());
		assertEquals("Vinchuca Sordida", vinchucaSordida.getValor());
		assertEquals("Vinchuca Gasayana", vinchucaGasayana.getValor());
		assertEquals("Chinche Foliada", chincheFoliada.getValor());
		assertEquals("Phitia Chinche", phitiaChinche.getValor());
		assertEquals("Ninguna", ninguna.getValor());
	}
}
