package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.Spy;

public class AplicacionVinchucasTest {
	
	@Mock
	private Buscador buscador= mock(Buscador.class);
	@Mock
	private FiltroPorFecha filtroFecha = mock(FiltroPorFecha.class);
	@Mock
	private FiltroAnd and = mock(FiltroAnd.class);
	@Mock
	private FiltroOr filtroCombinado = mock(FiltroOr.class);
	
	@Mock
	private FiltroParticipante filtroParticipante = mock(FiltroParticipante.class);

	@Mock
	private Muestra muestra = mock(Muestra.class);
	@Mock
	private Muestra muestra1 = mock(Muestra.class);
	@Mock
	private Votacion votacion = mock(Votacion.class);
	@Mock
	private Votacion votacion1 = mock(Votacion.class);
	@Mock
	private Votacion votacion2 = mock(Votacion.class);
	@Mock
	private Participante juanPerez = mock(Participante.class);
	@Mock
	private Participante pepitaLaPistolera = mock(Participante.class);
	@Mock
	private Participante mataHari = mock(Participante.class);
	
	
	

	private List<Muestra> muestras;
	private List<Muestra> muestrasConVotaciones;
	private List<Votacion> votaciones ;
	
	private AplicacionVinchucas aplicacion; 
	
	
	@BeforeEach
	public void setUp() {
			aplicacion = new AplicacionVinchucas(buscador); 
			votaciones = new ArrayList<Votacion>();
			muestrasConVotaciones = new ArrayList<Muestra>();	
			muestras = new ArrayList<Muestra>();
	}
	
	@Test
	public void seCreaUnaAplicacionVinchucasCorrectamente() {
		assertEquals(buscador, aplicacion.getBuscador());
	}
	
	// Testea:
	// -el agregado de zonas de cobertura a la lista de zonas
	// -el agregado de muestras a la lista de muestras
	// -que al agregar una muestra, las zonas a las que pertenece la muestra la agreguen a su lista
	// -que ademas las clases que monitorean la validacion de muestras agreguen a las zonas a las que la muestra pertenece
	@Test
	public void testSeAgregaUnaMuestraQuePerteneceADosZonasDeCobertura() {
		ZonaDeCobertura varela = mock(ZonaDeCobertura.class);
		ZonaDeCobertura berazategui = mock(ZonaDeCobertura.class);
		
		aplicacion.agregarZonaDeCobertura(varela);
		aplicacion.agregarZonaDeCobertura(berazategui);
		
		when(varela.zonaContieneUbicacionDeMuestra(muestra)).thenReturn(true);
		when(berazategui.zonaContieneUbicacionDeMuestra(muestra)).thenReturn(true);

		aplicacion.agregarMuestra(muestra);
		
		verify(varela).agregarMuestra(muestra);
		verify(berazategui).agregarMuestra(muestra);
		verify(muestra).asignarZona(varela);
		verify(muestra).asignarZona(berazategui);
	}

	// Testea:
	// -el agregado de zonas de cobertura a la lista de zonas
	// -el agregado de muestras a la lista de muestras
	// -que al agregar una muestra, las zonas a las que no pertenece la muestra no agreguen a la misma a su lista
	// -que ademas las clases que monitorean la validacion de muestras no agreguen a las zonas a las que la muestra no pertenece
	@Test
	public void testSeAgregaUnaMuestraQueNoPerteneceANingunaZonasDeCobertura() {
		ZonaDeCobertura varela = mock(ZonaDeCobertura.class);
		ZonaDeCobertura berazategui = mock(ZonaDeCobertura.class);
		
		aplicacion.agregarZonaDeCobertura(varela);
		aplicacion.agregarZonaDeCobertura(berazategui);
		
		when(varela.zonaContieneUbicacionDeMuestra(muestra)).thenReturn(false);
		when(berazategui.zonaContieneUbicacionDeMuestra(muestra)).thenReturn(false);

		aplicacion.agregarMuestra(muestra);
		
		verify(varela, never()).agregarMuestra(muestra);
		verify(berazategui, never()).agregarMuestra(muestra);
		verify(muestra, never()).asignarZona(varela);
		verify(muestra, never()).asignarZona(berazategui);
	}
	
	
	
	@Test
	public void retornaLaListaDeMuestrasFiltradasPorFecha() {
		
		muestras.add(muestra);
		muestras.add(muestra1);
		when(muestra.getParticipante()).thenReturn(juanPerez);
		when(muestra1.getParticipante()).thenReturn(juanPerez);

		aplicacion.agregarMuestra(muestra);
		aplicacion.agregarMuestra(muestra1);

		
		when(buscador.buscar(muestras, filtroFecha)).thenReturn(muestras);
		when(buscador.buscar(muestras, and)).thenReturn(muestras);
		when(buscador.buscar(muestras, filtroParticipante)).thenReturn(muestras);
		when(filtroFecha.criterioDeBusqueda(muestras)).thenReturn(muestras);
		when(filtroParticipante.criterioDeBusqueda(muestras)).thenReturn(muestras);
		when(and.criterioDeBusqueda(muestras)).thenReturn(muestras);

	
				
		assertEquals(muestrasConVotaciones, aplicacion.getMuestrasDeParticipantePorFecha(juanPerez, LocalDate.now()));
	}
	
	@Test 
	public void votacionesTotalesDelaAplicacion() {
		
		votaciones.add(votacion);
		votaciones.add(votacion1);
		when(muestra.getVotaciones()).thenReturn(votaciones);
	

		aplicacion.agregarMuestra(muestra);		
		
		assertEquals(votaciones, aplicacion.getVotaciones());
		
	}
	
	@Test
	public void seObtienLaListaDeVotacionesDeUnaListaDeMuestras() {

		aplicacion.agregarMuestra(muestra);
		aplicacion.agregarMuestra(muestra1);


		when(buscador.getVotacionesDeParticipanteEnLosUltimos30Dias(votaciones,juanPerez)).thenReturn(votaciones);
		when(muestra.getVotaciones()).thenReturn(votaciones);
	
		when(muestra.getParticipante()).thenReturn(juanPerez);
	
	
		assertEquals(votaciones, aplicacion.getVotacionesDeParticipanteDeLosUltimos30Dias(juanPerez));
	}
	
	@Test
	public void seRealizaLaActualizacionDeEstadoDeLosParticipantesCorrectamente() {
        
		when(juanPerez.getNivelDeConocimiento()).thenReturn("Nivel Basico");
		when(pepitaLaPistolera.getNivelDeConocimiento()).thenReturn("Nivel Experto");
		when(mataHari.getNivelDeConocimiento()).thenReturn("Nivel Experto");  
		
		aplicacion.agregarMuestra(muestra);
		aplicacion.agregarMuestra(muestra1);
		
		when(muestra.getParticipante()).thenReturn(juanPerez);
		when(muestra1.getParticipante()).thenReturn(mataHari);
		
		votaciones.add(votacion);
		votaciones.add(votacion1);
		
		List<Votacion>votaciones1 = new ArrayList<Votacion>();
		votaciones1.add(votacion2);
		
		when(muestra.getVotaciones()).thenReturn(votaciones);
		when(muestra1.getVotaciones()).thenReturn(votaciones1);
		
		when(votacion.getParticipante()).thenReturn(pepitaLaPistolera);
		when(votacion1.getParticipante()).thenReturn(mataHari);
		when(votacion2.getParticipante()).thenReturn(juanPerez);
		
		aplicacion.actualizarNivelDeConocimiento();
		
		verify(juanPerez).actualizarEstado();
		verify(pepitaLaPistolera).actualizarEstado();
		verify(mataHari).actualizarEstado();
	
	}



}
