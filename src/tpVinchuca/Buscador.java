package tpVinchuca;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class Buscador {
	
	public List<Votacion> getVotacionesDeParticipantePorFecha(List<Votacion> votaciones, Participante participante, LocalDate fecha){
		return votaciones.stream()
				.filter(votacion-> this.esVotacionDe(votacion, participante) && this.esVotacionEnFecha(votacion, fecha))
				.collect(Collectors.toList());
	}
	
	private boolean esVotacionDe(Votacion votacion, Participante participante) {
		return votacion.getParticipante().equals(participante);
	}
	
	private boolean esVotacionEnFecha(Votacion votacion, LocalDate fecha) {
		return votacion.getFechaDeCreacion().getYear()==fecha.getYear() && votacion.getFechaDeCreacion().getMonth().equals(fecha.getMonth());
	}

}
