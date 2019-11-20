package coletapreco.modelo.agregado;

import java.util.List;
import coletapreco.modelo.*;

public interface CategoriaLojaProdutoAgregadoI{

	// ComChaveEstrangeira
  	
		public CategoriaLoja getCategoriaLoja_ReferenteA(); 
		public void setCategoriaLoja_ReferenteA(CategoriaLoja item); 
		
		public void addListaCategoriaLoja_ReferenteA(CategoriaLoja item); 
		public CategoriaLoja getCorrenteCategoriaLoja_ReferenteA(); 
		
		
		public Produto getProduto_ReferenteA(); 
		public void setProduto_ReferenteA(Produto item); 
		
		public void addListaProduto_ReferenteA(Produto item); 
		public Produto getCorrenteProduto_ReferenteA(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
