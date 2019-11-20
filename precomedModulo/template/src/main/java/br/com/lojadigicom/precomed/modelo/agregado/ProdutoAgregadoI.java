package br.com.lojadigicom.precomed.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.precomed.modelo.*;

public interface ProdutoAgregadoI{

	// ComChaveEstrangeira
  	
		public LojaVirtual getLojaVirtual_LidoEm(); 
		public void setLojaVirtual_LidoEm(LojaVirtual item); 
		
		public void addListaLojaVirtual_LidoEm(LojaVirtual item); 
		public LojaVirtual getCorrenteLojaVirtual_LidoEm(); 
		
		
		public Marca getMarca_Possui(); 
		public void setMarca_Possui(Marca item); 
		
		public void addListaMarca_Possui(Marca item); 
		public Marca getCorrenteMarca_Possui(); 
		
		
	
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
 		
		public PrecoProduto getCorrentePrecoProduto_Possui();
		public void addListaPrecoProduto_Possui(PrecoProduto item);
		//public List<PrecoProduto> getListaPrecoProduto_Possui();
		public List<PrecoProduto> getListaPrecoProduto_PossuiOriginal();
		//public List<PrecoProduto> getListaPrecoProduto_Possui(int qtde);
		//public void setListaPrecoProduto_Possui(List<PrecoProduto> lista); 
		//public void setListaPrecoProduto_PossuiByDao(List<PrecoProduto> lista); 
		//public void criaVaziaListaPrecoProduto_Possui();
		public boolean existeListaPrecoProduto_Possui();
 		
	
	// UmPraUm
	
}
