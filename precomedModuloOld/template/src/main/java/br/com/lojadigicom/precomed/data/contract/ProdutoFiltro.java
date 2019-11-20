
package  br.com.lojadigicom.precomed.data.contract;

import java.util.List;
import br.com.lojadigicom.precomed.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class ProdutoFiltro {

		private LojaVirtual _LojaVirtual;
		public LojaVirtual getLojaVirtual () {
			return _LojaVirtual ;
		}
		public void setLojaVirtual (LojaVirtual value) {
			_LojaVirtual  = value;
		}
		public LojaVirtual validaLojaVirtual() {
			
			if (_LojaVirtual == null) {
				throw new RuntimeException("LojaVirtual eh nulo");
			}
			
			return _LojaVirtual ;
		}
		private ProdutoPesquisa _ProdutoPesquisa;
		public ProdutoPesquisa getProdutoPesquisa () {
			return _ProdutoPesquisa ;
		}
		public void setProdutoPesquisa (ProdutoPesquisa value) {
			_ProdutoPesquisa  = value;
		}
		public ProdutoPesquisa validaProdutoPesquisa() {
			
			if (_ProdutoPesquisa == null) {
				throw new RuntimeException("ProdutoPesquisa eh nulo");
			}
			
			return _ProdutoPesquisa ;
		}
		private String _NomeProduto;
		public String getNomeProduto () {
			return _NomeProduto ;
		}
		public void setNomeProduto (String value) {
			_NomeProduto  = value;
		}
		public String validaNomeProduto() {
			
			if (_NomeProduto == null) {
				throw new RuntimeException("NomeProduto eh nulo");
			}
			
			return _NomeProduto ;
		}
		private Long _CodigoLoja;
		public Long getCodigoLoja () {
			return _CodigoLoja ;
		}
		public void setCodigoLoja (Long value) {
			_CodigoLoja  = value;
		}
		public Long validaCodigoLoja() {
			
			if (_CodigoLoja == null) {
				throw new RuntimeException("CodigoLoja eh nulo");
			}
			
			return _CodigoLoja ;
		}

		public String getParam() {
			String saida = "";
			saida += "NomeProduto=" + _NomeProduto + "&";
			saida += "CodigoLoja=" + _CodigoLoja + "&";
			return saida;
		}
}