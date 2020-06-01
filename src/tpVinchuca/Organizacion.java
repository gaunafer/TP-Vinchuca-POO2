package tpVinchuca;


public class Organizacion implements IObservadoresDeZonas {
	
	private TipoDeOrganizacion tipoDeOrganizacion;
	private Ubicacion ubicacion;
	private Integer cantidadDeTrabajadores;
	private IFuncionalidadExterna funcionalidadCreacionDeMuestra;
	private IFuncionalidadExterna funcionalidadValidacionDeMuestra;
	
	/**
	 * Crea una instancia de la clase Organizacion
	 * @param tipoDeOrganizacion Uno de los tipos de TipoDeOrganizacion
	 * @param ubicacion Localizacion de la organizacion expresada segun la clase Ubicacion
	 * @param cantidadDeTrabajadores Cantidad de trabajadorxs que posee la organizacion
	 */
	public Organizacion(TipoDeOrganizacion tipoDeOrganizacion, Ubicacion ubicacion, Integer cantidadDeTrabajadores) {
		this.tipoDeOrganizacion = tipoDeOrganizacion;
		this.ubicacion = ubicacion;
		this.cantidadDeTrabajadores = cantidadDeTrabajadores;
	}

	/**
	 * Devuelve el tipo de la organizacion
	 */
	public TipoDeOrganizacion getTipoDeOrganizacion() {
		return tipoDeOrganizacion;
	}

	/**
	 * Devuelve la ubicacion de la organizacion
	 */
	public Ubicacion getUbicacion() {
		return ubicacion;
	}

	/**
	 * Devuelve la cantidad de trabajadorxs de la organizacion
	 */
	public Integer getCantidadDeTrabajadores() {
		return cantidadDeTrabajadores;
	}

	/**
	 * Devuelve la funcionalidad externa usada para los casos en los que se crea una muestra
	 */
	public IFuncionalidadExterna getFuncionalidadCreacionDeMuestra() {
		return funcionalidadCreacionDeMuestra;
	}

	/**
	 * Devuelve la funcionalidad externa usada para los casos en los que se valida una muestra
	 */
	public IFuncionalidadExterna getFuncionalidadValidacionDeMuestra() {
		return funcionalidadValidacionDeMuestra;
	}

	/**
	 * Funcionalidad utilizada en caso de la creacion de una muestra
	 * @param funcionalidadCreacionDeMuestra Objeto que implementa la interfaz IFuncionalidadExterna
	 */
	public void setFuncionalidadCreacionDeMuestra(IFuncionalidadExterna funcionalidadCreacionDeMuestra) {
		this.funcionalidadCreacionDeMuestra = funcionalidadCreacionDeMuestra;
	}

	/**
	 * Funcionalidad utilizada en caso de la validacion de una muestra
	 * @param funcionalidadCreacionDeMuestra Objeto que implementa la interfaz IFuncionalidadExterna
	 */
	public void setFuncionalidadValidacionDeMuestra(IFuncionalidadExterna funcionalidadValidacionDeMuestra) {
		this.funcionalidadValidacionDeMuestra = funcionalidadValidacionDeMuestra;
	}

	/**
	 * Metodo de la interaz IObservadoresDeZona.
	 * Convoca a la funcionalidad externa de creacion de muestra.
	 */
	@Override
	public void updateCreacionMuestra(Muestra muestra, ZonaDeCobertura zona) {
		funcionalidadCreacionDeMuestra.nuevoEvento(this, zona, muestra);
	}

	/**
	 * Metodo de la interaz IObservadoresDeZona.
	 * Convoca a la funcionalidad externa de validacion de muestra.
	 */
	@Override
	public void updateValidacionMuestra(Muestra muestra, ZonaDeCobertura zona) {
		funcionalidadValidacionDeMuestra.nuevoEvento(this, zona, muestra);
	}

}
