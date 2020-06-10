package tpVinchuca;

import java.time.LocalDate;

public enum OperadorLogico {
	
	MENORESA {
        @Override
        public Boolean comparar(LocalDate fechaAComparar, LocalDate fechaDeComparacion) {
            return fechaAComparar.isBefore(fechaDeComparacion);
        }
	},
    MAYORESA {
        @Override
        public Boolean comparar(LocalDate fechaAComparar, LocalDate fechaDeComparacion) {
        	return fechaAComparar.isAfter(fechaDeComparacion);
        }
	},
	IGUALESA {
		@Override
		public Boolean comparar(LocalDate fechaAComparar, LocalDate fechaDeComparacion) {
			return fechaAComparar.isEqual(fechaDeComparacion);
		}
	}
	;
        
	public abstract Boolean comparar(LocalDate fechaAComparar, LocalDate fechaDeComparacion);
	
}
