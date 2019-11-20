package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.repcom.modelo.*;

public interface ProdutoPedidoFornecedorAgregadoI{

	// ComChaveEstrangeira
  	
		public PedidoFornecedor getPedidoFornecedor_Associada(); 
		public void setPedidoFornecedor_Associada(PedidoFornecedor item); 
		
		public void addListaPedidoFornecedor_Associada(PedidoFornecedor item); 
		public PedidoFornecedor getCorrentePedidoFornecedor_Associada(); 
		
		
		public Produto getProduto_Associada(); 
		public void setProduto_Associada(Produto item); 
		
		public void addListaProduto_Associada(Produto item); 
		public Produto getCorrenteProduto_Associada(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
