package tpVinchucas.filtro;

import tpVinchuca.Muestra;

public class FiltroNivelValidacionMuestraVotada extends FiltroNivelValidacionMuestra {

	@Override
	public Boolean tieneNivelCorrespondiente(Muestra muestra) {
		return (!muestra.estaValidada());
	}

}
