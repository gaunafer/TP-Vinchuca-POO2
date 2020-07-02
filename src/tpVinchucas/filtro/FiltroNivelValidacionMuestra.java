package tpVinchucas.filtro;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import tpVinchuca.Muestra;

public abstract class FiltroNivelValidacionMuestra implements Filtro{

	public FiltroNivelValidacionMuestra() {
	}
	
	/**
	 * @param muestras. Lista de muestras
	 * @return List<Muestra> Retorna la lista de muestras que tienenNivelCorrespondiente
	 *  (ver metodo tieneNivelCorrespondiente)
	 */
	@Override
    public List<Muestra> criterioDeBusqueda(List<Muestra> muestras){
		List<Muestra> muestrasDeNivelDeValidacion = new ArrayList<Muestra>();
		
		for (Muestra muestra : muestras) {
			if (tieneNivelCorrespondiente(muestra)) {
				muestrasDeNivelDeValidacion.add(muestra);
			}
		}
		return muestrasDeNivelDeValidacion;
	}
	
	/**
	 * @param muestra
	 * @return True si la muestra tiene el nivel correspondiente
	 */
	public abstract Boolean tieneNivelCorrespondiente(Muestra muestra);
}
