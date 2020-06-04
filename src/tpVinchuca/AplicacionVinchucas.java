package tpVinchuca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AplicacionVinchucas {
	
	private Buscador buscador;

	private List<Muestra> muestras;
	
	

	public AplicacionVinchucas(Buscador buscador) {
		super();
		this.buscador = buscador;
		this.muestras = new ArrayList<Muestra>();
	}


    public List <Votacion> getVotaciones(){
    	 List < List<Votacion> > votaciones = this.muestras.stream()
    			 .map(muestra->muestra.getVotaciones())
    			 .collect(Collectors.toList());
    	 return votaciones.
    }
	public List<Votacion> getVotacionesDeParticipantePorFecha(Participante participante, LocalDate fecha) {
		return this.buscador.getVotacionesDeParticipantePorFecha( votaciones, participante, fecha);
		
	}
	
	public List<Votacion> votacionesPorParticipantePorFecha(Participante participante, LocalDate fecha){
		Filtro filtroFecha = new FiltroFecha(fecha);
		Filtro filtroParticipante = new FiltroVotacionesPorParticipante(participante);
		Filtro and = new FiltroAnd(filtroParticipante,filtroFecha);
		List < List<Votacion> > votaciones = this.buscador.buscar(muestras, and).stream().map(muestra->muestra.getVotaciones()).collect(Collectors.toList());
		return ;
		
	}


}
