package tpVinchuca;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroMuestraValida implements Filtro{
	
	private Participante participante;

	@Override
    public List<Muestra> criterioDeBusqueda(List<Muestra> muestras){
		
		return muestras.stream()
				.filter(muestra->muestra.getParticipante().equals(this.participante))
				.collect(Collectors.toList());
	}
}
