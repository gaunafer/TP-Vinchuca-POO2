package tpVinchuca;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FiltroFecha implements Filtro{
	
	private LocalDate fecha;
	
    public FiltroFecha(LocalDate fecha) {
		super();
		this.fecha = fecha;
	}

	public List<Muestra> criterioDeBusqueda(List<Muestra> muestras){
		
		return muestras.stream()
				.filter(muestra->muestra.getFecha().equals(this.fecha))
				.collect(Collectors.toList());
	}

}
