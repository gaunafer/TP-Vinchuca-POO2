package tpVinchucas.niveldeConocimiento;

import tpVinchuca.AplicacionVinchucas;
import tpVinchuca.Participante;

public class ExpertoValidado extends Experto{
	

	/**
	 * Crea una instancia de nivel de conocimiento {@code ExpertoValidado}
	 * @param aplicacionVinchucas
	 */
	public ExpertoValidado(AplicacionVinchucas aplicacionVinchucas) {
		super(aplicacionVinchucas);
		
	}

	/**
	 * El participante con nivel de conocimiento {@code ExpertoValidado} no 
	 * cambia su nivel de conocimiento, por lo que este metodo no realiza cambios.
	 */
	@Override
	public void cambiarABasico(Participante participante) {
				
	}

	/**
	 * El participante con nivel de conocimiento {@code ExpertoValidado} no 
	 * cambia su nivel de conocimiento, por lo que este metodo no realiza cambios.
	 */
	@Override
	public void cambiarAExperto(Participante participante) {
		
	}

}
