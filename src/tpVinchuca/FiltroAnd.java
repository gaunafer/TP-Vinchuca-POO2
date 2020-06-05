package tpVinchuca;

import java.util.List;

public class FiltroAnd implements Filtro{

	
	   private Filtro filtro;
	   private Filtro otroFiltro;

	   public FiltroAnd(Filtro filtro, Filtro otroFiltro) {
	      this.filtro = filtro;
	      this.otroFiltro = otroFiltro; 
	   }

	   @Override
	   public List<Muestra> criterioDeBusqueda(List<Muestra> muestras) {
	   
	      List<Muestra> muestrasSegunCriterio = filtro.criterioDeBusqueda(muestras);		
	      return otroFiltro.criterioDeBusqueda(muestrasSegunCriterio);
	   }
}
