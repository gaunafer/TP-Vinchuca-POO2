package tpVinchuca;

import java.util.ArrayList;
import java.util.List;

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

	public Double calcularDistancia(Ubicacion otraUbicacion) {
		Double lat1 = getLatitud();
		Double lng1 = getLongitud();
		
		Double lat2 = otraUbicacion.getLatitud();
		Double lng2 = otraUbicacion.getLongitud();
		
		double radioTierra = 6371;//en kilómetros  
        double dLat = Math.toRadians(lat2 - lat1);  
        double dLng = Math.toRadians(lng2 - lng1);  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;  
   
        return distancia;
	}

	public List<Ubicacion> ubicacionesAMenosDe(double distancia, List<Ubicacion> ubicaciones) {
		List<Ubicacion> ubicacionesAMenosDe = new ArrayList<Ubicacion>();
		for (Ubicacion ubicacion : ubicaciones) {
			if (this.calcularDistancia(ubicacion) < distancia) {
				ubicacionesAMenosDe.add(ubicacion);
			}
		}
		return ubicacionesAMenosDe;
	}

	
	
}
