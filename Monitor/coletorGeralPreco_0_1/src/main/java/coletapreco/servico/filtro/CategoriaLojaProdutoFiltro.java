
package coletapreco.servico.filtro;

import java.util.List;
import coletapreco.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class CategoriaLojaProdutoFiltro {

		private CategoriaLojaProduto _Item;
		public CategoriaLojaProduto getItem () {
			return _Item ;
		}
		public void setItem (CategoriaLojaProduto value) {
			_Item  = value;
		}
		public CategoriaLojaProduto validaItem() {
			
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