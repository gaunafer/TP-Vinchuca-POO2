package tpVinchuca;

import javax.imageio.IIOException;

public class ErrorParticipanteNoPuedeVotarMuestraPublicadaPorSiMismo extends IIOException {
	public ErrorParticipanteNoPuedeVotarMuestraPublicadaPorSiMismo() {
		super("Participante no puede votar una muestra publicada por el mismo");
	}

}
