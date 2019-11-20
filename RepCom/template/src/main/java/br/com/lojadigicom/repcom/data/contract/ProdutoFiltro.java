
package  br.com.lojadigicom.repcom.data.contract;

import java.util.List;
import br.com.lojadigicom.repcom.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class ProdutoFiltro {

		private String _url;
		public String geturl () {
			return _url ;
		}
		public void seturl (String value) {
			_url  = value;
		}
		public String validaurl() {
			
			if (_url == null) {
				throw new RuntimeException("url eh nulo");
			}
			
			return _url ;
		}
		private Produto _Item;
		public Produto getItem () {
			return _Item ;
		}
		public void setItem (Produto value) {
			_Item  = value;
		}
		public Produto validaItem() {
			
			if (_Item == null) {
				throw new RuntimeException("Item eh nulo");
			}
			
			return _Item ;
		}
		private Long _IdCategoria;
		public Long getIdCategoria () {
			return _IdCategoria ;
		}
		public void setIdCategoria (Long value) {
			_IdCategoria  = value;
		}
		public Long validaIdCategoria() {
			
			if (_IdCategoria == null) {
				throw new RuntimeException("IdCategoria eh nulo");
			}
			
			return _IdCategoria ;
		}

		public String getParam() {
			String saida = "";
			saida += "url=" + _url + "&";
			saida += "IdCategoria=" + _IdCategoria + "&";
			return saida;
		}
}