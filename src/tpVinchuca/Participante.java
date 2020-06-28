package tpVinchuca;

import tpVinchucas.niveldeConocimiento.NivelDeConocimiento;

public class Participante {
	
	private String alias;
	private NivelDeConocimiento nivelDeConocimiento;
	
	
	public Participante(String alias, NivelDeConocimiento nivelDeConocimiento) {
		super();
		this.alias = alias;
		this.nivelDeConocimiento = nivelDeConocimiento;
		this.actualizarEstado();
	}

	/**
	 * Retorna el alias del participante
	 */
	public String getAlias() {
		return alias;
	}

	/**
	 * Retorna el nivel de conocimiento del participante
	 */
	public NivelDeConocimiento getNivelDeConocimiento() {
		
		 return this.nivelDeConocimiento;
	}
	
    /**
     * Setea el nivel de conocimiento
     * @param nivelDeConocimiento
     */
    public void setNivelDeConocimiento(NivelDeConocimiento nivelDeConocimiento) {
		this.nivelDeConocimiento = nivelDeConocimiento;
		
	}

	/**
	 * Delega en estado la actualizacion del estado del participante al estado correspondiente
	 */
	public void actualizarEstado() {
		this.nivelDeConocimiento.verificarEstado(this);
	}	
	
	/**
	 * Redefine equals para busquedas realizar busquedas por igualdad
	 */
	@Override
	public boolean equals(Object participante) {
		Participante otroParticipante = (Participante)participante;
		return this.getAlias().equals(otroParticipante.getAlias());
	}

	/**
	 * Chequea si el nivel de conocimiento de participante es experto
	 * @return true si el nivel de conocimiento es experto, false si es basico
	 */
	public Boolean esExperto() {
		return this.nivelDeConocimiento.esExperto();
	}
	
}
