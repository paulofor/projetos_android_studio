package br.com.lojadigicom.precomed.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.precomed.modelo.*;

public interface ProdutoPesquisaAgregadoI{

	// ComChaveEstrangeira
  	
		public Usuario getUsuario_Sincroniza(); 
		public void setUsuario_Sincroniza(Usuario item); 
		
		public void addListaUsuario_Sincroniza(Usuario item); 
		public Usuario getCorrenteUsuario_Sincroniza(); 
		
		
		public ModeloProduto getModeloProduto_ReferenteA(); 
		public void setModeloProduto_ReferenteA(ModeloProduto item); 
		
		public void addListaModeloProduto_ReferenteA(ModeloProduto item); 
		public ModeloProduto getCorrenteModeloProduto_ReferenteA(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
