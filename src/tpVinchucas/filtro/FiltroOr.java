package tpVinchucas.filtro;

import java.util.ArrayList;
import java.util.List;

import tpVinchuca.Muestra;

public class FiltroOr extends OperadorBinario {

	   public FiltroOr(Filtro filtro, Filtro otroFiltro) {
	      super(filtro, otroFiltro); 
	   }

	   @Override
	   public List<Muestra> criterioDeBusqueda(List<Muestra> muestras) {
	      List<Muestra> primerCriterioDeBusqueda = getFiltro().criterioDeBusqueda(muestras);
	      List<Muestra> otroCriterioDeBusqueda = getOtroFiltro().criterioDeBusqueda(muestras);
	      List<Muestra> listaDeMuestraFiltrada = new ArrayList<Muestra>();

	      for (Muestra muestra : otroCriterioDeBusqueda) {
	         if(primerCriterioDeBusqueda.contains(muestra)){
	        	 listaDeMuestraFiltrada.add(muestra);
	         }
	      }	
	      return listaDeMuestraFiltrada;
	   }

}
 
