
package  br.com.lojadigicom.coletorprecocliente.data.contract;

import java.util.List;
import br.com.lojadigicom.coletorprecocliente.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class AppProdutoFiltro {

		private String _HashPesquisa;
		public String getHashPesquisa () {
			return _HashPesquisa ;
		}
		public void setHashPesquisa (String value) {
			_HashPesquisa  = value;
		}
		public String validaHashPesquisa() {
			
			if (_HashPesquisa == null) {
				throw new RuntimeException("HashPesquisa eh nulo");
			}
			
			return _HashPesquisa ;
		}

		public String getParam() {
			String saida = "";
			saida += "HashPesquisa=" + _HashPesquisa + "&";
			return saida;
		}
}