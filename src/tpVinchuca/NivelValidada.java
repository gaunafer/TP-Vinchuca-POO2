package tpVinchuca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class NivelValidada extends NivelExperto {
	
	/**
	 *Levanta exepcion: No se puede votar una muestra validada
	 *@throws ErrorParticipanteNoPuedeVotarEstaMuestra
	 */
	@Override
	public void registrarVotacion(Muestra muestra, Votacion votacion) throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		throw new ErrorParticipanteNoPuedeVotarEstaMuestra("No se puede votar una muestra validada");
	}
	
	/**
	 * Retorna el nivel de validacion de la muestra
	 */
	@Override
	protected String getNivelDeValidacion() {
		return "Nivel Validada";
	}
	
	public Boolean estaValidada() {
		return true;
	}
}
