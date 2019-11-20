package repcom.modelo.agregado;

import java.util.List;
import repcom.modelo.*;

public interface PedidoFornecedorAgregadoI{

	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
	
		public ProdutoPedidoFornecedor getCorrenteProdutoPedidoFornecedor_Associada();
		public void addListaProdutoPedidoFornecedor_Associada(ProdutoPedidoFornecedor item);
		public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_Associada();
		public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_AssociadaOriginal();
		public List<ProdutoPedidoFornecedor> getListaProdutoPedidoFornecedor_Associada(int qtde);
		public void setListaProdutoPedidoFornecedor_Associada(List<ProdutoPedidoFornecedor> lista); 
		public void setListaProdutoPedidoFornecedor_AssociadaByDao(List<ProdutoPedidoFornecedor> lista); 
		public void criaVaziaListaProdutoPedidoFornecedor_Associada();
		public boolean existeListaProdutoPedidoFornecedor_Associada();
 		
	
	// UmPraUm
	
}
