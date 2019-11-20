package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.coletorprecocliente.modelo.*;

public interface PrecoDiarioClienteAgregadoI{

	// ComChaveEstrangeira
  	
		public ProdutoCliente getProdutoCliente_PertenceA(); 
		public void setProdutoCliente_PertenceA(ProdutoCliente item); 
		
		public void addListaProdutoCliente_PertenceA(ProdutoCliente item); 
		public ProdutoCliente getCorrenteProdutoCliente_PertenceA(); 
		
		
		public Usuario getUsuario_Sincroniza(); 
		public void setUsuario_Sincroniza(Usuario item); 
		
		public void addListaUsuario_Sincroniza(Usuario item); 
		public Usuario getCorrenteUsuario_Sincroniza(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
