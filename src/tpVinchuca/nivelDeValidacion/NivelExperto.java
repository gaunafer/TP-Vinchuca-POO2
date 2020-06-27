package tpVinchuca.nivelDeValidacion;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import tpVinchuca.Muestra;
import tpVinchuca.Votacion;
import tpVinchucas.error.ErrorParticipanteNoPuedeVotarEstaMuestra;

public class NivelExperto extends NivelDeValidacion {

	
	/**
	 *Verifica si el participante puede votar la muestra y en caso afirmativo registra el voto
	 * @throws ErrorParticipanteNoPuedeVotarEstaMuestra
	 */
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
	/**
	 * Verifica si el participante puede votar la muestra. levanta excepcion si no puede votar
	 * @throws ErrorParticipanteNoPuedeVotarEstaMuestra
	 */
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
				throw new ErrorParticipanteNoPuedeVotarEstaMuestra("Error Participante no puede votar muestra creada por s� mismo");
			}
		}
	}
	
	private void verificarSiPuedeVotarExperto() {
		
	}
	/**
	 * Retorna la lista de votaciones de expertos de la muestra
	 */
	@Override
	public List<Votacion> getVotaciones(Muestra muestra) {
		Stream<Votacion> votacionesExpertas = muestra.getVotaciones().stream()
				.filter(votacion -> votacion.participanteEsExpertoAlMomentoDeVotar());
		return votacionesExpertas.collect(Collectors.toList());
	}
	
	/**
	 *Agrega la opinion de la muestra al ranking de votaciones de la muestra si la muestra fue creada por un experto
	 */
	@Override
	protected Map<String, Integer> agregarOpinionDeLaMuestraAlRanking(Muestra muestra,
			Map<String, Integer> contadorDeOpiniones) {
		if (muestra.getNivelDeConocimientoDeCreacion().equalsIgnoreCase("Nivel Experto")) {
			contadorDeOpiniones.put(muestra.getVeredicto(), 1);
		}
		return contadorDeOpiniones;
	}

	/**
	 * Retorna el nivel de validacion de la muestra
	 */
	@Override
	public String getNivelDeValidacion() {
		return "Nivel Experto";
	}

}
