package tpVinchuca;

public abstract class NivelDeValidacion {
	
	public abstract String resultadoActual(Muestra m);
	public abstract void registrarVotacion(Muestra muestra, Votacion votacion) throws Exception;
}
