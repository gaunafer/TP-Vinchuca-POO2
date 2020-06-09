package tpVinchuca;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FiltroFechaDeCreacionDesde implements Filtro{
	
	private LocalDate fecha;
	
    public FiltroFechaDeCreacionDesde(LocalDate fecha) {
		super();
		this.fecha = fecha;
	}

	public List<Muestra> criterioDeBusqueda(List<Muestra> muestras){
		
		return muestras.stream()
				.filter(muestra->muestra.getFecha().isAfter(this.fecha))
				.collect(Collectors.toList());
	}
	


}
