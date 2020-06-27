package tpVinchucas.filtro;

import java.util.List;

import tpVinchuca.Muestra;

public abstract class OperadorBinario implements Filtro {
	
	private Filtro filtro;
	private Filtro otroFiltro;

	public OperadorBinario(Filtro filtro, Filtro otroFiltro) {
		this.filtro = filtro;
	    this.otroFiltro = otroFiltro; 
	}

	public Filtro getFiltro() {
		return filtro;
	}

	public Filtro getOtroFiltro() {
		return otroFiltro;
	}

	@Override
	public abstract List<Muestra> criterioDeBusqueda(List<Muestra> muestras);

}
