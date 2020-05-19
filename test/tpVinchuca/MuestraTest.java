package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

public class MuestraTest {
	private Muestra muestra1;
	@Mock
	private Persona persona;
	@Mock
	private Ubicacion ubicacion;
	@Mock
	private Imagen imagen;
	@Mock
	private OpinionDeImagen opinion;
	@Mock
	private Votacion votacion;
	@Mock
	private Votacion votacion2;
	
	@BeforeEach
	public void setUp() {
		Mockito.doReturn("fer").when(persona).getAlias();
		Mockito.doReturn("vinchuca").when(opinion).getOpinionDeFoto();
		Mockito.doReturn("Chinche Foliada").when(votacion).getOpinion();
		Mockito.doReturn("Chinche Foliada").when(votacion).getOpinion();
		muestra1 = new Muestra(opinion, ubicacion, persona, imagen);
	}
	
	@Test
	public void muestraConstructorTest() {
		assertEquals(persona, muestra1.getPersona());
		assertEquals(ubicacion, muestra1.getUbicacion());
		assertEquals(imagen, muestra1.getImagen());
		assertEquals("vinchuca", muestra1.getTipoVinchuca());
	}
	@Test
	public void resultadoActualMuestraSinVotos() {
		assertEquals("vinchuca", muestra1.getResultadoActual());
	}
	@Test
	void void resultadoMuestraCon2Votos() {
		muestra1.registrarVotacion(votacion);
		muestra1.registrarVotacion(votacion2);
		assertEquals("Chinche Foliada", muestra1.getResultadoActual());
	}
}
