package tpVinchuca;

import java.time.LocalDate;

public class Votacion {
	private Participante participante;
	private ClasificacionDeFoto opinion;
	private LocalDate fecha;
	private String nivelConocimientoParticipante;
	
	
	public Votacion(Participante participante, ClasificacionDeFoto opinion) {
		this.participante = participante;
		this.opinion = opinion;
		this.fecha = LocalDate.now();
		this.nivelConocimientoParticipante = participante.getNivelDeConocimiento();
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
	 * Retorna el nivel de conocimiento del participante al momento de creacion de la muestra
	 */
	public String getNivelDeConocimientoParticipante() {
		return this.nivelConocimientoParticipante;
	}

}
