package br.com.lojadigicom.coletorprecocliente.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.coletorprecocliente.modelo.*;

public interface InteresseProdutoAgregadoI{

	// ComChaveEstrangeira
  	
		public ProdutoCliente getProdutoCliente_ReferenteA(); 
		public void setProdutoCliente_ReferenteA(ProdutoCliente item); 
		
		public void addListaProdutoCliente_ReferenteA(ProdutoCliente item); 
		public ProdutoCliente getCorrenteProdutoCliente_ReferenteA(); 
		
		
		public Usuario getUsuario_Sincroniza(); 
		public void setUsuario_Sincroniza(Usuario item); 
		
		public void addListaUsuario_Sincroniza(Usuario item); 
		public Usuario getCorrenteUsuario_Sincroniza(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
