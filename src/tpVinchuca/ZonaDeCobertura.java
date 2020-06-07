package tpVinchuca;

import java.util.*;

public class ZonaDeCobertura {
	
	private Ubicacion epicentro;
	private Double radio;
	private String nombre;
	private List<Muestra> muestras;
	private List<IObservadoresDeZonas> observers;

	/**
	 * Crea una zona de cobertura circular. Ademas de los parametros, incluye
	 * -una lista de las muestras que se ubican dentro de la zona de cobertura
	 * -una lista de los objetos que seran observadores de la zona de cobertura
	 * Ambas listas se crean vacias.
	 * 
	 * @param epicentro Expresa el centro del circulo de la zona, segun la clase Ubicacion
	 * @param radio Es el radio de la zona, expresado en kilometros
	 * @param nombre Es el nombre de la zona de cobertura
	 */
	public ZonaDeCobertura(Ubicacion epicentro, Double radio, String nombre) {
		
		this.epicentro = epicentro;
		setRadio(radio);
		this.nombre = nombre;
		this.muestras = new ArrayList<Muestra>();
		this.observers = new ArrayList<IObservadoresDeZonas>();
	}

	/**
	 * Devuelve el radio de la zona de cobertura, expresada en kilometros
	 */
	public Double getRadio() {
		return radio;
	}

	/** Devuelve en nombre de la zona de cobertura
	 * 
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Devuelve la Ubicacion donde se encuentra el epicentro de la zona de cobertura
	 */
	public Ubicacion getEpicentro() {
		return epicentro;
	}
	
	/**
	 * Setea el radio de la zona de cobertura. De ser igual o menor a 0km, lanza una excepcion
	 * @param radio Debe ser expresado en kilometros, mayor a 0km
	 */
	public void setRadio(Double radio) {
		if (radio <= 0d) {
			throw new IllegalArgumentException("El radio debe ser mayor a 0.");
		} else {
			this.radio = radio;
		}
	}

	/**
	 * Agrega la muestra a la variable lista de muestras que se encuentran en la zona de cobertura.
	 * Genera el aviso de que una nueva muestra ha sido creada
	 * @param muestra Instancia de la clase Muestra
	 */
	public void agregarMuestra(Muestra muestra) {
		muestras.add(muestra);
		notifyCreacionMuestra(muestra);
	}

	/**
	 * Dada una lista de zonas de cobertura, filtra por aquellas con las que se solapa.
	 * @param zonas Lista de zonas de cobertura 
	 * @return Otra lista con las zonas de cobertura que se solapan con la zona que convoca al metodo
	 */
	public List<ZonaDeCobertura> zonasConLasQueSeSolapa(List<ZonaDeCobertura> zonas) {
		List<ZonaDeCobertura> zonasQueSeSolapan = new ArrayList<ZonaDeCobertura>();
		
		for (ZonaDeCobertura zonaDeCobertura : zonas) {
			if (seSolapaCon(zonaDeCobertura)) {
				zonasQueSeSolapan.add(zonaDeCobertura);
			}
		}
		return zonasQueSeSolapan;
	}
	
	/**
	 * Devuelve true si la zona se solapa con otraZona, false en caso contrario.
	 * Una zona se solapa con otra si la suma de los radios de ambas zonas es mayor que la distancia entre los epicentros de las mismas
	 */
	private Boolean seSolapaCon(ZonaDeCobertura otraZona) {
		Boolean seSolapa = false;
		Ubicacion epicentroOtraZona = otraZona.getEpicentro();
		Double radioOtraZona = otraZona.getRadio();

		Double distanciaEntreEpicentros = this.getEpicentro().calcularDistancia(epicentroOtraZona);
		Double sumaDeRadios = this.getRadio() + radioOtraZona;

		if (sumaDeRadios > distanciaEntreEpicentros) {
			seSolapa = true;
		}
		return seSolapa;
	}
	
	/**
	 * Metodo convocado para avisar que una muestra de la zona ha sido validada
	 * @param muestra La muestra que ha sido validada
	 */
	public void muestraValidida(Muestra muestra) {
		notifyValidacionMuestra(muestra);
	}

	/**
	 * Agrega un observador de zonas a la lista observers de la zona
	 * @param observer Nuevo objeto observador de la zona
	 */
	public void agregarObserver(IObservadoresDeZonas observer) {
		observers.add(observer);
	}

	/**
	 * Elimina el observador pasado como parametro de la lista observers
	 * @param observer
	 */
	public void eliminarObserver(IObservadoresDeZonas observer) {
		observers.remove(observer);
	}
	
	/**
	 * Notifica a los observers que una muestra ha sido creada en la zona para que actualicen su estado
	 * @param muestra La muestra que ha sido creada
	 */
	public void notifyCreacionMuestra(Muestra muestra) { 
		for (IObservadoresDeZonas observer : observers) {
			observer.updateCreacionMuestra(muestra, this); 
		}
	}

	/**
	 * Notifica a los observers que una muestra de la zona ha sido validada para que actualicen su estado
	 * @param muestra La muestra que ha sido validada
	 */
	public void notifyValidacionMuestra(Muestra muestra) { 
		for (IObservadoresDeZonas observer : observers) {
			observer.updateValidacionMuestra(muestra, this); 
		}
	}

}
