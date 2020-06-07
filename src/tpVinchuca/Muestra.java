package tpVinchuca;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Muestra {
	private LocalDate fecha;
	private List<Votacion> votaciones;
	private Participante participante;
	private NivelDeValidacion nivelDeValidacion;
	private List<ZonaDeCobertura> zonasDeCobertura;
	private Imagen imagen;
	private ResultadoDeMuestra veredicto;
	private Ubicacion ubicacion;
	
	public Muestra(Imagen imagen, Participante participante, ResultadoDeMuestra veredicto, Ubicacion ubicacion) {
		this.participante = participante;
		this.imagen = imagen;
		this.fecha = LocalDate.now();
		this.veredicto = veredicto;
		this.ubicacion = ubicacion;
		this.inicializarEstado();
		 this.votaciones = new ArrayList<Votacion>();
	}
	
	
	private void inicializarEstado() {
		if (participante.getNivelDeConocimiento() == "Nivel Experto") {
			setNivelDeValidacionExperto();
		}
		else {
			setNivelDeValidacionBasico();
		}
	}

/**
 * Utiliza try catch porque eclipse lo dice.
 * @throws Exception 
 * 
 */
	public void registrarVotacion(Votacion votacion) throws Exception {
			nivelDeValidacion.registrarVotacion(this, votacion);
	}
	public String getAliasParticipante() {
		return participante.getAlias();
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public String getVeredicto() {
		return this.veredicto.getValor();
	}
	public String getResultadoActual() {
		return this.nivelDeValidacion.resultadoActual(this);
	}
	public Ubicacion getUbicacion() {
		return ubicacion;
	}
	public void informarVerificacion() {
		//Informa a zonade Cobertura
	}
	public void setNivelDeValidacionBasico() {
		this.nivelDeValidacion = new NivelBasico();
	}
	public void setNivelDeValidacionExperto() {
		this.nivelDeValidacion = new NivelExperto();
	}
	public void setNivelDeValidacionValidada() {
		this.nivelDeValidacion = new NivelValidada();
	}
	public List<Votacion> getVotaciones(){
		return this.votaciones;
	}

	public Imagen getImagen() {
		return this.imagen;
	}
	public void addVotacion(Votacion votacion) {
		this.votaciones.add(votacion);		
	}

	public Participante getParticipante() {
		return participante;
	}

	public Ubicacion getUbicacion() {
		// TODO Auto-generated method stub
		return null;
	}

}

