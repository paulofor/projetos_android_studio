
package coletapreco.servico.filtro;

import java.util.List;
import coletapreco.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class PalavraFiltro {

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
		private String _DescricaoPalavra;
		public String getDescricaoPalavra () {
			return _DescricaoPalavra ;
		}
		public void setDescricaoPalavra (String value) {
			_DescricaoPalavra  = value;
		}
		public String validaDescricaoPalavra() {
			
			if (_DescricaoPalavra == null) {
				throw new RuntimeException("DescricaoPalavra eh nulo");
			}
			
			return _DescricaoPalavra ;
		}


		//public String DescricaoPalavra;

		public String getRequest() {
			String saida = "";
			if (_DescricaoPalavra!=null)
				saida += "&DescricaoPalavra="+_DescricaoPalavra;
			return saida;	
		}
}