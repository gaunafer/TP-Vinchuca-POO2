package tpVinchuca;

public class FiltroNivelValidacionMuestraVotada extends FiltroNivelValidacionMuestra {

	@Override
	public Boolean tieneNivelCorrespondiente(Muestra muestra) {
		return (muestra.getNivelDeValidacion().equals("Nivel Experto") || muestra.getNivelDeValidacion().equals("Nivel Basico"));
	}

}
