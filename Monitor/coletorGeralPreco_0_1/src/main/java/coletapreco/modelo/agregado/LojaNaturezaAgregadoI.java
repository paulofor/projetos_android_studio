package coletapreco.modelo.agregado;

import java.util.List;
import coletapreco.modelo.*;

public interface LojaNaturezaAgregadoI{

	// ComChaveEstrangeira
  	
		public LojaVirtual getLojaVirtual_ReferenteA(); 
		public void setLojaVirtual_ReferenteA(LojaVirtual item); 
		
		public void addListaLojaVirtual_ReferenteA(LojaVirtual item); 
		public LojaVirtual getCorrenteLojaVirtual_ReferenteA(); 
		
		
		public NaturezaProduto getNaturezaProduto_ReferenteA(); 
		public void setNaturezaProduto_ReferenteA(NaturezaProduto item); 
		
		public void addListaNaturezaProduto_ReferenteA(NaturezaProduto item); 
		public NaturezaProduto getCorrenteNaturezaProduto_ReferenteA(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
