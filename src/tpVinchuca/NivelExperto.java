package tpVinchuca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NivelExperto extends NivelDeValidacion {
	
	@Override
	public void registrarVotacion(Muestra muestra, Votacion votacion) throws Exception {
		if(votacion.getParticipante().getNivelDeConocimiento() == "Nivel Basico") {
			throw new ErrorParticipanteBasicoVotaMuetraNivelExperto();
		}
		if (crearRankingDeOpiniones(muestra, getVotaciones(muestra)).containsKey(votacion.getOpinion()) ) {
			muestra.addVotacion(votacion);
			muestra.setNivelDeValidacionValidada();
		}
		else {
			muestra.addVotacion(votacion);
		}
		
	}

}
