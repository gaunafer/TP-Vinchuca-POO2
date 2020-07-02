package tpVinchucas.filtro;

import java.time.LocalDate;

public enum OperadorLogico {
	
	MENORESA {
		/**
		 * Devuelve true si la fechaAComparar es anterior a la fechaDeComparacion.
		 * Devuelve false si es igual o posterior.
		 */
        @Override
        public Boolean comparar(LocalDate fechaAComparar, LocalDate fechaDeComparacion) {
            return fechaAComparar.isBefore(fechaDeComparacion);
        }
	},
    MAYORESA {
		/**
		 * Devuelve true si la fechaAComparar es posterior a la fechaDeComparacion.
		 * Devuelve false si es igual o anterior.
		 */
        @Override
        public Boolean comparar(LocalDate fechaAComparar, LocalDate fechaDeComparacion) {
        	return fechaAComparar.isAfter(fechaDeComparacion);
        }
	},
	IGUALESA {
		/**
		 * Devuelve true solo si la fechaAComparar es igual a la fechaDeComparacion.
		 */
		@Override
		public Boolean comparar(LocalDate fechaAComparar, LocalDate fechaDeComparacion) {
			return fechaAComparar.isEqual(fechaDeComparacion);
		}
	}
	;
        
	public abstract Boolean comparar(LocalDate fechaAComparar, LocalDate fechaDeComparacion);
	
}
