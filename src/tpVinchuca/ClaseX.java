package tpVinchuca;

import java.util.*;

public class ClaseX {
	
	private List<ZonaDeCobertura> zonasQueContienenLaMuestra;

	/**
	 * Creacion de clase
	 * Esta clase contiene las zonas de cobertura que abarcan la ubicacion
	 * de una determinada muestra.
	 * Su funcion es avisarle a dichas zonas que la muestra ha sido validada
	 */
	public ClaseX() {
		zonasQueContienenLaMuestra = new ArrayList<ZonaDeCobertura>();
	}

	/**
	 * Agrega una zona de cobertura a la lista de zonas
	 * @param zona
	 */
	public void agregarZonaDeCobertura(ZonaDeCobertura zona) {
		zonasQueContienenLaMuestra.add(zona);
	}

	/**
	 * Elimina una zona de cobertura de la lista
	 * @param zona Zona a ser eliminada de la lista
	 */
	public void eliminarZonaDeCobertura(ZonaDeCobertura zona) {
		zonasQueContienenLaMuestra.remove(zona);
	}

	/**
	 * Le avisa a las zonas de cobertura que una muestra ha sido validada en su zona
	 * @param muestra Muestra que ha sido validada
	 */
	public void muestraValidada(Muestra muestra) {
		for (ZonaDeCobertura zonaDeCobertura : zonasQueContienenLaMuestra) {
			zonaDeCobertura.muestraValidida(muestra);
		}		
	}
	
}
