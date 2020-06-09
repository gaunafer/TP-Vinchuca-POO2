package tpVinchuca;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroMuestraValidada implements Filtro{
	
	private Participante participante;

	@Override
    public List<Muestra> criterioDeBusqueda(List<Muestra> muestras){
		
		return muestras.stream()
				.filter(muestra->muestra.getNivelDeValidacion().equals("Nivel Validada"))
				.collect(Collectors.toList());
	}
}
