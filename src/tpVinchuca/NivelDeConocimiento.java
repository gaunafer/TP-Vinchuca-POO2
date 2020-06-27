package tpVinchuca;

import java.time.LocalDate;
import java.util.List;

public abstract class NivelDeConocimiento {
	
	protected AplicacionVinchucas aplicacionVinchucas;
	
	/**
	 * Constructor para la clase nivel de conocimiento (instanciada en subclases 
	 * al ser clase abstracta).
	 * 
	 * @param aplicacionVinchucas
	 */
	public NivelDeConocimiento(AplicacionVinchucas aplicacionVinchucas) {
		this.aplicacionVinchucas = aplicacionVinchucas;
	}
	
	/**
	 * Metodo definido en las subclases.
	 * @return el correspondiente nivel de conocimiento.
	 */
	protected abstract String getNivelDeConocimiento();
	
	/**
	 * Metodo definido en las subclases, verifica que el nivel de conocimiento
	 * del participante no haya cambiado.
	 * @param participante participante del cual se verifica su nivel de conocimiento
	 */
	protected abstract void verificarEstado(Participante participante);
	
	/**
	 * Metodo definido en las subclases.
	 * @return devuelve true para nivel de conocimiento experto y false para 
	 * nivel de conocimiento basico
	 */
	protected abstract Boolean esExperto();

	protected abstract void actualizarNivelValidacionMuestra(Muestra muestra);
	
	/**
	 * Cambia el nivel de conocimiento del {@code participante} a Basico
	 * @param participante participante del cual se cambia su nivel de conocimiento
	 */
	public void cambiarABasico(Participante participante) {
		participante.setNivelDeConocimiento(new Basico(aplicacionVinchucas));
	}

	/**
	 * Cambia el nivel de conocimiento del {@code participante} a Experto
	 * @param participante participante del cual se cambia su nivel de conocimiento
	 */
	public void cambiarAExperto(Participante participante) {
		NivelDeConocimiento experto = new Experto(aplicacionVinchucas);
		participante.setNivelDeConocimiento(experto);
	}
	
	/**
	 * Calcula la cantidad de muestras creadas por {@code participante} en los 
	 * ultimos 30 dias
	 *  
	 * @param participante del/de la cual se cuentan las muestras
	 * @return el total de muestras creadas por participante en los ultimos 30 dias
	 */
	public Integer getCantidadDeMuestrasDeUnParticipanteA30DiasDeLaFechaActual(Participante participante) {
		LocalDate unMesAtras = LocalDate.now().minusMonths(1l);
		
		return this.aplicacionVinchucas.getMuestrasDeParticipantePorFecha(participante, unMesAtras).size();
	}
	

	/**
	 * Calcula la cantidad de votaciones realizadas por {@code participante} en los 
	 * ultimos 30 dias
	 *  
	 * @param participante del/de la cual se cuentan las votaciones
	 * @return el total de votaciones realizadas por participante en los ultimos 30 dias
	 */
	public Integer getCantidadDeVotacionesDeUnParticipanteA30DiasDeLaFechaActual(Participante participante) {
		return this.aplicacionVinchucas.getVotacionesDeParticipanteDeLosUltimos30Dias(participante).size();
	}	

}
