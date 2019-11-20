package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.repcom.modelo.*;

public interface LinhaProdutoAgregadoI{

	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
	
		public Produto getCorrenteProduto_Possui();
		public void addListaProduto_Possui(Produto item);
		//public List<Produto> getListaProduto_Possui();
		public List<Produto> getListaProduto_PossuiOriginal();
		//public List<Produto> getListaProduto_Possui(int qtde);
		//public void setListaProduto_Possui(List<Produto> lista); 
		//public void setListaProduto_PossuiByDao(List<Produto> lista); 
		//public void criaVaziaListaProduto_Possui();
		public boolean existeListaProduto_Possui();
 		
	
	// UmPraUm
	
}
