
package coletapreco.servico.filtro;

import java.util.List;
import coletapreco.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class OportunidadeDiaFiltro {

		private NaturezaProduto _Natureza;
		public NaturezaProduto getNatureza () {
			return _Natureza ;
		}
		public void setNatureza (NaturezaProduto value) {
			_Natureza  = value;
		}
		public NaturezaProduto validaNatureza() {
			
			if (_Natureza == null) {
				throw new RuntimeException("Natureza eh nulo");
			}
			
			return _Natureza ;
		}



		public String getRequest() {
			String saida = "";
			return saida;	
		}
}