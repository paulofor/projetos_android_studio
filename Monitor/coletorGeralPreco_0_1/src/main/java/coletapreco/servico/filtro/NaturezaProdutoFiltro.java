
package coletapreco.servico.filtro;

import java.util.List;
import coletapreco.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class NaturezaProdutoFiltro {

		private String _CodigoPesquisa;
		public String getCodigoPesquisa () {
			return _CodigoPesquisa ;
		}
		public void setCodigoPesquisa (String value) {
			_CodigoPesquisa  = value;
		}
		public String validaCodigoPesquisa() {
			
			if (_CodigoPesquisa == null) {
				throw new RuntimeException("CodigoPesquisa eh nulo");
			}
			
			return _CodigoPesquisa ;
		}
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


		//public String CodigoPesquisa;

		public String getRequest() {
			String saida = "";
			if (_CodigoPesquisa!=null)
				saida += "&CodigoPesquisa="+_CodigoPesquisa;
			return saida;	
		}
}