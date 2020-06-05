package tpVinchuca;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FiltroVotacionesPorFecha implements Filtro {
	private LocalDate fecha;
	private Participante participante;
	
	public FiltroVotacionesPorFecha(LocalDate fecha, Participante participante) {
		super();
		this.fecha = fecha;
		this.participante = participante;
	}

	@Override
	public List<Muestra> criterioDeBusqueda(List<Muestra> muestras) {
		return muestras;
		//Optional<Votacion> result  = muestras.stream()
		//		.map(muestra -> muestra.getVotaciones()).stream().findAny(votacion->{votacion.getParticipante().equals(this.participante);}));  
						//anyMatch(votacion->votacion.getParticipante().equals(this.participante) && votacion. ))
				      //.collect(Collectors.toList());
	}
	
	

}
