package tpVinchucas.filtro;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import tpVinchuca.Muestra;

public abstract class FiltroPorFecha implements Filtro{
	
	private LocalDate fecha;
	private OperadorLogico operador;
	
    public FiltroPorFecha(OperadorLogico operador, LocalDate fecha) {
		this.fecha = fecha;
		this.operador = operador;
	}

	public List<Muestra> criterioDeBusqueda(List<Muestra> muestras){
		
		return muestras.stream()
				.filter(muestra->enFechaCorrespondiente(fechaAComparar(muestra), fecha))
				.collect(Collectors.toList());
	}
	//muestra.getFecha().isAfter(this.fecha)
	public Boolean enFechaCorrespondiente(LocalDate fechaAComparar, LocalDate fechaDeComparacion) {
		return operador.comparar(fechaAComparar, fechaDeComparacion);
	}
	
	public abstract LocalDate fechaAComparar(Muestra muestra);

}
