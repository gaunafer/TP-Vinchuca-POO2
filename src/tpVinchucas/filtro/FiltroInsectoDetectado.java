package tpVinchucas.filtro;

import java.util.List;
import java.util.stream.Collectors;

import tpVinchuca.ClasificacionDeFoto;
import tpVinchuca.Muestra;

public class FiltroInsectoDetectado implements Filtro {
	
	private ClasificacionDeFoto insecto;
	
    public FiltroInsectoDetectado(ClasificacionDeFoto insecto) {
		super();
		this.insecto = insecto;
	}


	/**
	 *@param muestras Lista de muestras
	 *@return List<Muestra> cuyo resultado actual  es igual a this.insecto
	 */
	public List<Muestra> criterioDeBusqueda(List<Muestra> muestras){
		
		return muestras.stream()
				.filter(muestra->muestra.getResultadoActual().equals(insecto.getValor()))
				.collect(Collectors.toList());
	}

}
