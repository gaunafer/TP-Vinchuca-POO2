package tpVinchuca;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroVotacionesPorParticipante implements Filtro {
	private Participante participante;
	
	

	public FiltroVotacionesPorParticipante(Participante participante) {
		super();
		this.participante = participante;
	}



	@Override
	public List<Muestra> criterioDeBusqueda(List<Muestra> muestras) {
		List<Muestra> muestrasVotadasPor = muestras.stream()
				.filter(muestra -> muestra.getVotaciones().stream().anyMatch(votacion->votacion.getParticipante().equals(this.participante)))
				.collect(Collectors.toList());
		return muestrasVotadasPor;
	}

}
