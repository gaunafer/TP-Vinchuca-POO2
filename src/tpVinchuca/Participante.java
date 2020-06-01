package tpVinchuca;

import java.util.ArrayList;
import java.util.List;

public class Participante {
	
	private String alias;
	private List<Votacion> votaciones;
	private NivelDeConocimiento nivelDeConocimiento;
	
	
	public Participante(String alias, NivelDeConocimiento nivelDeConocimiento) {
		super();
		this.alias = alias;
		this.votaciones = new ArrayList<Votacion>();
		this.nivelDeConocimiento = nivelDeConocimiento;
	
		
	}

	public String getAlias() {
		return alias;
	}


	public String getNivelDeConocimiento() {
		
		 return this.nivelDeConocimiento.getNivelDeConocimiento();
	}

	public void archivarVotacion(Votacion votacion) {
		votaciones.add(votacion);
		
	}
	
	public void actualizarEstado() {
		this.nivelDeConocimiento.verificarEstado(this);
	}

	public void setNivelDeConocimiento(NivelDeConocimiento nivelDeConocimiento) {
		
		this.nivelDeConocimiento = nivelDeConocimiento;
		
	}	

}
