package tpVinchuca;

public class ErrorParticipanteVotaMuestraValidada extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2;

	public ErrorParticipanteVotaMuestraValidada() {
		super("No se puede votar una muestra Validada");
	}
}
