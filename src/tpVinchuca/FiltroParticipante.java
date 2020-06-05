package tpVinchuca;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroParticipante implements Filtro {
	
	private Participante participante;
	
	public FiltroParticipante(Participante participante) {
		super();
		this.participante = participante;
	}


	public List<Muestra> criterioDeBusqueda(List<Muestra> muestras){
		
		return muestras.stream()
				.filter(muestra->muestra.getParticipante().equals(this.participante))
				.collect(Collectors.toList());
	}

}
