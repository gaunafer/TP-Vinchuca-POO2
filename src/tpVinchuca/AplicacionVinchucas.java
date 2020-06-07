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
	
	public Buscador getBuscador() {
		return buscador;
	}

	public List<Muestra> getMuestras() {
		return muestras;
	}
	
	public void añadirMuestra(Muestra muestra) {
		muestras.add(muestra);
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
	
	public List<Votacion> getVotacionDeParticipantePorfecha(Participante participante){
		return this.buscador.getVotacionesDeParticipanteEnLosUltimos30Dias(this.getVotaciones(),participante);
	}

}
