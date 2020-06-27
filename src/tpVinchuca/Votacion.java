package tpVinchuca;

import java.time.LocalDate;

public class Votacion {
	private Participante participante;
	private ClasificacionDeFoto opinion;
	private LocalDate fecha;
	private Boolean participanteEsExperto;
	private NivelDeConocimiento nivelConocimientoParticipanteAlVotar;
	
	
	public Votacion(Participante participante, ClasificacionDeFoto opinion) {
		this.participante = participante;
		this.opinion = opinion;
		this.fecha = LocalDate.now();
		this.participanteEsExperto = participante.esExperto();
		this.nivelConocimientoParticipanteAlVotar = participante.getNivelDeConocimiento();
	}
	
	/**
	 * Retorna la opinion de la votacion
	 */
	public String getOpinion() {
		return opinion.getValor();
	}
	
	/**
	 * Retorna la fecha de creacion de la votacion
	 */
	public LocalDate getFecha() {
		return fecha;
	}
	
	/**
	 * Retorna el participante que vota
	 */
	public Participante getParticipante() {
				return participante;
	}
	
	/**
	 * Devuelve true si participante es experto al momento de realizar la votacion. 
	 * Devuelve false en caso de que participante tenga nivel de conocimiento basico 
	 * al momento de la votacion.
	 */
	public Boolean participanteEsExpertoAlMomentoDeVotar() {
		return this.participanteEsExperto;
	}
	
	public NivelDeConocimiento getNivelConocimientoParticipanteAlVotar() {
		return this.nivelConocimientoParticipanteAlVotar;
	}

}
