package tpVinchuca;

import java.time.LocalDate;

import tpVinchucas.niveldeConocimiento.*;

public class Votacion {
	private Participante participante;
	private ClasificacionDeFoto opinion;
	private LocalDate fecha;
	private NivelDeConocimiento nivelDeConocimientoParticipanteAlVotar;
	
	
	public Votacion(Participante participante, ClasificacionDeFoto opinion) {
		this.participante = participante;
		this.opinion = opinion;
		this.fecha = LocalDate.now();
		this.nivelDeConocimientoParticipanteAlVotar = participante.getNivelDeConocimiento();
	}
	
	/**
	 * Retorna la opinion de la votacion
	 */
	public String getOpinion() {
		return this.opinion.getValor();
	}
	
	/**
	 * Retorna la fecha de creacion de la votacion
	 */
	public LocalDate getFecha() {
		return this.fecha;
	}
	
	/**
	 * Retorna el participante que vota
	 */
	public Participante getParticipante() {
		return this.participante;
	}
	
	public NivelDeConocimiento getNivelDeConocimientoParticipanteAlVotar() {
		return this.nivelDeConocimientoParticipanteAlVotar;
	}
	
	/**
	 * Devuelve true si participante es experto al momento de realizar la votacion. 
	 * Devuelve false en caso de que participante tenga nivel de conocimiento basico 
	 * al momento de la votacion.
	 */
	public Boolean participanteEsExpertoAlMomentoDeVotar() {
		return this.nivelDeConocimientoParticipanteAlVotar.esExperto();
	}

}
