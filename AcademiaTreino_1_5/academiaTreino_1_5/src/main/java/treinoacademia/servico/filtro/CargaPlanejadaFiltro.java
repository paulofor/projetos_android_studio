
package treinoacademia.servico.filtro;

import java.util.List;
import treinoacademia.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class CargaPlanejadaFiltro {

		private Long _IdExercicio;
		public Long getIdExercicio () {
			return _IdExercicio ;
		}
		public void setIdExercicio (Long value) {
			_IdExercicio  = value;
		}
		public Long validaIdExercicio() {
			
			if (_IdExercicio == null) {
				throw new RuntimeException("IdExercicio eh nulo");
			}
			
			return _IdExercicio ;
		}


		//public Long IdExercicio;

		public String getRequest() {
			String saida = "";
			if (_IdExercicio!=null)
				saida += "&IdExercicio="+_IdExercicio;
			return saida;	
		}
}