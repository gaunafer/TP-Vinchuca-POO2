package tpVinchuca;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Buscador {
	
	
	public List<Muestra> buscar(List<Muestra> muestras, Filtro filtro){
		return filtro.criterioDeBusqueda(muestras);
	}
	
    private List<Votacion> getVotacionesPorParticipante(List<Votacion> votaciones, Participante participante){
		return votaciones.stream().filter(votacion-> votacion.getParticipante().equals(participante)).collect(Collectors.toList());
	}
	
	private List<Votacion> getVotacionesPorFecha(List<Votacion> votaciones, LocalDate fecha){
		return votaciones.stream().filter(votacion->votacion.getFecha().isAfter(fecha)).collect(Collectors.toList());
	}
	
	public List<Votacion> getVotacionesDeParticipanteEnLosUltimos30Dias(List<Votacion> votaciones, Participante participante){
		return this.getVotacionesPorFecha(this.getVotacionesPorParticipante(votaciones, participante), LocalDate.now().minusDays(30l));
	}

}
