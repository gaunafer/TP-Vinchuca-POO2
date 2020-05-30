package tpVinchuca;

public abstract class NivelDeConocimiento {
	
	
	protected abstract String getNivelDeConocimiento();
	protected abstract void verificarEstado(Participante participante);

	public void cambiarABasico(Participante participante) {
		
		participante.setNivelDeConocimiento(new Basico());
		
	}

	public void cambiarAExperto(Participante participante) {
		NivelDeConocimiento experto = new Experto();
		participante.setNivelDeConocimiento(experto);
		
	}

}
