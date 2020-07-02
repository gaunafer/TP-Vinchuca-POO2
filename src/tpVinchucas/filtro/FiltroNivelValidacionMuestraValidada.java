package tpVinchucas.filtro;

import tpVinchuca.Muestra;

public class FiltroNivelValidacionMuestraValidada extends FiltroNivelValidacionMuestra {

	/**
	 *@param muestra: Lista de muestras
	 *@return True si la muestra esta validada
	 */
	@Override
	public Boolean tieneNivelCorrespondiente(Muestra muestra) {
		return muestra.estaValidada();
	}

	
}
