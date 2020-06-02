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
			if (this.getCantidadDeVotacionesDeUnParticipantePertenecientesAlMesAnteriorAlMesEnCurso(participante) > 10l) {
					this.cambiarAExperto(participante);}
		}
}	
	

