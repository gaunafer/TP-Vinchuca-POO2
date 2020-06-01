package tpVinchuca;

import java.time.LocalDate;

public abstract class NivelDeConocimiento {
	
	protected AplicacionVinchucas aplicacionVinchucas;
	
	public NivelDeConocimiento(AplicacionVinchucas aplicacionVinchucas) {
		super();
		this.aplicacionVinchucas = aplicacionVinchucas;
	}
	protected abstract String getNivelDeConocimiento();
	protected abstract void verificarEstado(Participante participante);

	public void cambiarABasico(Participante participante) {
		
		participante.setNivelDeConocimiento(new Basico(aplicacionVinchucas));
		
	}

	public void cambiarAExperto(Participante participante) {
		NivelDeConocimiento experto = new Experto(aplicacionVinchucas);
		participante.setNivelDeConocimiento(experto);
		
	}
	
	public long getCantidadDeVotacionesDeUnParticipanteEnEsteMes(Participante participante) {
		return aplicacionVinchucas.getVotacionesDeParticipantePorFecha(participante, LocalDate.now()).stream()
				.filter(votacion->votacion.getFechaDeCreacion().getMonth() == LocalDate.now().getMonth()).count();
	}

}
