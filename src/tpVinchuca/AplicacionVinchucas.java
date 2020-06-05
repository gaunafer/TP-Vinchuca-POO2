package tpVinchuca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AplicacionVinchucas {
	
	private Buscador buscador;
    private List<Muestra> muestras;
	

	public AplicacionVinchucas(Buscador buscador) {
		super();
		this.buscador = buscador;
		this.muestras = new ArrayList<Muestra>();
	}
	
	public List<Muestra> getMuestrasDeParticipantePorFecha(Participante participante, LocalDate fecha) {
		Filtro filtroFecha =  new FiltroFecha(fecha);
		Filtro filtroParticipante = new FiltroParticipante(participante);
	    Filtro and = new FiltroAnd(filtroParticipante, filtroFecha);
		return this.buscador.buscar(muestras, and);
	}
	
	public List<Votacion> getVotaciones(){
		
		return muestras.stream().flatMap(muestra -> muestra.getVotaciones().stream()).collect(Collectors.toList());
		
	}
	
	public List<Votacion> getVotacionesPorParticipante(Participante participante){
		return this.getVotaciones().stream().filter(votacion-> votacion.getParticipante().equals(participante)).collect(Collectors.toList());
	}
	
	public List<Votacion> getVotacionesPorFeacha(List<Votacion> votaciones, LocalDate fecha){
		return votaciones.stream().filter(votacion->votacion.getFechaDeCreacion().isAfter(fecha)).collect(Collectors.toList());
	}
	
	public List<Votacion> getVotacionesDeParticipanteEnLosUltimos30Dias(Participante participante){
		return this.getVotacionesPorFeacha(this.getVotacionesPorParticipante(participante), LocalDate.now().minusDays(30l));
	}


}
