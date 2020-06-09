package tpVinchuca;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroNivelValidacionMuestra implements Filtro{
	
	private String nivelDeValidacion;

	@Override
    public List<Muestra> criterioDeBusqueda(List<Muestra> muestras){
		
		return muestras.stream()
				.filter(muestra->muestra.getNivelDeValidacion().equals(nivelDeValidacion))
				.collect(Collectors.toList());
	}
}
