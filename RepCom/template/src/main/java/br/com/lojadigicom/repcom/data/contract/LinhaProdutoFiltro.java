
package  br.com.lojadigicom.repcom.data.contract;

import java.util.List;
import br.com.lojadigicom.repcom.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class LinhaProdutoFiltro {

		private Long _IdLinhaProduto;
		public Long getIdLinhaProduto () {
			return _IdLinhaProduto ;
		}
		public void setIdLinhaProduto (Long value) {
			_IdLinhaProduto  = value;
		}
		public Long validaIdLinhaProduto() {
			
			if (_IdLinhaProduto == null) {
				throw new RuntimeException("IdLinhaProduto eh nulo");
			}
			
			return _IdLinhaProduto ;
		}

		public String getParam() {
			String saida = "";
			saida += "IdLinhaProduto=" + _IdLinhaProduto + "&";
			return saida;
		}
}