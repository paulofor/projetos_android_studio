package coletapreco.modelo.agregado;

import java.util.List;
import coletapreco.modelo.*;

public interface CategoriaLojaAgregadoI{

	// ComChaveEstrangeira
  	
		public CategoriaLoja getCategoriaLoja_Filho(); 
		public void setCategoriaLoja_Filho(CategoriaLoja item); 
		
		
		public NaturezaProduto getNaturezaProduto_ReferenteA(); 
		public void setNaturezaProduto_ReferenteA(NaturezaProduto item); 
		
		public void addListaNaturezaProduto_ReferenteA(NaturezaProduto item); 
		public NaturezaProduto getCorrenteNaturezaProduto_ReferenteA(); 
		
		
		public LojaVirtual getLojaVirtual_PertenceA(); 
		public void setLojaVirtual_PertenceA(LojaVirtual item); 
		
		public void addListaLojaVirtual_PertenceA(LojaVirtual item); 
		public LojaVirtual getCorrenteLojaVirtual_PertenceA(); 
		
		
	
	// SemChaveEstrangeira
	
		public CategoriaLojaProduto getCorrenteCategoriaLojaProduto_Possui();
		public void addListaCategoriaLojaProduto_Possui(CategoriaLojaProduto item);
		public List<CategoriaLojaProduto> getListaCategoriaLojaProduto_Possui();
		public List<CategoriaLojaProduto> getListaCategoriaLojaProduto_PossuiOriginal();
		public List<CategoriaLojaProduto> getListaCategoriaLojaProduto_Possui(int qtde);
		public void setListaCategoriaLojaProduto_Possui(List<CategoriaLojaProduto> lista); 
		public void setListaCategoriaLojaProduto_PossuiByDao(List<CategoriaLojaProduto> lista); 
		public void criaVaziaListaCategoriaLojaProduto_Possui();
		public boolean existeListaCategoriaLojaProduto_Possui();
 		
		public CategoriaLoja getCorrenteCategoriaLoja_Filho();
		public void addListaCategoriaLoja_Filho(CategoriaLoja item);
		public List<CategoriaLoja> getListaCategoriaLoja_Filho();
		public List<CategoriaLoja> getListaCategoriaLoja_FilhoOriginal();
		public List<CategoriaLoja> getListaCategoriaLoja_Filho(int qtde);
		public void setListaCategoriaLoja_Filho(List<CategoriaLoja> lista); 
		public void setListaCategoriaLoja_FilhoByDao(List<CategoriaLoja> lista); 
		public void criaVaziaListaCategoriaLoja_Filho();
		public boolean existeListaCategoriaLoja_Filho();
 		
	
	// UmPraUm
	
}
