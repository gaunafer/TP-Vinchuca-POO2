package tpVinchuca;

import java.time.LocalDate;

public class Experto extends NivelDeConocimiento{
	
	public String getNivelDeConocimiento() {
		return "Nivel Experto";
	}

	
	public void verificarEstado(Participante participante) {
		long cantidadDeVotacionesEnElMes = participante.getVotaciones().stream()
				.filter(votacion->votacion.getFechaDeCreacion().getMonth() == LocalDate.now().getMonth()).count();
				
				if (cantidadDeVotacionesEnElMes < 10l) {
					this.cambiarABasico(participante);
				}
	}
		
	}

	



