
package  br.com.lojadigicom.coletorprecocliente.data.contract;

import java.util.List;
import br.com.lojadigicom.coletorprecocliente.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class ProdutoClienteFiltro {

		private Long _IdNatureza;
		public Long getIdNatureza () {
			return _IdNatureza ;
		}
		public void setIdNatureza (Long value) {
			_IdNatureza  = value;
		}
		public Long validaIdNatureza() {
			
			if (_IdNatureza == null) {
				throw new RuntimeException("IdNatureza eh nulo");
			}
			
			return _IdNatureza ;
		}

		public String getParam() {
			String saida = "";
			saida += "IdNatureza=" + _IdNatureza + "&";
			return saida;
		}
}