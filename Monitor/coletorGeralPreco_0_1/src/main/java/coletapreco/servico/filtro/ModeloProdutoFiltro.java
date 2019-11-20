
package coletapreco.servico.filtro;

import java.util.List;
import coletapreco.modelo.*;
import android.content.Context;
import java.sql.Timestamp;



public class ModeloProdutoFiltro {

		private ModeloProduto _ModeloNovo;
		public ModeloProduto getModeloNovo () {
			return _ModeloNovo ;
		}
		public void setModeloNovo (ModeloProduto value) {
			_ModeloNovo  = value;
		}
		public ModeloProduto validaModeloNovo() {
			
			if (_ModeloNovo == null) {
				throw new RuntimeException("ModeloNovo eh nulo");
			}
			
			return _ModeloNovo ;
		}



		public String getRequest() {
			String saida = "";
			return saida;	
		}
}