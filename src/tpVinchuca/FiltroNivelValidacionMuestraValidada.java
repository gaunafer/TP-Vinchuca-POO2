package tpVinchuca;

public class FiltroNivelValidacionMuestraValidada extends FiltroNivelValidacionMuestra {

	@Override
	public Boolean tieneNivelCorrespondiente(Muestra muestra) {
		return muestra.estaValidada();
	}

	
}
