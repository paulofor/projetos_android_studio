package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.repcom.modelo.*;

public interface ClienteInteresseCategoriaAgregadoI{

	// ComChaveEstrangeira
  	
		public Cliente getCliente_Associada(); 
		public void setCliente_Associada(Cliente item); 
		
		public void addListaCliente_Associada(Cliente item); 
		public Cliente getCorrenteCliente_Associada(); 
		
		
		public CategoriaProduto getCategoriaProduto_Associada(); 
		public void setCategoriaProduto_Associada(CategoriaProduto item); 
		
		public void addListaCategoriaProduto_Associada(CategoriaProduto item); 
		public CategoriaProduto getCorrenteCategoriaProduto_Associada(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
