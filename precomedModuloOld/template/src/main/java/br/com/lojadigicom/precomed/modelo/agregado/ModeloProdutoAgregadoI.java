package br.com.lojadigicom.precomed.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.precomed.modelo.*;

public interface ModeloProdutoAgregadoI{

	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
	
		public ModeloProdutoProduto getCorrenteModeloProdutoProduto_ReferenteA();
		public void addListaModeloProdutoProduto_ReferenteA(ModeloProdutoProduto item);
		//public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteA();
		public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteAOriginal();
		//public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteA(int qtde);
		//public void setListaModeloProdutoProduto_ReferenteA(List<ModeloProdutoProduto> lista); 
		//public void setListaModeloProdutoProduto_ReferenteAByDao(List<ModeloProdutoProduto> lista); 
		//public void criaVaziaListaModeloProdutoProduto_ReferenteA();
		public boolean existeListaModeloProdutoProduto_ReferenteA();
 		
		public ProdutoPesquisa getCorrenteProdutoPesquisa_Viabiliza();
		public void addListaProdutoPesquisa_Viabiliza(ProdutoPesquisa item);
		//public List<ProdutoPesquisa> getListaProdutoPesquisa_Viabiliza();
		public List<ProdutoPesquisa> getListaProdutoPesquisa_ViabilizaOriginal();
		//public List<ProdutoPesquisa> getListaProdutoPesquisa_Viabiliza(int qtde);
		//public void setListaProdutoPesquisa_Viabiliza(List<ProdutoPesquisa> lista); 
		//public void setListaProdutoPesquisa_ViabilizaByDao(List<ProdutoPesquisa> lista); 
		//public void criaVaziaListaProdutoPesquisa_Viabiliza();
		public boolean existeListaProdutoPesquisa_Viabiliza();
 		
	
	// UmPraUm
	
}
