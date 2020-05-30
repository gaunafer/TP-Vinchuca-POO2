package tpVinchuca;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
//import org.mockito.Mock;
import org.mockito.Spy;

import static org.mockito.Mockito.*;


public class ParticipanteTest {

    private Participante pepe;
    private Participante pepa;
    private Participante pipo;
    private int mesActual = LocalDate.now().getMonthValue();



    @Mock
    private Votacion votacion1;
    private Votacion votacion2 = mock(Votacion.class);
    private Votacion votacion3 = mock(Votacion.class);
    private Votacion votacion4 = mock(Votacion.class);
    private Votacion votacion5 = mock(Votacion.class);
    private Votacion votacion6 = mock(Votacion.class);
    private Votacion votacion7 = mock(Votacion.class);
    private Votacion votacion8 = mock(Votacion.class);
    private Votacion votacion9 = mock(Votacion.class);
    private Votacion votacion10 = mock(Votacion.class);
    private Votacion votacion11 = mock(Votacion.class);


    @BeforeEach
    public void setUp() {

        pepe = new Participante("pepe", new Basico());
        pepa = new Participante("pepa", new ExpertoValidado());
        pipo = new Participante("pipo", new Experto());
        votacion1 = new Votacion(ResultadoDeMuestra.VINCHUCA, pepe);

        when(votacion1.getFechaDeCreacion().getMonthValue()).thenReturn(mesActual);
		/*when(votacion2.getFechaDeCreacion().getMonthValue()).thenReturn(mesActual);
		when(votacion3.getFechaDeCreacion().getMonthValue()).thenReturn(mesActual);
		when(votacion4.getFechaDeCreacion().getMonthValue()).thenReturn(mesActual);
		when(votacion5.getFechaDeCreacion().getMonthValue()).thenReturn(mesActual);
		when(votacion6.getFechaDeCreacion().getMonthValue()).thenReturn(mesActual);
		when(votacion7.getFechaDeCreacion().getMonthValue()).thenReturn(mesActual);
		when(votacion8.getFechaDeCreacion().getMonthValue()).thenReturn(mesActual);
		when(votacion9.getFechaDeCreacion().getMonthValue()).thenReturn(mesActual);
		when(votacion10.getFechaDeCreacion().getMonthValue()).thenReturn(mesActual);
		when(votacion11.getFechaDeCreacion().getMonthValue()).thenReturn(mesActual);*/

    }

    @Test
    public void seCreaUnParticipanteConSuAliasUnaListaVaciaDeVotacionesYConUnEstadoBasico() {
        System.out.println(votacion1.getFechaDeCreacion().getMonthValue());
        assertEquals("pepe", pepe.getAlias());
        assertTrue(pepe.getVotaciones().isEmpty());
        assertEquals("Nivel Basico", pepe.getNivelDeConocimiento());
    }

    @Test
    public void siElParticipanteHaceMasDe10VotacionesEnUnMismoMesSuEstadoEsExperto() {
        //Votacion votacion1 = this.crearVotacion(LocalDate.of(2020, mesActual, 3), ResultadoDeMuestra.VINCHUCA, pepe);
        pepe.archivarVotacion(votacion1);
        //Votacion votacion2 = this.crearVotacion(LocalDate.of(2020, mesActual, 3), ResultadoDeMuestra.VINCHUCA, pepe);
        pepe.archivarVotacion(votacion2);
        //Votacion votacion3 = this.crearVotacion(LocalDate.of(2020, mesActual, 3), ResultadoDeMuestra.VINCHUCA, pepe);
        pepe.archivarVotacion(votacion3);
        //Votacion votacion4 = this.crearVotacion(LocalDate.of(2020, mesActual, 3), ResultadoDeMuestra.VINCHUCA, pepe);
        pepe.archivarVotacion(votacion4);
        //Votacion votacion5 = this.crearVotacion(LocalDate.of(2020, mesActual, 3), ResultadoDeMuestra.VINCHUCA, pepe);
        pepe.archivarVotacion(votacion5);
        //Votacion votacion6 = this.crearVotacion(LocalDate.of(2020, mesActual, 3), ResultadoDeMuestra.VINCHUCA, pepe);
        pepe.archivarVotacion(votacion6);
        //Votacion votacion7 = this.crearVotacion(LocalDate.of(2020, mesActual, 3), ResultadoDeMuestra.VINCHUCA, pepe);
        pepe.archivarVotacion(votacion7);
        //Votacion votacion8 = this.crearVotacion(LocalDate.of(2020, mesActual, 3), ResultadoDeMuestra.VINCHUCA, pepe);
        pepe.archivarVotacion(votacion8);
        //Votacion votacion9 = this.crearVotacion(LocalDate.of(2020, mesActual, 3), ResultadoDeMuestra.VINCHUCA, pepe);
        pepe.archivarVotacion(votacion9);
        //Votacion votacion10 = this.crearVotacion(LocalDate.of(2020, mesActual, 3), ResultadoDeMuestra.VINCHUCA, pepe);
        pepe.archivarVotacion(votacion10);
        //Votacion votacion11 = this.crearVotacion(LocalDate.of(2020, mesActual, 3), ResultadoDeMuestra.VINCHUCA, pepe);
        pepe.archivarVotacion(votacion11);
        pepe.actualizarEstado();

        assertEquals(11,pepe.getVotaciones().size());

        assertEquals("Nivel Experto", pepe.getNivelDeConocimiento());
    }

    @Test
    public void siLaPersonaNoHizo10VotacionesEnUnMismoMesSuEstadoEsBasico() {
        //Votacion votacion1 = this.crearVotacion(LocalDate.of(2020, 5, 3), ResultadoDeMuestra.VINCHUCA, pepe);
        pepe.archivarVotacion(votacion1);
        //Votacion votacion2 = this.crearVotacion(LocalDate.of(2020, 5, 3), ResultadoDeMuestra.VINCHUCA, pepe);
        pepe.archivarVotacion(votacion2);
        //Votacion votacion3 = this.crearVotacion(LocalDate.of(2020, 5, 3), ResultadoDeMuestra.VINCHUCA, pepe);
        pepe.archivarVotacion(votacion3);
        //Votacion votacion4 = this.crearVotacion(LocalDate.of(2020, 5, 3), ResultadoDeMuestra.VINCHUCA, pepe);
        pepe.archivarVotacion(votacion4);

        assertEquals("Nivel Basico", pepe.getNivelDeConocimiento());
    }

    @Test
    public void siLaPersonaEnEstadoExpertoEnElMesSiguienteNoHizo10VotacionesVuelveAEstadoEstadoEsBasico() {
        pepe.setNivelDeConocimiento(new Experto());
        pepe.actualizarEstado();
        assertEquals("Nivel Basico", pepe.getNivelDeConocimiento());
    }

    @Test
    public void siLaPersonaHaceMasDe10VotacionesEnUnMesSuEstadoEsExperto() {
        pepe.actualizarEstado();
        assertEquals("Nivel Experto", pepa.getNivelDeConocimiento());
    }

    public Votacion crearVotacion(LocalDate fechaCreacion, ResultadoDeMuestra tipoResultadoMuestra, Participante participante) {
        Votacion votacion = new Votacion(tipoResultadoMuestra, participante);
        votacion.setFechaDeCreacion(fechaCreacion);
        return votacion;
    }


}
