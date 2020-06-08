package tpVinchuca;

import java.util.ArrayList;
import java.util.List;

public class FiltroOr implements Filtro{
	
	   private Filtro filtro;
	   private Filtro otroFiltro;

	   public FiltroOr(Filtro filtro, Filtro otroFiltro) {
	      this.filtro = filtro;
	      this.otroFiltro = otroFiltro; 
	   }

	   @Override
	   public List<Muestra> criterioDeBusqueda(List<Muestra> muestras) {
	      List<Muestra> primerCriterioDeBusqueda = filtro.criterioDeBusqueda(muestras);
	      List<Muestra> otroCriterioDeBusqueda = otroFiltro.criterioDeBusqueda(muestras);
	      List<Muestra> listaDeMuestraFiltrada = new ArrayList<Muestra>();

	      for (Muestra muestra : otroCriterioDeBusqueda) {
	         if(primerCriterioDeBusqueda.contains(muestra)){
	        	 listaDeMuestraFiltrada.add(muestra);
	         }
	      }	
	      return listaDeMuestraFiltrada;
	   }

}
 
