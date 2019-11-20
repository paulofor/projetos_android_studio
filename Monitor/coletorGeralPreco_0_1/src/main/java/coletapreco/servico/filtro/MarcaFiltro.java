
package coletapreco.servico.filtro;

import java.util.List;
import coletapreco.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class MarcaFiltro {

		private String _Nome;
		public String getNome () {
			return _Nome ;
		}
		public void setNome (String value) {
			_Nome  = value;
		}
		public String validaNome() {
			
			if (_Nome == null) {
				throw new RuntimeException("Nome eh nulo");
			}
			
			return _Nome ;
		}


		//public String Nome;

		public String getRequest() {
			String saida = "";
			if (_Nome!=null)
				saida += "&Nome="+_Nome;
			return saida;	
		}
}