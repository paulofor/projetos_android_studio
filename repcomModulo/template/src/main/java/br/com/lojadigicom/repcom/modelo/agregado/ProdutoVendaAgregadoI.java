package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.repcom.modelo.*;

public interface ProdutoVendaAgregadoI{

	// ComChaveEstrangeira
  	
		public Produto getProduto_Associada(); 
		public void setProduto_Associada(Produto item); 
		
		public void addListaProduto_Associada(Produto item); 
		public Produto getCorrenteProduto_Associada(); 
		
		
		public Venda getVenda_Associada(); 
		public void setVenda_Associada(Venda item); 
		
		public void addListaVenda_Associada(Venda item); 
		public Venda getCorrenteVenda_Associada(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
