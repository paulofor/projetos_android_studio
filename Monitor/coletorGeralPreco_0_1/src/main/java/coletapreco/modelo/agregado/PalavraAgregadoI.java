package coletapreco.modelo.agregado;

import java.util.List;
import coletapreco.modelo.*;

public interface PalavraAgregadoI{

	// ComChaveEstrangeira
  	
	
	// SemChaveEstrangeira
	
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
