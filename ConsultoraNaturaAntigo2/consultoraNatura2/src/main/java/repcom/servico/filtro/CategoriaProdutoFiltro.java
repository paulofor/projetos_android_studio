
package repcom.servico.filtro;

import java.util.List;
import repcom.modelo.*;
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


		//public Long IdPai;

		public String getRequest() {
			String saida = "";
			if (_IdPai!=null)
				saida += "&IdPai="+_IdPai;
			return saida;	
		}
}