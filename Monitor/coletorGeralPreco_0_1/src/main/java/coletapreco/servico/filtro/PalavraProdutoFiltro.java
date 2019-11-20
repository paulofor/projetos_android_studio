
package coletapreco.servico.filtro;

import java.util.List;
import coletapreco.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class PalavraProdutoFiltro {

		private PalavraProduto _Item;
		public PalavraProduto getItem () {
			return _Item ;
		}
		public void setItem (PalavraProduto value) {
			_Item  = value;
		}
		public PalavraProduto validaItem() {
			
			if (_Item == null) {
				throw new RuntimeException("Item eh nulo");
			}
			
			return _Item ;
		}



		public String getRequest() {
			String saida = "";
			return saida;	
		}
}