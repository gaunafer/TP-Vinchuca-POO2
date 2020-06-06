package tpVinchuca;

public enum ResultadoDeMuestra {
	
	VINCHUCA ("Vinchuca"),
	INDEFINIDA("Muestra Indefinida"),
	IMAGEN_POCO_LEGIBLE("Imagen poco legible");
	
	private String valor;

	private ResultadoDeMuestra(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}
}