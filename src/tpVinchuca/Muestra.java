package tpVinchuca;

public class Muestra {
	
	private String clasificacion; //= ClasificacionDeFoto.VINCHUCA_INFESTANS;
	
	
	public Muestra(Participante p, Ubicacion u, ClasificacionDeFoto c) {
		this.clasificacion=c.getDescripcion();
	}
	
	//public String getClasificacion() {
	//return clasificacion.getDescripcion();
	
	//}
	
}
