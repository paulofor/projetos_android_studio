package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.coletorprecocliente.modelo.*;

public interface OportunidadeDiaAgregadoI{

	// ComChaveEstrangeira
  	
		public NaturezaProduto getNaturezaProduto_PertenceA(); 
		public void setNaturezaProduto_PertenceA(NaturezaProduto item); 
		
		public void addListaNaturezaProduto_PertenceA(NaturezaProduto item); 
		public NaturezaProduto getCorrenteNaturezaProduto_PertenceA(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
