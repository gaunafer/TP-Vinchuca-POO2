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
	protected List<Votacion> getVotaciones(Muestra muestra) {
		Stream<Votacion> votacionesExpertas;
		votacionesExpertas = muestra.getVotaciones().stream()
				.filter(votacion -> votacion.getNivelDeConocimientoParticipante() == "Nivel Experto");
		return votacionesExpertas.collect(Collectors.toList());
	}

	@Override
	public void registrarVotacion(Muestra muestra, Votacion votacion) throws  ErrorParticipanteNoPuedeVotarEstaMuestra {
		verificarSiElParticipantePuedeVotarLaMuestra(muestra, votacion);
		if (crearRankingDeOpiniones(muestra, getVotaciones(muestra)).containsKey(votacion.getOpinion())) {
			muestra.addVotacion(votacion);
			muestra.setNivelDeValidacionValidada();
			muestra.informarVerificacion();
		} else {
			muestra.addVotacion(votacion);
		}
	}

	private void verificarSiElParticipantePuedeVotarLaMuestra(Muestra muestra, Votacion votacion)
			throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		if (votacion.getParticipante().getNivelDeConocimiento().equals("Nivel Basico")  ||
				muestra.muestraVotadaPor(votacion.getParticipante()) ||
				muestra.getParticipante().equals(votacion.getParticipante())) {
			if(votacion.getParticipante().getNivelDeConocimiento() == "Nivel Basico") {
			throw new ErrorParticipanteNoPuedeVotarEstaMuestra("Error participante Nivel Basico no puede votar muestra nivel experto");
			}
			else if (muestra.muestraVotadaPor(votacion.getParticipante())) {
				throw new ErrorParticipanteNoPuedeVotarEstaMuestra("Error Participante no puede volver a votar esta muestra");
			}
			else {
				throw new ErrorParticipanteNoPuedeVotarEstaMuestra("Error Participante no puede votar muestra creada por sí mismo");
			}
		}
	}
	@Override
	protected Map<String, Integer> agregarOpinionDeLaMuestraAlRanking(Muestra muestra,
			Map<String, Integer> contadorDeOpiniones) {
		if (muestra.getVeredicto().equalsIgnoreCase("Nivel Experto")) {
			contadorDeOpiniones.put(muestra.getVeredicto(), 1);
		}
		return contadorDeOpiniones;
	}

	@Override
	protected String getNivelDeValidacion() {
		return "Nivel Experto";
	}

}
