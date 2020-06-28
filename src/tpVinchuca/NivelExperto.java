package tpVinchuca;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NivelExperto extends NivelDeValidacion {

	
	/**
	 *Verifica si el participante puede votar la muestra y en caso afirmativo registra el voto
	 * @throws ErrorParticipanteNoPuedeVotarEstaMuestra
	 */
	@Override
	public void registrarVotacion(Muestra muestra, Votacion votacion) throws  ErrorParticipanteNoPuedeVotarEstaMuestra {
		verificarSiElParticipantePuedeVotarLaMuestra(muestra, votacion);
		muestra.addVotacion(votacion);
		votacion.getNivelConocimientoParticipanteAlVotar().actualizarNivelValidacionMuestra(muestra);
		/*if (crearRankingDeOpiniones(muestra, getVotaciones(muestra)).containsKey(votacion.getOpinion())) {
			muestra.addVotacion(votacion);
			muestra.setNivelDeValidacionValidada();
			muestra.informarVerificacion();
		} else {
			muestra.addVotacion(votacion);
		}*/
	}/*
		 * 
		 * MUESTRA actualizarNivelValidacionMuestra(NivelDeConocimiento nivel){
		 * this.nivelDeValidacion.actualizarblablabla(nivel, this) }
		 * 
		 * NIVEL DE VALIDACION actualizarblablabla(nivel, muestra)
		 * 
		 * Integer cantVotaciones = muestra.getVotaciones().size(); Votacion votacion =
		 * muestra.getVotaciones().get(cantVotaciones - 1);
		 * 
		 * Map<String, Integer> rankingOpiniones =
		 * muestra.getNivelDeValidacionNoString().crearRankingDeOpiniones(muestra,
		 * muestra.getVotacionesExpertas());
		 * 
		 * if (rankingOpiniones.get(votacion.getOpinion()).equals(2)) {
		 * muestra.setNivelDeValidacion(new NivelValidada());
		 * muestra.informarVerificacion(); } else { muestra.setNivelDeValidacion(new
		 * NivelExperto()); }
		 */
	
	/**
	 * Verifica si el participante puede votar la muestra. levanta excepcion si no puede votar
	 * @throws ErrorParticipanteNoPuedeVotarEstaMuestra
	 */
	private void verificarSiElParticipantePuedeVotarLaMuestra(Muestra muestra, Votacion votacion) 
			throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		//if (votacion.getParticipante().getNivelDeConocimiento().equals("Nivel Basico")  ||
			//	muestra.muestraVotadaPor(votacion.getParticipante()) ||
				// muestra.getParticipante().equals(votacion.getParticipante())) {
			if(!votacion.participanteEsExpertoAlMomentoDeVotar()) {
			throw new ErrorParticipanteNoPuedeVotarEstaMuestra("Error participante Nivel Basico no puede votar muestra nivel experto");
			}
			else if (muestra.muestraVotadaPor(votacion.getParticipante())) {
				throw new ErrorParticipanteNoPuedeVotarEstaMuestra("Error Participante no puede volver a votar esta muestra");
			}
			else if (muestra.getParticipante().equals(votacion.getParticipante())) {
				throw new ErrorParticipanteNoPuedeVotarEstaMuestra("Error Participante no puede votar muestra creada por s� mismo");
			}
		//}
	}
	
	private void verificarSiPuedeVotarExperto() {
		
	}
	/**
	 * Retorna la lista de votaciones de expertos de la muestra
	 */
	@Override
	protected List<Votacion> getVotaciones(Muestra muestra) {
		return muestra.getVotacionesExpertas();
	}
	
	/**
	 *Agrega la opinion de la muestra al ranking de votaciones de la muestra si la muestra fue creada por un experto
	 */
	@Override
	protected Map<String, Integer> agregarOpinionDeLaMuestraAlRanking(Muestra muestra,
			Map<String, Integer> contadorDeOpiniones) {
		if (muestra.getNivelDeConocimientoDeCreacion().esExperto()) {
			contadorDeOpiniones.put(muestra.getVeredicto(), 1);
		}
		return contadorDeOpiniones;
	}

	/**
	 * Retorna el nivel de validacion de la muestra
	 */
	@Override
	protected String getNivelDeValidacion() {
		return "Nivel Experto";
	}
	
	public Boolean estaValidada() {
		return false;
	}

}
