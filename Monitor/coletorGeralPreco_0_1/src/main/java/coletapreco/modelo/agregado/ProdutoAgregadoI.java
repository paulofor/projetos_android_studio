package coletapreco.modelo.agregado;

import java.util.List;
import coletapreco.modelo.*;

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
		public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteA();
		public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteAOriginal();
		public List<ModeloProdutoProduto> getListaModeloProdutoProduto_ReferenteA(int qtde);
		public void setListaModeloProdutoProduto_ReferenteA(List<ModeloProdutoProduto> lista); 
		public void setListaModeloProdutoProduto_ReferenteAByDao(List<ModeloProdutoProduto> lista); 
		public void criaVaziaListaModeloProdutoProduto_ReferenteA();
		public boolean existeListaModeloProdutoProduto_ReferenteA();
 		
		public PrecoProduto getCorrentePrecoProduto_Possui();
		public void addListaPrecoProduto_Possui(PrecoProduto item);
		public List<PrecoProduto> getListaPrecoProduto_Possui();
		public List<PrecoProduto> getListaPrecoProduto_PossuiOriginal();
		public List<PrecoProduto> getListaPrecoProduto_Possui(int qtde);
		public void setListaPrecoProduto_Possui(List<PrecoProduto> lista); 
		public void setListaPrecoProduto_PossuiByDao(List<PrecoProduto> lista); 
		public void criaVaziaListaPrecoProduto_Possui();
		public boolean existeListaPrecoProduto_Possui();
 		
		public CategoriaLojaProduto getCorrenteCategoriaLojaProduto_Possui();
		public void addListaCategoriaLojaProduto_Possui(CategoriaLojaProduto item);
		public List<CategoriaLojaProduto> getListaCategoriaLojaProduto_Possui();
		public List<CategoriaLojaProduto> getListaCategoriaLojaProduto_PossuiOriginal();
		public List<CategoriaLojaProduto> getListaCategoriaLojaProduto_Possui(int qtde);
		public void setListaCategoriaLojaProduto_Possui(List<CategoriaLojaProduto> lista); 
		public void setListaCategoriaLojaProduto_PossuiByDao(List<CategoriaLojaProduto> lista); 
		public void criaVaziaListaCategoriaLojaProduto_Possui();
		public boolean existeListaCategoriaLojaProduto_Possui();
 		
		public OportunidadeDia getCorrenteOportunidadeDia_PodePossuir();
		public void addListaOportunidadeDia_PodePossuir(OportunidadeDia item);
		public List<OportunidadeDia> getListaOportunidadeDia_PodePossuir();
		public List<OportunidadeDia> getListaOportunidadeDia_PodePossuirOriginal();
		public List<OportunidadeDia> getListaOportunidadeDia_PodePossuir(int qtde);
		public void setListaOportunidadeDia_PodePossuir(List<OportunidadeDia> lista); 
		public void setListaOportunidadeDia_PodePossuirByDao(List<OportunidadeDia> lista); 
		public void criaVaziaListaOportunidadeDia_PodePossuir();
		public boolean existeListaOportunidadeDia_PodePossuir();
 		
		public PalavraProduto getCorrentePalavraProduto_Possui();
		public void addListaPalavraProduto_Possui(PalavraProduto item);
		public List<PalavraProduto> getListaPalavraProduto_Possui();
		public List<PalavraProduto> getListaPalavraProduto_PossuiOriginal();
		public List<PalavraProduto> getListaPalavraProduto_Possui(int qtde);
		public void setListaPalavraProduto_Possui(List<PalavraProduto> lista); 
		public void setListaPalavraProduto_PossuiByDao(List<PalavraProduto> lista); 
		public void criaVaziaListaPalavraProduto_Possui();
		public boolean existeListaPalavraProduto_Possui();
 		
	
	// UmPraUm
	
}
