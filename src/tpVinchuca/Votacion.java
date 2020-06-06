package tpVinchuca;

import java.time.LocalDate;

public class Votacion {
	private Participante participante;
	private ResultadoDeMuestra opinion;
	private LocalDate fecha;
	
	
	public Votacion(Participante participante, ResultadoDeMuestra opinion) {
		this.participante = participante;
		this.opinion = opinion;
		this.fecha = LocalDate.now();
	}
	public String getOpinion() {
		return opinion.getValor();
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public Participante getParticipante() {
				return participante;
	}

}
