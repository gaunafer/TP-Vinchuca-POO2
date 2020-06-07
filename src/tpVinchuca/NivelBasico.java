package tpVinchuca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NivelBasico extends NivelDeValidacion {

	@Override
	protected List<Votacion> getVotaciones(Muestra muestra){
		List<Votacion> votaciones;
		votaciones = muestra.getVotaciones();
		return votaciones;
	}

	@Override
	public void registrarVotacion(Muestra muestra, Votacion votacion) throws ErrorParticipanteNoPuedeVotarMuestraPublicadaPorSiMismo  {
		if (muestra.getParticipante().equals(votacion.getParticipante())) {
			throw new ErrorParticipanteNoPuedeVotarMuestraPublicadaPorSiMismo();
			}
		muestra.addVotacion(votacion);
		if (votacion.getParticipante().getNivelDeConocimiento() == "Nivel Experto") {
			muestra.setNivelDeValidacionExperto();
		}
	}
/////
	/*private void verificarParticipante(Muestra muestra, Votacion votacion) throws Exception  {
		if (muestra.getParticipante().equals(votacion.getParticipante())) {
			throw new ErrorParticipanteNoPuedeVotarMuestraPublicadaPorSiMismo();
			}
	}*/

}
