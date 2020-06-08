package tpVinchuca;

import java.util.ArrayList;
import java.util.List;

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

}
