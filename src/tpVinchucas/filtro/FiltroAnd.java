package tpVinchucas.filtro;

import java.util.List;

import tpVinchuca.Muestra;

public class FiltroAnd extends OperadorBinario {

	   public FiltroAnd(Filtro filtro, Filtro otroFiltro) {
	      super(filtro, otroFiltro);
	   }

	@Override
	   public List<Muestra> criterioDeBusqueda(List<Muestra> muestras) {
	   
	      List<Muestra> muestrasSegunCriterio = getFiltro().criterioDeBusqueda(muestras);		
	      return getOtroFiltro().criterioDeBusqueda(muestrasSegunCriterio);
	   }
}
