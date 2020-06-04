package tpVinchuca;

import java.time.LocalDate;
import java.util.List;

public class Muestra {
	
	private String clasificacion; //= ClasificacionDeFoto.VINCHUCA_INFESTANS;
	private Participante participante;
	private LocalDate fechaDeCreacion;
	private List<Votacion> votaciones;
	
	
	public Muestra(Participante p, Ubicacion u, ClasificacionDeFoto c, LocalDate fecha) {
		this.clasificacion=c.getDescripcion();
		this.participante=p;
		this.fechaDeCreacion = fecha;
	}


	public Participante getParticipante() {
		
		return this.participante;
	}


	public LocalDate getFecha() {
		
		return this.fechaDeCreacion;
	}
	
	public List<Votacion> getVotaciones(){
		return this.votaciones;
	}
	
	//public String getClasificacion() {
	//return clasificacion.getDescripcion();
	
	//}
	
}
