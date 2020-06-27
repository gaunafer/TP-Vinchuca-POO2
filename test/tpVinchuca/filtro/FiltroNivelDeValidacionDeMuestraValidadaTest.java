package tpVinchuca.filtro;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpVinchuca.Muestra;
import tpVinchucas.filtro.Filtro;
import tpVinchucas.filtro.FiltroNivelValidacionMuestraValidada;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class FiltroNivelDeValidacionDeMuestraValidadaTest {
	
	 private Filtro filtroNivelDeValidacionValidada;
	 
	 private List<Muestra> muestras;
	 private Muestra muestra1;
	 private Muestra muestra2;
	 private Muestra muestra3;
	 private Muestra muestra4;
	 
	 @BeforeEach
	 public void setUp() {
		 filtroNivelDeValidacionValidada= new FiltroNivelValidacionMuestraValidada();
		 
		 muestras = new ArrayList<Muestra>();
		 muestra1 = mock(Muestra.class);
		 muestra2 = mock(Muestra.class);
		 muestra3 = mock(Muestra.class);
		 muestra4 = mock(Muestra.class);
		 
		 when(muestra1.getNivelDeValidacion()).thenReturn("Nivel Validada");
		 when(muestra2.getNivelDeValidacion()).thenReturn("Nivel Experto");
		 when(muestra3.getNivelDeValidacion()).thenReturn("Nivel Basico");
		 when(muestra4.getNivelDeValidacion()).thenReturn("Nivel Validada");
		 
		 muestras.add(muestra1);
		 muestras.add(muestra2);
		 muestras.add(muestra3);
		 muestras.add(muestra4);
	 }
	 
	 
	 @Test
	 public void seTraenTodasLasMuestrasQueCorrespondenAlNivelDeValidacionDado() {
		 
		 List<Muestra> muestrasValidadas = filtroNivelDeValidacionValidada.criterioDeBusqueda(muestras);
		 Integer cantidadDeMuestrasValidadas = muestrasValidadas.size();
		 
		 assertEquals(2, cantidadDeMuestrasValidadas);
	 }
}
