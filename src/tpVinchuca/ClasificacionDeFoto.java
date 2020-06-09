package tpVinchuca;

public enum ClasificacionDeFoto {
	
	VINCHUCA ("Vinchuca"),
	INDEFINIDA("Muestra Indefinida"),
    VINCHUCA_INFESTANS("Vinchuca Infestans"),
    VINCHUCA_SORDIDA("Vinchuca Sordida"),
    VINCHUCA_GUASAYANA("Vinchuca Gasayana"),
    CHINCHE_FOLIADA("Chinche Foliada"),
    PHITIA_CHINCHE("Phitia Chinche"),
    NINGUNA("Ninguna"),
    IMAGEN_POCO_CLARA("Imagen poco clara");
	private String valor;

	private ClasificacionDeFoto(String valor) {
		this.valor = valor;
	}

	public String getValor() {
		return valor;
	}	

}


