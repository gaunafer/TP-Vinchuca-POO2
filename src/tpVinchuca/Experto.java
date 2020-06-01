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
		if (this.getCantidadDeVotacionesDeUnParticipanteEnEsteMes(participante) < 10l) {
					this.cambiarABasico(participante);
				}
	}
		
	}

	



