package tpVinchuca;

public final class ErrorParticipanteBasicoVotaMuetraNivelExperto extends Exception {
	
	/**
	 * No se que es esto, averiguar
	 */
	private static final long serialVersionUID = 1;

	public ErrorParticipanteBasicoVotaMuetraNivelExperto() {
		super("Los usuarios básicos no pueden votar en muestras voradas previamente por expertos");
	}
}
