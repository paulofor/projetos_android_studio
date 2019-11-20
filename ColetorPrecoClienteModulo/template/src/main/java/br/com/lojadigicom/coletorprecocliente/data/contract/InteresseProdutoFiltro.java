
package  br.com.lojadigicom.coletorprecocliente.data.contract;

import java.util.List;
import br.com.lojadigicom.coletorprecocliente.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class InteresseProdutoFiltro {

		private String _CodUsuario;
		public String getCodUsuario () {
			return _CodUsuario ;
		}
		public void setCodUsuario (String value) {
			_CodUsuario  = value;
		}
		public String validaCodUsuario() {
			
			if (_CodUsuario == null) {
				throw new RuntimeException("CodUsuario eh nulo");
			}
			
			return _CodUsuario ;
		}

		public String getParam() {
			String saida = "";
			saida += "CodUsuario=" + _CodUsuario + "&";
			return saida;
		}
}