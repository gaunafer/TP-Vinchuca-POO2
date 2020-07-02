package tpVinchucas.error;
import javax.imageio.IIOException;

public class ErrorParticipanteNoPuedeVotarEstaMuestra extends RuntimeException {
	/**
	 * Excepcion lanzada cuando un participante intentavotar una muestra que no puede votar.
	 * @param String con el mensaje del error
	 */
	private static final long serialVersionUID = 1L;

	public ErrorParticipanteNoPuedeVotarEstaMuestra(String error) {
		super(error);
	}

}
