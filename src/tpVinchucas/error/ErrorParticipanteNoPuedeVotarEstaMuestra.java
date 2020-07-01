package tpVinchucas.error;
import javax.imageio.IIOException;

public class ErrorParticipanteNoPuedeVotarEstaMuestra extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorParticipanteNoPuedeVotarEstaMuestra(String error) {
		super(error);
	}

}
