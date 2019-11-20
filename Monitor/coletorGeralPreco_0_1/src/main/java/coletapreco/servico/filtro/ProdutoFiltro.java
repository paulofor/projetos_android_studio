
package coletapreco.servico.filtro;

import java.util.List;
import coletapreco.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class ProdutoFiltro {

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
		private String _UrlProduto;
		public String getUrlProduto () {
			return _UrlProduto ;
		}
		public void setUrlProduto (String value) {
			_UrlProduto  = value;
		}
		public String validaUrlProduto() {
			
			if (_UrlProduto == null) {
				throw new RuntimeException("UrlProduto eh nulo");
			}
			
			return _UrlProduto ;
		}
		private String _DataInicioOportunidade;
		public String getDataInicioOportunidade () {
			return _DataInicioOportunidade ;
		}
		public void setDataInicioOportunidade (String value) {
			_DataInicioOportunidade  = value;
		}
		public String validaDataInicioOportunidade() {
			
			if (_DataInicioOportunidade == null) {
				throw new RuntimeException("DataInicioOportunidade eh nulo");
			}
			
			return _DataInicioOportunidade ;
		}
		private Float _PercentualMinimoOportunidade;
		public Float getPercentualMinimoOportunidade () {
			return _PercentualMinimoOportunidade ;
		}
		public void setPercentualMinimoOportunidade (Float value) {
			_PercentualMinimoOportunidade  = value;
		}
		public Float validaPercentualMinimoOportunidade() {
			
			if (_PercentualMinimoOportunidade == null) {
				throw new RuntimeException("PercentualMinimoOportunidade eh nulo");
			}
			
			return _PercentualMinimoOportunidade ;
		}
		private List _ListaPalavraProduto;
		public List getListaPalavraProduto () {
			return _ListaPalavraProduto ;
		}
		public void setListaPalavraProduto (List value) {
			_ListaPalavraProduto  = value;
		}
		public List validaListaPalavraProduto() {
			
			if (_ListaPalavraProduto == null) {
				throw new RuntimeException("ListaPalavraProduto eh nulo");
			}
			
			return _ListaPalavraProduto ;
		}


		//public String NomeProduto;
		//public Long CodigoLoja;
		//public String UrlProduto;
		//public String DataInicioOportunidade;
		//public Float PercentualMinimoOportunidade;
		//public List ListaPalavraProduto;

		public String getRequest() {
			String saida = "";
			if (_NomeProduto!=null)
				saida += "&NomeProduto="+_NomeProduto;
			if (_CodigoLoja!=null)
				saida += "&CodigoLoja="+_CodigoLoja;
			if (_UrlProduto!=null)
				saida += "&UrlProduto="+_UrlProduto;
			if (_DataInicioOportunidade!=null)
				saida += "&DataInicioOportunidade="+_DataInicioOportunidade;
			if (_PercentualMinimoOportunidade!=null)
				saida += "&PercentualMinimoOportunidade="+_PercentualMinimoOportunidade;
			if (_ListaPalavraProduto!=null)
				saida += "&ListaPalavraProduto="+_ListaPalavraProduto;
			return saida;	
		}
}