package coletapreco.modelo.agregado;

import java.util.List;
import coletapreco.modelo.*;

public interface PalavraProdutoAgregadoI{

	// ComChaveEstrangeira
  	
		public Palavra getPalavra_RelaciondoA(); 
		public void setPalavra_RelaciondoA(Palavra item); 
		
		public void addListaPalavra_RelaciondoA(Palavra item); 
		public Palavra getCorrentePalavra_RelaciondoA(); 
		
		
		public Produto getProduto_RelaciondoA(); 
		public void setProduto_RelaciondoA(Produto item); 
		
		public void addListaProduto_RelaciondoA(Produto item); 
		public Produto getCorrenteProduto_RelaciondoA(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
