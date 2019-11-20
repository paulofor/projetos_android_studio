
package  br.com.lojadigicom.precomed.data.contract;

import java.util.List;
import br.com.lojadigicom.precomed.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class MarcaFiltro {

		private String _NomeMarca;
		public String getNomeMarca () {
			return _NomeMarca ;
		}
		public void setNomeMarca (String value) {
			_NomeMarca  = value;
		}
		public String validaNomeMarca() {
			
			if (_NomeMarca == null) {
				throw new RuntimeException("NomeMarca eh nulo");
			}
			
			return _NomeMarca ;
		}

		public String getParam() {
			String saida = "";
			saida += "NomeMarca=" + _NomeMarca + "&";
			return saida;
		}
}