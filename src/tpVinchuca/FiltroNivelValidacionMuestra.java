package tpVinchuca;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class FiltroNivelValidacionMuestra implements Filtro{

	public FiltroNivelValidacionMuestra() {
	}
	
	@Override
    public List<Muestra> criterioDeBusqueda(List<Muestra> muestras){
		/*return muestras.stream()
				.filter(muestra->muestra.getNivelDeValidacion().equals(nivelDeValidacion))
				.collect(Collectors.toList());
		*/
		List<Muestra> muestrasDeNivelDeValidacion = new ArrayList<Muestra>();
		
		for (Muestra muestra : muestras) {
			if (tieneNivelCorrespondiente(muestra)) {
				muestrasDeNivelDeValidacion.add(muestra);
			}
		}
		return muestrasDeNivelDeValidacion;
	}
	
	public abstract Boolean tieneNivelCorrespondiente(Muestra muestra);
}
