package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.repcom.modelo.*;

public interface PrecoProdutoAgregadoI{

	// ComChaveEstrangeira
  	
		public Produto getProduto_PertenceA(); 
		public void setProduto_PertenceA(Produto item); 
		
		public void addListaProduto_PertenceA(Produto item); 
		public Produto getCorrenteProduto_PertenceA(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
