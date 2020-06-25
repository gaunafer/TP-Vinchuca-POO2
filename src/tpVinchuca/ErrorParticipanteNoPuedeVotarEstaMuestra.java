package tpVinchuca;
import javax.imageio.IIOException;

public class ErrorParticipanteNoPuedeVotarEstaMuestra extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorParticipanteNoPuedeVotarEstaMuestra(String error) {
		super(error);
	}

}
