
package repcom.servico.filtro;

import java.util.List;
import repcom.modelo.*;
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


		//public Long IdLinhaProduto;

		public String getRequest() {
			String saida = "";
			if (_IdLinhaProduto!=null)
				saida += "&IdLinhaProduto="+_IdLinhaProduto;
			return saida;	
		}
}