package tpVinchuca;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Buscador {
	
	/**
	 * Busca en la lista de muestras aquellas que cumplan con el criterio 
	 * de busqueda del filtro pasado como parametro.
	 * 
	 * @param muestras lista muestras sobre la cual se realiza la busqueda
	 * @param filtro filtro que establece el criterio de busqueda
	 * @return una lista de muestras que cumplen con el criterio de busqueda del filtro
	 */
	public List<Muestra> buscar(List<Muestra> muestras, Filtro filtro){
		return filtro.criterioDeBusqueda(muestras);
	}
	
	/**
	 * Metodo privado. Dada una lista de votaciones devuelve aquellas realizadas por 
	 * {@code participante}
	 * 
	 * @param votaciones Lista de todas las votaciones realizadas
	 * @param participante Participante del cual se quieren obtener las votaciones
	 * @return una lista de las votaciones hechas por participante
	 */
    private List<Votacion> getVotacionesPorParticipante(List<Votacion> votaciones, Participante participante){
		return votaciones.stream().filter(votacion-> votacion.getParticipante().equals(participante)).collect(Collectors.toList());
	}

	/**
	 * Metodo privado. Dada una lista de votaciones devuelve aquellas realizadas 
	 * despues de la fecha indicada.
	 * 
	 * @param votaciones Lista de todas las votaciones realizadas
	 * @param fecha Fecha a partir de la cual deben haber sido realizadas las votaciones
	 * @return una lista de las votaciones hechas despues de la fecha indicada
	 */
	private List<Votacion> getVotacionesPorFecha(List<Votacion> votaciones, LocalDate fecha){
		return votaciones.stream().filter(votacion->votacion.getFecha().isAfter(fecha)).collect(Collectors.toList());
	}
	
	/**
	 * Devuelve una lista con las votaciones hechas por {@code participante} en los 
	 * ultimos 30 dias.
	 * 
	 * @param votaciones Una lista de votaciones
	 * @param participante Participante del cual se quieren obtener las votaciones
	 * @return una lista con las votaciones hechas por {@code participante} en los 
	 * ultimos 30 dias
	 */
	public List<Votacion> getVotacionesDeParticipanteEnLosUltimos30Dias(List<Votacion> votaciones, Participante participante){
		return this.getVotacionesPorFecha(this.getVotacionesPorParticipante(votaciones, participante), LocalDate.now().minusDays(30l));
	}

}
