package br.com.lojadigicom.capitalexterno.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.capitalexterno.modelo.*;

public interface ProdutoAgregadoI{

	// ComChaveEstrangeira
  	
		public LinhaProduto getLinhaProduto_PertenceA(); 
		public void setLinhaProduto_PertenceA(LinhaProduto item); 
		
		public void addListaLinhaProduto_PertenceA(LinhaProduto item); 
		public LinhaProduto getCorrenteLinhaProduto_PertenceA(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
