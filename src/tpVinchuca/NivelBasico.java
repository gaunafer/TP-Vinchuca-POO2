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
	protected  List<Votacion> getVotaciones(Muestra muestra){
		List<Votacion> votaciones;
		votaciones = muestra.getVotaciones();
		return votaciones;
	}

	@Override
	public void registrarVotacion(Muestra muestra, Votacion votacion) throws ErrorParticipanteNoPuedeVotarEstaMuestra  {
		verificarSiElParticipantePuedeVotarLaMuestra(muestra, votacion);
		muestra.addVotacion(votacion);
		if (votacion.getNivelDeConocimientoParticipante() == "Nivel Experto") {
			muestra.setNivelDeValidacionExperto();
		}
	}

	private void verificarSiElParticipantePuedeVotarLaMuestra(Muestra muestra, Votacion votacion)
			throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		if (muestra.getParticipante().equals(votacion.getParticipante()) || muestra.muestraVotadaPor(votacion.getParticipante())) {
			if(muestra.getParticipante().equals(votacion.getParticipante())) {
				throw new ErrorParticipanteNoPuedeVotarEstaMuestra("Error participante no puede votar muestra creada por sí mismo");
			} else {
				throw new ErrorParticipanteNoPuedeVotarEstaMuestra("Error el participante no pueve volver a votar esta muestra");
			}
		}
	}
	@Override
	protected Map<String, Integer> agregarOpinionDeLaMuestraAlRanking(Muestra muestra, Map<String, Integer> contadorDeOpiniones) {
		contadorDeOpiniones.put(muestra.getVeredicto(), 1);
		return contadorDeOpiniones;
	}

	@Override
	protected String getNivelDeValidacion() {
		return "Nivel Basico";
	}


}
