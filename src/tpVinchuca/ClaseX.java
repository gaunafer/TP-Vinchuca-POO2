package tpVinchuca;

import java.util.*;
import java.util.function.BooleanSupplier;

public class ClaseX {
	
	private List<ZonaDeCobertura> zonasQueContienenLaMuestra;

	public ClaseX() {
		zonasQueContienenLaMuestra = new ArrayList<ZonaDeCobertura>();
	}

	public void agregarZonaDeCobertura(ZonaDeCobertura zona) {
		zonasQueContienenLaMuestra.add(zona);
	}

	public Boolean esZonaDeMuestra(ZonaDeCobertura zona) {
		return zonasQueContienenLaMuestra.contains(zona);
	}

	public void eliminarZonaDeCobertura(ZonaDeCobertura zona) {
		zonasQueContienenLaMuestra.remove(zona);
	}

	public void muestraValidada(Muestra muestra) {
		for (ZonaDeCobertura zonaDeCobertura : zonasQueContienenLaMuestra) {
			zonaDeCobertura.muestraValidida(muestra);
		}		
	}
	
	
	
}
