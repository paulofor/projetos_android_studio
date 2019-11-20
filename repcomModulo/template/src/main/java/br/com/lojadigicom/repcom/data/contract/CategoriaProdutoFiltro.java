
package  br.com.lojadigicom.repcom.data.contract;

import java.util.List;
import br.com.lojadigicom.repcom.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class CategoriaProdutoFiltro {

		private Long _IdPai;
		public Long getIdPai () {
			return _IdPai ;
		}
		public void setIdPai (Long value) {
			_IdPai  = value;
		}
		public Long validaIdPai() {
			
			if (_IdPai == null) {
				throw new RuntimeException("IdPai eh nulo");
			}
			
			return _IdPai ;
		}

		public String getParam() {
			String saida = "";
			saida += "IdPai=" + _IdPai + "&";
			return saida;
		}
}