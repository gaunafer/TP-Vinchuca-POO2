package tpVinchuca;


import java.util.List;
import java.util.Map;


public class NivelBasico extends NivelDeValidacion {

	/**
	 *Verifica si el participante puede votar la muestra y en caso afirmativo registra el voto
	 * @throws ErrorParticipanteNoPuedeVotarEstaMuestra
	 */
	@Override
	public void registrarVotacion(Muestra muestra, Votacion votacion) throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		verificarSiElParticipantePuedeVotarLaMuestra(muestra, votacion);
		muestra.addVotacion(votacion);
		votacion.getNivelConocimientoParticipanteAlVotar().actualizarNivelValidacionMuestra(muestra);
	}

	/**
	 * Verifica si el participante puede votar la muestra. levanta excepcion si no puede votar
	 * @throws ErrorParticipanteNoPuedeVotarEstaMuestra
	 */
	private void verificarSiElParticipantePuedeVotarLaMuestra(Muestra muestra, Votacion votacion)
			throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		if (muestra.getParticipante().equals(votacion.getParticipante())
				|| muestra.muestraVotadaPor(votacion.getParticipante())) {
			if (muestra.getParticipante().equals(votacion.getParticipante())) {
				throw new ErrorParticipanteNoPuedeVotarEstaMuestra(
						"Error participante no puede votar muestra creada por si mismo");
			} else {
				throw new ErrorParticipanteNoPuedeVotarEstaMuestra(
						"Error el participante no pueve volver a votar esta muestra");
			}
		}
	}

	/**
	 * Retorna la lista de votaciones de la muestra
	 */
	@Override
	protected  List<Votacion> getVotaciones(Muestra muestra) {
	
		return muestra.getVotaciones();
	}

	/**
	 *Agrega la opinion de la muestra al ranking de votaciones de la muestra
	 */
	@Override
	protected Map<String, Integer> agregarOpinionDeLaMuestraAlRanking(Muestra muestra,
			Map<String, Integer> contadorDeOpiniones) {
		contadorDeOpiniones.put(muestra.getVeredicto(), 1);
		return contadorDeOpiniones;
	}

	/**
	 * Retorna el nivel de validacion de la muestra
	 */
	@Override
	protected String getNivelDeValidacion() {
		return "Nivel Basico";
	}

	public Boolean estaValidada() {
		return false;
	}
	
}
