
package  br.com.lojadigicom.repcom.data.contract;

import java.util.List;
import br.com.lojadigicom.repcom.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class ParcelaVendaFiltro {

		private Venda _ItemVenda;
		public Venda getItemVenda () {
			return _ItemVenda ;
		}
		public void setItemVenda (Venda value) {
			_ItemVenda  = value;
		}
		public Venda validaItemVenda() {
			
			if (_ItemVenda == null) {
				throw new RuntimeException("ItemVenda eh nulo");
			}
			
			return _ItemVenda ;
		}

		public String getParam() {
			String saida = "";
			return saida;
		}
}