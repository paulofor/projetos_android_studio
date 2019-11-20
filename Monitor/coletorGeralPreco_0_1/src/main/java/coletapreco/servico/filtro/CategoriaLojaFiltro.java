
package coletapreco.servico.filtro;

import java.util.List;
import coletapreco.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class CategoriaLojaFiltro {

		private CategoriaLoja _Item;
		public CategoriaLoja getItem () {
			return _Item ;
		}
		public void setItem (CategoriaLoja value) {
			_Item  = value;
		}
		public CategoriaLoja validaItem() {
			
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