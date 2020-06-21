package tpVinchuca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Muestra {
	private LocalDate fechaDeCreacion;
	private List<Votacion> votaciones;
	private Participante participante;
	private NivelDeValidacion nivelDeValidacion;
	private Imagen imagen;
	private ClasificacionDeFoto veredicto;
	private Ubicacion ubicacion;
	private InformadorDeZonas informadorDeZonas;
	private String nivelDeConocimientoDeCreacion;

	public Muestra(Imagen imagen, Participante participante, ClasificacionDeFoto veredicto, Ubicacion ubicacion) {
		this.participante = participante;
		this.imagen = imagen;
		this.fechaDeCreacion = LocalDate.now();
		this.veredicto = veredicto;
		this.ubicacion = ubicacion;
		this.inicializarEstado();
		this.votaciones = new ArrayList<Votacion>();
		this.informadorDeZonas = new InformadorDeZonas();
		this.nivelDeConocimientoDeCreacion = participante.getNivelDeConocimiento();
	}

	/**
	 * Setea el nivel de validacion de la muestra en Basico o experto dependiendo
	 * del nivel de conocimiento del participante
	 */
	private void inicializarEstado() {
		if (participante.getNivelDeConocimiento() == "Nivel Experto") {
			setNivelDeValidacionExperto();
		} else {
			setNivelDeValidacionBasico();
		}
	}

	/**
	 * Delega en su nivel de validacion el registro de una votacion. levanta
	 * excepcion si no puede registrarse la votacion
	 * 
	 * @param votacion
	 * @throws ErrorParticipanteNoPuedeVotarEstaMuestra
	 * 
	 * 
	 * 
	 */
	public void registrarVotacion(Votacion votacion) throws ErrorParticipanteNoPuedeVotarEstaMuestra {
		nivelDeValidacion.registrarVotacion(this, votacion);
	}

	/**
	 * Retorna el alias del participante que creo la muestra.
	 */
	public String getAliasParticipante() {
		return participante.getAlias();
	}

	/**
	 * Retorna el resultado actual de lamuestra.
	 */
	public String getResultadoActual() {
		return this.nivelDeValidacion.resultadoActual(this);
	}

	/**
	 * Delega en informador de zonas informar que pasa a nivel de validacion
	 * validada.
	 */
	public void informarVerificacion() {
		getInformadorDeZonas().muestraValidada(this);
	}

	/**
	 * Retorna la ubicacion.
	 */
	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	/**
	 * Retorna la fecha de creacion de la muestra
	 */
	public LocalDate getFecha() {
		return fechaDeCreacion;
	}

	/**
	 * Retorna el participante que creo lamuestra
	 */
	public Participante getParticipante() {
		return participante;
	}

	/**
	 * Retorna la lista de todas las votaciones
	 */
	public List<Votacion> getVotaciones() {
		return this.votaciones;
	}

	/**
	 * Asigna la zona a la lista de zonas del InformadorDeZonas a las que pertenece
	 * la muestra
	 * 
	 * @param zona ZonaDeCobertura a agregar
	 */
	public void asignarZona(ZonaDeCobertura zona) {
		getInformadorDeZonas().agregarZonaDeCobertura(zona);
	}
	
	/**
	 * Elimina la zona de la lista de zonas del InformadorDeZonas a las que pertenece
	 * la muestra. Esto sucede cuando una zona cambia su area, dejando por fuera la 
	 * ubicacion de la muestra.
	 * 
	 * @param zona ZonaDeCobertura a eliminar
	 */
	public void eliminarZona(ZonaDeCobertura zona) {
		getInformadorDeZonas().eliminarZonaDeCobertura(zona);
	}

	/**
	 * retorna el veredicto con el que fue creada la muestra.
	 */
	public String getVeredicto() {
		return this.veredicto.getValor();
	}

	/**
	 * Setea nivel de validacion Basico
	 */
	public void setNivelDeValidacionBasico() {
		this.nivelDeValidacion = new NivelBasico();
	}

	/**
	 * Setea nivel de validacion Experto
	 */
	public void setNivelDeValidacionExperto() {
		this.nivelDeValidacion = new NivelExperto();
	}

	/**
	 * Setea nivel de validacion Validada
	 */
	public void setNivelDeValidacionValidada() {
		this.nivelDeValidacion = new NivelValidada();
	}

	/**
	 * Retorna la imagen de la muestra
	 */
	public Imagen getImagen() {
		return this.imagen;
	}

	/**
	 * Retorna el informadorDeZonas
	 */
	public InformadorDeZonas getInformadorDeZonas() {
		return this.informadorDeZonas;
	}

	/**
	 * Agrega una votacion a la lista de votaciones
	 * 
	 * @param votacion
	 */
	public void addVotacion(Votacion votacion) {
		this.votaciones.add(votacion);
	}

	/**
	 * @param participante Retorna True si la muestra fue votada por el participante
	 *                     recibido por parametro
	 */
	public Boolean muestraVotadaPor(Participante participante) {
		List<Votacion> votaciones = this.getVotaciones().stream()
				.filter(votacion -> votacion.getParticipante().equals(participante)).collect(Collectors.toList());
		return !votaciones.isEmpty();
	}

	/**
	 * retorna nivel de validacion actual
	 */
	public String getNivelDeValidacion() {
		return nivelDeValidacion.getNivelDeValidacion();
	}

	/**
	 * Retorna true si la muestra tiene votaciones.
	 */
	public Boolean esMuestraVotada() {
		return votaciones.size() > 0;
	}

	/**
	 * este metodo solo es utilizado para Muestras que han sido votadas al menos una
	 * ves.
	 * 
	 * @return Fecha de la votacion mas reciente que se le realizo a esa muestra.
	 */

	public LocalDate getFechaUltimaVotacion() {

		return votaciones.get(this.getVotaciones().size() - 1).getFecha();
	}
	/**
	 * Retorna el nivel de creacion del creador de la muestra al momento de la creacion
	 */
	public String getNivelDeConocimientoDeCreacion() {
		return nivelDeConocimientoDeCreacion;
	}
}
