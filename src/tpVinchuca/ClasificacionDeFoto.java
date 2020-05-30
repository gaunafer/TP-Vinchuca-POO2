package tpVinchuca;

public enum ClasificacionDeFoto {
	
	    VINCHUCA_INFESTANS("Vinchuca", "Vinchuca Infestans"),
	    VINCHUCA_SORDIDA("Vinchuca", "Vinchuca Sordida"),
	    VINCHUCA_GUASAYANA("Vinchuca", "Vinchuca Gasayana"),
	    CHINCHE_FOLIADA("Chinche", "Chinche Foliada"),
	    PHITIA_CHINCHE("Chinche", "Phitia Chinche"),
	    NINGUNA("Imagen", "Ninguna"),
	    IMAGEN_POCO_CLARA("Imagen", "Imagen poco clara");
	    
	    private String tipo;
	    private String descripcion;

	    ClasificacionDeFoto(String tipo, String descripcion) {
	        this.tipo = tipo;
	        this.descripcion = descripcion;
	    }

	    public String getTipo() {
	        return tipo;
	    }

	    public String getDescripcion() {
	        return descripcion;
	    }
	}


