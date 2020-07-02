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

	/**
	 *@param muestras: lista de muestras
	 *@return lista de muestras con las muestras que dan True en enFechaCorrespondiente(LocalDate, LocalDate)
	 */
	public List<Muestra> criterioDeBusqueda(List<Muestra> muestras){
		
		return muestras.stream()
				.filter(muestra->enFechaCorrespondiente(fechaAComparar(muestra), fecha))
				.collect(Collectors.toList());
	}
	
	public Boolean enFechaCorrespondiente(LocalDate fechaAComparar, LocalDate fechaDeComparacion) {
		return operador.comparar(fechaAComparar, fechaDeComparacion);
	}
	
	public abstract LocalDate fechaAComparar(Muestra muestra);

}
