
package coletapreco.servico.filtro;

import java.util.List;
import coletapreco.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class PrecoProdutoFiltro {

		private Produto _Produto;
		public Produto getProduto () {
			return _Produto ;
		}
		public void setProduto (Produto value) {
			_Produto  = value;
		}
		public Produto validaProduto() {
			
			if (_Produto == null) {
				throw new RuntimeException("Produto eh nulo");
			}
			
			return _Produto ;
		}



		public String getRequest() {
			String saida = "";
			return saida;	
		}
}