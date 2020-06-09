package tpVinchuca;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FiltroFechaDesde implements Filtro{
	
	private LocalDate fecha;
	
    public FiltroFechaDesde(LocalDate fecha) {
		super();
		this.fecha = fecha;
	}

	public List<Muestra> criterioDeBusqueda(List<Muestra> muestras){
		
		return muestras.stream()
				.filter(muestra->muestra.getFecha().isAfter(this.fecha))
				.collect(Collectors.toList());
	}
	


}
