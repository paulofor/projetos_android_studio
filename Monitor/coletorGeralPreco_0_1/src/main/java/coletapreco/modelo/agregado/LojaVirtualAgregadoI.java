package coletapreco.modelo.agregado;

import java.util.List;
import coletapreco.modelo.*;

public interface LojaVirtualAgregadoI{

	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
	
		public Produto getCorrenteProduto_Possui();
		public void addListaProduto_Possui(Produto item);
		public List<Produto> getListaProduto_Possui();
		public List<Produto> getListaProduto_PossuiOriginal();
		public List<Produto> getListaProduto_Possui(int qtde);
		public void setListaProduto_Possui(List<Produto> lista); 
		public void setListaProduto_PossuiByDao(List<Produto> lista); 
		public void criaVaziaListaProduto_Possui();
		public boolean existeListaProduto_Possui();
 		
		public CategoriaLoja getCorrenteCategoriaLoja_Possui();
		public void addListaCategoriaLoja_Possui(CategoriaLoja item);
		public List<CategoriaLoja> getListaCategoriaLoja_Possui();
		public List<CategoriaLoja> getListaCategoriaLoja_PossuiOriginal();
		public List<CategoriaLoja> getListaCategoriaLoja_Possui(int qtde);
		public void setListaCategoriaLoja_Possui(List<CategoriaLoja> lista); 
		public void setListaCategoriaLoja_PossuiByDao(List<CategoriaLoja> lista); 
		public void criaVaziaListaCategoriaLoja_Possui();
		public boolean existeListaCategoriaLoja_Possui();
 		
		public LojaNatureza getCorrenteLojaNatureza_Oferece();
		public void addListaLojaNatureza_Oferece(LojaNatureza item);
		public List<LojaNatureza> getListaLojaNatureza_Oferece();
		public List<LojaNatureza> getListaLojaNatureza_OfereceOriginal();
		public List<LojaNatureza> getListaLojaNatureza_Oferece(int qtde);
		public void setListaLojaNatureza_Oferece(List<LojaNatureza> lista); 
		public void setListaLojaNatureza_OfereceByDao(List<LojaNatureza> lista); 
		public void criaVaziaListaLojaNatureza_Oferece();
		public boolean existeListaLojaNatureza_Oferece();
 		
	
	// UmPraUm
	
}
