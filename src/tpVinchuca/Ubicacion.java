package tpVinchuca;

import java.util.ArrayList;
import java.util.List;

public class Ubicacion {

	private Double latitud;
	private Double longitud;
	
	/**
	 * Construye una ubicacion a partir de una latitud y una longitud
	 * @param latitud Punto de latitud expesado en decimal
	 * @param longitud Punto de longitud expesado en decimal
	 */
	public Ubicacion(Double latitud, Double longitud) {
		setLatitud(latitud);
		setLongitud(longitud);
	}

	/**
	 * Devuelve la latitud de la ubicacion
	 * @return Varible latitud de la ubicacion
	 */
	public Double getLatitud() {
		return latitud;
	}

	/**
	 * Devuelve la longitud de la ubicacion
	 * @return Varible longitud de la ubicacion
	 */
	public Double getLongitud() {
		return longitud;
	}

	/**
	 * Define la variable latitud de la ubicacion.
	 * Lanza una excepcion en caso de que el valor sea menor a -90 o mayor a 90 
	 * @param latitud Latitud expresada en decimal
	 */
	public void setLatitud(Double latitud) {
		 if ((latitud < -90 || latitud > 90)) {
	          throw new IllegalArgumentException("Ubicacion inexistente. La latitud debe tener un valor entre -90 y 90");
	       } else {
	    	   this.latitud = latitud;
	       }
	}

	/**
	 * Define la variable longitud de la ubicacion.
	 * Lanza una excepcion en caso de que el valor sea menor a -180 o mayor a 180 
	 * @param latitud Longitud expresada en decimal
	 */
	public void setLongitud(Double longitud) {
		 if (longitud < -180 || longitud > 180) {
			throw new IllegalArgumentException("Ubicacion inexistente. La longitud debe tener un valor entre -180 y 180");
		 } else {
			this.longitud = longitud;
	     }
	}

	/**
	 * Recibe otra ubicacion y calcula la distancia entre ambas
	 * @param otraUbicacion
	 * @return Devuelve la distancia entre la ubicacion que invoca al metodo y otraUbicacion, expresada en kilometros
	 */
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

	/**
	 * Devuelve las ubicaciones que se encuentran a menor distancia, en kilometros, de la ubicacion 
	 * @param distancia Distancia en kilometros
	 * @param ubicaciones Lista de ubicaciones de las que se calculara la distancia
	 * @return Una lista de ubicaciones que se encuentran a la distancia mencionada de la ubicacion que invoca el metodo
	 */
	public List<Ubicacion> ubicacionesAMenosDe(Double distancia, List<Ubicacion> ubicaciones) {
		List<Ubicacion> ubicacionesAMenosDe = new ArrayList<Ubicacion>();
		for (Ubicacion ubicacion : ubicaciones) {
			if (calcularDistancia(ubicacion) < distancia) {
				ubicacionesAMenosDe.add(ubicacion);
			}
		}
		return ubicacionesAMenosDe;
	}

	// aclarar si la muestra original queda en la lista resultado
	public List<Muestra> muestrasAMenosDe(Double distancia, Muestra muestra, List<Muestra> muestras) {
		List<Muestra> muestrasAMenosDe = new ArrayList<Muestra>();
		
		Ubicacion ubicacionDeMuestra = muestra.getUbicacion();
		for (Muestra muestraDeLaLista : muestras) {
			Ubicacion ubicacionDeMuestraDeLaLista = muestraDeLaLista.getUbicacion();
			Double distanciaEntreMuestras = ubicacionDeMuestra.calcularDistancia(ubicacionDeMuestraDeLaLista);
			
			if (distanciaEntreMuestras < distancia) {
				muestrasAMenosDe.add(muestraDeLaLista);
			}
		}
		muestrasAMenosDe.remove(muestra);
		return muestrasAMenosDe;
	}
	
}
