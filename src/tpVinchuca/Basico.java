package tpVinchuca;

import java.time.LocalDate;

public class Basico extends NivelDeConocimiento{
	
	public String getNivelDeConocimiento() {
		return "Nivel Basico";
	}
	
	public void verificarEstado(Participante participante) {
		long cantidadDeVotacionesEnElMes = participante.getVotaciones().stream()
				.filter(votacion->votacion.getFechaDeCreacion().getMonth() == LocalDate.now().getMonth()).count();
				
				if (cantidadDeVotacionesEnElMes > 10l) {
					this.cambiarAExperto(participante);}
				
	}
}	
	

