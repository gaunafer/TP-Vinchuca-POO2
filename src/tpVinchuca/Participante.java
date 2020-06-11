package tpVinchuca;

public class Participante {
	
	private String alias;
	private NivelDeConocimiento nivelDeConocimiento;
	
	
	public Participante(String alias, NivelDeConocimiento nivelDeConocimiento) {
		super();
		this.alias = alias;
		this.nivelDeConocimiento = nivelDeConocimiento;	
	}

	public String getAlias() {
		return alias;
	}


	public String getNivelDeConocimiento() {
		this.actualizarEstado();
		
		 return this.nivelDeConocimiento.getNivelDeConocimiento();
	}
	
    public void setNivelDeConocimiento(NivelDeConocimiento nivelDeConocimiento) {
		this.nivelDeConocimiento = nivelDeConocimiento;
		
	}

	public void actualizarEstado() {
		this.nivelDeConocimiento.verificarEstado(this);
	}	
	
	@Override
	public boolean equals(Object participante) {
		Participante otroParticipante = (Participante)participante;
		return this.getAlias().equals(otroParticipante.getAlias());
	}

}
