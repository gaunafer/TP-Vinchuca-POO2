package tpVinchuca;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FiltroUltimaFechaDeVotacion implements Filtro {
	
	private LocalDate fecha;
	

	public FiltroUltimaFechaDeVotacion(LocalDate fecha) {
		super();
		this.fecha = fecha;
	}


	public List<Muestra> criterioDeBusqueda(List<Muestra> muestras){
		return muestras.stream().filter(muestra -> muestra.esMuestraVotada())
				.filter(muestra->muestra.getFechaUltimaVotacion().isAfter(this.fecha))
				.collect(Collectors.toList());
	}

}
