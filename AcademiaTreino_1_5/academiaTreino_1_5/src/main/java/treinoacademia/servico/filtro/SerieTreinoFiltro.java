
package treinoacademia.servico.filtro;

import java.util.List;
import treinoacademia.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class SerieTreinoFiltro {

		private SerieTreino _Item;
		public SerieTreino getItem () {
			return _Item ;
		}
		public void setItem (SerieTreino value) {
			_Item  = value;
		}
		public SerieTreino validaItem() {
			
			if (_Item == null) {
				throw new RuntimeException("Item eh nulo");
			}
			
			return _Item ;
		}
		private Long _IdSerieTreino;
		public Long getIdSerieTreino () {
			return _IdSerieTreino ;
		}
		public void setIdSerieTreino (Long value) {
			_IdSerieTreino  = value;
		}
		public Long validaIdSerieTreino() {
			
			if (_IdSerieTreino == null) {
				throw new RuntimeException("IdSerieTreino eh nulo");
			}
			
			return _IdSerieTreino ;
		}


		//public Long IdSerieTreino;

		public String getRequest() {
			String saida = "";
			if (_IdSerieTreino!=null)
				saida += "&IdSerieTreino="+_IdSerieTreino;
			return saida;	
		}
}