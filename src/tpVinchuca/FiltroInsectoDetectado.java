package tpVinchuca;

import java.util.List;
import java.util.stream.Collectors;

public class FiltroInsectoDetectado implements Filtro {
	
	private ClasificacionDeFoto insecto;
	
    public FiltroInsectoDetectado(ClasificacionDeFoto insecto) {
		super();
		this.insecto = insecto;
	}

	public List<Muestra> criterioDeBusqueda(List<Muestra> muestras){
		
		return muestras.stream()
				.filter(muestra->muestra.getResultadoActual().equals(insecto.getValor()))
				.collect(Collectors.toList());
	}

}
