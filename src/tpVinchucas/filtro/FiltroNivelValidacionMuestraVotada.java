package tpVinchucas.filtro;

import tpVinchuca.Muestra;

public class FiltroNivelValidacionMuestraVotada extends FiltroNivelValidacionMuestra {

	/**
	 *@param muestra: Lista de muestras
	 *@return True si la muestra es votada (no validada).
	 */
	@Override
	public Boolean tieneNivelCorrespondiente(Muestra muestra) {
		return (!muestra.estaValidada());
	}

}
