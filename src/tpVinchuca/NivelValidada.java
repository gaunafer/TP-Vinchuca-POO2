package tpVinchuca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NivelValidada extends NivelDeValidacion {

	@Override
	public String resultadoActual(Muestra muestra) {
		Map<String, Integer> contadorDeOpiniones = crearRankingDeOpiniones(muestra);
		List<String> estadosMasVotados = obtenerOpinionesMasVotadas(contadorDeOpiniones);
		return analizarOpiniones(estadosMasVotados);
	}

	@Override
	public void registrarVotacion(Muestra muestra, Votacion votacion) throws Exception {
		throw new ErrorParticipanteVotaMuestraValidada();
		
	}
	
	private Map<String, Integer> crearRankingDeOpiniones(Muestra muestra) {
		Stream<Votacion> votacionesExpertas;
		votacionesExpertas = muestra.getVotaciones().stream().filter(votacion->votacion.getParticipante().getNivelDeConocimiento() == "Nivel Experto");
		Map<String, Integer> contadorDeOpiniones = new HashMap<>();
		contadorDeOpiniones.put(muestra.getVeredicto(), 1);
		for (Votacion vot : votacionesExpertas.collect(Collectors.toList())) {
			if (contadorDeOpiniones.containsKey(vot.getOpinion())) {
				contadorDeOpiniones.put(vot.getOpinion(), contadorDeOpiniones.get(vot.getOpinion())+1);
			}
			else {
				contadorDeOpiniones.put(vot.getOpinion(),1);
			}
		}
		return contadorDeOpiniones;
	}
	
	private List<String> obtenerOpinionesMasVotadas(Map<String, Integer> contadorDeOpiniones) {
		Integer valorMaximo = Collections.max(contadorDeOpiniones.values());
		List<String> estadosMasVotados = new ArrayList<String>();
		for (String opinion : contadorDeOpiniones.keySet()) {
			if (contadorDeOpiniones.get(opinion).equals(valorMaximo)) {
				estadosMasVotados.add(opinion);
			}
		}
		return estadosMasVotados;
	}
	private String analizarOpiniones(List<String> estadosMasVotados) {
		if (estadosMasVotados.size() > 1 ) {
			return ResultadoDeMuestra.INDEFINIDA.getValor();
		}
		else {
			return estadosMasVotados.get(0);
		}
	}

}
