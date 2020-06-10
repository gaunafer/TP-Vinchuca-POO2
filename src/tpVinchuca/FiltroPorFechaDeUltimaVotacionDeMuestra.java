package tpVinchuca;

import java.time.LocalDate;

public class FiltroPorFechaDeUltimaVotacionDeMuestra extends FiltroPorFecha {

	public FiltroPorFechaDeUltimaVotacionDeMuestra(OperadorLogico operador, LocalDate fechaDeComparacion) {
		super(operador, fechaDeComparacion);
	}

	@Override
	public LocalDate fechaAComparar(Muestra muestra) {
		return muestra.getFechaUltimaVotacion();
	}

}
