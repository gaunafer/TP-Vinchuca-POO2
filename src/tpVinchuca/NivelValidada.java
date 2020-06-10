package tpVinchuca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NivelValidada extends NivelExperto {
	@Override
	public void registrarVotacion(Muestra muestra, Votacion votacion) throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		throw new ErrorParticipanteNoPuedeVotarEstaMuestra("No se puede votar una muestra validada");
	}
	@Override
	protected String getNivelDeValidacion() {
		return "Nivel Validada";
	}
}
