
package repcom.servico.filtro;

import java.util.List;
import repcom.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class ClienteFiltro {

		private String _CodigoAgendaTel;
		public String getCodigoAgendaTel () {
			return _CodigoAgendaTel ;
		}
		public void setCodigoAgendaTel (String value) {
			_CodigoAgendaTel  = value;
		}
		public String validaCodigoAgendaTel() {
			
			if (_CodigoAgendaTel == null) {
				throw new RuntimeException("CodigoAgendaTel eh nulo");
			}
			
			return _CodigoAgendaTel ;
		}
		private Cliente _Item;
		public Cliente getItem () {
			return _Item ;
		}
		public void setItem (Cliente value) {
			_Item  = value;
		}
		public Cliente validaItem() {
			
			if (_Item == null) {
				throw new RuntimeException("Item eh nulo");
			}
			
			return _Item ;
		}


		//public String CodigoAgendaTel;

		public String getRequest() {
			String saida = "";
			if (_CodigoAgendaTel!=null)
				saida += "&CodigoAgendaTel="+_CodigoAgendaTel;
			return saida;	
		}
}