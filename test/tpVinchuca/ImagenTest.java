package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ImagenTest {
	
	private Imagen imagen;
	
	@BeforeEach
	public void setUp() {
		imagen = new Imagen("foto");
	}
	
	@Test
	public void seCreaUnaImagenConUnaFoto() {
		assertEquals("foto",imagen.getFoto());
	}

}
