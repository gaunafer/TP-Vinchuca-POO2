package tpVinchuca;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

public class AplicacionVinchucas {
	
	private Buscador buscador;
    private List<Muestra> muestras;
    private List<ZonaDeCobertura> zonas;
	

	public AplicacionVinchucas(Buscador buscador) {
		super();
		this.buscador = buscador;
		this.muestras = new ArrayList<Muestra>();
		this.zonas = new ArrayList<ZonaDeCobertura>();
	}
	
	public List<Muestra> getMuestrasDeParticipantePorFecha(Participante participante, LocalDate fecha) {
		Filtro filtroFecha =  new FiltroFecha(fecha);
		Filtro filtroParticipante = new FiltroParticipante(participante);
	    Filtro and = new FiltroAnd(filtroParticipante, filtroFecha);
		return this.buscador.buscar(muestras, and);
	}
	
    public List<Votacion> getVotaciones(){
		
		return muestras.stream().flatMap(muestra -> muestra.getVotaciones().stream()).collect(Collectors.toList());
		
	}
	
	public List<Votacion> getVotacionDeParticipantePorfecha(Participante participante){
		return this.buscador.getVotacionesDeParticipanteEnLosUltimos30Dias(this.getVotaciones(),participante);
	}

	/**
	 * Agrega una zona de cobertura a la lista de zonas de la aplicacion
	 * @param zonaDeCobertura
	 */
	public void agregarZonaDeCobertura(ZonaDeCobertura zonaDeCobertura) {
		zonas.add(zonaDeCobertura);
	}

	/**
	 * Registra la creacion de una nueva muestra agregandola a su lista de muestras. 
	 * Ademas, revisa la lista de zonas de cobertura y agrega la muestra en aquellas 
	 * zonas a las que pertenece la muestra. 
	 * Por ultimo, se asegura que la clase que monitorea la validacion de cada muestra
	 * agregue las zonas.
	 * @param muestra
	 */
	public void agregarMuestra(Muestra muestra) {
		muestras.add(muestra);
		
		for (ZonaDeCobertura zona : this.zonas) {
			if (zona.zonaContieneUbicacionDeMuestra(muestra)) {
				zona.agregarMuestra(muestra);
				muestra.asignarZona(zona);
			}
		}
	}

}
