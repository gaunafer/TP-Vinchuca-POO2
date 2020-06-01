package tpVinchuca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AplicacionVinchucas {
	
	private Buscador buscador;
	private List<Votacion> votaciones;
	
	

	public AplicacionVinchucas(Buscador buscador) {
		super();
		this.buscador = buscador;
		this.votaciones = new ArrayList<Votacion>();
	}



	public List<Votacion> getVotacionesDeParticipantePorFecha(Participante participante, LocalDate fecha) {
		return this.buscador.getVotacionesDeParticipantePorFecha( votaciones, participante, fecha);
		
	}
	
	public void archivarVotacion(Votacion votacion) {
		votaciones.add(votacion);
		
	}

}
