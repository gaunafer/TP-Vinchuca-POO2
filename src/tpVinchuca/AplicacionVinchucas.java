package tpVinchuca;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.*;

import tpVinchucas.filtro.Filtro;
import tpVinchucas.filtro.FiltroAnd;
import tpVinchucas.filtro.FiltroParticipante;
import tpVinchucas.filtro.FiltroPorFechaDeCreacionDeMuestra;
import tpVinchucas.filtro.OperadorLogico;

public class AplicacionVinchucas {
	
	private Buscador buscador;
    private List<Muestra> muestras;
    private List<ZonaDeCobertura> zonas;
	

    /**
     * Construye una instancia de la aplicacion, con un buscador.
     * La aplicacion se construye tambien con una lista vacia de muestras 
     * y otra lista vacia de zonas de cobertura 
     * 
     * @param buscador El buscador al que recurrira la aplicacion para realizar busquedas
     */
	public AplicacionVinchucas(Buscador buscador) {
		this.buscador = buscador;
		this.muestras = new ArrayList<Muestra>();
		this.zonas = new ArrayList<ZonaDeCobertura>();
	}
	
	/**
	 * Devuelve el buscador con el que se crea una aplicacion.
	 * 
	 * @return el buscador de la aplicacion
	 */
	public Buscador getBuscador() {
		return buscador;
	}

	/**
	 * Dado un {@code participante}, filtra la lista de muestras de la aplicacion 
	 * por aquellas que fueron creadas por dicho participante desde la {@code fecha}
	 * pasada como parametro hasta la fecha actual.
	 * 
	 * @param participante Participante que crea las muestras que se desea obtener
	 * @param fecha Fecha desde la cual se quiere obtener las muestras 
	 * @return una lista de todas las muestras creadas por {@code participante} 
	 * creadas despues de {@code fecha} 
	 */
	public List<Muestra> getMuestrasDeParticipantePorFecha(Participante participante, LocalDate fecha) {
		Filtro filtroFecha =  new FiltroPorFechaDeCreacionDeMuestra(OperadorLogico.IGUALESA, fecha);
		Filtro filtroParticipante = new FiltroParticipante(participante);
	    Filtro and = new FiltroAnd(filtroParticipante, filtroFecha);
		return buscar(muestras, and);
	}
	
	/**
	 * A partir de la lista de muestras obtiene una lista de todas las votaciones 
	 * de todas las muestras. 
	 * 
	 * @return una lista de todas las votaciones de todas las muestras
	 */
    public List<Votacion> getVotaciones(){
		return muestras.stream().flatMap(muestra -> muestra.getVotaciones().stream()).collect(Collectors.toList());
		
	}
	
    /**
     * Devuelve las votaciones hechas por el participante en los ultimos 30 dias.
     * 
     * @param participante Participante del cual se quieren las votaciones
     * @return una lista con las votaciones hechas por {@code participante} en los 
     * ultimos 30 dias
     */
	public List<Votacion> getVotacionesDeParticipanteDeLosUltimos30Dias(Participante participante){
		return this.buscador.getVotacionesDeParticipanteEnLosUltimos30Dias(this.getVotaciones(),participante);
	}

	/**
	 * Agrega una zona de cobertura a la lista de zonas de la aplicacion
	 * 
	 * @param zonaDeCobertura
	 */
	public void agregarZonaDeCobertura(ZonaDeCobertura zonaDeCobertura) {
		zonas.add(zonaDeCobertura);
	}

	/**
	 * Registra la creacion de una nueva muestra agregandola a su lista de muestras. 
	 * Ademas, revisa la lista de zonas de cobertura y agrega la muestra en aquellas 
	 * zonas a las que pertenece la muestra. 
	 * Por ultimo, se asegura que el informador de zonas de la muestra agregue las zonas.
	 * 
	 * @param muestra muestra creada
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
	
	/**
	 * Busca en la lista de muestras aquellas que cumplan con el criterio dado por el filtro
	 * 
	 * @param muestras la lista de muestras donde se realizara la busqueda 
	 * @param filtro criterio de busqueda por el cual se filtraran las muestras
	 * @return una lista con las muestras que cumplen el criterio del filtro
	 */
	public List<Muestra> buscar(List<Muestra> muestras, Filtro filtro) {
		return getBuscador().buscar(muestras, filtro);
	}
	
	/**
	 * Busca la lista de participantes que han subido muestras a la palicacion o simplemente votado y actualiza sus estados
	 * se prefiere utilizar la interfaz Set en ves de List, ya que no admite objetos repetidos
	 * 
	 */
	
	public void actualizarNivelDeConocimiento() {
		Set<Participante> participantesEnMuestras = new HashSet<Participante>();
		participantesEnMuestras = this.muestras.stream().map(muestra -> muestra.getParticipante()).collect(Collectors.toSet());
		Set<Participante> participantesEnVotaciones =new HashSet<Participante>();
		participantesEnVotaciones = this.getVotaciones().stream().map(votacion->votacion.getParticipante()).collect(Collectors.toSet());
		Set<Participante> participantes = Stream.concat(participantesEnMuestras.stream(), participantesEnVotaciones.stream()).collect(Collectors.toSet());
		System.out.println(participantes);
		participantes.stream().forEach(participante -> participante.actualizarEstado());
	}


}
