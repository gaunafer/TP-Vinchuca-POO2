package tpVinchuca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NivelBasico extends NivelDeValidacion {

	public NivelBasico() {
		super();
	}

	@Override
	public String resultadoActual(Muestra muestra) {
		Map<String, Integer> contadorDeOpiniones = crearRankingDeOpiniones(muestra);
		List<String> estadosMasVotados = obtenerOpinionesMasVotadas(contadorDeOpiniones);
		return analizarOpiniones(estadosMasVotados);
	}

	private String analizarOpiniones(List<String> estadosMasVotados) {
		if (estadosMasVotados.size() > 1) {
			return ResultadoDeMuestra.INDEFINIDA.getValor();
		} 
		else {
			return estadosMasVotados.get(0);
		}
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

	private Map<String, Integer> crearRankingDeOpiniones(Muestra muestra) {
		Map<String, Integer> contadorDeOpiniones = new HashMap<>();
		contadorDeOpiniones.put(muestra.getVeredicto(), 1);
		for (Votacion vot : muestra.getVotaciones()) {
			if (contadorDeOpiniones.containsKey(vot.getOpinion())) {
				contadorDeOpiniones.put(vot.getOpinion(), contadorDeOpiniones.get(vot.getOpinion()) + 1);
			} else {
				contadorDeOpiniones.put(vot.getOpinion(), 1);
			}
		}
		return contadorDeOpiniones;
	}

	@Override
	public void registrarVotacion(Muestra muestra, Votacion votacion) {
		muestra.addVotacion(votacion);
		if (votacion.getParticipante().getNivelDeConocimiento() == "Nivel Experto") {
			muestra.setNivelDeValidacionExperto();
		}
	}

}
