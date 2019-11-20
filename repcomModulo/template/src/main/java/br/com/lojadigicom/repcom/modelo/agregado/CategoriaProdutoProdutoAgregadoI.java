package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.repcom.modelo.*;

public interface CategoriaProdutoProdutoAgregadoI{

	// ComChaveEstrangeira
  	
		public CategoriaProduto getCategoriaProduto_ReferenteA(); 
		public void setCategoriaProduto_ReferenteA(CategoriaProduto item); 
		
		public void addListaCategoriaProduto_ReferenteA(CategoriaProduto item); 
		public CategoriaProduto getCorrenteCategoriaProduto_ReferenteA(); 
		
		
		public Produto getProduto_ReferenteA(); 
		public void setProduto_ReferenteA(Produto item); 
		
		public void addListaProduto_ReferenteA(Produto item); 
		public Produto getCorrenteProduto_ReferenteA(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
