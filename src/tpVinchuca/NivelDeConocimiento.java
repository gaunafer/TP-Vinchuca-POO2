package tpVinchuca;


import java.time.LocalDate;
import java.util.List;

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
	
	public Integer getCantidadDeMuestrasDeUnParticipanteA30DiasDeLaFechaActual(Participante participante) {
		LocalDate unMesAtras = LocalDate.now().minusMonths(1l);
		
		return this.aplicacionVinchucas.getMuestrasDeParticipantePorFecha(participante, unMesAtras).size();
	}
	

	public Integer getCantidadDeVotacionesDeUnParticipanteA30DiasDeLaFechaActual(Participante participante) {
		
		return this.aplicacionVinchucas.getVotacionDeParticipantePorfecha(participante).size();
	}	

}
