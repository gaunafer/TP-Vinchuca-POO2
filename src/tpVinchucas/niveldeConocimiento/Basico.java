package tpVinchucas.niveldeConocimiento;

import java.time.LocalDate;

import tpVinchuca.AplicacionVinchucas;
import tpVinchuca.Participante;

public class Basico extends NivelDeConocimiento{
	
	/**
	 * Crea una instancia de nivel de conocimiento {@code Basico}.
	 * 
	 * @param aplicacionVinchucas
	 */
	public Basico(AplicacionVinchucas aplicacionVinchucas) {
		super(aplicacionVinchucas);
	}

	/**
	 * Devuelve un String con el nivel de conocimiento, en este caso Nivel Basico.
	 */
	public String getNivelDeConocimiento() {
		return "Nivel Basico";
	}
	
	/**
	 * Verifica si hubo cambios en el nivel de conocimiento del participante.
	 * En caso de que se cumpla que:
	 * a.{@code participante} tiene mas de 10 muestras creadas en los ultimos 30 dias; y 
	 * b.{@code participante} tiene mas de 20 votaciones realizadas en los ultimos 30 dias,  
	 * en ese caso el nivel de conocimiento del participante pasa a ser {@code Experto}
	 */
	public void verificarEstado(Participante participante) {
			if (this.getCantidadDeMuestrasDeUnParticipanteA30DiasDeLaFechaActual(participante) > 10
					&& this.getCantidadDeVotacionesDeUnParticipanteA30DiasDeLaFechaActual(participante) > 20) {
					this.cambiarAExperto(participante);}
		}
	
	/**
	 * Devuelve false para indicar que el nivel de conocimiento de la persona 
	 * no es experto
	 */
	public Boolean esExperto() {
		return false;
	}
}	
	

