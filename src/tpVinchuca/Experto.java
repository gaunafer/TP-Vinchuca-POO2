package tpVinchuca;

import java.time.LocalDate;

public class Experto extends NivelDeConocimiento{
	
	public Experto(AplicacionVinchucas aplicacionVinchucas) {
		super(aplicacionVinchucas);
	}


	public String getNivelDeConocimiento() {
		return "Nivel Experto";
	}

	
	public void verificarEstado(Participante participante) {
		if (this.getCantidadDeMuestrasDeUnParticipantePertenecientesAlMesAnteriorAlMesEnCurso(participante) < 10
				|| this.getCantidadDeVotacionesDeUnParticipanteA30DiasDeLaFechaActual(participante)<20) {
					this.cambiarABasico(participante);
				}
	}


		
	}

	



