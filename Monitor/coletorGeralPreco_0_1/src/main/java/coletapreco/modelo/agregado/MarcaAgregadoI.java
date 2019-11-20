package coletapreco.modelo.agregado;

import java.util.List;
import coletapreco.modelo.*;

public interface MarcaAgregadoI{

	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
	
		public Produto getCorrenteProduto_ReferenteA();
		public void addListaProduto_ReferenteA(Produto item);
		public List<Produto> getListaProduto_ReferenteA();
		public List<Produto> getListaProduto_ReferenteAOriginal();
		public List<Produto> getListaProduto_ReferenteA(int qtde);
		public void setListaProduto_ReferenteA(List<Produto> lista); 
		public void setListaProduto_ReferenteAByDao(List<Produto> lista); 
		public void criaVaziaListaProduto_ReferenteA();
		public boolean existeListaProduto_ReferenteA();
 		
	
	// UmPraUm
	
}
