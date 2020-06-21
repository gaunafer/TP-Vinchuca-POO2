package tpVinchuca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class NivelDeValidacion {
	
	public abstract void registrarVotacion(Muestra muestra, Votacion votacion) throws ErrorParticipanteNoPuedeVotarEstaMuestra;
	/**
	 * retorna el resultado actual de la muestra
	 * */
	public  String resultadoActual(Muestra muestra) {
		List<Votacion> votaciones = getVotaciones(muestra);
		Map<String, Integer> contadorDeOpiniones = crearRankingDeOpiniones(muestra, votaciones);
		contadorDeOpiniones = agregarOpinionDeLaMuestraAlRanking(muestra, contadorDeOpiniones);
		List<String> estadosMasVotados = obtenerOpinionesMasVotadas(contadorDeOpiniones);
		return analizarOpiniones(estadosMasVotados);
	}
	protected abstract Map<String, Integer> agregarOpinionDeLaMuestraAlRanking(Muestra muestra, Map<String, Integer> contadorDeOpiniones);
	/**
	 * Obtiene las votaciones que seran tenidas en cuenta para analizar el resultado actual de la muestra.
	 * */
	protected abstract List<Votacion> getVotaciones(Muestra muestra);
	
	/**
	 * Crea un ranking de las opiniones recibidas con la cantidad de veces que se voto cada opcion
	 * @Return 
	 * */	
	protected Map<String, Integer> crearRankingDeOpiniones(Muestra muestra, List<Votacion> votaciones) {
		Map<String, Integer> contadorDeOpiniones = new HashMap<>();
		for (Votacion vot : votaciones) {
			if (contadorDeOpiniones.containsKey(vot.getOpinion())) {
				contadorDeOpiniones.put(vot.getOpinion(), contadorDeOpiniones.get(vot.getOpinion())+1);
			}
			else {
				contadorDeOpiniones.put(vot.getOpinion(),1);
			}
		}
		return contadorDeOpiniones;
	}
	/**
	 * Genera una lista con la opinion mas votadas, si hubiera empate incluye en la lista todas las opiniones 
	 * que estan empatadas en primer lugar en el ranking de opiniones.
	 * */
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
	/**
	 * Analiza las opiniones mas votadas y retorna el estado actual de la muestra. Siendo indefinida si 
	 * hay m�s de una opinion en la lista o la opinion en caso de que exista solo una.
	 * */
	private String analizarOpiniones(List<String> estadosMasVotados) {
		if (estadosMasVotados.size() > 1 ) {
			return ClasificacionDeFoto.INDEFINIDA.getValor();
		}
		else {
			return estadosMasVotados.get(0);
		}
	}
	protected abstract String getNivelDeValidacion();
	
}
