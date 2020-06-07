package tpVinchuca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NivelValidada extends NivelDeValidacion {
	@Override
	public void registrarVotacion(Muestra muestra, Votacion votacion) throws Exception {
		throw new ErrorParticipanteVotaMuestraValidada();
		
	}
}
