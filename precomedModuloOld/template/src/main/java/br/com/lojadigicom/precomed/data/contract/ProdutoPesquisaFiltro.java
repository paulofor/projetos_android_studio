
package  br.com.lojadigicom.precomed.data.contract;

import java.util.List;
import br.com.lojadigicom.precomed.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class ProdutoPesquisaFiltro {

		private Timestamp _data_pesquisa;
		public Timestamp getdata_pesquisa () {
			return _data_pesquisa ;
		}
		public void setdata_pesquisa (Timestamp value) {
			_data_pesquisa  = value;
		}
		public Timestamp validadata_pesquisa() {
			
			if (_data_pesquisa == null) {
				throw new RuntimeException("data_pesquisa eh nulo");
			}
			
			return _data_pesquisa ;
		}

		public String getParam() {
			String saida = "";
			saida += "data_pesquisa=" + _data_pesquisa + "&";
			return saida;
		}
}