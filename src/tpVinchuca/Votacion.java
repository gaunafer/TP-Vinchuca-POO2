package tpVinchuca;

import java.time.LocalDate;

public class Votacion {

    private LocalDate fechaDeCreacion;
    private ResultadoDeMuestra veredicto;
    private Participante participante;
    private String nivelDeParticipante;

    public Votacion(ResultadoDeMuestra veredicto, Participante participante) {
        super();
        this.fechaDeCreacion = LocalDate.now();
        this.veredicto = veredicto;
        this.participante = participante;
        this.nivelDeParticipante = this.obtenerNivelDeConocimientoDe(participante);
    }

    private String obtenerNivelDeConocimientoDe(Participante participante) {
        participante.actualizarEstado();
        return participante.getNivelDeConocimiento();
    }

    public LocalDate getFechaDeCreacion() {
        return fechaDeCreacion;
    }

    public ResultadoDeMuestra getVeredicto() {
        return veredicto;
    }

    public Participante getParticipante() {
        return participante;
    }

    public void setFechaDeCreacion(LocalDate fechaCreacion) {
        this.fechaDeCreacion = fechaCreacion;
    }

    public String getNivelDeParticipante() {
        return nivelDeParticipante;
    }

}
