package tpVinchuca.filtro;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tpVinchuca.Muestra;
import tpVinchucas.filtro.Filtro;
import tpVinchucas.filtro.FiltroNivelValidacionMuestraVotada;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;

class FiltroNivelDeValidacionDeMuestraVotadaTest {
	
	 private Filtro filtroNivelDeValidacionVotada;
	 
	 private List<Muestra> muestras;
	 private Muestra muestra1;
	 private Muestra muestra2;
	 private Muestra muestra3;
	 private Muestra muestra4;
	 
	 @BeforeEach
	 public void setUp() {
		 filtroNivelDeValidacionVotada= new FiltroNivelValidacionMuestraVotada();
		 
		 muestras = new ArrayList<Muestra>();
		 muestra1 = mock(Muestra.class);
		 muestra2 = mock(Muestra.class);
		 muestra3 = mock(Muestra.class);
		 muestra4 = mock(Muestra.class);
		 
		 when(muestra1.estaValidada()).thenReturn(true);
		 when(muestra2.estaValidada()).thenReturn(false);
		 when(muestra3.estaValidada()).thenReturn(false);
		 when(muestra4.estaValidada()).thenReturn(true);
		 
		 muestras.add(muestra1);
		 muestras.add(muestra2);
		 muestras.add(muestra3);
		 muestras.add(muestra4);
	 }
	 
	 
	 @Test
	 public void seTraenTodasLasMuestrasQueCorrespondenAlNivelDeValidacionDado() {
		 
		 List<Muestra> muestrasVotadas = filtroNivelDeValidacionVotada.criterioDeBusqueda(muestras);
		 Integer cantidadDeMuestrasVotadas = muestrasVotadas.size();
		 
		 assertEquals(2, cantidadDeMuestrasVotadas);
	 }
}
