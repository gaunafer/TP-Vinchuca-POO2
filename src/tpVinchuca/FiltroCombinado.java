package tpVinchuca;

import java.util.List;

public class FiltroCombinado implements Filtro{
	
	   private Filtro filtro;
	   private Filtro otroFiltro;

	   public FiltroCombinado(Filtro filtro, Filtro otroFiltro) {
	      this.filtro = filtro;
	      this.otroFiltro = otroFiltro; 
	   }

	   @Override
	   public List<Muestra> criterioDeBusqueda(List<Muestra> muestras) {
	      List<Muestra> primerCriterioDeBusqueda = filtro.criterioDeBusqueda(muestras);
	      List<Muestra> otroCriterioDeBusqueda = otroFiltro.criterioDeBusqueda(muestras);

	      for (Muestra muestra : otroCriterioDeBusqueda) {
	         if(!primerCriterioDeBusqueda.contains(muestra)){
	            primerCriterioDeBusqueda.add(muestra);
	         }
	      }	
	      return primerCriterioDeBusqueda;
	   }

}
 
