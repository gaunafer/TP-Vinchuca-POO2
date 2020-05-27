package tpVinchuca;

public class Ubicacion {

	private Double latitud;
	private Double longitud;
	
	public Ubicacion(Double latitud, Double longitud) {
		setLatitud(latitud);
		setLongitud(longitud);
	}

	public Double getLatitud() {
		return latitud;
	}

	public Double getLongitud() {
		return longitud;
	}

	public void setLatitud(Double latitud) {
		 if ((latitud < -90 || latitud > 90)) {
	          throw new IllegalArgumentException("Ubicacion inexistente. La latitud debe tener un valor entre -90 y 90");
	       } else {
	    	   this.latitud = latitud;
	       }
	}

	public void setLongitud(Double longitud) {
		 if (longitud < -180 || longitud > 180) {
			throw new IllegalArgumentException("Ubicacion inexistente. La longitud debe tener un valor entre -180 y 180");
		 } else {
			this.longitud = longitud;
	     }
	}

	
	
}
