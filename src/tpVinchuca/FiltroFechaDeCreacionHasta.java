package tpVinchuca;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FiltroFechaDeCreacionHasta implements Filtro {

	private LocalDate fecha;
	
    public FiltroFechaDeCreacionHasta(LocalDate fecha) {
		super();
		this.fecha = fecha;
	}

	public List<Muestra> criterioDeBusqueda(List<Muestra> muestras){
		
		return muestras.stream()
				.filter(muestra->muestra.getFecha().isBefore(this.fecha))
				.collect(Collectors.toList());
	}
}
