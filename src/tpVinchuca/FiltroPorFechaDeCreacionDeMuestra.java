package tpVinchuca;

import java.time.LocalDate;

public class FiltroPorFechaDeCreacionDeMuestra extends FiltroPorFecha {

	public FiltroPorFechaDeCreacionDeMuestra(OperadorLogico operador, LocalDate fechaDeComparacion) {
		super(operador, fechaDeComparacion);
	}

	@Override
	public LocalDate fechaAComparar(Muestra muestra) {
		return muestra.getFecha();
	}

}
