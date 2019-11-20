
package  br.com.lojadigicom.coletorprecocliente.data.contract;

import java.util.List;
import br.com.lojadigicom.coletorprecocliente.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class UsuarioFiltro {

		private Usuario _Item;
		public Usuario getItem () {
			return _Item ;
		}
		public void setItem (Usuario value) {
			_Item  = value;
		}
		public Usuario validaItem() {
			
			if (_Item == null) {
				throw new RuntimeException("Item eh nulo");
			}
			
			return _Item ;
		}

		public String getParam() {
			String saida = "";
			return saida;
		}
}