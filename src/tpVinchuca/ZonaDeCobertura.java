package tpVinchuca;

import java.util.*;

public class ZonaDeCobertura {
	
	private Ubicacion epicentro;
	private Double radio;
	private String nombre;

	public ZonaDeCobertura(Ubicacion epicentro, Double radio, String nombre) {
		
		this.epicentro = epicentro;
		setRadio(radio);
		this.nombre = nombre;
	}

	public Double getRadio() {
		return radio;
	}

	public String getNombre() {
		return nombre;
	}
	
	public Ubicacion getEpicentro() {
		return epicentro;
	}
	
	public void setRadio(Double radio) {
		if (radio < 0d) {
			throw new IllegalArgumentException("El radio debe ser mayor a 0.");
		} else {
			this.radio = radio;
		}
	}

	public List<ZonaDeCobertura> zonasConLasQueSeSolapa(List<ZonaDeCobertura> zonas) {
		List<ZonaDeCobertura> zonasQueSeSolapan = new ArrayList<ZonaDeCobertura>();
		
		for (ZonaDeCobertura zonaDeCobertura : zonas) {
			if (this.seSolapaCon(zonaDeCobertura)) {
				zonasQueSeSolapan.add(zonaDeCobertura);
			}
		}
		return zonasQueSeSolapan;
	}
	
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

}
