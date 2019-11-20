package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.repcom.modelo.*;

public interface CategoriaProdutoAgregadoI{

	// ComChaveEstrangeira
  	
		public CategoriaProduto getCategoriaProduto_Pai(); 
		public void setCategoriaProduto_Pai(CategoriaProduto item); 
		
		
	
	// SemChaveEstrangeira
	
		public ClienteInteresseCategoria getCorrenteClienteInteresseCategoria_Associada();
		public void addListaClienteInteresseCategoria_Associada(ClienteInteresseCategoria item);
		//public List<ClienteInteresseCategoria> getListaClienteInteresseCategoria_Associada();
		public List<ClienteInteresseCategoria> getListaClienteInteresseCategoria_AssociadaOriginal();
		//public List<ClienteInteresseCategoria> getListaClienteInteresseCategoria_Associada(int qtde);
		//public void setListaClienteInteresseCategoria_Associada(List<ClienteInteresseCategoria> lista); 
		//public void setListaClienteInteresseCategoria_AssociadaByDao(List<ClienteInteresseCategoria> lista); 
		//public void criaVaziaListaClienteInteresseCategoria_Associada();
		public boolean existeListaClienteInteresseCategoria_Associada();
 		
		public CategoriaProdutoProduto getCorrenteCategoriaProdutoProduto_Possui();
		public void addListaCategoriaProdutoProduto_Possui(CategoriaProdutoProduto item);
		//public List<CategoriaProdutoProduto> getListaCategoriaProdutoProduto_Possui();
		public List<CategoriaProdutoProduto> getListaCategoriaProdutoProduto_PossuiOriginal();
		//public List<CategoriaProdutoProduto> getListaCategoriaProdutoProduto_Possui(int qtde);
		//public void setListaCategoriaProdutoProduto_Possui(List<CategoriaProdutoProduto> lista); 
		//public void setListaCategoriaProdutoProduto_PossuiByDao(List<CategoriaProdutoProduto> lista); 
		//public void criaVaziaListaCategoriaProdutoProduto_Possui();
		public boolean existeListaCategoriaProdutoProduto_Possui();
 		
		public CategoriaProduto getCorrenteCategoriaProduto_Pai();
		public void addListaCategoriaProduto_Pai(CategoriaProduto item);
		//public List<CategoriaProduto> getListaCategoriaProduto_Pai();
		public List<CategoriaProduto> getListaCategoriaProduto_PaiOriginal();
		//public List<CategoriaProduto> getListaCategoriaProduto_Pai(int qtde);
		//public void setListaCategoriaProduto_Pai(List<CategoriaProduto> lista); 
		//public void setListaCategoriaProduto_PaiByDao(List<CategoriaProduto> lista); 
		//public void criaVaziaListaCategoriaProduto_Pai();
		public boolean existeListaCategoriaProduto_Pai();
 		
	
	// UmPraUm
	
}
