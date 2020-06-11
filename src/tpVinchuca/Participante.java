package tpVinchuca;

public class Participante {
	
	private String alias;
	private NivelDeConocimiento nivelDeConocimiento;
	
	
	public Participante(String alias, NivelDeConocimiento nivelDeConocimiento) {
		super();
		this.alias = alias;
		this.nivelDeConocimiento = nivelDeConocimiento;	
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
	public String getNivelDeConocimiento() {
		this.actualizarEstado();
		
		 return this.nivelDeConocimiento.getNivelDeConocimiento();
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

}
