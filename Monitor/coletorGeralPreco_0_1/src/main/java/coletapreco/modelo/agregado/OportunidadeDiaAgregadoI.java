package coletapreco.modelo.agregado;

import java.util.List;
import coletapreco.modelo.*;

public interface OportunidadeDiaAgregadoI{

	// ComChaveEstrangeira
  	
		public Produto getProduto_ReferenteA(); 
		public void setProduto_ReferenteA(Produto item); 
		
		public void addListaProduto_ReferenteA(Produto item); 
		public Produto getCorrenteProduto_ReferenteA(); 
		
		
		public NaturezaProduto getNaturezaProduto_PertenceA(); 
		public void setNaturezaProduto_PertenceA(NaturezaProduto item); 
		
		public void addListaNaturezaProduto_PertenceA(NaturezaProduto item); 
		public NaturezaProduto getCorrenteNaturezaProduto_PertenceA(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
