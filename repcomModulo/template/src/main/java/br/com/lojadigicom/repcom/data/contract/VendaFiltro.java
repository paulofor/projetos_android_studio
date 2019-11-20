
package  br.com.lojadigicom.repcom.data.contract;

import java.util.List;
import br.com.lojadigicom.repcom.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class VendaFiltro {

		private Venda _itemAtualizacao;
		public Venda getitemAtualizacao () {
			return _itemAtualizacao ;
		}
		public void setitemAtualizacao (Venda value) {
			_itemAtualizacao  = value;
		}
		public Venda validaitemAtualizacao() {
			
			if (_itemAtualizacao == null) {
				throw new RuntimeException("itemAtualizacao eh nulo");
			}
			
			return _itemAtualizacao ;
		}

		public String getParam() {
			String saida = "";
			return saida;
		}
}