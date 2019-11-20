package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.coletorprecocliente.modelo.*;

public interface PalavraChavePesquisaAgregadoI{

	// ComChaveEstrangeira
  	
		public Usuario getUsuario_Sincroniza(); 
		public void setUsuario_Sincroniza(Usuario item); 
		
		public void addListaUsuario_Sincroniza(Usuario item); 
		public Usuario getCorrenteUsuario_Sincroniza(); 
		
		
		public NaturezaProduto getNaturezaProduto_ReferenteA(); 
		public void setNaturezaProduto_ReferenteA(NaturezaProduto item); 
		
		public void addListaNaturezaProduto_ReferenteA(NaturezaProduto item); 
		public NaturezaProduto getCorrenteNaturezaProduto_ReferenteA(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
