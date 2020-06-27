package tpVinchucas.filtro;

import java.time.LocalDate;

import tpVinchuca.Muestra;

public class FiltroPorFechaDeCreacionDeMuestra extends FiltroPorFecha {

	public FiltroPorFechaDeCreacionDeMuestra(OperadorLogico operador, LocalDate fechaDeComparacion) {
		super(operador, fechaDeComparacion);
	}

	@Override
	public LocalDate fechaAComparar(Muestra muestra) {
		return muestra.getFecha();
	}

}
