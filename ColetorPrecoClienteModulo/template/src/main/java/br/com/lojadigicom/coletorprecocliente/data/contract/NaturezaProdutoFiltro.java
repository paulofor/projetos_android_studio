
package  br.com.lojadigicom.coletorprecocliente.data.contract;

import java.util.List;
import br.com.lojadigicom.coletorprecocliente.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class NaturezaProdutoFiltro {

		private Long _IdUsuario;
		public Long getIdUsuario () {
			return _IdUsuario ;
		}
		public void setIdUsuario (Long value) {
			_IdUsuario  = value;
		}
		public Long validaIdUsuario() {
			
			if (_IdUsuario == null) {
				throw new RuntimeException("IdUsuario eh nulo");
			}
			
			return _IdUsuario ;
		}

		public String getParam() {
			String saida = "";
			saida += "IdUsuario=" + _IdUsuario + "&";
			return saida;
		}
}