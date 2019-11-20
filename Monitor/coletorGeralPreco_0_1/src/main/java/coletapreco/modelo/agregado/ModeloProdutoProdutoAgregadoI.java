package coletapreco.modelo.agregado;

import java.util.List;
import coletapreco.modelo.*;

public interface ModeloProdutoProdutoAgregadoI{

	// ComChaveEstrangeira
  	
		public ModeloProduto getModeloProduto_ReferenteA(); 
		public void setModeloProduto_ReferenteA(ModeloProduto item); 
		
		public void addListaModeloProduto_ReferenteA(ModeloProduto item); 
		public ModeloProduto getCorrenteModeloProduto_ReferenteA(); 
		
		
		public Produto getProduto_ReferenteA(); 
		public void setProduto_ReferenteA(Produto item); 
		
		public void addListaProduto_ReferenteA(Produto item); 
		public Produto getCorrenteProduto_ReferenteA(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
