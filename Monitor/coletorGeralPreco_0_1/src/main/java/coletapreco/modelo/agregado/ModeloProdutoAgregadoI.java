package coletapreco.modelo.agregado;

import java.util.List;
import coletapreco.modelo.*;

public interface ModeloProdutoAgregadoI{

	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
	
		public ModeloProdutoProduto getCorrenteModeloProdutoProduto_ReferenteA();
		public void addListaModeloProdutoProduto_ReferenteA(ModeloProdutoProduto item);
		public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteA();
		public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteAOriginal();
		public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteA(int qtde);
		public void setListaModeloProdutoProduto_ReferenteA(List<ModeloProdutoProduto> lista); 
		public void setListaModeloProdutoProduto_ReferenteAByDao(List<ModeloProdutoProduto> lista); 
		public void criaVaziaListaModeloProdutoProduto_ReferenteA();
		public boolean existeListaModeloProdutoProduto_ReferenteA();
 		
	
	// UmPraUm
	
}
