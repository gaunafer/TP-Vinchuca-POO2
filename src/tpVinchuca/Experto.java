package tpVinchuca;

import java.time.LocalDate;

public class Experto extends NivelDeConocimiento{
	
	/**
	 * Crea una instancia de nivel de conocimiento {@code Experto}
	 * @param aplicacionVinchucas
	 */
	public Experto(AplicacionVinchucas aplicacionVinchucas) {
		super(aplicacionVinchucas);
	}

	/**
	 * Devuelve un String con el nivel de conocimiento, en este caso Nivel Experto.
	 */
	public String getNivelDeConocimiento() {
		return "Nivel Experto";
	}

	/**
	 * Verifica si hubo cambios en el nivel de conocimiento del participante.
	 * En caso de que se cumpla que:
	 * a.{@code participante} tiene menos o igual a 10 muestras creadas en los ultimos 30 dias; y 
	 * b.{@code participante} tiene menos o igual a 20 votaciones realizadas en los ultimos 30 dias,  
	 * en ese caso el nivel de conocimiento del participante pasa a ser {@code Basico}
	 */
	public void verificarEstado(Participante participante) {
		if (this.getCantidadDeMuestrasDeUnParticipanteA30DiasDeLaFechaActual(participante) <= 10
				|| this.getCantidadDeVotacionesDeUnParticipanteA30DiasDeLaFechaActual(participante) <= 20) {
					this.cambiarABasico(participante);
				}
	}
	
	/**
	 * Devuelve true para indicar que el nivel de conocimiento de la persona 
	 * es experto
	 */
	public Boolean esExperto() {
		return true;
	}
		
}
