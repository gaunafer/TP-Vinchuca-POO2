package tpVinchuca;

import java.time.LocalDate;

public class Basico extends NivelDeConocimiento{
	
	public Basico(AplicacionVinchucas aplicacionVinchucas) {
		super(aplicacionVinchucas);
		
	}

	public String getNivelDeConocimiento() {
		return "Nivel Basico";
	}
	
	public void verificarEstado(Participante participante) {
			if (this.getCantidadDeMuestrasDeUnParticipantePertenecientesAlMesAnteriorAlMesEnCurso(participante) > 10
					&& this.getCantidadDeVotacionesDeUnParticipanteA30DiasDeLaFechaActual(participante) > 20) {
					this.cambiarAExperto(participante);}
		}
}	
	

